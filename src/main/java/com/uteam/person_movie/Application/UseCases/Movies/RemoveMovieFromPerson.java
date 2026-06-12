package com.uteam.person_movie.Application.UseCases.Movies;

import com.uteam.person_movie.Application.Contracts.Results.Result;
import com.uteam.person_movie.Application.Contracts.UseCases.Movies.IRemoveMovieFromPersonUseCase;
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
public class RemoveMovieFromPerson implements IRemoveMovieFromPersonUseCase {

    private final IPersonRepository repository;

    public Result<Object> execute(int personId, String title) {

        Optional<Person> person = repository.findById(personId);
        if(person.isEmpty()) {
            return Result.failure(HttpStatus.NOT_FOUND, List.of("No se encontro la persona con id: " + personId));
        }

        String[] errors = person.get().removeMovie(title);
        if(errors.length > 0) {
            return Result.failure(HttpStatus.BAD_REQUEST, List.of(errors));
        }

        repository.update(person.get());
        repository.save();

        return Result.success(HttpStatus.OK, null).withDescription("La pelicula se elimino correctamente.");
    }
}
