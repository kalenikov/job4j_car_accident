package ru.job4j.accident.repository.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentRepository;

import java.util.*;

@Repository
public class AccidentJdbcRepository implements AccidentRepository {
    private final JdbcTemplate jdbc;

    public AccidentJdbcRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Accident save(Accident accident) {
        jdbc.update("insert into accident (name) values (?)",
                accident.getName());
        return accident;
    }


    @Override
    public List<Accident> findAll() {
        Map<Integer, Accident> rsl = new HashMap<>();
        jdbc.query("select a.*, type.name type_name, rules.rule_id, rule.name rule_name from accident a " +
                        "inner join acc_types type on a.type_id = type.id " +
                        "inner join accident_rules rules on a.id = rules.acc_id " +
                        "inner join rules rule on rules.rule_id = rule.id",
                (rs, row) -> {
                    //loop over accident + rule
                    int id = rs.getInt("id");
                    AccidentType type = AccidentType.of(rs.getInt("type_id"), rs.getString("type_name"));
                    Rule rule = Rule.of(rs.getInt("rule_id"), rs.getString("rule_name"));
                    Accident accident = rsl.get(id);
                    if (accident == null) {
                        accident = Accident.of(id,
                                rs.getString("name"),
                                rs.getString("text"),
                                rs.getString("address"),
                                type,
                                new HashSet<>(Set.of(rule)));
                        rsl.put(id, accident);
                    } else {
                        accident.getRules().add(rule);
                    }
                    return accident;
                });
        return new ArrayList<>(rsl.values());
    }

    @Override
    public Accident get(int id) {
        return jdbc.queryForObject("select id, name from accident where id= ?",
                Accident.class, id);
    }

}