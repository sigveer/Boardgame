package com.gruppe24.boardgames.commonclasses;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DieTest {

  private CommonDie die;

  @BeforeEach
  void setUp() {
    die = new CommonDie();
  }


  @Test
  void testRoll() {
    for (int i = 0; i < 100; i++) { // Increased iterations for better coverage
      int value = die.roll();
      assertTrue(value >= 1 && value <= 6, "Rolled value should be between 1 and 6");
    }
  }

  @Test
  void testGetLastRoll() {
    for (int i = 0; i < 10; i++) {
      int rolledValue = die.roll();
      int lastRoll = die.getLastRoll();
      assertEquals(rolledValue, lastRoll, "Last rolled value should match the returned value");
    }
  }

  @Test
  void testInitialState() {
    assertEquals(0, die.getLastRoll(), "Initial last rolled value should be 0");
  }
}