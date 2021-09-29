package ru.job4j.accident.repository.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.AccidentType;

@Repository
public interface TypesRepository extends CrudRepository<AccidentType, Integer>{
}
