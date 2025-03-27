package com.gruppe24.BoardGames.LadderGame.Models.Tile;

import com.gruppe24.BoardGames.LadderGame.Models.Player;

/**
 * Class representing a tile that is a ladder.
 */
public class LadderUpTile extends SpecialTile {

  //attributes
  private final int climbUp;

  //constructor
  /**
   * Constructor that initializes the ladder tile.
   *
   * @param position The position of the tile.
   * @param climbUp The position to climb up to.
   *
   * @Author Sigveer
   * @Date: 06.02.2025
   * @Version: 1.0
   */
  public LadderUpTile(int position, int climbUp){
    super(position, climbUp);
    this.climbUp = climbUp;
  }

  //methods

  /**
   * Method that performs the action of the tile.
   *
   * @param player The player that lands on the tile.
   *
   * @Author Sigveer
   * @Date: 06.02.2025
   * @Version: 1.0
   */
  @Override
  public void perform(Player player) {
    player.setPosition(getDestination());
  }

}
