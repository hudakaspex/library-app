package com.project.libraryManagement.models.commons;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Person extends Party {

    @Column
    private String firstName;

    @Column
    private String midName;

    @Column
    private String lastName;

    public void setSplitName(String name) {
        setName(name);
        String arr[] = name.split(" ");
        switch (arr.length) {
            case 1:
                this.firstName = name;
                break;
            case 2:
                this.firstName = arr[0];
                this.midName = arr[1];
                break;
            default:
                this.firstName = arr[0];
                this.midName = arr[1];
                this.lastName = arr[2];
                break;
        }
    }
}
