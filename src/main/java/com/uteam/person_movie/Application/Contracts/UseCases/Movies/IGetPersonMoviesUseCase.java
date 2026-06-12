package com.uteam.person_movie.Application.Contracts.UseCases.Movies;

import com.uteam.person_movie.Application.Contracts.DTOs.Movies.Response.MovieResponseDto;
import com.uteam.person_movie.Application.Contracts.Results.Result;

import java.util.List;

public interface IGetPersonMoviesUseCase {
    Result<List<MovieResponseDto>> execute(int personId);
}
