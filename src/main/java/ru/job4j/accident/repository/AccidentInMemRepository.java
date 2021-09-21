package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentInMemRepository implements AccidentRepository {
    private final Map<Integer, Accident> store = new HashMap<>();
    private final AtomicInteger counter = new AtomicInteger(0);

    public AccidentInMemRepository() {
        save(new Accident("name1", "text1", "address1"));
        save(new Accident("name2", "text2", "address2"));
    }

    @Override
    public List<Accident> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Accident save(Accident accident) {
        if (accident.isNew()) {
            accident.setId(counter.incrementAndGet());
            store.put(accident.getId(), accident);
            return accident;
        }
        return store.computeIfPresent(accident.getId(), (k, v) -> accident);
    }

    @Override
    public Accident get(int id) {
        return store.get(id);
    }
}
