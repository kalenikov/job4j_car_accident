package ru.job4j.accident.model;

import lombok.*;

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

    public Accident(String name, String text, String address, AccidentType type) {
        this.name = name;
        this.text = text;
        this.address = address;
        this.type = type;
    }

    public boolean isNew() {
        return id == 0;
    }
}
