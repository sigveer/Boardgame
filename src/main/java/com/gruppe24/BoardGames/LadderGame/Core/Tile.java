package com.gruppe24.BoardGames.LadderGame.Core;

/**
 * Abstract class representing a tile on the board.
 */
public abstract class Tile {
  protected int position;

  public Tile(int position) {
    this.position = position;
  }

  /**
   * Getter-method for position
   *
   * @return position-variable
   *
   * @Author Ingve
   * @Date: 06.02.2025
   * @Version: 1.0
   */
  public int getPosition() {
    return position;
  }
}
