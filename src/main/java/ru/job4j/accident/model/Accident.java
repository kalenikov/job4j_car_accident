package ru.job4j.accident.model;

import lombok.*;

import java.util.Set;

@AllArgsConstructor
@ToString
@EqualsAndHashCode(of = {"id"})
@Getter
@NoArgsConstructor
@Setter
public class Accident {
    private int id;
    private String name;
    private String text;
    private String address;
    private AccidentType type;
    private Set<Rule> rules;

    public Accident(String name, String text, String address, AccidentType type, Set<Rule> rules) {
        this.name = name;
        this.text = text;
        this.address = address;
        this.type = type;
        this.rules = rules;
    }

    public boolean isNew() {
        return id == 0;
    }
}
