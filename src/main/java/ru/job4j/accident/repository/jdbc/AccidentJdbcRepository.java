package ru.job4j.accident.repository.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentRepository;

import java.sql.PreparedStatement;
import java.util.*;

//@Repository
public class AccidentJdbcRepository implements AccidentRepository {
    private final JdbcTemplate jdbc;

    public AccidentJdbcRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public Accident saveNew(Accident accident) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement("insert into accident (name, text, address, type_id) values (?,?,?,?)",
                            new String[] { "id" });
            ps.setString(1, accident.getName());
            ps.setString(2, accident.getText());
            ps.setString(3, accident.getAddress());
            ps.setInt(4, accident.getType().getId());
            return ps;
        }, keyHolder);
        for (Rule rule : accident.getRules()) {
            jdbc.update("insert into accident_rules (acc_id, rule_id) values (?,?)",
                    keyHolder.getKey(),
                    rule.getId()
            );
        }
        return accident;
    }

    @Override
    public Accident save(Accident accident) {
        if (accident.isNew()) {
            return saveNew(accident);
        } else {
            return update(accident);
        }
    }

    @Transactional
    public Accident update(Accident accident) {
        jdbc.update("update accident set name = ?, text = ?, address = ? , type_id =? where id = ?",
                accident.getName(),
                accident.getText(),
                accident.getAddress(),
                accident.getType().getId(),
                accident.getId()
        );
        for (Rule rule : accident.getRules()) {
            jdbc.update("delete from accident_rules where acc_id=?", accident.getId());
            jdbc.update("insert into accident_rules (acc_id, rule_id) values (?,?)",
                    accident.getId(),
                    rule.getId()
            );
        }
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
        Accident rsl = new Accident();
        jdbc.query("select a.*, type.name type_name, rules.rule_id, rule.name rule_name from accident a " +
                        "inner join acc_types type on a.type_id = type.id " +
                        "inner join accident_rules rules on a.id = rules.acc_id " +
                        "inner join rules rule on rules.rule_id = rule.id",
                (rs, row) -> {
                    //loop over accident + rule
                    AccidentType type = AccidentType.of(rs.getInt("type_id"), rs.getString("type_name"));
                    Rule rule = Rule.of(rs.getInt("rule_id"), rs.getString("rule_name"));
                    if (rsl.isNew()) {
                        rsl.setId(id);
                        rsl.setName(rs.getString("name"));
                        rsl.setText(rs.getString("text"));
                        rsl.setAddress(rs.getString("address"));
                        rsl.setType(type);
                    }
                    rsl.getRules().add(rule);
                    return rsl;
                });
        return rsl;
    }

}