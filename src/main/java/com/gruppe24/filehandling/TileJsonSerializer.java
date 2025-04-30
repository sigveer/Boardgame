package com.gruppe24.filehandling;

import com.google.gson.JsonObject;

/**
 * Interface for serializing tile JSON data.
 * This interface defines a method to add an action to a tile JSON object.
 */
public interface TileJsonSerializer {

  /**
   * Adds an action to the given tile JSON object.
   *
   * @param tileJson The JSON object representing the tile.
   * @param tileId The ID of the tile to which the action is added.
   */
  void addActionToJson(JsonObject tileJson, int tileId);
}
