package com.gruppe24.BoardGames.LadderGame.Models.Board;

import com.gruppe24.BoardGames.LadderGame.Models.Board.Tile.RandomTeleportTile;

/**
 * Special board implementation with different ladder and snake configurations
 */
class SpecialBoard extends Board {

  public SpecialBoard() {
    super();
  }

  @Override
  public void initializeLaddersAndSnake() {
    ladderUp.put(1, 40);
    ladderUp.put(8, 10);
    ladderUp.put(36, 52);
    ladderUp.put(43, 62);
    ladderUp.put(49, 79);
    ladderUp.put(65, 82);
    ladderUp.put(68, 85);

    ladderDown.put(24, 5);
    ladderDown.put(33, 3);
    ladderDown.put(42, 30);
    ladderDown.put(56, 37);
    ladderDown.put(64, 27);
    ladderDown.put(74, 12);
    ladderDown.put(87, 70);
  }

  @Override
  protected void initializeTiles() {
    super.initializeTiles();

    tiles[50] = new RandomTeleportTile(50);
  }
}