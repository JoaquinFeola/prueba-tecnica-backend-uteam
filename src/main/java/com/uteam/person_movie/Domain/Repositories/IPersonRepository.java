package com.uteam.person_movie.Domain.Repositories;

import com.uteam.person_movie.Domain.Entities.Person;

import java.util.List;
import java.util.Optional;

public interface IPersonRepository {
    List<Person> findAll();
    Optional<Person> findById(int id);
    List<Person> findByName(String firstName, String lastName);
    Person add(Person person);
    Person update(Person person);
    void delete(int id);
    void save();
}
