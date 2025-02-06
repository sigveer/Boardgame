package com.gruppe24.BoardGames.LadderGame.Models;

import com.gruppe24.BoardGames.LadderGame.Tile;
import com.gruppe24.BoardGames.LadderGame.TileAction;

/**
 * Class representing a tile that is a snake.
 */
public class SnakeTile extends Tile implements TileAction {

  //attributes
  private final int slideDown;

  //constructor

  /**
   * @Author Sigve
   * Version: 1.0
   * Date: 06.02.2025
   * Creates a new snake tile.
   *
   * @param position The position of the tile.
   * @param SlideDown The position to slide down to.
   */
  public SnakeTile(int position, int SlideDown){
    super(position);
    this.slideDown = SlideDown;
  }


  //methods

  /**
   * @Author Sigve
   * Version: 1.0
   * Date: 06.02.2025
   * Moves the player to the bottom of the snake.
   *
   * @param player The player to move.
   */
  @Override
  public void perform(Player player){
    System.out.println(player.getName() + " Oh no a snake!! Slided down to tile " + slideDown);
    player.setPosition(slideDown);
  }

}
