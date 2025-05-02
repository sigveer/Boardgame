package com.gruppe24.boardgames.commonclasses;

import java.util.Random;

public abstract class AbstractDie {

  protected int lastRolledValue;
  protected Random random;


  public AbstractDie() {
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
