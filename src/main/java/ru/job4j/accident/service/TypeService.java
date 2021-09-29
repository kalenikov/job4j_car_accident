package ru.job4j.accident.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.repository.data.TypesRepository;

import java.util.List;

@Service

public class TypeService {
    @Autowired
    private TypesRepository repo;

    public List<AccidentType> findAll() {
        return (List<AccidentType>) repo.findAll();
    }

    public AccidentType get(int id) {
        return repo.findById(id).orElse(null);
    }
}
