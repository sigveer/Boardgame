package com.gruppe24.BoardGames.LadderGame.Models;

import static org.junit.jupiter.api.Assertions.*;

import javafx.scene.paint.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PlayerTest {

  private Player player;

  @BeforeEach
  void setUp() {
    player = new Player("TestPlayer", Color.RED);
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