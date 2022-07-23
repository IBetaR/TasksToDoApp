package com.ibetar.taskstodo.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class TaskToDoExceptions extends RuntimeException{

    private String message;
    private HttpStatus httpStatus;

    public TaskToDoExceptions(String message, HttpStatus httpStatus) {
        super(message);
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
