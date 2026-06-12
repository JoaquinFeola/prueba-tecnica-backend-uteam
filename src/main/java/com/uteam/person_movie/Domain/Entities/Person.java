package com.uteam.person_movie.Domain.Entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
@NoArgsConstructor
public class Person {
    private int id;
    private String firstName;
    private String lastName;
    private LocalDate birthdate;
    private boolean hasInsurance;

    private List<Movie> favouriteMovies = new ArrayList<>();

    public static Person create(
            String firstName,
            String lastName,
            LocalDate birthdate,
            boolean hasInsurance)
    {

        Person person = new Person();
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setBirthdate(birthdate);
        person.setHasInsurance(hasInsurance);

        return person;
    }

    public int updateIfChanges(
            String firstName,
            String lastName,
            LocalDate birthdate,
            Boolean hasInsurance)
    {
        int updatedFieldsCount = 0;

        if (firstName != null) {
            this.firstName = firstName;
            updatedFieldsCount++;
        }

        if (lastName != null) {
            this.lastName = lastName;
            updatedFieldsCount++;
        }

        if (birthdate != null) {
            this.birthdate = birthdate;
            updatedFieldsCount++;
        }

        if (hasInsurance != null) {
            this.hasInsurance = hasInsurance;
            updatedFieldsCount++;
        }

        return updatedFieldsCount;
    }

    private boolean hasMaxMovies(int maxMovies) {
        return favouriteMovies.size() >= maxMovies;
    }

    public String[] addFavouriteMovie(String title, String genre, int maxMovies ) {
        if ( hasMaxMovies(maxMovies) ) {
            return new String[]{"Has alcanzado el limite de películas que puedes añadir"};
        }

        boolean exists = favouriteMovies.stream()
                .anyMatch(m -> m.getTitle().equalsIgnoreCase(title));

        if (exists) {
            return new String[]{"Ya existe una película con ese nombre"};
        }

        favouriteMovies.add(Movie.create(title, genre));

        return new String[]{};
    }

    public String[] replaceFavouriteMovies(List<Movie> movies, int maxMovies) {
        if ( hasMaxMovies(maxMovies) ) {
            return new String[]{"Has alcanzado el limite de películas que puedes añadir"};
        }

        this.favouriteMovies.clear();
        this.favouriteMovies.addAll(movies);

        return new String[]{};
    }

    public String[] removeMovie(String title) {
        if ( favouriteMovies.isEmpty() ) {
            return new String[]{"No hay películas para eliminar"};
        }

        Optional<Movie> movie = findMovieByTitle(title);
        if (movie.isEmpty()) {
            return new String[]{"No se encontró la película con titulo: " + title};
        }

        favouriteMovies.remove(movie.get());

        return new String[]{};
    }

    public Optional<Movie> findMovieByTitle(String title) {
        return favouriteMovies.stream()
                .filter(m -> m.getTitle().equalsIgnoreCase(title))
                .findFirst();
    }
}