package com.gruppe24.boardgames.laddergame.models.board.tiles;

import com.gruppe24.boardgames.commonclasses.CommonTile;

/**
 * Class representing a normal tile.
 */
public class NormalTile extends CommonTile {

  @Override
  public int getTileType() {
    return 0;
  }

  /**
   * Constructor that initializes the snake tile.
   *
   * @param position The position of the tile.
   */
  public NormalTile(int position) {
    super(position);
  }
}