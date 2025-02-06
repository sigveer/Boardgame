package com.gruppe24.BoardGames.LadderGame;

import com.gruppe24.BoardGames.LadderGame.Models.Player;

public abstract class Tile implements TileAction {

  protected int position;

  /**
   * FILLER
   * @param position
   */
  public Tile(int position) {
    this.position = position;
  }

  /**
   * Implementation from TileAction interface
   * @param player the relevant player
   */
  @Override
  public void perform(Player player){

  }
}
