package com.project.libraryManagement.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ExceptionResponse {
    private Integer status;
    private HttpStatus error;
    private Object message;
    private Long timestamp;
}
