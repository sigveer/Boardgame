package com.gruppe24.BoardGames.LadderGame;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * {@code DieTest} is a test class for the {@code Die} class.
 */
class DieTest {

  private Die die;


  /**
   * Sets up the test fixture by creating a new die before each test.
   */
  @BeforeEach
  void setUp() {
    die = new Die();
  }


  /**
   * Tests the {@code roll} method in the {@code Die} class.
   */
  @Test
  void roll() {
    for (int i = 0; i < 10; i++) {
      int value = die.roll();
      assertTrue(value >= 1 && value <= 6);
      System.out.println("Rolled: " + value);
    }
  }


  /**
   * Tests the {@code getValue} method in the {@code Die} class.
   */
  @Test
  void getValue() {
    die.roll();
    int value = die.getValue();
    assertTrue(value >= 1 && value <= 6);
    System.out.println("Value: " + value + " which is between 1 and 6");
  }
}