package com.gruppe24.BoardGames.TicTacToe.App;

import javafx.application.Application;
import javafx.stage.Stage;
import com.gruppe24.BoardGames.TicTacToe.Controller.GameController;

public class TicTacToeApp extends Application {
  private static final String TITLE = "Tic Tac Toe";

  @Override
  public void start(Stage stage) {
    GameController gameController = new GameController();
    gameController.startGame(stage, TITLE);
  }
}
