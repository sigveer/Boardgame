package com.gruppe24.boardgames.laddergame.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.gruppe24.boardgames.commonclasses.CommonPlayer;
import com.gruppe24.boardgames.laddergame.models.Player;
import com.gruppe24.observerpattern.GameSubject;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PlayerControllerTest {

  public PlayerController playerController;
  public GameSubject gameSubject;

  @BeforeEach
  void setUp() {
    gameSubject = new GameSubject();
    playerController = new PlayerController(gameSubject);
  }

  @Test
  void testCreatePlayer() {
    CommonPlayer abstractPlayer = playerController.createPlayer("Mann", 3);

    assertNotNull(playerController);
    assertInstanceOf(Player.class, abstractPlayer);

    Player concretePlayer = (Player) abstractPlayer;

    assertEquals("Mann", concretePlayer.getName());
    assertEquals(3, concretePlayer.getIconIndex());
  }

  @Test
  void testGetMaxPlayers() {
    assertEquals(5, playerController.getMaxPlayers());
  }

  @Test
  void testAddDefaultPlayer() {
    List<Player> playerList = playerController.getPlayers();

    // Upon making an instance of playerController, a player should have been added
    assertEquals(1, playerList.size());
  }
}
