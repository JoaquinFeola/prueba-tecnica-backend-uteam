package com.uteam.person_movie.Application.UseCases.Persons;

import com.uteam.person_movie.Application.Contracts.DTOs.Persons.Request.CreatePersonRequestDto;
import com.uteam.person_movie.Application.Contracts.DTOs.Persons.Response.CreatePersonResponseDto;
import com.uteam.person_movie.Application.Contracts.DTOs.Persons.Response.PersonResponseDto;
import com.uteam.person_movie.Application.Contracts.Results.Result;
import com.uteam.person_movie.Application.Contracts.UseCases.Persons.ICreatePersonUseCase;
import com.uteam.person_movie.Application.Contracts.UseCases.Persons.IGetAllPersonUseCase;
import com.uteam.person_movie.Domain.Entities.Person;
import com.uteam.person_movie.Domain.Repositories.IPersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllPerson implements IGetAllPersonUseCase {
    private final IPersonRepository repository;

    public Result<List<PersonResponseDto>> execute() {
        List<Person> persons = repository.findAll();

        List<PersonResponseDto> responseDto = PersonResponseDto.from(persons)
                .stream()
                .sorted(
                        Comparator.comparing(PersonResponseDto::firstName)
                                .thenComparing(PersonResponseDto::lastName)
                )
                .toList();

        return Result.success(HttpStatus.OK, responseDto);
    }
}
