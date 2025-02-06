package com.gruppe24.BoardGames.LadderGame.Models;

import com.gruppe24.BoardGames.LadderGame.Tile;
import com.gruppe24.BoardGames.LadderGame.TileAction;

public class SnakeTile extends Tile implements TileAction {

  //attributes
  private final int slideDown;


  public SnakeTile(int position, int SlideDown){
    super(position);
    this.slideDown = SlideDown;
  }

  @Override
  public void perform(Player player){
    System.out.println(player.getName() + " Oh no a snake!! Slided down to tile " + slideDown);
    player.setPosition(slideDown);
  }

}
