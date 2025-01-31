package com.gruppe24.BoardGames.LadderGame;

public class LadderAction implements TileAction {

  //attributes
  private int destinationTileID;

  public LadderAction(int destinationTileID, String description){
    this.destinationTileID = destinationTileID;

  }

  //methods
  @Override
  public void perform(Player player) {

  }

}
