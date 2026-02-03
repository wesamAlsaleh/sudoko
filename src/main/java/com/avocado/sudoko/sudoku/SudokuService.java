package com.avocado.sudoko.sudoku;

import com.avocado.sudoko.engine.Generator;
import com.avocado.sudoko.sudoku.dtos.SudokuDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SudokuService {
    private final Generator generator;
    private final SudokuMapper sudokuMapper;

    // function to generate a new game
    public SudokuDto generateSudoku() {
        // generate new sudoku game
        var sudoku = generator.generateSudokuGame(SudokuDifficulty.MEDIUM);

        sudoku.printSudoku();
        sudoku.printSudokuSolution();

        // return the sudoko as dto
        return sudokuMapper.toDto(sudoku);
    }
}
