package com.gruppe24.boardgames.laddergame.models.board.tiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.google.gson.JsonObject;
import com.gruppe24.boardgames.laddergame.models.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * {@code ladderDownTileTest} is a test class for the {@code ladderDownTile} class.
 */
class LadderDownTileTest {

  private LadderDownTile ladderDownTile;
  private Player player;

  /**
   * Sets up the test fixture by creating a new ladderDown tile and player before each test.
   */
  @BeforeEach
  void setUp() {
    ladderDownTile = new LadderDownTile(10, 5);
    player = new Player("TestPlayer", 1);
    player.setPosition(10);
  }

  /**
   * Tests the perform method in the {@code ladderDownTile} class.
   */
  @Test
  void perform() {
    ladderDownTile.perform(player);
    assertEquals(5, player.getPosition());
  }

  @Test
  void testGetTileType() {
    assertEquals(2, ladderDownTile.getTileType());
  }

  @Test
  void testAddActionToJson() {
    JsonObject tileJson = new JsonObject();
    ladderDownTile.addActionToJson(tileJson, 10);

    JsonObject actionJson = tileJson.getAsJsonObject("action");
    assertNotNull(actionJson);
    assertEquals("LadderDownAction", actionJson.get("type").getAsString());
    assertEquals(5, actionJson.get("destinationTileId").getAsInt());
    assertEquals("Ladder from 10 to 5", actionJson.get("description").getAsString());
  }
}