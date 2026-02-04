package com.avocado.sudoko.sudoku;

import com.avocado.sudoko.engine.Generator;
import com.avocado.sudoko.sudoku.dtos.SudokuDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class SudokuService {
    private final Generator generator;
    private final SudokuMapper sudokuMapper;
    private final SudokuRepository sudokuRepository;

    // function to generate a new game
    public SudokuDto generateSudoku() {
        // generate new sudoku game
        var sudoku = generator.generateSudokuGame(SudokuDifficulty.MEDIUM);

        // save the generated game
        sudokuRepository.save(sudoku);

        // return the sudoko as dto
        return sudokuMapper.toDto(sudoku);
    }

    // function to load a game using UUID
    public SudokuDto loadSudoko(UUID uuid) {
        // get the sudoku details from the db
        var sudoku = sudokuRepository.getSudokuByUUID(uuid);

        // return the sudoko as dto
        return sudokuMapper.toDto(sudoku);
    }
}
