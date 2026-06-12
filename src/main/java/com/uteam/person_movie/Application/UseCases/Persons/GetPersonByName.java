package com.uteam.person_movie.Application.UseCases.Persons;

import com.uteam.person_movie.Application.Contracts.DTOs.Persons.Response.PersonResponseDto;
import com.uteam.person_movie.Application.Contracts.Results.Result;
import com.uteam.person_movie.Application.Contracts.UseCases.Persons.IGetPersonByNameUseCase;
import com.uteam.person_movie.Domain.Entities.Person;
import com.uteam.person_movie.Domain.Repositories.IPersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetPersonByName implements IGetPersonByNameUseCase {
    private final IPersonRepository repository;

    public Result<List<PersonResponseDto>> execute(String firstName, String lastName) {
        List<Person> persons = repository.findByName(firstName, lastName);

        if (persons.isEmpty()) {
            return Result.failure(HttpStatus.NOT_FOUND, List.of("No se encontraron personas con ese nombre") );
        }

        List<PersonResponseDto> responseDto = PersonResponseDto.from(persons);

        return Result.success(HttpStatus.OK, responseDto);
    }
}
