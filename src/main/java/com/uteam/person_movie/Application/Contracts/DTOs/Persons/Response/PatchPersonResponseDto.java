package com.uteam.person_movie.Application.Contracts.DTOs.Persons.Response;

import com.uteam.person_movie.Domain.Entities.Movie;
import com.uteam.person_movie.Domain.Entities.Person;

import java.time.LocalDate;
import java.util.List;

public record PatchPersonResponseDto(
        int id,
        String firstName,
        String lastName,
        LocalDate birthDate,
        boolean hasInsurance,
        List<Movie> favouriteMovies,
        int updatedFields
) {
    public static PatchPersonResponseDto from(Person person, int updatedFields) {
        return new PatchPersonResponseDto(
                person.getId(),
                person.getFirstName(),
                person.getLastName(),
                person.getBirthdate(),
                person.isHasInsurance(),
                person.getFavouriteMovies(),
                updatedFields
        );
    }
}
