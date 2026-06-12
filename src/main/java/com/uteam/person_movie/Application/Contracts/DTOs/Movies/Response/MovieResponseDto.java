package com.uteam.person_movie.Application.Contracts.DTOs.Movies.Response;

import com.uteam.person_movie.Domain.Entities.Movie;

import java.util.List;

public record MovieResponseDto(
        String title,
        String genre
) {
    public static MovieResponseDto from(Movie movie) {
        return new MovieResponseDto(
                movie.getTitle(),
                movie.getGenre()
        );
    }
    public static List<MovieResponseDto> from(List<Movie> movies) {
        return movies.stream()
                .map(MovieResponseDto::from)
                .toList();
    }
}