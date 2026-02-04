package com.avocado.sudoko.sudoku.dtos;

import com.avocado.sudoko.sudoku.SudokuDifficulty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@AllArgsConstructor
@Data
public class SudokuDto {
    private UUID gameId;
    private SudokuDifficulty difficulty;
    private String puzzleString;
}
