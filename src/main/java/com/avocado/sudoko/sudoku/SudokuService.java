package com.avocado.sudoko.sudoku;

import com.avocado.sudoko.engine.Generator;
import com.avocado.sudoko.sudoku.dtos.SudokuDto;
import com.avocado.sudoko.sudoku.dtos.SudokuSolveRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class SudokuService {
    private final Generator generator;

    private final SudokuRepository sudokuRepository;

    // function to generate a new game
    public Sudoku generateSudoku() {
        // generate new sudoku game
        var sudoku = generator.generateSudokuGame(SudokuDifficulty.MEDIUM);

        // save the generated game
        sudokuRepository.save(sudoku);

        // for testing purposes
        sudoku.printSudoku();
        System.out.println(" ");
        sudoku.printSudokuSolution();
        System.out.println(sudoku.getPuzzleSolution());

        // return the sudoko
        return sudoku;
    }

    // function to load a game using UUID
    public Sudoku loadSudoko(UUID uuid) {
        // get and return the sudoku details from the db
        return sudokuRepository.getSudokuByUUID(uuid);
    }

    // function to submit a puzzle solution
    public boolean submitSudoku(SudokuSolveRequest request) {
        // get the sudoku solution
        var sudokuSolution =
                loadSudoko(UUID.fromString(request.getUuid()))
                        .getPuzzleSolution();

        // get the player solution
        var playerSolution = request.getPlayerSolution().trim(); // trim to be safe

        // compare the player solution with the original solution
        return playerSolution.equals(sudokuSolution.trim());
    }
}
