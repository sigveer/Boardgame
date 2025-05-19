package com.gruppe24.boardgames.laddergame.models.board.tiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.google.gson.JsonObject;
import com.gruppe24.boardgames.laddergame.models.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * {@code LadderUpTileTest} is a test class for the {@code LadderUpTile} class.
 */
class LadderUpTileTest {

  private LadderUpTile ladderUpTile;
  private Player player;

  /**
   * Sets up the test fixture by creating a new ladderUp tile and player before each test.
   */
  @BeforeEach
  void setUp() {
    ladderUpTile = new LadderUpTile(5, 10);
    player = new Player("TestPlayer", 1);
    player.setPosition(5);
  }

  /**
   * Tests the perform method in the {@code LadderUpTile} class.
   */
  @Test
  void perform() {
    ladderUpTile.perform(player);
    assertEquals(10, player.getPosition());
  }

  @Test
  void testGetTileType() {
    assertEquals(1, ladderUpTile.getTileType());
  }

  @Test
  void testAddActionToJson() {
    JsonObject tileJson = new JsonObject();
    ladderUpTile.addActionToJson(tileJson, 5);

    JsonObject actionJson = tileJson.getAsJsonObject("action");
    assertNotNull(actionJson);
    assertEquals("LadderUpAction", actionJson.get("type").getAsString());
    assertEquals(10, actionJson.get("destinationTileId").getAsInt());
    assertEquals("Ladder from 5 to 10", actionJson.get("description").getAsString());
  }
}