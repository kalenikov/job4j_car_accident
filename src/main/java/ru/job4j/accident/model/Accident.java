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

    public Accident(String name, String text, String address) {
        this.name = name;
        this.text = text;
        this.address = address;
    }

    public boolean isNew() {
        return id == 0;
    }
}
