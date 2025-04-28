package com.gruppe24.boardgames.laddergame.models.board.tiles;

import com.google.gson.JsonObject;
import com.gruppe24.boardgames.laddergame.models.Player;
import java.util.Random;

/**
 * A special tile that teleports the player to a random position on the board.
 */
public class RandomTeleportTile extends SpecialTile {

  private final Random random = new Random();

  /**
   * Constructor that initializes the random teleport tile.
   *
   * @param position The position of the tile.
   */
  public RandomTeleportTile(int position) {
    super(position);
  }

  /**
   * Method that performs the action of the tile.
   *
   * @param player The player that lands on the tile.
   */
  @Override
  public void perform(Player player) {

    int maxPosition = 89;
    int minPosition = 1;
    int destinationPosition = random.nextInt(maxPosition - minPosition + 1) + minPosition;

    player.setPosition(destinationPosition);
  }

  @Override
  public int getTileType() {
    return 3;
  }

  @Override
  public void addActionToJson(JsonObject tileJson, int tileId) {
    JsonObject actionJson = new JsonObject();
    actionJson.addProperty("type", "RandomTeleportAction");
    actionJson.addProperty("description",
        "Player gets teleported to a random tile from " + tileId);
    tileJson.add("action", actionJson);
  }
}