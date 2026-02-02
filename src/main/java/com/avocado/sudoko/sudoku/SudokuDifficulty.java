package com.avocado.sudoko.sudoku;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SudokuDifficulty {
    // difficulty system
    EASY(35),
    MEDIUM(45),
    HARD(54);

    private final int digitsToRemove;
}
