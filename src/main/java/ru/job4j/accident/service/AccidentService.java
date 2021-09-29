package ru.job4j.accident.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.data.AccidentRepository;

import java.util.List;

@Service
public class AccidentService {
    @Autowired
    private AccidentRepository repo;

    public List<Accident> findAll() {
        return (List<Accident>) repo.findAll();
    }

    public Accident get(int id) {
        return repo.findById(id).get();
    }

    public Accident save(Accident accident) {
        return repo.save(accident);
    }
}
