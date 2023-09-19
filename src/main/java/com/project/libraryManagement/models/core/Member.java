package com.project.libraryManagement.models.core;

import com.project.libraryManagement.models.commons.Person;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Calendar;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Member extends Person {

    @NotNull(message = "Date Joined is requried")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_joined")
    private Calendar dateJoined;
}
