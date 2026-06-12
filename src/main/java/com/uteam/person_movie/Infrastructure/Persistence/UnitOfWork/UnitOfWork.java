package com.uteam.person_movie.Infrastructure.Persistence.UnitOfWork;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.*;

@Getter
@RequiredArgsConstructor
public class UnitOfWork<T> {
    private final Map<Integer, T> store = new HashMap<>();
    private final Map<Integer, ChangeEntry<T>> tracker = new HashMap<>();

    public void registerInsert(int id, T  entity) {
        ChangeEntry<T> changeEntry = new ChangeEntry<>(entity, OperationType.INSERT);
        tracker.put(id, changeEntry);
    }

    public void registerUpdate(int id, T  entity) {
        ChangeEntry<T> changeEntry = new ChangeEntry<>(entity, OperationType.UPDATE);
        tracker.put(id, changeEntry);
    }

    public void registerDelete(int id) {
        T fakeEntity = null;
        ChangeEntry<T> changeEntry = new ChangeEntry<>(fakeEntity, OperationType.DELETE);

        tracker.put(id, changeEntry);
    }

    public List<T> getAll() {
        return new ArrayList<>(store.values());
    }

    public Optional<T> findOne(int id) {
        return Optional.ofNullable(store.get(id));
    }

    public void save() {
        for (Map.Entry<Integer, ChangeEntry<T>> entry : tracker.entrySet()) {
            int id = entry.getKey();
            ChangeEntry<T> changeEntry = entry.getValue();

            switch (changeEntry.getOperation()) {
                case INSERT, UPDATE -> store.put(id, changeEntry.getEntity());
                case DELETE -> store.remove(id);
            }
        }
        tracker.clear();
    }
}
