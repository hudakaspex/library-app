package com.project.libraryManagement.models.core;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.project.libraryManagement.models.commons.Person;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Member extends Person {

    @NotNull(message = "Date Joined is requried")
    @Column(name = "date_joined")
    private long dateJoined;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<Loan> loans;
}
