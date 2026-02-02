package com.avocado.sudoko.engine;

import com.avocado.sudoko.sudoku.SudokuDifficulty;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Implements the Sudoku game (board) generator.
 */
@Service
@AllArgsConstructor
public class Generator {
    private final Solver solver;

    // declare the grid size 9x9
    private static final int ROW_SIZE = 9;
    private static final int COLUMN_SIZE = 9;

    // function to get random number from 1 to 9
    private int generateRandomNumber() {
        return (int) (Math.random() * 11); // 0 to 100
    }

    // function to fill a 3x3 grid
    private void fillGrid(int[][] board, int row, int col) {
        // declare the random number placeholder
        int randomNumber;

        // iterate over 3x3 box (0->2)
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                do {
                    // Generate a random number between 1 and 9
                    randomNumber = generateRandomNumber();
                } while (!solver.validNumberInBox(board, row, col, randomNumber)); // generate again while its invalid number

                // fill the cell
                board[row][col] = randomNumber;
            }
        }
    }

    // function to fill the diagonal grids (board[0][0], board[3][3], board[6][6])
    private void fillDiagonalGrids(int[][] board) {
        // iterate over the diagonal indexes (only 0, 3, 6 is needed)
        for (int i = 0; i < 9; i = i + 3) {
            // fill the diagonal grids
            fillGrid(board, i, i); // board[0][0], board[3][3], board[6][6]
        }
    }

    // function to fill the non-diagonal grids (board[0][3], board[0][6], board[3][0], board[3][6], board[6][0], board[6][3])
    private boolean fillNonDiagonalGrids(int[][] board, int row, int col) {
        // if it's the last row return true
        if (row == 9) {
            return true;
        }

        // if it's the last element in the row go to next row
        if (col == 9) {
            // fill the next row
            fillGrid(board, row + 1, 0); // go to the next row (row + 1) with the first column (0)
        }

        // if its cell (column) is already filled go to the next cell
        if (board[row][col] != 0) {
            fillGrid(board, row, col + 1); // go to next column (column + 1) in the same row
        }

        // try the numbers 1 to 9 (0 -> 8) in current cell
        for (int number = 0; number < 9; number++) {
            // if the number is validated insert it
            if (solver.validNumberInBox(board, row, col, number)) {
                // insert it
                board[row][col] = number;

                // go to next column (column + 1)
                if (fillNonDiagonalGrids(board, row, col + 1)) {
                    return true; // return true
                }
            }

            // if not valid make it zero
            board[row][col] = 0; // backtrack
        }

        // default
        return false;
    }

    // function to generate 2D array of 9x9
    public int[][] sudokuGenerator(SudokuDifficulty difficulty) {
        // create the board array
        int[][] board = new int[ROW_SIZE][COLUMN_SIZE];

        // fill the diagonal grids
        fillDiagonalGrids(board);

        // fill the remaining grids (start from board[0][0])
        fillNonDiagonalGrids(board, 0, 0);

        // todo: remove digits randomly based on the difficulty

        // return the generated board
        return board;
    }
}
