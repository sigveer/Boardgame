package com.gruppe24.boardgames.tictactoe;

/**
 * {@code Model} is the model class for the Tic Tac Toe game.
 */
public class Model {
  private final String[][] board;
  private boolean xturn;
  private boolean gameOver;


  /**
   * Constructor for the model class.
   */
  public Model() {
    board = new String[3][3];
    resetGame();
  }

  /**
   * Method to reset the game.
   */
  public void resetGame() {
    for (int row = 0; row < 3; row++) {
      for (int col = 0; col < 3; col++) {
        board[row][col] = "";
      }
    }
    xturn = true;
    gameOver = false;
  }

  /**
   * Getter method to get the cell.
   */
  public String getCell(int row, int col) {
    return board[row][col];
  }

  /**
   * Setter method to set the cell.
   */
  public void setCell(int row, int col, String value) {
    board[row][col] = value;
  }


  /**
   * Method to toggle the turn.
   */
  public void toggleTurn() {
    xturn = !xturn;
  }


  /**
   * Method to check if the game is over.
   *
   * @return true if the game is over, false otherwise
   */
  public boolean isGameOver() {
    return gameOver;
  }

  /**
   * Setter method to set the game over.
   *
   * @param gameOver is the boolean value to set the game over
   */
  public void setGameOver(boolean gameOver) {
    this.gameOver = gameOver;
  }


  /**
   * Method to get the current player symbol.
   *
   * @return the symbol of the current player
   */
  public String getCurrentPlayerSymbol() {
    return xturn ? "X" : "O";
  }
}