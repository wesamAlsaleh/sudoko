package com.avocado.sudoko.sudoku.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class SudokuSolveRequest {
    @NotBlank(message = "Game ID must not be blank")
    @Pattern(
            regexp = "[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}", // 8dig-4dig-4dig-4dig-12dig (0-9 and a-f A-F)
            message = "Game ID is not valid UUID format"
    )
    private String uuid; // UUID v4 format

    @NotBlank(message = "Player board must not be blank")
    @Pattern(
            regexp = "[1-9]{81}", // must contain exactly 81 digits (0–9)
            message = "Player board must contain exactly 81 digits (0–9)"
    )
    private String playerSolution;
}
