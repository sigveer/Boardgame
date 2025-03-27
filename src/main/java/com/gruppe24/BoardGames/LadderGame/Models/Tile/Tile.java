package com.gruppe24.BoardGames.LadderGame.Models.Tile;

import com.gruppe24.BoardGames.LadderGame.Models.Player;

/**
 * Abstract class representing a tile on the board.
 */
public abstract class Tile {
  private int position;
  public int checkTileType = 0;

  /**
   * Constructor that initializes the normal tile.
   *
   * @param position The position of the tile on the board.
   *
   * @Author Sigveer, Ingve
   * @Date: 16.02.2025
   * @Version: 1.0
   */
  public Tile(int position){
    this.position = position;
  }


  public void perform(Player player){
    checkTileType = 0;
  }
}

