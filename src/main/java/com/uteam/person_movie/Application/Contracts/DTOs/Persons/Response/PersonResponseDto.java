package com.uteam.person_movie.Application.Contracts.DTOs.Persons.Response;

import com.uteam.person_movie.Application.Contracts.DTOs.Movies.Response.MovieResponseDto;
import com.uteam.person_movie.Domain.Entities.Person;

import java.time.LocalDate;
import java.util.List;

public record PersonResponseDto(
        int id,
        String firstName,
        String lastName,
        String fullName,
        LocalDate birthdate,
        boolean hasInsurance,
        List<MovieResponseDto> favouriteMovies
) {
    public static PersonResponseDto from(Person person) {
        String fullName = String.join(
                " ",
                person.getFirstName(),
                person.getLastName()
        );

        return new PersonResponseDto(
                person.getId(),
                person.getFirstName(),
                person.getLastName(),
                fullName,
                person.getBirthdate(),
                person.isHasInsurance(),
                MovieResponseDto.from(person.getFavouriteMovies())
        );
    }

    public static List<PersonResponseDto> from(List<Person> persons) {
        return persons
                .stream()
                .map(PersonResponseDto::from)
                .toList();
    }
}
