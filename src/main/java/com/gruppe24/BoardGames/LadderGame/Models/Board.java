package com.gruppe24.BoardGames.LadderGame.Models;

import com.gruppe24.BoardGames.LadderGame.Tile;
import java.util.HashMap;

public class Board {

  //Attributes
  Player player;
  private final HashMap<Integer,Integer> board = new HashMap<>(100);
  private final HashMap<Integer, Integer> ladders;
  private final HashMap<Integer, Integer> snakes;

  public Board(){
    ladders = new HashMap<>();
    snakes = new HashMap<>();
    initializeLadders();
    initializeSnakes();
  }

  //methods
  public void initializeLadders(){
    ladders.put(2, 39);
    ladders.put(3, 11);
    ladders.put(21, 49);
    ladders.put(30, 60);
    ladders.put(83, 85);
  }

  public void initializeSnakes(){
    snakes.put(98, 80);
    snakes.put(44, 36);
    snakes.put(42, 21);
    snakes.put(14, 4);
    snakes.put(38, 1);
  }

  public Tile getTile(int position) {
    if (ladders.containsKey(position)) {
      return new LadderTile(position, ladders.get(position));
    } else if (snakes.containsKey(position)) {
      return new SnakeTile(position, snakes.get(position));
    }
    return null;
  }

  //accessor-methods
  public HashMap<Integer, Integer> getBoard(){
    return board;
  }


}
