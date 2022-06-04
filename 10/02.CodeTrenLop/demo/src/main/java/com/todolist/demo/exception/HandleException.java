package com.todolist.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice // Khi có lỗi trả về json cho client
public class HandleException {

    // Xử lý exception NotFound
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage handleNotFoundException(NotFoundException e) {
        return new ErrorMessage(HttpStatus.NOT_FOUND, e.getMessage());
    }

    // Xử lý exception còn lại
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage handleOthersException(Exception e) {
        return new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }
}
