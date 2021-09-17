package ru.job4j.accident.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@EqualsAndHashCode(of = {"id"})
@Getter
public class Accident {
    private int id;
    private String name;
    private String text;
    private String address;
}
