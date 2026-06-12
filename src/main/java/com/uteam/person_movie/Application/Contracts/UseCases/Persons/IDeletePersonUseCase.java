package com.uteam.person_movie.Application.Contracts.UseCases.Persons;

import com.uteam.person_movie.Application.Contracts.Results.Result;

public interface IDeletePersonUseCase {
    Result<Object> execute(int id);
}
