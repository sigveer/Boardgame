package com.gruppe24.BoardGames.LadderGame;

import java.util.HashMap;
import java.util.Map;

public class Board {

  //Attributes
  Player player;
  private final HashMap<Integer,Integer> board = new HashMap<>(100);

  private final int boardSize = 100;
  private final HashMap<Integer, Integer> ladders;
  private final HashMap<Integer, Integer> snakes;

  public Board(){
    ladders = new HashMap<>();
    snakes = new HashMap<>();
    initializeLadders();
    initializeSnakes();
  }

  //methods
  private void initializeLadders(){
    ladders.put(1, 38);
    ladders.put(4, 14);
    ladders.put(21, 42);
    ladders.put(36, 44);
    ladders.put(80, 98);
  }

  private void initializeSnakes(){
    snakes.put(98, 80);
    snakes.put(44, 36);
    snakes.put(42, 21);
    snakes.put(14, 4);
    snakes.put(38, 1);
  }


  public HashMap<Integer, Integer> getBoard(){
    return board;
  }

}
