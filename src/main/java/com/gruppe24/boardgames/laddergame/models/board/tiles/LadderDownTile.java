package com.gruppe24.boardgames.laddergame.models.board.tiles;

import com.gruppe24.boardgames.laddergame.models.Player;

/**
 * Class representing a tile that is a snake.
 */
public class LadderDownTile extends SpecialTile {

  @Override
  public void perform(Player player) {
    player.setPosition(getDestination());
  }

  @Override
  public int getTileType() {
    return 2;
  }

  /**
   * Constructor that initializes the snake tile.
   *
   * @param position  The position of the tile.
   * @param slideDown The position to slide down to.
   */
  public LadderDownTile(int position, int slideDown) {
    super(position, slideDown);
  }
}
