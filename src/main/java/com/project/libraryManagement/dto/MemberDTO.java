package com.project.libraryManagement.dto;

import com.project.libraryManagement.models.core.Loan;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

@Getter
@Setter
public class MemberDTO {
    private Long id;

    private String name;

    private String phone;

    private String address;

    private String email;

    private Calendar dateJoined;
}
