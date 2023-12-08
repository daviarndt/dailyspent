package com.example.dailyspent.utils.exceptions;

import com.example.dailyspent.utils.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IdIsIllegalException.class)
    public ResponseEntity<ApiResponse> handleIdIsIllegalException(IdIsIllegalException ex) {
        return new ResponseEntity<>(ApiResponse.exception(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiResponse> handleUserNotFoundException(UserNotFoundException ex) {
        return new ResponseEntity<>(ApiResponse.exception(ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ApiResponse> handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
        return new ResponseEntity<>(ApiResponse.exception(ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PhoneNotFoundException.class)
    public ResponseEntity<ApiResponse> handlePhoneNotFoundException(PhoneNotFoundException ex) {
        return new ResponseEntity<>(ApiResponse.exception(ex.getMessage()), HttpStatus.NOT_FOUND);
    }
}
