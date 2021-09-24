CREATE TABLE acc_types
(
    id   serial primary key,
    name varchar(100)
);


CREATE TABLE rules
(
    id   serial primary key,
    name varchar(100)
);


CREATE TABLE accident
(
    id      serial primary key,
    name    varchar(100),
    text    varchar(2000),
    address varchar(100),
    type_id int references acc_types
);

CREATE TABLE accident_rules
(
    acc_id  int references accident,
    rule_id int references acc_types
);

insert into acc_types(name)
values ('type1'),
       ('type2');

insert into rules(name)
values ('rule1'),
       ('rule2');

insert into accident(name, text, address, type_id)
VALUES ('acc1', 'text1', 'address1', 1);
insert into accident_rules(acc_id, rule_id)
VALUES (1, 1);

insert into accident(name, text, address, type_id)
VALUES ('acc2', 'text2', 'address2', 1);
insert into accident_rules(acc_id, rule_id)
VALUES (2, 1),
       (2, 2)