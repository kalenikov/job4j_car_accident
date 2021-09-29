package ru.job4j.accident.repository.hbm;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.AccidentType;

import java.util.List;

import static ru.job4j.accident.repository.hbm.HibernateUtil.tx;

//@Repository
public class TypesHibernateRepository {
    private final SessionFactory sf;

    public TypesHibernateRepository(SessionFactory sf) {
        this.sf = sf;
    }

    public List<AccidentType> findAll() {
        return tx(sf, session -> session
                .createQuery("from AccidentType", AccidentType.class)
                .list());
    }

    public AccidentType get(int id) {
        return tx(sf, session -> session.get(AccidentType.class, id));
    }
}
