package com.gruppe24.BoardGames.TicTacToe;


/**
 * {@code Model} is the model class for the Tic Tac Toe game.
 *
 * @Author Sigveer
 * @Date: 15.03.2025
 * @Version: 1.0
 */
public class Model {
  private String[][] board;
  private boolean xTurn;
  private boolean gameOver;


  /**
   * Constructor for the model class
   *
   * @Author Sigveer
   * @Date: 15.03.2025
   * @Version: 1.0
   */
  public Model() {
    board = new String[3][3];
    resetGame();
  }


  /**
   * Method to reset the game
   *
   * @Author Sigveer
   * @Date: 15.03.2025
   * @Version: 1.0
   */
  public void resetGame() {
    for (int row = 0; row < 3; row++) {
      for (int col = 0; col < 3; col++) {
        board[row][col] = "";
      }
    }
    xTurn = true;
    gameOver = false;
  }


  /**
   * Getter method to get the cell.
   *
   * @Author Sigveer
   * @Date: 15.03.2025
   * @Version: 1.0
   */
  public String getCell(int row, int col) {
    return board[row][col];
  }


  /**
   * Setter method to set the cell.
   *
   * @Author Sigveer
   * @Date: 15.03.2025
   * @Version: 1.0
   */
  public void setCell(int row, int col, String value) {
    board[row][col] = value;
  }


  /**
   * Method to toggle the turn
   *
   * @Author Sigveer
   * @Date: 15.03.2025
   * @Version: 1.0
   */
  public void toggleTurn() {
    xTurn = !xTurn;
  }


  /**
   * Method to check if the game is over
   *
   * @return true if the game is over, false otherwise
   * @Author Sigveer
   * @Date: 15.03.2025
   * @Version: 1.0
   */
  public boolean isGameOver() {
    return gameOver;
  }


  /**
   * Setter method to set the game over
   *
   * @param gameOver is the boolean value to set the game over
   * @Author Sigveer
   * @Date: 15.03.2025
   * @Version: 1.0
   */
  public void setGameOver(boolean gameOver) {
    this.gameOver = gameOver;
  }


  /**
   * Method to get the current player symbol
   *
   * @return the symbol of the current player
   * @Author Sigveer
   * @Date: 15.03.2025
   * @Version: 1.0
   */
  public String getCurrentPlayerSymbol() {
    return xTurn ? "X" : "O";
  }
}