package com.gruppe24.BoardGames.LadderGame.Models;

import java.util.Random;

/**
 * Class representing a die.
 */
public class Die {


  //attributes
  private int lastRolledValue;
  private final Random random;


  /**
   * Constructor that initializes the die.
   *
   * @Author Sigveer
   * @Date: 04.02.2025
   * @Version: 1.0
   */
  public Die(){
    this.random = new Random();
    this.lastRolledValue = 0;

  }

  //methods
  /**
   * Rolls the die and returns the value.
   *
   * @return The value of the die after rolling.
   *
   * @Author Sigveer
   * @Date: 04.02.2025
   * @Version: 1.0
   */
  public int roll(){
    lastRolledValue = random.nextInt(6) + 1;
    return lastRolledValue;
  }


  /**
   * Returns the value of the last rolled die.
   *
   * @return The value of the last rolled die.
   *
   * @Author Sigveer
   * @Date: 04.02.2025
   * @Version: 1.0
   */
  public int getValue(){
    return lastRolledValue;
  }

}
