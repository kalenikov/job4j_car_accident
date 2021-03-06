package ru.job4j.accident.repository;

import ru.job4j.accident.model.Accident;

import java.util.List;

public interface AccidentRepository {
    List<Accident> findAll();
    Accident save(Accident accident);
    Accident get(int id);
}
