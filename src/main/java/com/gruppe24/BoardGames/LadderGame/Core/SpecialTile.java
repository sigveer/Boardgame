package com.gruppe24.BoardGames.LadderGame.Core;

/**
 * Class representing a special tile.
 */
public abstract class SpecialTile extends Tile implements TileAction {

  /**
   * Constructor that initializes the special tile.
   *
   * @param position The position of the tile.
   *
   * @Author Sigveer
   * @Date: 07.02.2025
   * @Version: 1.0
   */
  public SpecialTile(int position) {
    super(position);
  }
}
