package com.gruppe24.BoardGames.TicTacToe.Model;

/**
 * The {@code GameLogic} class is the model for the Tic Tac Toe game.
 */
public class GameLogic {
  private final Cell[][] board;
  private final int size;


  /**
   * The constructor initializes the game logic.
   *
   * @param board the game board
   */
  public GameLogic(Cell[][] board) {
    this.board = board;
    this.size = board.length;
  }


  /**
   * Checks if the player X has won the game.
   *
   * @return true if player X has won the game, false otherwise
   */
  public boolean isWinnerX() {
    return checkWinner(true);
  }


  /**
   * Checks if the player O has won the game.
   *
   * @return true if player O has won the game, false otherwise
   */
  public boolean isWinnerO() {
    return checkWinner(false);
  }


  /**
   * Checks if a player has won the game.
   *
   * @param checkForX true if player X should be checked, false if player O should be checked
   * @return true if a player has won the game, false otherwise
   */
  private boolean checkWinner(boolean checkForX) {
    for (int row = 0; row < size; row++) {
      if (allMarkedSame(board[row][0], board[row][1], board[row][2], checkForX)) {
        return true;
      }
    }
    for (int col = 0; col < size; col++) {
      if (allMarkedSame(board[0][col], board[1][col], board[2][col], checkForX)) {
        return true;
      }
    }
    if (allMarkedSame(board[0][0], board[1][1], board[2][2], checkForX)) {
      return true;
    }
    return allMarkedSame(board[0][2], board[1][1], board[2][0], checkForX);
  }


  /**
   * Checks if all cells are marked with the same mark.
   *
   * @param a the first cell
   * @param b the second cell
   * @param c the third cell
   * @param checkForX true if player X should be checked, false if player O should be checked
   * @return true if all cells are marked with the same mark, false otherwise
   */
  private boolean allMarkedSame(Cell a, Cell b, Cell c, boolean checkForX) {
    if (checkForX) {
      return a.isMarkedX() && b.isMarkedX() && c.isMarkedX();
    } else {
      return a.isMarkedO() && b.isMarkedO() && c.isMarkedO();
    }
  }


  /**
   * Checks if the game is a draw.
   *
   * @return true if the game is a draw, false otherwise
   */
  public boolean hasEmptyCells() {
    for (Cell[] row : board) {
      for (Cell cell : row) {
        if (cell.isEmpty()) {
          return true;
        }
      }
    }
    return false;
  }
}