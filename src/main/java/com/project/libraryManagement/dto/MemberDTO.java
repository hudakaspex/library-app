package com.project.libraryManagement.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MemberDTO {
    private Long id;

    private String name;

    private String phone;

    private String address;

    private String email;

    private LocalDateTime dateJoined;
}
