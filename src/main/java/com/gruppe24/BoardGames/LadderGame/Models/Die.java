package com.gruppe24.BoardGames.LadderGame;

import java.util.Random;


public class Die {


  //attributes
  /**
   * Edited by: Sigveer
   * Version: 1.0
   * Date: 04.02.2025
   * The value of the last rolled die.
   */
  private int lastRolledValue;
  private final Random random;


  /**
   * Edited by: Sigveer
   * Version: 1.0
   * Date: 04.02.2025
   * Constructor for the Die class.
   */
  public Die(){
    this.random = new Random();
    this.lastRolledValue = 0;

  }

  //methods
  /**
   * Edited by: Sigveer
   * Version: 1.0
   * Date: 04.02.2025
   * Rolls the die and returns the value.
   *
   * @return The value of the die after rolling.
   */
  public int roll(){
    lastRolledValue = random.nextInt(6) + 1;
    return lastRolledValue;
  }


  /**
   * Edited by: Sigveer
   * Version: 1.0
   * Date: 04.02.2025
   *
   * @return The value of the last rolled die.
   */
  public int getValue(){
    return lastRolledValue;
  }

}
