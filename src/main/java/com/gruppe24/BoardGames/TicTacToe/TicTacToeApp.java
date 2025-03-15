package com.gruppe24.BoardGames.TicTacToe;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

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


  public static void main(String[] args) {
    launch(args);
  }
}
