package com.avocado.sudoko.global.exceptions;

public class SudokuFileNotFoundException extends RuntimeException {
    public SudokuFileNotFoundException(String message) {
        super(message);
    }
}
