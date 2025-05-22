package com.gruppe24.boardgames.laddergame.models.board.tiles;

/**
 * Class that represents a winning tile.
 */
public class WinningTile extends SpecialTile {

  @Override
  public int getTileType() {
    return -3;
  }

  /**
   * Constructor for WinningTile.
   *
   * @param position the position of the tile
   */
  public WinningTile(int position) {
    super(position);
  }

}
