package com.gruppe24.BoardGames.LadderGame.Models;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PlayerTest {

  private Player player;

  @BeforeEach
  void setUp() {
    player = new Player("TestPlayer");
  }

  @Test
  void handlePlayerTurn() {
    System.setIn(new ByteArrayInputStream("\n".getBytes()));
    int initialPosition = player.getPosition();
    player.handlePlayerTurn();
    assertTrue(player.getPosition() > initialPosition);
  }

  @Test
  void movePlayer() {
    player.setPosition(5);
    int sumDice = 3;
    player.movePlayer(sumDice);
    assertEquals(8, player.getPosition());
  }

  @Test
  void testMovePlayerOvershoot() {
    player.setPosition(98);
    int sumDice = 6;
    player.movePlayer(sumDice);
    assertEquals(96, player.getPosition());
  }

  @Test
  void getName() {
    assertEquals("TestPlayer", player.getName());
  }

  @Test
  void getPosition() {
    player.setPosition(8);
    assertEquals(8, player.getPosition());
  }

  @Test
  void setPosition() {
    player.setPosition(10);
    assertEquals(10, player.getPosition());
  }
}