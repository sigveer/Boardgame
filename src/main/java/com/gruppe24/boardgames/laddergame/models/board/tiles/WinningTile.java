package com.gruppe24.boardgames.laddergame.models.board.tiles;

//burde implementeres
public class WinningTile extends SpecialTile {


  public WinningTile(int position) {
    super(position);
  }

  @Override
  public int getTileType() {
    return -3;
  }
}
