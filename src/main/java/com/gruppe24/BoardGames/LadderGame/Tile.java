package com.gruppe24.BoardGames.LadderGame;

import com.gruppe24.BoardGames.LadderGame.Models.Player;

/**
 * Abstract class representing a tile on the board.
 */
public abstract class Tile implements TileAction {

  protected int position;

  public Tile(int position) {
    this.position = position;
  }

  /**
   * Overridden method that activates the action of the tile.
   *
   * @param player the relevant player
   *
   * @Author Sigveer
   * @Date: 06.02.2025
   * @Version: 1.0
   */
  @Override
  public void perform(Player player){

  }
}
