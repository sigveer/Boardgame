package com.gruppe24.BoardGames.TicTacToe.View;

import com.gruppe24.BoardGames.TicTacToe.Model.Cell;
import javafx.scene.Group;
import com.gruppe24.BoardGames.TicTacToe.Controller.GameController;

public class BoardView {

  private final GameController controller;
  private Group boardGroup;

  public BoardView(GameController controller) {
    this.controller = controller;
    this.boardGroup = buildBoard();
  }

  public Group getBoardGroup() {
    return boardGroup;
  }

  public void rebuildBoard() {
    this.boardGroup = buildBoard();
  }

  private Group buildBoard() {
    Group panel = new Group();
    Cell[][] board = controller.getBoard();
    int boardSize = controller.getBoardSize();
    int cellSize = controller.getCellSize();

    for (int row = 0; row < boardSize; row++) {
      for (int col = 0; col < boardSize; col++) {
        Cell cell = board[row][col];
        UIFactory.configureCellUI(cell, col, row, cellSize);
        panel.getChildren().add(cell);
        }
      }
    return panel;
  }
}

