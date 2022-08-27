package com.project.exception;

import com.project.controller.ProjectDetailsController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException e) {

        Map<String, String> error = new HashMap<>();
        e.getBindingResult().getFieldErrors()
                .forEach(fieldError ->
                        error.put(fieldError.getField(), fieldError.getDefaultMessage()));
        logger.error(error.toString());
        return error;
    }

    @ExceptionHandler(NumberFormatException.class)
    public Map<String, String> numberFormatException(NumberFormatException e) {
        Map<String, String> error = new HashMap<>();
        error.put("Error", "Enter valid url "+e.getMessage());
        logger.error(error.toString());
        return error;
    }
}