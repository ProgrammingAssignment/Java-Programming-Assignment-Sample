# Generate new random Sudokus

Before solving this Java programming exercises you should have finished the Java backtracking algorithm to solve Sudokus.

You can create new Sudoku puzzles by inserting n random numbers in the range from 1 - 9 and checking for each new number, whether the rules of the game still apply or not. After that, you can call the backtracking algorithm to solve the new Sudoku: if a solution have been found, your Java programm has created a new Sudoku puzzle; if not, you start the whole process again.

This method is able to create new Sudoku puzzles with a certain probability 0 < p < 1. It is unlikly, but possible, that your algorithm never terminates. But when it terminates, the result is always correct. Such algorithms based on randomize creation of problem solutions are called.

Add a Java method that creates new Sudoku puzzles with n numbers to the existing backtracking algorithm
