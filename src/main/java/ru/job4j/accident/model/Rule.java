package ru.job4j.accident.model;

import lombok.*;

@AllArgsConstructor(staticName = "of")
@ToString
@EqualsAndHashCode(of = {"id"})
@Getter
@NoArgsConstructor
@Setter
public class Rule {
    private int id;
    private String name;
}
