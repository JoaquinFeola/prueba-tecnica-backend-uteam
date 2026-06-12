package com.uteam.person_movie.Application.UseCases.Persons;

import com.uteam.person_movie.Application.Contracts.DTOs.Movies.Requests.CreateMovieRequestDto;
import com.uteam.person_movie.Application.Contracts.DTOs.Persons.Request.CreatePersonRequestDto;
import com.uteam.person_movie.Application.Contracts.DTOs.Persons.Response.CreatePersonResponseDto;
import com.uteam.person_movie.Application.Contracts.Results.Result;
import com.uteam.person_movie.Application.Contracts.UseCases.Persons.ICreatePersonUseCase;
import com.uteam.person_movie.Domain.Entities.Person;
import com.uteam.person_movie.Domain.Repositories.IPersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CreatePerson implements ICreatePersonUseCase {
    private final IPersonRepository repository;

    @Value("${app.person.max-movies}")
    private int maxMoviesPerPerson;

    public Result<CreatePersonResponseDto> execute(CreatePersonRequestDto requestDto) {
        Person person = Person.create(
                requestDto.firstName(),
                requestDto.lastName(),
                requestDto.birthdate(),
                requestDto.hasInsurance()
        );

        if (requestDto.favouriteMovies() != null) {
            Set<String> titles = new HashSet<>();
            for (CreateMovieRequestDto movie : requestDto.favouriteMovies()) {
                if (!titles.add(movie.title().toLowerCase())) {
                    return Result.failure(
                            HttpStatus.BAD_REQUEST,
                            List.of("Película duplicada: " + movie.title())
                    );
                }

                String[] errors = person.addFavouriteMovie(
                        movie.title(),
                        movie.genre(),
                        maxMoviesPerPerson
                );

                if (errors.length > 0) {
                    return Result.failure(
                            HttpStatus.BAD_REQUEST,
                            List.of(errors)
                    );
                }
            }
        }

        Person newPerson = repository.add(person);
        repository.save();

        CreatePersonResponseDto responseDto = CreatePersonResponseDto.from(newPerson);
        return Result.success(HttpStatus.CREATED, responseDto)
                .withDescription("Persona creada correctamente");
    }
}
