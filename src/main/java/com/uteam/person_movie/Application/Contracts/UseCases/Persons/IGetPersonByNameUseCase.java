package com.uteam.person_movie.Application.Contracts.UseCases.Persons;

import com.uteam.person_movie.Application.Contracts.DTOs.Persons.Response.PersonResponseDto;
import com.uteam.person_movie.Application.Contracts.Results.Result;

import java.util.List;

public interface IGetPersonByNameUseCase {
    Result<List<PersonResponseDto>> execute(String firstName, String lastName);
}
