package com.uteam.person_movie.Application.Contracts.UseCases.Movies;

import com.uteam.person_movie.Application.Contracts.DTOs.Movies.Requests.CreateMovieRequestDto;
import com.uteam.person_movie.Application.Contracts.DTOs.Movies.Response.MovieResponseDto;
import com.uteam.person_movie.Application.Contracts.Results.Result;

public interface IAddMovieToPersonUseCase {
    Result<MovieResponseDto> execute(int personId, CreateMovieRequestDto requestDto);
}
