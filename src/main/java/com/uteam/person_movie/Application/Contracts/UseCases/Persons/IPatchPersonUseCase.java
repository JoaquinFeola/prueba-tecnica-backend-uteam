package com.uteam.person_movie.Application.Contracts.UseCases.Persons;

import com.uteam.person_movie.Application.Contracts.DTOs.Persons.Request.PatchPersonRequestDto;
import com.uteam.person_movie.Application.Contracts.DTOs.Persons.Response.PatchPersonResponseDto;
import com.uteam.person_movie.Application.Contracts.Results.Result;

public interface IPatchPersonUseCase {
    Result<PatchPersonResponseDto> execute(int id, PatchPersonRequestDto requestDto);
}
