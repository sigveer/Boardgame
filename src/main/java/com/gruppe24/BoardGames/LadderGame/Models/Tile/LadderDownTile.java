package com.gruppe24.BoardGames.LadderGame.Models.Tile;

import com.gruppe24.BoardGames.LadderGame.Models.Player;

/**
 * Class representing a tile that is a snake.
 */
public class LadderDownTile extends SpecialTile {
  private final int slideDown;

  /**
   * Constructor that initializes the snake tile.
   *
   * @param position The position of the tile.
   * @param SlideDown The position to slide down to.
   *
   * @Author Sigveer
   * @Date: 06.02.2025
   * @Version: 1.0
   */
  public LadderDownTile(int position, int SlideDown){
    super(position, SlideDown);
    this.slideDown = SlideDown;
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
    checkTileType = 2;
  }
}
