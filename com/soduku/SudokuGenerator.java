package com.soduku;

import java.util.Random;

public class SudokuGenerator {

    //A number Chosen to populate predefined no of cells in the puzzle.
    static final int populatedCellCount = 28;

    public static int[][] generate() {

        int[][] grid = new int[9][9];

        //row no
        int row = 0;

        //col no
        int col = 0;

        //value
        int randomNumber = 0;

        Random random = new Random();
        for (int i = 1; i <= populatedCellCount; i++) {

            //get me any row from 0-8
            row = random.nextInt(9);

            //get me any col from 0-8
            col = random.nextInt(9);

            //get me any value from 1-9
            randomNumber = random.nextInt(9) + 1;

            if (grid[row][col] == 0 && checkConflict(grid, row, col, randomNumber)) { //if no is not already initialized and no is not in conflict
                grid[row][col] = randomNumber; //populate value
            } else {
                i--; //make up for invalid number and repeat loop
            }
        }

        SudokuSolver.printSudoku(grid);

        return  grid;

    }

    public static boolean checkConflict(int[][] array, int row, int col, int num) {

        //check if no is not present in column
        for (int i = 0; i < 9; i++) {
            if (array[row][i] == num) {
                return false;
            }
            //check if no is not present in row
            if (array[i][col] == num) {
                return false;
            }
        }

        //check if no is not present in the puzzle
        int gridRow = row - (row % 3);
        int gridColumn = col - (col % 3);
        for (int p = gridRow; p < gridRow + 3; p++) {
            for (int q = gridColumn; q < gridColumn + 3; q++) {
                if (array[p][q] == num) {
                    return false;
                }
            }
        }
        return true;
    }
}