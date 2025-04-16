package com.gruppe24.boardgames.laddergame.models.board.tiles;

import com.gruppe24.boardgames.laddergame.models.Player;

/**
 * Class representing a tile that is a ladder.
 */
public class LadderUpTile extends SpecialTile {

  /**
   * Constructor that initializes the ladder tile.
   *
   * @param position The position of the tile.
   * @param climbUp The position to climb up to.
   */
  public LadderUpTile(int position, int climbUp) {
    super(position, climbUp);
  }

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
    checkTileType = 1;
  }

}
