package com.gruppe24.boardgames.tictactoe;

/**
 * {@code Controller} is the controller class for the Tic Tac Toe game.
 */
public class Controller {
  private final Model model;
  private View view;

  /**
   * Constructor for the controller class.
   *
   * @param model is the model for the game
   */
  public Controller(Model model) {
    this.model = model;
  }

  /**
   * Method to set the view for the controller.
   */
  public void setView(View view) {
    this.view = view;
  }

  /**
   * Method to handle the click event.
   *
   * @param row is the row of the cell clicked
   * @param col is the column of the cell clicked
   */
  public void handleClick(int row, int col) {
    if (model.isGameOver() || !model.getCell(row, col).isEmpty()) {
      return;
    }

    String symbol = model.getCurrentPlayerSymbol();
    model.setCell(row, col, symbol);

    view.updateBoard();

    if (checkWin(row, col)) {
      model.setGameOver(true);
      view.showAlert(symbol + " wins!");
    } else if (checkDraw()) {
      model.setGameOver(true);
      view.showAlert("It's a draw!");
    } else {
      model.toggleTurn();
    }
  }

  /**
   * Method to restart the game.
   */
  public void restartGame() {
    model.resetGame();
    view.updateBoard();
  }


  /**
   * Method to check if the game is won.
   *
   * @param row is the row of the cell clicked
   * @param col is the column of the cell clicked
   * @return true if the game is won, false otherwise
   */
  private boolean checkWin(int row, int col) {
    String symbol = model.getCurrentPlayerSymbol();

    // Check rows
    if (model.getCell(row, 0).equals(symbol)
        && model.getCell(row, 1).equals(symbol)
        && model.getCell(row, 2).equals(symbol)) {
      return true;
    }

    // Check columns
    if (model.getCell(0, col).equals(symbol)
        && model.getCell(1, col).equals(symbol)
        && model.getCell(2, col).equals(symbol)) {
      return true;
    }

    // Check diagonals
    if (row == col
        && model.getCell(0, 0).equals(symbol)
        && model.getCell(1, 1).equals(symbol)
        && model.getCell(2, 2).equals(symbol)) {
      return true;
    }

    return row + col == 2
        && model.getCell(0, 2).equals(symbol)
        && model.getCell(1, 1).equals(symbol)
        && model.getCell(2, 0).equals(symbol);
  }

  /**
   * Method to check if the game is a draw.
   *
   * @return true if the game is a draw, false otherwise
   */
  private boolean checkDraw() {
    for (int row = 0; row < 3; row++) {
      for (int col = 0; col < 3; col++) {
        if (model.getCell(row, col).isEmpty()) {
          return false;
        }
      }
    }
    return true;
  }
}