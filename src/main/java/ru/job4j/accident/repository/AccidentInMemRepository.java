package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AccidentInMemRepository implements AccidentRepository {
    private final Map<Integer, Accident> store = new HashMap<>();

    public AccidentInMemRepository() {
        store.put(1, new Accident(1, "name1", "text1", "address1"));
        store.put(2, new Accident(1, "name2", "text2", "address2"));
    }

    @Override
    public List<Accident> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void add(Accident accident) {
        store.put(accident.getId(), accident);
    }
}
