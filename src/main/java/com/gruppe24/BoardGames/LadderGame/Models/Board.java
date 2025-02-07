package com.gruppe24.BoardGames.LadderGame.Models;

import com.gruppe24.BoardGames.LadderGame.Tile;
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
  Player player;
  private final HashMap<Integer,Integer> board = new HashMap<>(100);
  private final HashMap<Integer, Integer> ladders;
  private final HashMap<Integer, Integer> snakes;

  /**
   * Constructor that initializes the ladders and snakes.
   *
   * @author Ingve, Sigveer
   * @version 1.0.0
   * @date 06.02.2025
   */
  public Board(){
    ladders = new HashMap<>();
    snakes = new HashMap<>();
    initializeLadders();
    initializeSnakes();
  }

  //methods

  /**
   * Method that puts ladders at certain indexes in ladders-hashMap.
   *
   * @author Ingve, Sigveer
   * @version 1.0.0
   * @date 06.02.2025
   */
  public void initializeLadders(){
    ladders.put(2, 39);
    ladders.put(3, 11);
    ladders.put(21, 49);
    ladders.put(30, 60);
    ladders.put(83, 85);
  }

  /**
   * Method that puts snakes at certain indexes in ladders-hashMap.
   *
   * @author Ingve, Sigveer
   * @version 1.0.0
   * @date 06.02.2025
   */
  public void initializeSnakes(){
    snakes.put(98, 80);
    snakes.put(44, 36);
    snakes.put(42, 21);
    snakes.put(14, 4);
    snakes.put(38, 1);
  }

  /**
   * Method that returns the tile at a certain position.
   *
   * @param position of the tile
   * @return the tile at the position
   *
   * @author Ingve
   * @Version 1.0.0
   * @date 06.02.2025
   */
  public Tile getTile(int position) {
    if (ladders.containsKey(position)) {
      return new LadderTile(position, ladders.get(position));
    } else if (snakes.containsKey(position)) {
      return new SnakeTile(position, snakes.get(position));
    }
    return null;
  }

  //accessor-methods
  /**
   * Method that returns the board.
   * @return attribute board
   *
   * @author Ingve
   * @date 06.02.2025
   * @version 1.0.0
   *
   */
  public HashMap<Integer, Integer> getBoard(){
    return board;
  }


}
