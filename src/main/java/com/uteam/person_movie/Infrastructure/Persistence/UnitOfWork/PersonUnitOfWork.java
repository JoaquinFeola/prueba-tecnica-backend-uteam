package com.uteam.person_movie.Infrastructure.Persistence.UnitOfWork;

import com.uteam.person_movie.Domain.Entities.Person;
import org.springframework.stereotype.Component;

@Component
public class PersonUnitOfWork extends UnitOfWork<Person> { }
