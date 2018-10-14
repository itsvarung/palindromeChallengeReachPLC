package com.palindromeChallenge.palindromeChallenge;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class PalindromeNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(PalindromeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String employeeNotFoundHandler(PalindromeNotFoundException ex) {
        return ex.getMessage();
    }
}
