package com.gruppe24.BoardGames.TicTacToe.Model;

public class GameLogic {
  private final Cell[][] board;
  private final int size;

  public GameLogic(Cell[][] board) {
    this.board = board;
    this.size = board.length;
  }

  public boolean isWinnerX() {
    return checkWinner(true);
  }

  public boolean isWinnerO() {
    return checkWinner(false);
  }

  private boolean checkWinner(boolean checkForX) {
    for (int row = 0; row < size; row++) {
      if (allMarkedSame(board[row][0], board[row][1], board[row][2], checkForX)) {
        return true;
      }
    }
    for (int col = 0; col < size; col++) {
      if (allMarkedSame(board[0][col], board[1][col], board[2][col], checkForX)) {
        return true;
      }
    }
    if (allMarkedSame(board[0][0], board[1][1], board[2][2], checkForX)) {
      return true;
    }
    return allMarkedSame(board[0][2], board[1][1], board[2][0], checkForX);
  }

  private boolean allMarkedSame(Cell a, Cell b, Cell c, boolean checkForX) {
    if (checkForX) {
      return a.isMarkedX() && b.isMarkedX() && c.isMarkedX();
    } else {
      return a.isMarkedO() && b.isMarkedO() && c.isMarkedO();
    }
  }

  public boolean hasEmptyCells() {
    for (Cell[] row : board) {
      for (Cell cell : row) {
        if (cell.isEmpty()) {
          return true;
        }
      }
    }
    return false;
  }
}