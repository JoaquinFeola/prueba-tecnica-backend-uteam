package com.uteam.person_movie.Application.Contracts.DTOs.Persons.Response;

import com.uteam.person_movie.Domain.Entities.Movie;
import com.uteam.person_movie.Domain.Entities.Person;

import java.time.LocalDate;
import java.util.List;

public final record CreatePersonResponseDto(
        int id,
        String firstName,
        String lastName,
        LocalDate birthDate,
        boolean hasInsurance,
        List<Movie> favouriteMovies
) {
    public static CreatePersonResponseDto from(Person person) {
        return new CreatePersonResponseDto(
                person.getId(),
                person.getFirstName(),
                person.getLastName(),
                person.getBirthdate(),
                person.isHasInsurance(),
                person.getFavouriteMovies()
        );
    }
}
