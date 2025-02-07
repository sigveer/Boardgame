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
   * Constructor that initializes the ladder tile.
   *
   * @param position The position of the tile.
   * @param climbUp The position to climb up to.
   *
   * @Author Sigveer
   * @Date: 06.02.2025
   * @Version: 1.0
   */
  public LadderTile(int position, int climbUp){
    super(position);
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
  public void perform(Player player){
    System.out.println(player.getName() + " Oh a ladder!! Climbed up to tile " + climbUp);
    player.setPosition(climbUp);
  }

}
