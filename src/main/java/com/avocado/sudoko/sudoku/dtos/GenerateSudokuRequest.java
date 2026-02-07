package com.avocado.sudoko.sudoku.dtos;

import com.avocado.sudoko.sudoku.SudokuDifficulty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class GenerateSudokuRequest {
    @NotBlank(message = "Difficulty must be provided")
    private SudokuDifficulty difficulty;
}
