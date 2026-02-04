package com.avocado.sudoko.sudoku;

import com.avocado.sudoko.sudoku.dtos.SudokuSolveRequest;
import com.avocado.sudoko.sudoku.dtos.SudokuSolveResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/sudoku")
@AllArgsConstructor
public class SudokuController {
    private final SudokuService sudokuService;
    private final SudokuMapper sudokuMapper;

    // api endpoint to generate a game
    @PostMapping
    public ResponseEntity<?> generateSudoku() {
        // try to generate a game
        var sudoku = sudokuService.generateSudoku();

        // return the generated game as sudokuDto
        return ResponseEntity.ok(sudokuMapper.toDto(sudoku));
    }

    // todo: add the uuid in the params to load a game
    // todo: handle not found exception
    // api endpoint to load a game
    @GetMapping
    public ResponseEntity<?> getSudoku() {
        // try to get a generated game
        var sudoku = sudokuService.loadSudoko(UUID.fromString("08f7418c-e25f-4c22-b254-930a0d21c3f9"));

        // return the generated game as sudokuDto
        return ResponseEntity.ok(sudokuMapper.toDto(sudoku));
    }

    // todo: handle not found exception
    // api endpoint to submit a solution
    @PostMapping("/submit")
    public ResponseEntity<?> submitSolution(@Valid @RequestBody SudokuSolveRequest request) {
        // try to compare the player solution and the original solution
        var success = sudokuService.submitSudoku(request);

        // prepare the message
        var message = (success)
                ? "Congratulations! The solution is correct 🎉"
                : "The solution is incorrect. Please try again.";

        // return a response with a message based on the success
        return ResponseEntity.ok(
                new SudokuSolveResponse(message, success)
        );
    }

}
