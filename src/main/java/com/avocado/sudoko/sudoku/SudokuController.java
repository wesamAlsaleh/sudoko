package com.avocado.sudoko.sudoku;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/sudoku")
@AllArgsConstructor
public class SudokuController {
    private final SudokuService sudokuService;

    // api endpoint to generate a game
    @PostMapping
    public ResponseEntity<?> generateSudoku() {
        // try to generate a game
        var sudoku = sudokuService.generateSudoku();

        // return the generated game
        return ResponseEntity.ok(sudoku);
    }

    // api endpoint to load a game
    @GetMapping
    public ResponseEntity<?> getSudoku() {
        // try to get a generated game
        var sudoku = sudokuService.loadSudoko(UUID.fromString("08f7418c-e25f-4c22-b254-930a0d21c3f9"));

        // return the generated game
        return ResponseEntity.ok(sudoku);
    }
}
