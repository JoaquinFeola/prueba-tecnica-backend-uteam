package com.uteam.person_movie.Application.Contracts.DTOs.Persons.Request;

import com.uteam.person_movie.Application.Contracts.DTOs.Movies.Requests.CreateMovieRequestDto;
import jakarta.validation.Valid;

import java.time.LocalDate;
import java.util.List;

public record PatchPersonRequestDto(
        String firstName,

        String lastName,

        LocalDate birthdate,

        Boolean hasInsurance,

        @Valid
        List<CreateMovieRequestDto> favouriteMovies
) { }
