package com.gruppe24.BoardGames.LadderGame;

import java.util.ArrayList;
import java.util.List;

public class Dice {

  //attributes
  /**
   * @Author Sigveer
   * Version: 1.0
   * Date: 04.02.2025
   * List of dice objects.
   */
  private final List<Die> dice;


  /**
   * @Author Sigveer
   * Version: 1.0
   * Date: 04.02.2025
   * The value of the last rolled die.
   *
   * @param numberOfDice The number of dice to be created.
   */
  public Dice(int numberOfDice){
    if (numberOfDice < 1) {
      throw new IllegalArgumentException("Number of dice must be at least 1 Dice");
    }
    dice = new ArrayList<>();
    for (int i = 0; i < numberOfDice; i++) {
      dice.add(new Die());
    }
  }

  //methods
  /**
   * @Author Sigveer, Ingve
   * Version: 1.0
   * Date: 06.02.2025
   * Rolls all dice and returns the sum of the values.
   *
   * @return The sum of the values of all dice after rolling.
   */
  public int rollSum(){
    int sum = 0;
    for (Die die : dice) {
      sum += die.roll();
    }
    return sum;
  }


  /**
   * @Author Sigveer
   * Version: 1.0
   * Date: 04.02.2025
   * Returns the value of a specific die.
   *
   * @param dieNumber The number of the die to get the value of.
   * @return The value of the die.
   * @throws IllegalArgumentException if the die number is out of bounds.
   */
  public int getDie(int dieNumber){
    if (dieNumber < 0 || dieNumber >= dice.size()) {
      throw new IllegalArgumentException("Die number out of bounds");
    }
    return dice.get(dieNumber).getValue();
  }
}
