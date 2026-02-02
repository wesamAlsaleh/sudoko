package com.avocado.sudoko.sudoku;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

//@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Sudoku {
    private UUID uuid;

    private SudokuDifficulty difficulty;

    private String puzzle;
}
