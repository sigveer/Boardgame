package com.gruppe24.BoardGames.LadderGame;

import com.gruppe24.BoardGames.LadderGame.Models.Player;

public abstract class Tile implements TileAction {

  protected int position;

  public Tile(int position) {
    this.position = position;
  }

  @Override
  public void perform(Player player){

  }
}
