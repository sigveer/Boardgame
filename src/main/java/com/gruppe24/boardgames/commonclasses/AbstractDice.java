package com.gruppe24.boardgames.commonclasses;

import com.gruppe24.boardgames.laddergame.models.Die;
import java.util.ArrayList;
import java.util.List;


/**
 * Abstract class representing a set of dice. This class provides methods to roll the dice and get
 * the sum of their values.
 */
public abstract class AbstractDice {

  protected final List<AbstractDie> dice;
  protected int sum = 0;

  /**
   * The value of the last rolled die.
   *
   * @param numberOfDice The number of dice to be created.
   */
  public AbstractDice(int numberOfDice) {
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
    for (AbstractDie die : dice) {
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

  /**
   * Returns the path to the dice image.
   *
   * @param dice the number on the dice
   * @return the path to the dice image
   */
  public String dicePath(int dice) {
    return "pictures/dices/dice" + dice + ".png";
  }

  /**
   * Rolls a single die (defaults to first die).
   *
   * @return The value of the rolled die
   */
  public int roll() {
    return dice.getFirst().roll();
  }

  public int getSum() {
    return this.sum;
  }

}
