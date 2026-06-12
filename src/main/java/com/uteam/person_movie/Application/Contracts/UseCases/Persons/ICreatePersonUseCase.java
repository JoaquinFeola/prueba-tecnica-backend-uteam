package com.uteam.person_movie.Application.Contracts.UseCases.Persons;

import com.uteam.person_movie.Application.Contracts.DTOs.Persons.Request.CreatePersonRequestDto;
import com.uteam.person_movie.Application.Contracts.DTOs.Persons.Response.CreatePersonResponseDto;
import com.uteam.person_movie.Application.Contracts.Results.Result;

public interface ICreatePersonUseCase {
    Result<CreatePersonResponseDto> execute(CreatePersonRequestDto requestDto);
}
