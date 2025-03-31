package com.gruppe24.BoardGames.LadderGame.Controller;

import static org.junit.jupiter.api.Assertions.*;

import com.gruppe24.BoardGames.LadderGame.Models.Board;
import com.gruppe24.BoardGames.LadderGame.Models.Player;
import com.gruppe24.BoardGames.LadderGame.Models.Tile.LadderUpTile;
import com.gruppe24.BoardGames.LadderGame.Models.Tile.SnakeDownTile;
import com.gruppe24.BoardGames.LadderGame.Models.Tile.Tile;
import org.junit.jupiter.api.Test;


/**
 * {@code BoardTest} is a test class for the {@code Board} class.
 */
class BoardTest {

  private Board board;
  private Player testPlayer;

  /**
   * Tests the {@code getTile} method on a normal tile in the {@code Board} class.
   */
  @Test
  void getNormalTile() {
    TileAction tile = board.getTile(2);
    assertInstanceOf(Tile.class, tile);
  }


  /**
   * Tests the {@code getTile} method on a ladder tile in the {@code Board} class.
   */
  @Test
  void getLadderTile() {
    TileAction tile = board.getTile(1);
    assertInstanceOf(LadderUpTile.class, tile);
  }


  /**
   * Tests the {@code getTile} method on a snake tile in the {@code Board} class.
   */
  @Test
  void getSnakeTile() {
    TileAction tile = board.getTile(24);
    assertInstanceOf(SnakeDownTile.class, tile);
  }


}