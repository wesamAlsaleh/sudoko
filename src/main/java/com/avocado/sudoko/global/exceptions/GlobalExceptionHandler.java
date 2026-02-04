package com.avocado.sudoko.global.exceptions;

import com.avocado.sudoko.global.dtos.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
// OR @ControllerAdvice, which is an annotation to handle exceptions globally across all controllers
public class GlobalExceptionHandler {
    // method to handle Jakarta validation violations
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDto> handleValidationViolation(
            MethodArgumentNotValidException exception
    ) {
        // initialize errors hash map
        List<String> errors = new ArrayList<>();

        // iterate through all fields errors and add them to the errors map
        exception
                .getBindingResult()
                .getFieldErrors()
                .forEach(error -> {
                    // result format: { "board": "Must be 81 characters", "uuid": "Cannot be null" }
                    errors.add(error.getDefaultMessage());
                });

        // return 400 bad request with the error details
        return ResponseEntity
                .badRequest()
                .body(new ErrorDto(errors.get(0))); // print the first error
    }

    // method to handle write operations
    @ExceptionHandler(FileWriteException.class)
    public ResponseEntity<ErrorDto> handleFileWriteException(FileWriteException exception) {
        // return 500 internal server error with a message
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorDto(exception.getMessage()));
    }

    // method to handle Sudoku file not created exception
    @ExceptionHandler(SudokuFileNotCreatedException.class)
    public ResponseEntity<ErrorDto> handleFileNotCreatedException(SudokuFileNotCreatedException exception) {
        // return 500 internal server error with a message
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorDto(exception.getMessage()));
    }

    // method to handle Sudoku file not found exception
    @ExceptionHandler(SudokuFileNotFoundException.class)
    public ResponseEntity<ErrorDto> handleSudokuFileNotFoundException(SudokuFileNotFoundException exception) {
        // return 404 not found with a message
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorDto(exception.getMessage()));
    }

    // method to handle character violation exception
    @ExceptionHandler(InvalidCharacterException.class)
    public ResponseEntity<ErrorDto> handleInvalidCharacterException(InvalidCharacterException exception) {
        // return 400 bad request with a message
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorDto(exception.getMessage()));
    }

}
