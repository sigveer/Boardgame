package com.gruppe24.boardgames.laddergame.models;

import java.util.Random;

/**
 * Class representing a die.
 */
public class Die { //kan abstraktiseres

  private int lastRolledValue;
  private final Random random;

  /**
   * Constructor that initializes the die.
   */
  public Die() {
    this.random = new Random();
    this.lastRolledValue = 0;
  }

  /**
   * Rolls the die and returns the value.
   *
   * @return The value of the die after rolling.
   */
  public int roll() {
    lastRolledValue = random.nextInt(6) + 1;
    return lastRolledValue;
  }

  /**
   * Returns the value of the last rolled die.
   *
   * @return The value of the last rolled die.
   */
  public int getLastRoll() {
    return lastRolledValue;
  }

}
