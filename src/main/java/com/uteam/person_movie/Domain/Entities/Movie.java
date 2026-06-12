package com.uteam.person_movie.Domain.Entities;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Movie {
    private String title;
    private String genre;

    public static Movie create(String title, String genre) {
        Movie movie = new Movie();
        movie.setTitle(title);
        movie.setGenre(genre);

        return movie;
    }
}
