package com.gruppe24.boardgames.commonclasses;

import com.gruppe24.exeptions.InvalidDiceValueException;
import java.util.ArrayList;
import java.util.List;


/**
 * Abstract class representing a set of dice. This class provides methods to roll the dice and get
 * the sum of their values.
 */
public class CommonDice {

  protected final List<CommonDie> dice;
  protected int sum = 0;

  /**
   * The value of the last rolled die.
   *
   * @param numberOfDice The number of dice to be created.
   * @throws InvalidDiceValueException if number of dice is less than 1
   */
  public CommonDice(int numberOfDice) {
    if (numberOfDice < 1) {
      throw new InvalidDiceValueException("Number of dice must be at least 1 Dice");
    }
    dice = new ArrayList<>();
    for (int i = 0; i < numberOfDice; i++) {
      dice.add(new CommonDie());
    }
  }

  /**
   * Rolls all dice and returns the sum of the values.
   *
   * @return The sum of the values of all dice after rolling.
   */
  public int rollSum() {
    sum = 0;
    for (CommonDie die : dice) {
      sum += die.roll();
    }
    return sum;
  }

  /**
   * Gets the value of a specific die.
   *
   * @param dieNumber The index of the die to get the value of.
   * @return The value of the specified die.
   * @throws InvalidDiceValueException if dieNumber is out of bounds
   */
  public int getDie(int dieNumber) {
    if (dieNumber < 0 || dieNumber >= dice.size()) {
      throw new InvalidDiceValueException("Die number out of bounds");
    }
    return dice.get(dieNumber).getLastRoll();
  }

  /**
   * Returns the path to the dice image.
   *
   * @param dice the number on the dice
   * @return the path to the dice image
   * @throws InvalidDiceValueException if dice value is not between 1 and 6
   */
  public String dicePath(int dice) {
    if (dice < 1 || dice > 6) {
      throw new InvalidDiceValueException("Invalid dice value for image path: " + dice);
    }
    return "pictures/dices/dice" + dice + ".png";
  }
}
