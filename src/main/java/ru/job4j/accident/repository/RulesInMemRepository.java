package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class RulesInMemRepository {
    private final Map<Integer, Rule> store = new HashMap<>();

    public RulesInMemRepository() {
        store.put(1, Rule.of(1, "rule1"));
        store.put(2, Rule.of(2, "rule2"));
        store.put(3, Rule.of(3, "rule3"));
    }

    public List<Rule> findAll() {
        return new ArrayList<>(store.values());
    }

    public Rule get(int id) {
        return store.get(id);
    }
}
