package ru.job4j.accident.repository.hbm;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentRepository;

import java.util.List;

import static ru.job4j.accident.repository.hbm.HibernateUtil.tx;

@Repository
public class AccidentHibernateRepository implements AccidentRepository {
    private final SessionFactory sf;

    public AccidentHibernateRepository(SessionFactory sf) {
        this.sf = sf;
    }

    @Override
    public Accident save(Accident accident) {
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.saveOrUpdate(accident);
            tx.commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
        return accident;
    }


    @Override
    public Accident get(int id) {
        return tx(sf, session -> session.get(Accident.class, id));
    }

    @Override
    public List<Accident> findAll() {
        return tx(sf, session -> session.createQuery("select distinct a from Accident a " +
                "join fetch a.rules " +
                "join fetch a.type " +
                "order by a.id", Accident.class).list());
    }


}