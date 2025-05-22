package com.gruppe24.boardgames.laddergame.models.board.tiles;

import com.google.gson.JsonObject;
import com.gruppe24.boardgames.laddergame.models.Player;

/**
 * Class representing a tile that is a ladder.
 */
public class LadderUpTile extends SpecialTile {

  @Override
  public void perform(Player player) {
    player.setPosition(getDestination());
  }

  @Override
  public int getTileType() {
    return 1;
  }

  @Override
  public void addActionToJson(JsonObject tileJson, int tileId) {
    JsonObject actionJson = new JsonObject();
    actionJson.addProperty("type", "LadderUpAction");
    actionJson.addProperty("destinationTileId", getDestination());
    actionJson.addProperty("description",
        "Ladder from " + tileId + " to " + getDestination());
    tileJson.add("action", actionJson);
  }

  /**
   * Constructor that initializes the ladder tile.
   *
   * @param position The position of the tile.
   * @param climbUp  The position to climb up to.
   */
  public LadderUpTile(int position, int climbUp) {
    super(position, climbUp);
  }
}
