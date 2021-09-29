package ru.job4j.accident.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.data.RuleRepository;

import java.util.List;

@Service

public class RulesService {
    @Autowired
    private RuleRepository repo;

    public List<Rule> findAll() {
        return (List<Rule>) repo.findAll();
    }

    public Rule get(int id) {
        return repo.findById(id).orElse(null);
    }
}
