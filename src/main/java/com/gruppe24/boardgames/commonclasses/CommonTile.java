package com.gruppe24.boardgames.commonclasses;

import com.gruppe24.boardgames.laddergame.models.Player;
import com.gruppe24.exeptions.InvalidPlayerException;

/**
 * Abstract model class representing a tile on the board.
 */
public abstract class CommonTile {

  protected int position;
  public int tileTypeNumber = 0;

  /**
   * Constructor that initializes the normal tile.
   *
   * @param position The position of the tile on the board.
   * @throws IllegalArgumentException if position is negative
   * @Author Sigveer, Ingve
   * @Date: 16.02.2025
   * @Version: 1.0
   */
  public CommonTile(int position) {
    if (position < 0) {
      throw new IllegalArgumentException("Parameter position cannot be less than 0");
    }
    this.position = position;
  }

  /**
   * Abstract getter for the tile type number.
   *
   * @return The tile type number.
   */
  public abstract int getTileType();

  /**
   * Performs the action associated with this tile for the given player.
   *
   * @param player The player who is currently on this tile.
   * @throws InvalidPlayerException if player is null
   */
  public void perform(Player player) {
    if (player == null) {
      throw new InvalidPlayerException();
    }
    tileTypeNumber = 0;
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
