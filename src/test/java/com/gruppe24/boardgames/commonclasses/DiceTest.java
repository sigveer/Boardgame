package com.gruppe24.boardgames.commonclasses;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DiceTest {

  private CommonDice dice;

  @BeforeEach
  void setUp() {
    dice = new CommonDice(3);
  }

  @Test
  void testRollSum() {
    for (int i = 0; i < 10; i++) {
      int sum = dice.rollSum();
      assertTrue(sum >= 3 && sum <= 18, "Sum should be between 3 and 18 for 3 dice");
    }
  }

  @Test
  void testGetDie() {
    dice.rollSum();
    for (int i = 0; i < 3; i++) {
      int value = dice.getDie(i);
      assertTrue(value >= 1 && value <= 6, "Die value should be between 1 and 6");
    }
  }

//  @Test
//  void testGetSum() {
//    int sum = dice.rollSum();
//    assertEquals(sum, dice.getSum(), "getSum should return the same value as rollSum");
//  }

  @Test
  void testDicePath() {
    for (int i = 1; i <= 6; i++) {
      String path = dice.dicePath(i);
      assertEquals("pictures/dices/dice" + i + ".png", path, "Path should match the dice number");
    }
  }

//  /**
//   * Tests the {@code roll} method to ensure it rolls a single die correctly.
//   */
//  @Test
//  void testRollSingleDie() {
//    int value = dice.roll();
//    assertTrue(value >= 1 && value <= 6, "Single die roll should be between 1 and 6");
//  }

  @Test
  void testInvalidNumberOfDice() {
    assertThrows(IllegalArgumentException.class, () -> new CommonDice(0),
        "Should throw exception for less than 1 die");
  }

  @Test
  void testInvalidDieIndex() {
    dice.rollSum();
    assertThrows(IllegalArgumentException.class, () -> dice.getDie(3),
        "Should throw exception for out-of-bounds index");
  }
}