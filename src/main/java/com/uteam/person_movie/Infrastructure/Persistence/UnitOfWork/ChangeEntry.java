package com.uteam.person_movie.Infrastructure.Persistence.UnitOfWork;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ChangeEntry<T> {
    private final T entity;
    private final OperationType operation;
}