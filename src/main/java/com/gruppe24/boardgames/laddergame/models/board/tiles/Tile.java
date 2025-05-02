package com.gruppe24.boardgames.laddergame.models.board.tiles;

import com.google.gson.JsonObject;
import com.gruppe24.boardgames.laddergame.models.Player;
import com.gruppe24.filehandling.TileJsonSerializer;

/**
 * Abstract class representing a tile on the board.
 */
public abstract class Tile implements TileJsonSerializer {

  protected int position;
  public int tileTypeNumber = 0;

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
    if (player == null) {
      throw new IllegalArgumentException("Player on tile cannot be empty");
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

  /**
   * Getter for the tile type number.
   *
   * @return The tile type number.
   */
  public abstract int getTileType();


  @Override
  public void addActionToJson(JsonObject tileJson, int tileId) {
  }
}

