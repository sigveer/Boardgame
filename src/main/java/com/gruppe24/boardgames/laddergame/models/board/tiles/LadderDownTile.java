package com.gruppe24.boardgames.laddergame.models.board.tiles;

import com.google.gson.JsonObject;
import com.gruppe24.boardgames.laddergame.models.Player;

/**
 * Class representing a tile that is a snake.
 */
public class LadderDownTile extends SpecialTile {

  /**
   * Constructor that initializes the snake tile.
   *
   * @param position The position of the tile.
   * @param slideDown The position to slide down to.
   */
  public LadderDownTile(int position, int slideDown) {
    super(position, slideDown);
  }

  /**
   * Method that performs the action of the tile.
   *
   * @param player The player that lands on the tile.
   */
  @Override
  public void perform(Player player) {
    player.setPosition(getDestination());
  }

  @Override
  public int getTileType() {
    return 2;
  }

  @Override
  public void addActionToJson(JsonObject tileJson, int tileId) {
    JsonObject actionJson = new JsonObject();
    actionJson.addProperty("type", "LadderDownAction");
    actionJson.addProperty("destinationTileId", getDestination());
    actionJson.addProperty("description",
        "Ladder from " + tileId + " to " + getDestination());
    tileJson.add("action", actionJson);
  }
}
