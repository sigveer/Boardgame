package com.gruppe24.boardgames.laddergame.models.board.tiles;

import com.google.gson.JsonObject;
import com.gruppe24.boardgames.laddergame.models.Player;

/**
 * Represents a frozen tile on the board.
 *
 */
public class FrozenTile extends SpecialTile {

  /**
   * Constructor that initializes the normal tile.
   *
   * @param position The position of the tile on the board.
   */
  public FrozenTile(int position) {
    super(position);
  }

  /**
   * Method that performs the action of the tile.
   *
   * @param player The player that lands on the tile.
   */
  @Override
  public void perform(Player player) {
    player.setFrozen(true);
  }

  @Override
  public int getTileType() {
    return 4;
  }

  @Override
  public void addActionToJson(JsonObject tileJson, int tileId) {
    JsonObject actionJson = new JsonObject();
    actionJson.addProperty("type", "FrozenAction");
    actionJson.addProperty("description",
        "Player gets frozen on tile " + tileId + " for 1 turn");
    tileJson.add("action", actionJson);
  }
}
