package com.gruppe24.boardgames.laddergame.models.board.tiles;

import com.gruppe24.boardgames.commonclasses.Tile;

/**
 * Class representing a normal tile.
 */
public class NormalTile extends Tile {

  /**
   * Constructor that initializes the snake tile.
   *
   * @param position The position of the tile.
   */
  public NormalTile(int position) {
    super(position);
  }

  @Override
  public int getTileType() {
    return 0;
  }
}