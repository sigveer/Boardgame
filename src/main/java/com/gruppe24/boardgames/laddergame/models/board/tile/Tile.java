package com.gruppe24.boardgames.laddergame.models.board.tile;

import com.gruppe24.boardgames.laddergame.models.Player;

/**
 * Abstract class representing a tile on the board.
 */
public abstract class Tile {

  protected int position;
  public int checkTileType = 0;

  /**
   * Constructor that initializes the normal tile.
   *
   * @param position The position of the tile on the board.
   *
   * @Author Sigveer, Ingve
   * @Date: 16.02.2025
   * @Version: 1.0
   */
  public Tile(int position) {
    if (position < 0) {
      throw new IllegalArgumentException("Parameter position cannot be less than 0");
    }
    this.position = position;
  }

  /**
   * Performs the action associated with this tile for the given player.
   *
   * @param player The player who is currently on this tile.
   */
  public void perform(Player player) {
    checkTileType = 0;
  }

  /**
   * Getter for the position of the tile.
   *
   * @return The position of the tile on the board.
   */
  public int getPosition() {
    return position;
  }
}

