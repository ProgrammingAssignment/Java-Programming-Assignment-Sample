package com.soduku;

public class SudokuSolver {

    static int puzzle[][] = null;

    static boolean isValid(Cell cell, int value) {

        if (puzzle[cell.row][cell.col] != 0) {
            throw new RuntimeException(
                    "Cannot call for cell which already has a value");
        }

        // if v present row, return false
        for (int c = 0; c < 9; c++) {
            if (puzzle[cell.row][c] == value)
                return false;
        }

        // if v present in col, return false
        for (int r = 0; r < 9; r++) {
            if (puzzle[r][cell.col] == value)
                return false;
        }

        // to get the puzzle we should calculate (x1,y1) (x2,y2)
        int x1 = 3 * (cell.row / 3);
        int y1 = 3 * (cell.col / 3);
        int x2 = x1 + 2;
        int y2 = y1 + 2;

        for (int x = x1; x <= x2; x++)
            for (int y = y1; y <= y2; y++)
                if (puzzle[x][y] == value)
                    return false;

        // if value not present in row, col and bounding box, return true
        return true;
    }

    //get next cell
    static Cell getNextCell(Cell cur) {

        int row = cur.row;
        int col = cur.col;

        // next cell => col++
        col++;

        // if col > 8, then col = 0, row++
        // reached end of row, got to next row
        if (col > 8) {
            // goto next line
            col = 0;
            row++;
        }

        // reached end of matrix, return null
        if (row > 8)
            return null; // reached end

        Cell next = new Cell(row, col);
        return next;
    }

    //return true, if the soduku is solved, return false otherwise
    static boolean solve(Cell cur) {

        // if the cell is null,reached the end
        if (cur == null)
            return true;

        // if puzzle[cur] already has a value, there is nothing to solve here,
        // continue on to next cell
        if (puzzle[cur.row][cur.col] != 0) {
            // return whatever is being returned by solve(next)
            // i.e the state of soduku's solution is not being determined by
            // this cell, but by other cells
            return solve(getNextCell(cur));
        }

        // this is where each possible value is being assigned to the cell, and
        // checked if a solutions could be arrived at.

        // if puzzle[cur] doesn't have a value
        // try each possible value
        for (int i = 1; i <= 9; i++) {
            // check if valid, if valid, then update
            boolean valid = isValid(cur, i);

            if (!valid) // i not valid for this cell, try other values
                continue;

            // assign here
            puzzle[cur.row][cur.col] = i;

            // continue with next cell
            boolean solved = solve(getNextCell(cur));
            // if solved, return, else try other values
            if (solved)
                return true;
            else
                puzzle[cur.row][cur.col] = 0; // reset
            // continue with other possible values
        }

        // if you reach here, then no value from 1 - 9 for this cell can solve
        // return false
        return false;
    }

    public static void main(String[] args) {

        boolean solved = false;

        while (!solved) {
            puzzle = SudokuGenerator.generate();
            solved = solve(new Cell(0, 0));
            if (!solved) {
                System.out.println("SUDOKU cannot be solved.\n");
            }

        }
        System.out.println("SOLUTION\n");
        printSudoku(puzzle);
    }


    //print puzzle
    static void printSudoku(int grid[][]) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++)
                System.out.print(grid[row][col]+"  ");
            System.out.println();
        }
    }

    /**
     * Class to abstract the representation of a cell. Cell => (x, y)
     */
    static class Cell {

        int row, col;

        public Cell(int row, int col) {
            super();
            this.row = row;
            this.col = col;
        }

        @Override
        public String toString() {
            return "Cell [row=" + row + ", col=" + col + "]";
        }
    };

}
