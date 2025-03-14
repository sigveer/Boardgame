package com.gruppe24.BoardGames.TicTacToe.View;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class GameView {
  private static final int BOARD_SIZE = 3;
  private static final int CELL_SIZE = 50;
  private static final int WINDOW_SIZE = BOARD_SIZE * CELL_SIZE + 40;

  private GameBoard gameBoard;

  /**
   * Initializes the game view
   *
   * @param stage The JavaFX stage
   */
  public void initialize(Stage stage) {
    BorderPane borderPane = new BorderPane();

    gameBoard = new GameBoard(BOARD_SIZE, CELL_SIZE);
    borderPane.setCenter(gameBoard);

    HBox controlPanel = createControlPanel();
    borderPane.setBottom(controlPanel);

    Scene scene = new Scene(borderPane, WINDOW_SIZE, WINDOW_SIZE);
    stage.setScene(scene);
  }
}
