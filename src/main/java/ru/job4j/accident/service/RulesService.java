package ru.job4j.accident.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.RulesInMemRepository;

import java.util.Collection;

@Service

public class RulesService {
    @Autowired
    private RulesInMemRepository repo;

    public Collection<Rule> findAll() {
        return repo.findAll();
    }

    public Rule get(int id) {
        return repo.get(id);
    }
}
