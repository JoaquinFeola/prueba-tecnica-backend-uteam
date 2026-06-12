package com.uteam.person_movie.Infrastructure.Persistence.Repositories;

import com.uteam.person_movie.Domain.Entities.Person;
import com.uteam.person_movie.Domain.Repositories.IPersonRepository;
import com.uteam.person_movie.Infrastructure.Persistence.UnitOfWork.PersonUnitOfWork;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
@AllArgsConstructor
public class InMemoryIPersonRepository implements IPersonRepository {
    private final PersonUnitOfWork _unitOfWork;
    private final AtomicInteger idSequence = new AtomicInteger(0);

    @Override
    public List<Person> findAll() {
        return new ArrayList<>(_unitOfWork.getAll());
    }

    @Override
    public Optional<Person> findById(int id) {
        return _unitOfWork.findOne(id);
    }

    @Override
    public List<Person> findByName(String firstName, String lastName) {
        return _unitOfWork.getAll()
                .stream()
                .filter(person -> {
                    boolean matchesFirst = firstName == null ||
                            person.getFirstName().equalsIgnoreCase(firstName);
                    boolean matchesLast = lastName == null ||
                            person.getLastName().equalsIgnoreCase(lastName);
                    return matchesFirst && matchesLast;
                })
                .toList();
    }

    @Override
    public Person add(Person person) {
        int id = idSequence.incrementAndGet();
        _unitOfWork.registerInsert(id, person);
        person.setId(id);

        return person;
    }

    @Override
    public Person update(Person person) {
        _unitOfWork.registerUpdate(person.getId(), person);

        return person;
    }

    @Override
    public void delete(int id) {
        _unitOfWork.registerDelete(id);
    }

    @Override
    public void save() {
        _unitOfWork.save();
    }
}
