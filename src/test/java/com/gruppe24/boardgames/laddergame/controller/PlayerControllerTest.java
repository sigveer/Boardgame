package com.gruppe24.boardgames.laddergame.controller;

import static javafx.beans.binding.Bindings.when;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gruppe24.boardgames.laddergame.models.Player;
import com.gruppe24.observerpattern.EventType;
import com.gruppe24.observerpattern.GameSubject;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PlayerControllerTest {

  private PlayerController playerController;
  private BoardController mockBoardController;
  private GameSubject mockGameSubject;

  @BeforeEach
  void setUp() {
    mockBoardController = mock(BoardController.class);
    mockGameSubject = mock(GameSubject.class);
    playerController = new PlayerController(mockBoardController, mockGameSubject);
  }

  @Test
  void testAddPlayer() {
    playerController.addPlayer();
    List<Player> players = playerController.getPlayers();
    assertEquals(2, players.size());
    assertEquals("Player 2", players.get(1).getName());
    verify(mockGameSubject).notifyListener(eq(EventType.PLAYER_ADDED), any(Player.class));
  }

  @Test
  void testRemovePlayer() {
    playerController.addPlayer();
    playerController.removePlayer();
    List<Player> players = playerController.getPlayers();
    assertEquals(1, players.size());
    verify(mockGameSubject).notifyListener(eq(EventType.PLAYER_REMOVED), any(Player.class));
  }

  @Test
  void testRemovePlayerWhenOnlyOneExists() {
    playerController.removePlayer();
    List<Player> players = playerController.getPlayers();
    assertEquals(1, players.size());
  }

  @Test
  void testCyclePlayerIcon() {
    playerController.cyclePlayerIcon(0);
    Player player = playerController.getPlayers().get(0);
    verify(mockGameSubject).notifyListener(eq(EventType.PLAYER_ICON_CHANGED), eq(player), anyInt());
  }

  @Test
  void testHandlePlayerTurn() {
    Player player = playerController.getPlayers().get(0);

    // Call the method under test
    playerController.handlePlayerTurn(player, 4);

    // Verify the player's position is updated correctly
    assertEquals(5, player.getPosition());

    // Verify interactions with the mocked BoardController
    verify(mockBoardController).handleOvershoot(4);
    verify(mockBoardController).handleTileAction(player, 5);

    // Verify the GameSubject notification
    verify(mockGameSubject).notifyListener(eq(EventType.DICE_ROLLED), eq(player), eq(4));
  }

  @Test
  void testHandlePlayerTurnWithNullPlayer() {
    assertThrows(IllegalArgumentException.class, () -> playerController.handlePlayerTurn(null, 4));
  }

  @Test
  void testHandlePlayerTurnWithNegativeDiceValue() {
    Player player = playerController.getPlayers().get(0);
    assertThrows(IllegalArgumentException.class, () -> playerController.handlePlayerTurn(player, -1));
  }

  @Test
  void testGetPlayers() {
    List<Player> players = playerController.getPlayers();
    assertNotNull(players);
    assertEquals(1, players.size());
  }
}