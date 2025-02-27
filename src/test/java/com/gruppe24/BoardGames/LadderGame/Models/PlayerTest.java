package com.gruppe24.BoardGames.LadderGame.Models;

import static org.junit.jupiter.api.Assertions.*;

import com.gruppe24.BoardGames.LadderGame.Controller.Board;
import java.io.ByteArrayInputStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PlayerTest {

  private Player player;
  private Board board;

  @BeforeEach
  void setUp() {
    player = new Player("TestPlayer");
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