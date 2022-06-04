package com.firstproject.exception;
// Thông báo message khi bị lỗi

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ErrorMessage {

    private HttpStatus httpStatus;
    private String message;
}
