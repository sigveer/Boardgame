package com.gruppe24.BoardGames.TicTacToe;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;


/**
 * Class to represent the Tic Tac Toe application
 *
 * @Author Sigveer
 * @Date: 15.03.2025
 * @Version: 1.0
 */
public class TicTacToeApp extends Application {
  private Button[][] board = new Button[3][3];
  private boolean xTurn = true;
  private boolean gameOver = false;


  /**
   * Method to start the application
   *
   * @param primaryStage is the stage to be shown
   * @Author Sigveer
   * @Date: 15.03.2025
   * @Version: 1.0
   */
  @Override
  public void start(javafx.stage.Stage primaryStage) {
    GridPane grid = new GridPane();

    for (int row = 0; row < 3; row++) {
      for (int col = 0; col < 3; col++) {
        board[row][col] = new Button();
        board[row][col].setPrefSize(200, 200);
        board[row][col].setStyle("-fx-font-size: 50px;");

        final int finalRow = row;
        final int finalCol = col;

        board[row][col].setOnAction(e -> handleClick(finalRow, finalCol));

        grid.add(board[row][col], col, row);
      }
    }

    Scene scene = new Scene(grid);
    primaryStage.setScene(scene);
    primaryStage.setTitle("Tic Tac Toe");
    primaryStage.show();
  }


  /**
   * Method to handle the click event on the board
   *
   * @param row is the row of the button
   * @param col is the column of the button
   * @Author Sigveer
   * @Date: 15.03.2025
   * @Version: 1.0
   */
  public void handleClick(int row, int col) {
    if (gameOver || !board[row][col].getText().isEmpty()) {
      return;
    }

    board[row][col].setText(xTurn ? "X" : "O");

    if (checkWin(row, col)) {
      gameOver = true;
      showAlert(xTurn ? "X wins!" : "O wins!");
    } else if (checkDraw()) {
      gameOver = true;
      showAlert("It's a draw!");
    } else {
      xTurn = !xTurn;
    }
  }


  /**
   * Method to check if the game is won
   *
   * @param row is the row of the button
   * @param col is the column of the button
   * @return true if the game is won, false otherwise
   * @Author Sigveer
   * @Date: 15.03.2025
   * @Version: 1.0
   */
  private boolean checkWin(int row, int col) {
    String symbol = xTurn ? "X" : "O";

    //Check rows
    if(board[row][0].getText().equals(symbol) &&
        board[row][1].getText().equals(symbol) &&
        board[row][2].getText().equals(symbol)) {
      return true;
    }

    //Check columns
    if(board[0][col].getText().equals(symbol) &&
        board[1][col].getText().equals(symbol) &&
        board[2][col].getText().equals(symbol)) {
      return true;
    }

    //Check diagonals
    if(row == col &&
        board[0][0].getText().equals(symbol) &&
        board[1][1].getText().equals(symbol) &&
        board[2][2].getText().equals(symbol)) {
      return true;
    }

    if (row + col == 2 &&
        board[0][2].getText().equals(symbol) &&
        board[1][1].getText().equals(symbol) &&
        board[2][0].getText().equals(symbol)) {
      return true;
    }
    return false;
  }


  /**
   * Method to check if the game is a draw
   *
   * @return true if the game is a draw, false otherwise
   * @Author Sigveer
   * @Date: 15.03.2025
   * @Version: 1.0
   */
  private boolean checkDraw() {
    for (int row = 0; row < 3; row++) {
      for (int col = 0; col < 3; col++) {
        if (board[row][col].getText().isEmpty()) {
          return false;
        }
      }
    }
    return true;
  }


  /**
   * Method to show an alert
   *
   * @param message is the message to be shown in the alert
   * @Author Sigveer
   * @Date: 15.03.2025
   * @Version: 1.0
   */
  private void showAlert(String message) {
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("Game Over");
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
  }


  /**
   * Main method to launch the application
   *
   * @param args is the command line arguments
   * @Author Sigveer
   * @Date: 15.03.2025
   * @Version: 1.0
   */
  public static void main(String[] args) {
    launch(args);
  }
}
