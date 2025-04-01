package com.gruppe24.BoardGames.LadderGame.Models.Tile;


/**
 * Class representing a special tile.
 */
public class SpecialTile extends Tile {
  private int destination;

  /**
   * Constructor that initializes the special tile.
   *
   * @param position The position of the tile.
   *
   * @Author Sigveer, Ingve
   * @Date: 16.02.2025
   * @Version: 1.0
   */
  public SpecialTile(int position, int destination) {
    super(position);
    this.destination = destination;
  }

  public int getDestination() {
    return destination;
  }
}
