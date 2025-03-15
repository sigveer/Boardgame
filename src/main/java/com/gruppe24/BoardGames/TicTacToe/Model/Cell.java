package com.gruppe24.BoardGames.TicTacToe.Model;

import javafx.scene.shape.Rectangle;

public class Cell extends Rectangle {
  private boolean isX = false;
  private boolean isO = false;

  public Cell(boolean isX, boolean isO) {
    this.isX = isX;
    this.isO = isO;
  }

  public void mark(boolean asX) {
    this.isX = asX;
    this.isO = !asX;
  }

  public boolean isMarkedX() {
    return this.isX;
  }

  public boolean isMarkedO() {
    return this.isO;
  }

  public boolean isEmpty() {
    return !this.isX && !this.isO;
  }
}