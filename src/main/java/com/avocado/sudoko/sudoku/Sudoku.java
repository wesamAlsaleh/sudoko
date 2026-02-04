package com.avocado.sudoko.sudoku;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Arrays;
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

    private String puzzleSolution;

    // function to print the sudoku board
    public void printSudoku() {
        printSudoku(this.puzzle);
    }

    // function to print the sudoku solution
    public void printSudokuSolution() {
        printSudoku(this.puzzleSolution);
    }

    // function to print the board
    private void printSudoku(String puzzleString) {
        // create 2d 9x9 array
        var board = new int[9][9];

        // string index flag
        var stringIndex = 0;

        // convert the string to 2D array
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                // get the value
                board[i][j] = Character.getNumericValue(puzzleString.charAt(stringIndex));

                // update the pointer
                stringIndex++;
            }
        }

        // print in a nice way
        for (int row = 0; row < 9; row++) {
            // print divider after each 3 rows
            if (row % 3 == 0) {
                System.out.println("+-------+-------+-------+");
            }
            for (int col = 0; col < 9; col++) {
                // print divider after each 3 columns
                if (col % 3 == 0) {
                    System.out.print("| ");
                }
                System.out.print(board[row][col] + " ");

                // print the divider on the end of the column
                if (col == 8) {
                    System.out.print("|");
                }
            }
            System.out.println(); // go to next row after finishing from the column elements

            // print the divider on the end of the row
            if (row == 8) {
                System.out.println("+-------+-------+-------+");
            }
        }
    }
}
