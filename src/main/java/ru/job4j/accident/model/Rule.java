package ru.job4j.accident.model;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor(staticName = "of")
@ToString
@EqualsAndHashCode(of = {"id"})
@Getter
@NoArgsConstructor
@Setter
@Entity
@Table(name = "rules")
public class Rule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
}
