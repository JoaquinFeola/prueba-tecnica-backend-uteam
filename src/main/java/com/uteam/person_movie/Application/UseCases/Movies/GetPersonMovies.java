package com.uteam.person_movie.Application.UseCases.Movies;

import com.uteam.person_movie.Application.Contracts.DTOs.Movies.Response.MovieResponseDto;
import com.uteam.person_movie.Application.Contracts.Results.Result;
import com.uteam.person_movie.Application.Contracts.UseCases.Movies.IGetPersonMoviesUseCase;
import com.uteam.person_movie.Domain.Entities.Movie;
import com.uteam.person_movie.Domain.Entities.Person;
import com.uteam.person_movie.Domain.Repositories.IPersonRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GetPersonMovies implements IGetPersonMoviesUseCase {

    private final IPersonRepository repository;

    public Result<List<MovieResponseDto>> execute(int personId) {

        Optional<Person> person = repository.findById(personId);
        if(person.isEmpty()) {
            return Result.failure(HttpStatus.NOT_FOUND, List.of("No se encontro la persona con id: " + personId));
        }

        List<Movie> moviesFromPerson = person.get().getFavouriteMovies();
        if(moviesFromPerson.isEmpty()) {
            return Result.failure(HttpStatus.NOT_FOUND,
                    List.of("No se encontraron peliculas para la persona con id: " + personId));
        }

        List<MovieResponseDto> responseDto = MovieResponseDto.from(moviesFromPerson);
        return Result.success(HttpStatus.OK, responseDto);
    }
}
