package com.avocado.sudoko.global.utils;

import org.springframework.stereotype.Component;

@Component
public class BoardUtils {
    // declare the grid size 9x9
    private final static int ROW_SIZE = 9;
    private final static int COLUMN_SIZE = 9;

    // function to convert a 2D array of 9x9 to String of numbers
    public String boardToString(int[][] board) {
        // safety check
        if (board == null) return "";

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

    // function to clone 2d Array
    public int[][] deepCopyBoard(int[][] board) {
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
}
