package com.uteam.person_movie.Application.Contracts.UseCases.Movies;

import com.uteam.person_movie.Application.Contracts.Results.Result;

public interface IRemoveMovieFromPersonUseCase {
    Result<Object> execute(int personId, String title);
}
