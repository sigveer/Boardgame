package com.gruppe24.boardgames.laddergame.models.board.tile;

import com.gruppe24.boardgames.laddergame.models.Player;

/**
 * Represents a frozen tile on the board.
 *
 */
public class FrozenTile extends SpecialTile {

  /**
   * Constructor that initializes the normal tile.
   *
   * @param position The position of the tile on the board.
   */
  public FrozenTile(int position) {
    super(position);
  }

  /**
   * Method that performs the action of the tile.
   *
   * @param player The player that lands on the tile.
   */
  @Override
  public void perform(Player player) {
    player.setFrozen(true);
    checkTileType = 4;
  }
}
