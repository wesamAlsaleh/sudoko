package com.avocado.sudoko.sudoku;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/sudoku")
@AllArgsConstructor
public class SudokuController {
    private final SudokuService sudokuService;

    // api endpoint to generate a board
    @PostMapping
    public ResponseEntity<?> generateSudoku() {
        var game = sudokuService.generateSudoku();

        return ResponseEntity.ok(game);
    }
}
