package com.gruppe24.boardgames.laddergame.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing a set of dice.
 */
public class Dice {

  private final List<Die> dice;
  private int sum = 0;

  /**
   * The value of the last rolled die.
   *
   * @param numberOfDice The number of dice to be created.
   */
  public Dice(int numberOfDice) {
    if (numberOfDice < 1) {
      throw new IllegalArgumentException("Number of dice must be at least 1 Dice");
    }
    dice = new ArrayList<>();
    for (int i = 0; i < numberOfDice; i++) {
      dice.add(new Die());
    }
  }

  /**
   * Rolls all dice and returns the sum of the values.
   *
   * @return The sum of the values of all dice after rolling.
   */
  public int rollSum() {
    sum = 0;
    for (Die die : dice) {
      sum += die.roll();
    }
    return sum;
  }

  /**
   * Rolls all dice and returns the values of each die.
   *
   * @return The values of all dice after rolling.
   */
  public int getDie(int dieNumber) {
    if (dieNumber < 0 || dieNumber >= dice.size()) {
      throw new IllegalArgumentException("Die number out of bounds");
    }
    return dice.get(dieNumber).getLastRoll();
  }

  public int getSum() {
    return this.sum;
  }
}
