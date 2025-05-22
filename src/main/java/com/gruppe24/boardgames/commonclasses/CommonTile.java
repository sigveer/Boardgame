package com.gruppe24.boardgames.commonclasses;

import com.google.gson.JsonObject;
import com.gruppe24.boardgames.laddergame.models.Player;
import com.gruppe24.exeptions.InvalidPlayerException;
import com.gruppe24.filehandling.TileJsonSerializer;

/**
 * Abstract model class representing a tile on the board.
 */
public abstract class CommonTile implements TileJsonSerializer {

  protected int position;
  public int tileTypeNumber = 0;


  @Override
  public void addActionToJson(JsonObject tileJson, int tileId) {
  }

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
