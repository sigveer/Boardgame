package com.gruppe24.BoardGames.LadderGame.Models.Board.Tile;


/**
 * Class representing a special tile.
 */
public class SpecialTile extends Tile {
  private int destination;

  public SpecialTile(int position){
    super(position);
  }
  public SpecialTile(int position, int destination) {
    super(position);
    if(destination < 0){
      throw new IllegalArgumentException("Parameter destination cannot be less than 0");
    }
    this.destination = destination;
  }

  public int getDestination() {
    return destination;
  }
}
