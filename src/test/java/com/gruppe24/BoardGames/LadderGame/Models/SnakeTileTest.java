package com.gruppe24.BoardGames.LadderGame.Models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SnakeTileTest {

  private SnakeTile snakeTile;
  private Player player;

  @BeforeEach
  void setUp() {
    snakeTile = new SnakeTile(10, 5);
    player = new Player("TestPlayer");
    player.setPosition(10);
  }

  @Test
  void perform() {
    snakeTile.perform(player);
    assertEquals(5, player.getPosition());
  }
}