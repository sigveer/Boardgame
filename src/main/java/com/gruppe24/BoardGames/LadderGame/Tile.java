package com.gruppe24.BoardGames.LadderGame;

import com.gruppe24.BoardGames.LadderGame.Models.Player;

public abstract class Tile {
  protected int position;

  public Tile(int position) {
    this.position = position;
  }

  public int getPosition() {
    return position;
  }

  public abstract void perform(Player player);
}
