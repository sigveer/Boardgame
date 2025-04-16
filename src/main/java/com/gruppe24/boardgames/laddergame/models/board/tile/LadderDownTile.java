package com.gruppe24.boardgames.laddergame.models.board.tile;

import com.gruppe24.boardgames.laddergame.models.Player;

/**
 * Class representing a tile that is a snake.
 */
public class LadderDownTile extends SpecialTile {

  /**
   * Constructor that initializes the snake tile.
   *
   * @param position The position of the tile.
   * @param slideDown The position to slide down to.
   */
  public LadderDownTile(int position, int slideDown) {
    super(position, slideDown);
  }

  /**
   * Method that performs the action of the tile.
   *
   * @param player The player that lands on the tile.
   */
  @Override
  public void perform(Player player) {
    player.setPosition(getDestination());
    checkTileType = 2;
  }
}
