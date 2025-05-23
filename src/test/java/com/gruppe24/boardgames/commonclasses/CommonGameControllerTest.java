package com.gruppe24.boardgames.commonclasses;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mock;

import com.gruppe24.boardgames.laddergame.controller.BoardController;
import com.gruppe24.boardgames.laddergame.controller.LadderGameController;
import com.gruppe24.boardgames.laddergame.models.Player;
import com.gruppe24.boardgames.laddergame.models.board.Board;
import com.gruppe24.exeptions.InvalidPlayerException;
import com.gruppe24.observerpattern.GameSubject;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CommonGameControllerTest {

  GameSubject mockGameSubject;
  BoardController mockBoardController;
  CommonGameController commonGameController;
  LadderGameController ladderGameController;

  @BeforeEach
  void setUp() {
    mockGameSubject = mock(GameSubject.class);
    ladderGameController = new LadderGameController();
    mockBoardController = new BoardController(new Board());
    commonGameController = new CommonGameController() {
      @Override
      protected CommonPlayer createPlayer(String name, int iconIndex) {
        return mock(CommonPlayer.class);
      }
    };
    commonGameController.setMaxPlayers(5);
  }

  @Test
  void testAddPlayer() {
    ladderGameController.addPlayer();
    List<Player> players = ladderGameController.getPlayerList();

    assertEquals(2, players.size());
    assertEquals("Player 2", players.get(1).getName());
  }

  @Test
  void testRemovePlayer() {
    commonGameController.setMaxPlayers(2);

    commonGameController.addPlayer();
    commonGameController.addPlayer();
    List<CommonPlayer> players = commonGameController.players;

    assertEquals(2, players.size());

    commonGameController.removePlayer();
    assertEquals(1, players.size());

    try {
      commonGameController.removePlayer();
      fail("Should have thrown IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Cannot remove last player", e.getMessage());
    }
    assertEquals(1, players.size());
  }

  @Test
  void testGetPlayerList() {
    List<Player> players = ladderGameController.getPlayerList();
    assertNotNull(players);
    assertEquals(1, players.size());
  }


  @Test
  void testHandleOvershootBeyondLimit() {
    int position = 95;
    commonGameController.setWinCondition(90);

    assertEquals(85, commonGameController.handleOvershoot(position));
  }

}