package com.avocado.sudoko.sudoku;

import com.avocado.sudoko.sudoku.dtos.GenerateSudokuRequest;
import com.avocado.sudoko.sudoku.dtos.SudokuSolveRequest;
import com.avocado.sudoko.sudoku.dtos.SudokuSolveResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/sudoku")
@AllArgsConstructor
public class SudokuController {
    private final SudokuService sudokuService;
    private final SudokuMapper sudokuMapper;

    // api endpoint to generate a game
    @PostMapping
    public ResponseEntity<?> generateSudoku(
            @Valid @RequestBody GenerateSudokuRequest request,
            UriComponentsBuilder uriComponentsBuilder
    ) {
        // try to generate a game
        var sudoku = sudokuService.generateSudoku(request);

        // build the location URI to tells the client where to find the resource
        var uri = uriComponentsBuilder.path("/api/v1/sudoku/{id}")
                .buildAndExpand(sudoku.getUuid())
                .toUri();

        // return the generated game as sudokuDto
        return ResponseEntity
                .created(uri) // sets the Location header to /api/v1/sudoku/123...
                .body(sudokuMapper.toDto(sudoku));
    }

    // api endpoint to load a game
    @GetMapping("/{id}")
    public ResponseEntity<?> getSudoku(@PathVariable(name = "id") String uuid) {
        // try to get a generated game
        var sudoku = sudokuService.loadSudoko(UUID.fromString(uuid));

        // return the generated game as sudokuDto
        return ResponseEntity.ok(sudokuMapper.toDto(sudoku));
    }

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
