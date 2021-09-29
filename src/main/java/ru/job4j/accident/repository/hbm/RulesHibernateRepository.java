package ru.job4j.accident.repository.hbm;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Rule;

import java.util.List;

import static ru.job4j.accident.repository.hbm.HibernateUtil.tx;

//@Repository
public class RulesHibernateRepository {
    private final SessionFactory sf;

    public RulesHibernateRepository(SessionFactory sf) {
        this.sf = sf;
    }

    public List<Rule> findAll() {
        return tx(sf, session -> session
                .createQuery("from Rule", Rule.class)
                .list());
    }

    public Rule get(int id) {
        return tx(sf, session -> session.get(Rule.class, id));
    }
}
