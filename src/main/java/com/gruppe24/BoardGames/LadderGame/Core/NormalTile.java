package com.gruppe24.BoardGames.LadderGame.Core;

/**
 * Abstract class representing a tile on the board.
 */
public abstract class NormalTile implements TileAction {

  // extended from Tile-superclass: Tile()
  // implemented from TileAction-interface: perform()

  private int position;

  /**
   * Constructor that initializes the normal tile.
   *
   * @param position
   *
   * @Author Sigveer, Ingve
   * @Date: 16.02.2025
   * @Version: 1.0
   */
  public NormalTile(int position){
    this.position = position;
  }

  public int getPosition() {
    return position;
  }
}

