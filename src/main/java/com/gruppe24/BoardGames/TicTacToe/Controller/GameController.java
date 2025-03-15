package com.gruppe24.BoardGames.TicTacToe.Controller;

import com.gruppe24.BoardGames.TicTacToe.Model.Cell;
import com.gruppe24.BoardGames.TicTacToe.Model.GameLogic;
import javafx.stage.Stage;


/**
 * The {@code GameController} class is the controller for the Tic Tac Toe game.
 */
public class GameController {
  private static final int BOARD_SIZE = 3;
  private static final int CELL_SIZE = 50;

  /**
   * The startGame method starts the Tic Tac Toe game.
   *
   * @param stage the stage for the game
   * @param title the title of the game
   */
  public void startGame(Stage stage, String title) {
    stage.setTitle(title);
    stage.setResizable(false);
    stage.show();
  }


}
