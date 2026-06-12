package com.uteam.person_movie.Application.Contracts.DTOs.Persons.Request;

import com.uteam.person_movie.Application.Contracts.DTOs.Movies.Requests.CreateMovieRequestDto;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;
import java.util.List;

public record CreatePersonRequestDto(
    @NotBlank(message = "El nombre es obligatorio")
    String firstName,

    @NotBlank(message = "El apellido es obligatorio")
    String lastName,

    @NotNull(message = "La fecha de nacimiento es obligatoria")
    @PastOrPresent(message = "La fecha de nacimiento no puede ser futura")
    LocalDate birthdate,

    @NotNull(message = "Debe indicar si posee seguro")
    Boolean hasInsurance,

    @Nullable
    @Valid
    List<CreateMovieRequestDto> favouriteMovies
) {}
