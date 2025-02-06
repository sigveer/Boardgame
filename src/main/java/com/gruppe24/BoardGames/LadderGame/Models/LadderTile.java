package com.gruppe24.BoardGames.LadderGame.Models;

import com.gruppe24.BoardGames.LadderGame.Tile;
import com.gruppe24.BoardGames.LadderGame.TileAction;


/**
 * Class representing a tile that is a ladder.
 */
public class LadderTile extends Tile implements TileAction {

  //attributes
  private final int climbUp;

  //constructor

  /**
   * @Author Sigve
   * Version: 1.0
   * Date: 06.02.2025
   * Creates a new ladder tile.
   *
   * @param position The position of the tile.
   * @param climbUp The position to climb up to.
   */
  public LadderTile(int position, int climbUp){
    super(position);
    this.climbUp = climbUp;
  }

  //methods

  /**
   * @Author Sigve
   * Version: 1.0
   * Date: 06.02.2025
   * Moves the player to the top of the ladder.
   *
   * @param player The player to move.
   */
  @Override
  public void perform(Player player){
    System.out.println(player.getName() + " Oh a ladder!! Climbed up to tile " + climbUp);
    player.setPosition(climbUp);
  }

}
