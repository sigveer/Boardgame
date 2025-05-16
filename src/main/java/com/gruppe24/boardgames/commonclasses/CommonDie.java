package com.gruppe24.boardgames.commonclasses;

import java.util.Random;

/**
 * CommonDie is a class that represents a die. It provides methods to roll the die and get the last
 * rolled value.
 */
public class CommonDie {

  protected int lastRolledValue;
  protected Random random;

  /**
   * Constructor for CommonDie.
   */
  public CommonDie() {
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
