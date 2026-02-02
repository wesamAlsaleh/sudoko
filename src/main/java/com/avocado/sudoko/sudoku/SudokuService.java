package com.avocado.sudoko.sudoku;

import com.avocado.sudoko.engine.Generator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SudokuService {
    private final Generator generator;

    // function to generate a new game
    public int[][] generateSudoku() {
        // generate and return the game
        return  generator.generateSudoku(SudokuDifficulty.EASY);
    }
}
