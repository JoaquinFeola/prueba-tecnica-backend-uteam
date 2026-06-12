package com.uteam.person_movie.Application.UseCases.Persons;

import com.uteam.person_movie.Application.Contracts.DTOs.Persons.Response.PersonResponseDto;
import com.uteam.person_movie.Application.Contracts.Results.Result;
import com.uteam.person_movie.Application.Contracts.UseCases.Persons.IGetPersonByIdUseCase;
import com.uteam.person_movie.Domain.Entities.Person;
import com.uteam.person_movie.Domain.Repositories.IPersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetPersonById implements IGetPersonByIdUseCase {
    private final IPersonRepository repository;

    public Result<PersonResponseDto> execute(int id) {
        Person person = repository.findById(id)
                .orElse(null);

        if (person == null) {
            return Result.failure(HttpStatus.NOT_FOUND, List.of("Persona no encontrada con el id: " + id) );
        }

        PersonResponseDto responseDto = PersonResponseDto.from(person);

        return Result.success(HttpStatus.OK, responseDto);
    }
}
