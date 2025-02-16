package com.gruppe24.BoardGames.LadderGame.Models;

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

  /**
   * Constructor that initializes the ladders and snakes.
   *
   * @author Ingve, Sigveer
   * @date 06.02.2025
   * @Version 1.0
   */
  public Board(){
    initializeLadders();
    initializeSnakes();
  }

  //methods

  /**
   * Method that puts ladders at certain indexes in ladders-hashMap.
   *
   * @author Ingve, Sigveer
   * @date 06.02.2025
   * @Version 1.0
   */
  public void initializeLadders(){
    ladders.put(2, 39);
    ladders.put(5, 25);
    ladders.put(12, 28);
    ladders.put(22, 77);
    ladders.put(35, 55);
    ladders.put(45, 90);
    ladders.put(50, 70);
    ladders.put(65, 85);
  }

  /**
   * Method that puts snakes at certain indexes in ladders-hashMap.
   *
   * @author Ingve, Sigveer
   * @date 06.02.2025
   * @Version 1.0
   */
  public void initializeSnakes(){
    snakes.put(16, 6);
    snakes.put(33, 20);
    snakes.put(42, 21);
    snakes.put(47, 30);
    snakes.put(60, 10);
    snakes.put(68, 52);
    snakes.put(75, 25);
    snakes.put(88, 72);
    snakes.put(99, 40);
  }

  //--accessor-methods
  public HashMap<Integer, Integer> getLadder(){
    return this.ladders;
  }

  public HashMap<Integer,Integer> getSnakes(){
    return this.snakes;
  }

}
