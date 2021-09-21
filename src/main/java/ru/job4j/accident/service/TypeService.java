package ru.job4j.accident.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.repository.TypesInMemRepository;

import java.util.List;

@Service

public class TypeService {
    @Autowired
    private TypesInMemRepository repo;

    public List<AccidentType> findAll() {
        return repo.findAll();
    }

    public AccidentType get(int id) {
        return repo.get(id);
    }
}
