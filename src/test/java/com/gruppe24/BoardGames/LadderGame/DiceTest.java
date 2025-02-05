package com.gruppe24.BoardGames.LadderGame;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * {@code DieTest} is a test class for the {@code Die} class.
 */
class DiceTest {

  private Dice dice;


  /**
   * Sets up the test fixture by creating a new die before each test.
   */
  @BeforeEach
  void setUp() {
    dice = new Dice(3);
  }


  /**
   * Tests the {@code roll} method in the {@code Die} class.
   */
  @Test
  void roll() {
    for (int i = 0; i < 10; i++) {
      int value = dice.roll();
      assertTrue(value >= 3 && value <= 18);
      System.out.println("Rolled: " + value);
    }
  }


  /**
   * Tests the {@code getDie} method in the {@code Die} class.
   */
  @Test
  void getDie() {
    dice.roll();
    int value1 = dice.getDie(0);
    assertTrue(value1 >= 1 && value1 <= 6);
    System.out.println("Dice1: " + value1 + " which is between 1 and 6");

    dice.roll();
    int value2 = dice.getDie(1);
    assertTrue(value2 >= 1 && value2 <= 6);
    System.out.println("Dice2: " + value2 + " which is between 1 and 6");

    dice.roll();
    int value3 = dice.getDie(2);
    assertTrue(value3 >= 1 && value3 <= 6);
    System.out.println("Dice3: " + value3 + " which is between 1 and 6");
  }


  /**
   * Checks if the number of dice is invalid.
   */
  @Test
  void InvalidNumberOfDice() {
    assertThrows(IllegalArgumentException.class, () -> new Dice(0));
    System.out.println("Invalid number of dice");
  }


  /**
   * Checks if the die number is invalid.
   */
  @Test
  void InvalidDieNumber() {
    dice.roll();
    assertThrows(IllegalArgumentException.class, () -> dice.getDie(3));
    System.out.println("Invalid die number");
  }
}