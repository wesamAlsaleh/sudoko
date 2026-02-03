package com.avocado.sudoko.sudoku;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/sudoku")
@AllArgsConstructor
public class SudokuController {
    private final SudokuService sudokuService;

    // api endpoint to generate a game
    @PostMapping
    public ResponseEntity<?> generateSudoku() {
        var sudoku = sudokuService.generateSudoku();

        return ResponseEntity.ok(sudoku);
    }

    // api endpoint to load a game
    @GetMapping
    public ResponseEntity<?> getSudoku() {
        var sudoku = sudokuService.loadSudoko(UUID.fromString("08f7418c-e25f-4c22-b254-930a0d21c3f9"));

        return ResponseEntity.ok(sudoku);
    }
}
