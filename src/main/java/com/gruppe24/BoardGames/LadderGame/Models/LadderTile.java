package com.gruppe24.BoardGames.LadderGame.Models;

import com.gruppe24.BoardGames.LadderGame.Tile;
import com.gruppe24.BoardGames.LadderGame.TileAction;

public class LadderTile extends Tile implements TileAction {

  //attributes
  private final int climbUp;


  public LadderTile(int position, int climbUp){
    super(position);
    this.climbUp = climbUp;
  }

  @Override
  public void perform(Player player){
    System.out.println(player.getName() + " Oh a ladder!! Climbed up to tile " + climbUp);
    player.setPosition(climbUp);
  }

}
