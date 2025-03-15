package com.gruppe24.BoardGames.TicTacToe.Controller;

import javafx.stage.Stage;

public class GameController {
  private static final int BOARD_SIZE = 3;
  private static final int CELL_SIZE = 50;



  public void startGame(Stage stage, String title) {

    stage.setTitle(title);
    stage.setResizable(false);
    stage.show();
  }
}
