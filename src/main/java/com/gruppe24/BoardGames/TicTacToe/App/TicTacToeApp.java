package com.gruppe24.BoardGames.TicTacToe.App;

import com.gruppe24.BoardGames.TicTacToe.View.GameView;
import javafx.application.Application;
import javafx.stage.Stage;

public class TicTacToeApp extends Application {
  private static final String TITLE = "Tic Tac Toe";

  @Override
  public void start(Stage stage) {
    GameView gameView = new GameView();
    gameView.initialize(stage);
    stage.setTitle(TITLE);
    stage.setResizable(false);
    stage.show();
  }
}
