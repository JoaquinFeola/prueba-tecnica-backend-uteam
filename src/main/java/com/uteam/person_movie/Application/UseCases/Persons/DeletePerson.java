package com.uteam.person_movie.Application.UseCases.Persons;

import com.uteam.person_movie.Application.Contracts.Results.Result;
import com.uteam.person_movie.Application.Contracts.UseCases.Persons.IDeletePersonUseCase;
import com.uteam.person_movie.Domain.Repositories.IPersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeletePerson implements IDeletePersonUseCase {
    private final IPersonRepository repository;

    public Result<Object> execute(int id) {
        repository.delete(id);
        repository.save();

        return Result.success(HttpStatus.OK, null)
                .withDescription("Persona con el id: " + id + " " + "fue eliminado correctamente");
    }


}
