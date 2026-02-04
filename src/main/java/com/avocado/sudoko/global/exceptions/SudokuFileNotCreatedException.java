package com.avocado.sudoko.global.exceptions;

public class SudokuFileNotCreatedException extends RuntimeException {
    public SudokuFileNotCreatedException(String message) {
        super(message);
    }
}
