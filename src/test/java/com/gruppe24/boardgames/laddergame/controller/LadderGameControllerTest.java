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

class LadderGameControllerTest {

  public LadderGameController ladderGameController;
  public GameSubject gameSubject;

  @BeforeEach
  void setUp() {
    ladderGameController = new LadderGameController();
  }

  @Test
  void testCreatePlayer() {
    CommonPlayer abstractPlayer = ladderGameController.createPlayer("Mann", 3);

    assertNotNull(ladderGameController);
    assertInstanceOf(Player.class, abstractPlayer);

    Player concretePlayer = (Player) abstractPlayer;

    assertEquals("Mann", concretePlayer.getName());
    assertEquals(3, concretePlayer.getIconIndex());
  }

  @Test
  void testGetMaxPlayers() {
    assertEquals(5, ladderGameController.getMaxPlayers());
  }

  @Test
  void testAddDefaultPlayer() {
    List<Player> playerList = ladderGameController.getPlayerList();

    // Upon making an instance of playerController, a player should have been added
    assertEquals(1, playerList.size());
  }
}
