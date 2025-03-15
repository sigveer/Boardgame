package com.gruppe24.BoardGames.TicTacToe;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;


/
public class TicTacToeApp extends Application {
  private Button[][] board = new Button[3][3];
  private boolean xTurn = true;
  private boolean gameOver = false;


  @Override
  public void start(javafx.stage.Stage primaryStage) {
    GridPane grid = new GridPane();

    for (int row = 0; row < 3; row++) {
      for (int col = 0; col < 3; col++) {
        board[row][col] = new Button();
        board[row][col].setPrefSize(200, 200);
        board[row][col].setStyle("-fx-font-size: 50px;");

        board[row][col].setOnAction(event -> {});

        grid.add(board[row][col], col, row);
      }
    }

    Scene scene = new Scene(grid);
    primaryStage.setScene(scene);
    primaryStage.setTitle("Tic Tac Toe");
    primaryStage.show();
  }

  public void handleClick(int row, int col) {
    if (gameOver || board[row][col].getText().isEmpty()) {
      return;
    }

    board[row][col].setText(xTurn ? "X" : "O");

    if () {

    }

  }

  public void checkWin(int row, int col) {
    String symbol = xTurn ? "X" : "O";

    //Check rows
    if(board[row][0].getText().equals(symbol) &&
        board[row][1].getText().equals(symbol) &&
        board[row][2].getText().equals(symbol)) {
      gameOver = true;
    }

    //Check columns
    if(board[0][col].getText().equals(symbol) &&
        board[1][col].getText().equals(symbol) &&
        board[2][col].getText().equals(symbol)) {
      gameOver = true;
    }

    //Check diagonals
    if(row == col &&
        board[0][0].getText().equals(symbol) &&
        board[1][1].getText().equals(symbol) &&
        board[2][2].getText().equals(symbol)) {
      gameOver = true;
    }

    if(row + col == 2 &&
        board[0][2].getText().equals(symbol) &&
        board[1][1].getText().equals(symbol) &&
        board[2][0].getText().equals(symbol)) {
      gameOver = true;
    }
  }


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


  public static void main(String[] args) {
    launch(args);
  }
}
