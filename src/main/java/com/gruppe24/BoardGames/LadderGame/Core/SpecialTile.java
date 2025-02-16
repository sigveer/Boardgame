package com.gruppe24.BoardGames.LadderGame.Core;


/**
 * Class representing a special tile.
 */
public abstract class SpecialTile implements TileAction {

  // extended from Tile-superclass: Tile()
  // implemented from TileAction-interface: perform()

  private int position;

  /**
   * Constructor that initializes the special tile.
   *
   * @param position The position of the tile.
   *
   * @Author Sigveer, Ingve
   * @Date: 16.02.2025
   * @Version: 1.0
   */
  public SpecialTile(int position) {
    this.position = position;
  }

}
