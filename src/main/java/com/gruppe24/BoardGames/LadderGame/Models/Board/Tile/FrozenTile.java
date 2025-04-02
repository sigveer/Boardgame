package com.gruppe24.BoardGames.LadderGame.Models.Board.Tile;

import com.gruppe24.BoardGames.LadderGame.Models.Player;

public class FrozenTile extends SpecialTile{

  /**
   * Constructor that initializes the normal tile.
   *
   * @param position The position of the tile on the board.
   * @Author Sigveer, Ingve
   * @Date: 16.02.2025
   * @Version: 1.0
   */
  public FrozenTile(int position) {
    super(position);
  }

  @Override
  public void perform(Player player) {
    player.setFrozen(true);
    checkTileType = 4;
  }
}
