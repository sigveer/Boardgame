package com.gruppe24.boardgames.laddergame.models.board.tiles;


/**
 * Class representing a special tile.
 */
public class SpecialTile extends Tile {

  private int destination;

  /**
   * Constructor that initializes the special tile.
   *
   * @param position The position of the tile.
   */
  public SpecialTile(int position) {
    super(position);
  }

  /**
   * Constructor that initializes the special tile with a destination.
   *
   * @param position The position of the tile.
   * @param destination The destination of the tile.
   */
  public SpecialTile(int position, int destination) {
    super(position);
    if (destination < 0) {
      throw new IllegalArgumentException("Parameter destination cannot be less than 0");
    }
    this.destination = destination;
  }

  /**
   * Getter for the destination of the tile.
   *
   * @return The destination of the tile.
   */
  public int getDestination() {
    return destination;
  }
}
