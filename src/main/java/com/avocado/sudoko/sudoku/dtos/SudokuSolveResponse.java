package com.avocado.sudoko.sudoku.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class SudokuSolveResponse {
    private String message;
    private boolean success;
}
