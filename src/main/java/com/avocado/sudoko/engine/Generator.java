package com.avocado.sudoko.engine;

import com.avocado.sudoko.sudoku.Sudoku;
import com.avocado.sudoko.sudoku.SudokuDifficulty;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Random;
import java.util.UUID;

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
        return (int) (Math.random() * 10); // 0 to 9
    }

    // function to get random cell id from 0 to 80
    private int getRandomCellIndex() {
        // create random instance
        var random = new Random();

        // return the random
        return random.nextInt(ROW_SIZE * COLUMN_SIZE); // 0..80
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
        // if it's the end of the grid
        if (row == 9) {
            return true;
        }

        // if it's the last element in the row go to next row
        if (col == 9) {
            // fill the next row
            return fillNonDiagonalGrids(board, row + 1, 0); // go to the next row (row + 1) with the first column (0)
        }

        // if its cell (column) is already filled go to the next cell
        if (board[row][col] != 0) {
            return fillNonDiagonalGrids(board, row, col + 1); // go to next column (column + 1) in the same row
        }

        // try the numbers 1 to 9 (1 -> 9) in current cell
        for (int number = 1; number <= 9; number++) {
            // if the number is validated insert it
            if (solver.isValidPlacement(board, row, col, number)) {
                // insert it
                board[row][col] = number;

                // go to next column (column + 1)
                if (fillNonDiagonalGrids(board, row, col + 1)) {
                    return true; // return true
                }

                // if not valid make it zero
                board[row][col] = 0; // backtrack
            }
        }

        // default
        return false;
    }

    // function to clone 2d Array
    private int[][] deepCopyBoard(int[][] board) {
        // create the board array
        int[][] copyBoard = new int[ROW_SIZE][COLUMN_SIZE];

        // iterate over the rows (0 -> 8)
        for (int i = 0; i < ROW_SIZE; i++) {
            // copy the cells in each row
            System.arraycopy(
                    board[i], // array to be copied from
                    0, // starting position in source array from where to copy (first index)
                    copyBoard[i], // array to be copied in
                    0, // starting position in destination array, where to paste in (first index as well)
                    COLUMN_SIZE // total no. of components to be copied (9 cells in each row)
            );
        }

        // return the copied array
        return copyBoard;
    }

    // function to remove digits based on the difficulty level
    private int[][] removeDigits(int[][] board, SudokuDifficulty difficulty) {
        // create a clone of the board
        var puzzleArray = deepCopyBoard(board);

        // get the digits to remove count based on the difficulty
        var digitsToRemove = difficulty.getDigitsToRemove();

//        System.out.println("Removing " + digitsToRemove);

        // while there are numbers to remove
        while (digitsToRemove > 0) {
            // get random cell id (0 -> 81)
            var cellId = getRandomCellIndex();

            // get the cell coordinates
            var row = cellId / ROW_SIZE;
            var col = cellId % COLUMN_SIZE;

            // if the number is not removed (zero) remove it
            if (puzzleArray[row][col] != 0) {
                // make it zero (removed)
                puzzleArray[row][col] = 0;

                // reduce the numbers to remove counter
                digitsToRemove--;
            }
        }

        // return the updated board
        return puzzleArray;
    }

    // function to convert a 2D array of 9x9 to String of numbers
    private String getBoardInString(int[][] board) {
        // string builder instance
        StringBuilder stringBuilder = new StringBuilder();

        // iterate over the board to convert the array to string
        for (int i = 0; i < ROW_SIZE; i++) {
            for (int j = 0; j < COLUMN_SIZE; j++) {
                stringBuilder.append(board[i][j]);
            }
        }

        // return the array as string
        return stringBuilder.toString();
    }

    // function to generate a solved puzzle
    private int[][] generateSolvedPuzzle() {
        // create the board array
        int[][] board = new int[ROW_SIZE][COLUMN_SIZE];

        // fill the diagonal grids
        fillDiagonalGrids(board);

        // fill the remaining grids (start from board[0][0])
        fillNonDiagonalGrids(board, 0, 0);

        // return the solved sudoku
        return board;
    }

    // function to create puzzle from the solution
    private int[][] createPuzzle(int[][] solvedBoard, SudokuDifficulty difficulty) {
        // remove digits randomly based on the difficulty and return the board
        return removeDigits(solvedBoard, difficulty);
    }

    // function to build a sudoku game
    private Sudoku buildSudoku(
            int[][] solvedBoard,
            int[][] puzzleBoard,
            SudokuDifficulty difficulty
    ) {
        // create sudoku instance
        var sudoku = new Sudoku();

        // fill the sudoku fields
        sudoku.setUuid(UUID.randomUUID()); // generate a unique uuid
        sudoku.setPuzzle(getBoardInString(puzzleBoard)); // convert the puzzle to string
        sudoku.setPuzzleSolution(getBoardInString(solvedBoard)); // convert the puzzle solution to string
        sudoku.setDifficulty(difficulty); // set the difficulty


        // todo: store the puzzle solution in a file
        // todo: store the puzzle in a file

        // return the generated board
        return sudoku;
    }

    // function to generate a sudoku game
    public Sudoku generateSudokuGame(SudokuDifficulty difficulty) {
        // generate a solved board
        var solvedBoard = generateSolvedPuzzle();

        // create the puzzle based on the difficulty
        var puzzleBoard = createPuzzle(solvedBoard, difficulty);

        // build and return the game as object
        return buildSudoku(
                solvedBoard,
                puzzleBoard,
                difficulty
        );
    }
}
