package com.gruppe24.BoardGames.LadderGame;

public class Tile {

  //attributes
  private Tile nextTile;
  private int tileID;
  private TileAction landAction;

  public Tile(int tileID){
    this.tileID = tileID;
  }

  //methods
  public void landPlayer(Player player){
  }

  public void leavePlayer(Player player){
  }

  public void setNextTile(Tile nextTile){
    this.nextTile = nextTile;
  }

}
