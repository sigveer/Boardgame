package com.gruppe24.BoardGames.LadderGame.Models;

import com.gruppe24.BoardGames.LadderGame.Models.Tile.LadderTile;
import com.gruppe24.BoardGames.LadderGame.Models.Tile.NormalTile;
import com.gruppe24.BoardGames.LadderGame.Models.Tile.SnakeTile;
import com.gruppe24.BoardGames.LadderGame.Models.Tile.TileAction;
import java.util.HashMap;

/**
 * Represets Snakes and Ladder board.
 *
 * @author Ingve, Sigveer.
 * @date 06.02.2025
 * @version 1.0.0
 */
public class Board {

  //Attributes
  private final HashMap<Integer, Integer> ladders = new HashMap<>();
  private final HashMap<Integer, Integer> snakes = new HashMap<>();
  private static final int Columns = 9;
  private static final int Rows = 10;
  private TileAction[] tiles;

  /**
   * Constructor that initializes the ladders and snakes.
   *
   * @author Ingve, Sigveer
   * @date 06.02.2025
   * @Version 1.0
   */
  public Board(){
    initializeLaddersAndSnake();
    initializeTiles();
  }

  //methods

  /**
   * Method that puts ladders at certain indexes in ladders-hashMap.
   *
   * @author Ingve, Sigveer
   * @date 06.02.2025
   * @Version 1.0
   */
  public void initializeLaddersAndSnake(){
    ladders.put(1, 40);
    ladders.put(8, 10);
    ladders.put(36, 52);
    ladders.put(43, 62);
    ladders.put(49, 79);
    ladders.put(65, 82);
    ladders.put(68, 85);

    snakes.put(24, 5);
    snakes.put(33, 3);
    snakes.put(42, 30);
    snakes.put(56, 37);
    snakes.put(64, 27);
    snakes.put(74, 12);
    snakes.put(87, 70);
  }


  /**
   * Method that initializes the tiles.
   *
   * @Author Sigveer, Ingve
   * @Date: 19.02.2025
   * @Version: 1.0
   */
  private void initializeTiles() {
    tiles = new TileAction[Columns * Rows];
    for (int i = 0; i < Columns * Rows; i++) {
      if (ladders.containsKey(i)) {
        tiles[i] = new LadderTile(i, ladders.get(i));
      } else if (snakes.containsKey(i)) {
        tiles[i] = new SnakeTile(i, snakes.get(i));
      } else {
        tiles[i] = new NormalTile(i);
      }
    }
  }


/**
   * Method that gets the tile.
   *
   * @param position the position of the tile
   * @return the tile
   *
   * @Author Sigveer, Ingve
   * @Date: 20.02.2025
   * @Version: 1.0
   */
  public TileAction getTile(int position) {
    if (position >= 0 && position < tiles.length) {
      return tiles[position];
    }
    return new NormalTile(position);
  }
}
