package com.gruppe24.boardgames.laddergame.models.board.tiles;

import com.gruppe24.boardgames.laddergame.models.Player;

/**
 * Represents a frozen tile on the board.
 */
public class FrozenTile extends SpecialTile {

  @Override
  public void perform(Player player) {
    player.setFrozen(true);
  }

  @Override
  public int getTileType() {
    return 4;
  }

  /**
   * Constructor that initializes the normal tile.
   *
   * @param position The position of the tile on the board.
   */
  public FrozenTile(int position) {
    super(position);
  }
}
