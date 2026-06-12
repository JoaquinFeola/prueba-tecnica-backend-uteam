package com.uteam.person_movie.Application.Contracts.DTOs.Movies.Requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateMovieRequestDto(
            @NotNull(message = "El título no puede ser nulo")
            @NotBlank(message = "El título de la película no puede estar vacío")
            String title,

            @NotNull(message = "El género no puede ser nulo")
            @NotBlank(message = "El género no puede estar vacío")
            String genre
){}
