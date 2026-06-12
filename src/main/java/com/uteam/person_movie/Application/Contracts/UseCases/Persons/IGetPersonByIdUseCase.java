package com.uteam.person_movie.Application.Contracts.UseCases.Persons;

import com.uteam.person_movie.Application.Contracts.DTOs.Persons.Response.PersonResponseDto;
import com.uteam.person_movie.Application.Contracts.Results.Result;

public interface IGetPersonByIdUseCase {
    Result<PersonResponseDto> execute(int id);
}
