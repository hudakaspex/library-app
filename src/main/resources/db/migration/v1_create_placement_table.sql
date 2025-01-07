-- Active: 1733650272899@@127.0.0.1@3306@library_management
CREATE TABLE placement (
    id int NOT NULL AUTO_INCREMENT,
    shelf varchar(200),
    column_placement int,
    row_placement int,
    PRIMARY KEY (id)
) engine=InnoDB
