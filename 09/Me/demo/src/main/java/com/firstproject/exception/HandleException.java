package com.firstproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j // Log lỗi
public class HandleException {

    // Xử lý cho trường hợp BadRequest
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleBadRequestException(BadRequestException e) {
        // Log lỗi ở đây
        log.error("Please fix bugs");
        // Log lỗi ra file
        return new ErrorMessage(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    // Xử lý cho trường hợp NotFound
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage handleNotFoundException(NotFoundException e) {
        // Log lỗi ở đây
        log.error("Please fix bugs");
        // Log lỗi ra file
        return new ErrorMessage(HttpStatus.NOT_FOUND, e.getMessage());
    }

    // Xử lý cho các trường hợp còn lại
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage handleOthersException(Exception e) {
        // Log lỗi ở đây
        log.error("Please fix bugs");
        // Log lỗi ra file
        return new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }
}
