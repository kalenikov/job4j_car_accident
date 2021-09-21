package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.AccidentType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class TypesInMemRepository {
    private final Map<Integer, AccidentType> store = new HashMap<>();

    public TypesInMemRepository() {
        store.put(1, new AccidentType(1, "Car + car"));
        store.put(2, new AccidentType(2, "Car + man"));
        store.put(3, new AccidentType(3, "Car + bicycle"));
    }

    public List<AccidentType> findAll() {
        return new ArrayList<>(store.values());
    }

    public AccidentType get(int id) {
        return store.get(id);
    }
}
