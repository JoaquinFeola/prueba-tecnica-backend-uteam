package com.uteam.person_movie.Application.UseCases.Persons;

import com.uteam.person_movie.Application.Contracts.DTOs.Persons.Request.PatchPersonRequestDto;
import com.uteam.person_movie.Application.Contracts.DTOs.Persons.Response.PatchPersonResponseDto;
import com.uteam.person_movie.Application.Contracts.Results.Result;
import com.uteam.person_movie.Application.Contracts.UseCases.Persons.IPatchPersonUseCase;
import com.uteam.person_movie.Domain.Entities.Movie;
import com.uteam.person_movie.Domain.Entities.Person;
import com.uteam.person_movie.Domain.Repositories.IPersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatchPerson implements IPatchPersonUseCase {
    private final IPersonRepository repository;

    @Value("${app.person.max-movies}")
    private int maxMoviesPerPerson;

    public Result<PatchPersonResponseDto> execute(int id, PatchPersonRequestDto requestDto) {
        Person person = repository.findById(id)
                .orElse(null);

        if (person == null)
            return Result.failure(HttpStatus.NOT_FOUND, List.of("No se encontró la persona con el id: " + id));

        int fieldsUpdatedCount = person.updateIfChanges(
                requestDto.firstName(),
                requestDto.lastName(),
                requestDto.birthdate(),
                requestDto.hasInsurance()
        );

        if (requestDto.favouriteMovies() != null) {
            List<Movie> movies = requestDto.favouriteMovies()
                    .stream()
                    .map(movieDto -> Movie.create(
                            movieDto.title(),
                            movieDto.genre()
                    ))
                    .toList();

            String[] errors = person.replaceFavouriteMovies(movies, maxMoviesPerPerson);
            if (errors.length > 0) {
                return Result.failure(
                        HttpStatus.BAD_REQUEST,
                        List.of(errors)
                );
            }
            fieldsUpdatedCount++;
        }

        if (fieldsUpdatedCount == 0)
            return Result.failure(HttpStatus.BAD_REQUEST, List.of("No se enviaron campos para actualizar"));

        Person patchedPerson = repository.update(person);
        repository.save();

        PatchPersonResponseDto responseDto = PatchPersonResponseDto.from(patchedPerson, fieldsUpdatedCount);
        return Result.success(HttpStatus.OK, responseDto)
                .withDescription("Persona actualizada correctamente");
    }
}
