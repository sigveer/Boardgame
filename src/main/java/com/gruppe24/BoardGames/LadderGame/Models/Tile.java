package com.gruppe24.BoardGames.LadderGame.Models;

import com.gruppe24.BoardGames.LadderGame.Core.NormalTile;

/**
 * Class representing a normal tile.
 */
public class Tile extends NormalTile {

  //Implemented from TileAction: perform()
  //Extended from NormalTile: position

  /**
   * Constructor that initializes the snake tile.
   *
   * @param position The position of the tile.
   *
   * @Author Sigveer
   * @Date: 06.02.2025
   * @Version: 1.0
   */
  public Tile(int position) {
    super(position);
  }

  @Override
  public void perform(Player player) {
    //Filler for interface
  }

}
