package com.avocado.sudoko.engine;

import org.springframework.stereotype.Service;

/**
 * Implements the Backtracking algorithm logic for solving a Sudoku puzzle.
 * Provides validation methods to check rows, columns, and 3x3 sub-grids
 * to ensure no duplicate numbers exist.
 */
@Service
public class Solver {
    // declare the grid size 9x9
    private static final int GRID_SIZE = 9;

    // this function is to check if the row contain repetition numbers
    private boolean isNumberInRow(int[][] board, int row, int number) {
        // iterate over the row elements (0 -> 8)
        for (int i = 0; i < GRID_SIZE; i++) {
            // if the number available return true
            if (board[row][i] == number) {
                return true;
            }
        }

        // default
        return false;
    }

    // this function is to check if the column contain repetition numbers
    private boolean isNumberInColumn(int[][] board, int col, int number) {
        // iterate over the column elements (0 -> 8)
        for (int i = 0; i < GRID_SIZE; i++) {
            // if the number available return true
            if (board[i][col] == number) {
                return true;
            }
        }

        // default
        return false;
    }

    // this function is to check if the 3x3 grid (box) contain repetition numbers
    private boolean isNumberInGrid(int[][] board, int row, int col, int number) {
        // get the grid start index (top-left of the box)
        var localRowIndex = row - row % 3;
        var localColIndex = col - col % 3;

        // declare the box end boundary
        var rowEnd = localRowIndex + 3;
        var colEnd = localColIndex + 3;

        // iterate over the grid
        for (int i = localRowIndex; i < rowEnd; i++) {
            for (int j = localColIndex; j < colEnd; j++) {
                // if the number exist return true
                if (board[i][j] == number) {
                    return true;
                }
            }
        }

        // default
        return false;
    }

    // function to check if the number is a valid number to insert it in the 9x9 board
    public boolean isValidPlacement(int[][] board, int row, int col, int number) {
        // return true if the number is not in the row, column, grid
        return !isNumberInRow(board, row, number) &&
                !isNumberInColumn(board, col, number) &&
                !isNumberInGrid(board, row, col, number);
    }

    // function to check if the number is in a 3x3 box
    public boolean validNumberInBox(int[][] board, int row, int col, int number) {
        // return true if the number is not in the grid
        return !isNumberInGrid(board, row, col, number);
    }
}
