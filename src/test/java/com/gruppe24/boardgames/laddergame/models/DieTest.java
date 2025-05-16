package com.gruppe24.boardgames.laddergame.models;

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
   * Tests the {@code roll} method to ensure it generates values between 1 and 6.
   */
  @Test
  void testRoll() {
    for (int i = 0; i < 100; i++) { // Increased iterations for better coverage
      int value = die.roll();
      assertTrue(value >= 1 && value <= 6, "Rolled value should be between 1 and 6");
    }
  }

  /**
   * Tests the {@code getLastRoll} method to ensure it returns the last rolled value.
   */
  @Test
  void testGetLastRoll() {
    for (int i = 0; i < 10; i++) {
      int rolledValue = die.roll();
      int lastRoll = die.getLastRoll();
      assertEquals(rolledValue, lastRoll, "Last rolled value should match the returned value");
    }
  }

  /**
   * Tests the initial state of the die to ensure the last rolled value is 0.
   */
  @Test
  void testInitialState() {
    assertEquals(0, die.getLastRoll(), "Initial last rolled value should be 0");
  }
}