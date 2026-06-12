package com.uteam.person_movie.Application.UseCases.Movies;

import com.uteam.person_movie.Application.Contracts.DTOs.Movies.Requests.CreateMovieRequestDto;
import com.uteam.person_movie.Application.Contracts.DTOs.Persons.Request.CreatePersonRequestDto;
import com.uteam.person_movie.Application.Contracts.DTOs.Movies.Response.MovieResponseDto;
import com.uteam.person_movie.Application.Contracts.Results.Result;
import com.uteam.person_movie.Application.Contracts.UseCases.Movies.IAddMovieToPersonUseCase;
import com.uteam.person_movie.Domain.Entities.Movie;
import com.uteam.person_movie.Domain.Entities.Person;
import com.uteam.person_movie.Domain.Repositories.IPersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddMovieToPerson implements IAddMovieToPersonUseCase {

    private final IPersonRepository repository;

    @Value("${app.person.max-movies}")
    private int maxMoviesPerPerson;

    public Result<MovieResponseDto> execute(int personId, CreateMovieRequestDto requestDto) {

        Optional<Person> person = repository.findById(personId);
        if(person.isEmpty()) {
            return Result.failure(HttpStatus.NOT_FOUND, List.of("No se encontro la persona con id: " + personId));
        }

        String[] errors = person.get().addFavouriteMovie(requestDto.title(), requestDto.genre(), maxMoviesPerPerson);
        if(errors.length > 0) {
            return Result.failure(HttpStatus.BAD_REQUEST, List.of(errors));
        }

        repository.update(person.get());
        repository.save();

        Optional<Movie> movie = person.get().findMovieByTitle(requestDto.title());
        if(movie.isEmpty()) {
            return Result.failure(HttpStatus.NOT_FOUND, List.of("No se encontro la pelicula con nombre: " + requestDto.title()));
        }

        MovieResponseDto responseDto = MovieResponseDto.from(movie.get());

        return Result.success(HttpStatus.OK, responseDto);
    }
}
