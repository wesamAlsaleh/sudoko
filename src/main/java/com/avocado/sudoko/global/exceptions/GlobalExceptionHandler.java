package com.avocado.sudoko.global.exceptions;

import com.avocado.sudoko.global.dtos.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
// OR @ControllerAdvice, which is an annotation to handle exceptions globally across all controllers
public class GlobalExceptionHandler {
    // method to handle Jakarta validation violations
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationViolation(
            MethodArgumentNotValidException exception
    ) {
        // initialize errors hash map
        HashMap<String, String> errors = new HashMap<>();

        // iterate through all fields errors and add them to the errors map
        exception
                .getBindingResult()
                .getFieldErrors()
                .forEach(error -> {
                    // result format: { "board": "Must be 81 characters", "uuid": "Cannot be null" }
                    errors.put(error.getField(), error.getDefaultMessage());
                });

        // return 400 bad request with the error details
        return ResponseEntity.badRequest().body(errors);
    }

    // method to handle file not found exception
    @ExceptionHandler(SudokuFileNotFoundException.class)
    public ResponseEntity<ErrorDto> handleSudokuFileNotFound(SudokuFileNotFoundException exception) {
        // return 404 not found with a message
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorDto(exception.getMessage()));
    }
}
