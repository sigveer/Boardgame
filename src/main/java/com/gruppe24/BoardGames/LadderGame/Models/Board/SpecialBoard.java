package com.gruppe24.BoardGames.LadderGame.Models.Board;

import com.gruppe24.BoardGames.LadderGame.Models.Board.Tile.FrozenTile;
import com.gruppe24.BoardGames.LadderGame.Models.Board.Tile.RandomTeleportTile;

/**
 * Special board implementation with different ladder and snake configurations
 */
class SpecialBoard extends Board {

  public SpecialBoard() {
    super();
  }

  @Override
  public void initializeLadders() {

    super.initializeLadders();
  }

  @Override
  protected void initializeTiles() {
    super.initializeTiles();

    initializeSpecialTiles();
  }
}