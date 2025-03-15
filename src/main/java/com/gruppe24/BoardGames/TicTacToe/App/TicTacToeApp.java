package com.gruppe24.BoardGames.TicTacToe.App;

import javafx.application.Application;
import javafx.stage.Stage;
import com.gruppe24.BoardGames.TicTacToe.Controller.GameController;

/**
 * The {@code TicTacToeApp} class is the entry point for the Tic Tac Toe game.
 */
public class TicTacToeApp extends Application {
  private static final String TITLE = "Tic Tac Toe";


  /**
   * The main method is the entry point for the Tic Tac Toe game.
   */
  @Override
  public void start(Stage stage) {
    GameController gameController = new GameController();
    gameController.startGame(stage, TITLE);
  }
}
