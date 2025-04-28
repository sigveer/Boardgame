package com.gruppe24.boardgames.laddergame.models.board.tiles;

import com.google.gson.JsonObject;

/**
 * Class that represents a winning tile.
 */
public class WinningTile extends SpecialTile {

  /**
   * Constructor for WinningTile.
   *
   * @param position the position of the tile
   */
  public WinningTile(int position) {
    super(position);
  }

  /**
   * Return the type of the tile.
   *
   * @return the type of the tile
   */
  @Override
  public int getTileType() {
    return -3;
  }

  @Override
  public void addActionToJson(JsonObject tileJson, int tileId) {
    JsonObject actionJson = new JsonObject();
    actionJson.addProperty("type", "WinningAction");
    actionJson.addProperty("description", "Player wins the game on tile " + tileId);
    tileJson.add("action", actionJson);
  }
}
