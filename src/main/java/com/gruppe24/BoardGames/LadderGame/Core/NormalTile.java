package com.gruppe24.BoardGames.LadderGame.Core;

/**
 * Abstract class representing a tile on the board.
 */
public abstract class NormalTile implements TileAction {

  private int position;

  public NormalTile(int position){
    this.position = position;
  }
}

