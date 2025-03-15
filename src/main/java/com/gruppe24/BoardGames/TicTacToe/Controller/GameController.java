package com.gruppe24.BoardGames.TicTacToe.Controller;

import com.gruppe24.BoardGames.TicTacToe.Model.Cell;
import com.gruppe24.BoardGames.TicTacToe.Model.GameLogic;
import com.gruppe24.BoardGames.TicTacToe.View.BoardView;
import javafx.scene.control.Alert;
import javafx.stage.Stage;


/**
 * The {@code GameController} class is the controller for the Tic Tac Toe game.
 */
public class GameController {
  private static final int BOARD_SIZE = 3;
  private static final int CELL_SIZE = 50;

  private Cell[][] board;
  private GameLogic gameLogic;
  private BoardView boardView;


  /**
   * The constructor initializes the Tic Tac Toe game.
   */
  public GameController() {
    initalizeGame();
  }


  /**
   * The initalizeGame method initializes the Tic Tac Toe game.
   */
  private void initalizeGame() {
    this.board = new Cell[BOARD_SIZE][BOARD_SIZE];
    for (int row = 0; row < BOARD_SIZE; row++) {
      for (int col = 0; col < BOARD_SIZE; col++) {
        this.board[row][col] = new Cell();
      }

    }
  }


  /**
   * The startGame method starts the Tic Tac Toe game.
   *
   * @param stage the stage for the game
   * @param title the title of the game
   */
  public void startGame(Stage stage, String title) {


//    stage.setScene(scene);
    stage.setTitle(title);
    stage.setResizable(false);
    stage.show();
  }

  private boolean checkGameState() {
    boolean hasEmpty = this.gameLogic.hasEmptyCells();
    if (!hasEmpty) {
      showAlert("All cells filled! Start a new game!");
    }
    return hasEmpty;
  }

  private void checkWinner() {
    if (this.gameLogic.isWinnerX()) {
      showAlert("X wins! Start a new game!");
    } else if (this.gameLogic.isWinnerO()) {
      showAlert("O wins! Start a new game!");
    }
  }


  /**
   * Method for showing an alert message.
   *
   * @param message the message to show
   */
  private void showAlert(String message) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Game Status");
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
  }


  /**
   * Method for resetting the game.
   */
  private void resetGame() {
    initalizeGame();
    this.boardView.rebuildBoard();
  }


  public Cell[][] getBoard() {
    return board;
  }

  public int getBoardSize() {
    return BOARD_SIZE;
  }

  public int getCellSize() {
    return CELL_SIZE;
  }
}
