package com.gruppe24.boardgames.commonclasses;

import com.gruppe24.exeptions.InvalidDiceValueException;
import java.util.Random;

/**
 * Concrete model class that represents a die. It provides methods to roll the die and get the last
 * rolled value.
 */
public class CommonDie {

  protected int lastRolledValue;
  protected Random random;
  protected final int minValue = 1;
  protected final int maxValue = 6;

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
   * @throws InvalidDiceValueException if the roll produces an invalid value (should not happen with
   *                                   proper implementation)
   */
  public int roll() {
    lastRolledValue = random.nextInt(6) + 1;

    return lastRolledValue;
  }

  /**
   * Returns the value of the last rolled die.
   *
   * @return The value of the last rolled die.
   * @throws InvalidDiceValueException if no roll has been performed yet
   */
  public int getLastRoll() {
    if (lastRolledValue == 0) {
      throw new InvalidDiceValueException("Dice has not been rolled yet");
    }
    return lastRolledValue;
  }
}
