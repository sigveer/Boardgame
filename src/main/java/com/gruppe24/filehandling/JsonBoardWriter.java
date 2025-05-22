package com.gruppe24.filehandling;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.gruppe24.boardgames.commonclasses.CommonTile;
import com.gruppe24.boardgames.laddergame.models.board.Board;
import com.gruppe24.boardgames.laddergame.models.board.tiles.FrozenTile;
import com.gruppe24.boardgames.laddergame.models.board.tiles.LadderDownTile;
import com.gruppe24.boardgames.laddergame.models.board.tiles.LadderUpTile;
import com.gruppe24.boardgames.laddergame.models.board.tiles.RandomTeleportTile;
import com.gruppe24.boardgames.laddergame.models.board.tiles.WinningTile;
import java.io.IOException;

/**
 * JSONBoardWriter is a class that implements the FileWriter interface to write a Board object to a
 * JSON file.
 */
public class JsonBoardWriter {

  /**
   * Writes the given Board object to a JSON file at the specified file path.
   *
   * @param board    the Board object to write to the file.
   * @param filePath the path to the file where the object will be written
   * @return true if the write operation was successful, false otherwise
   * @AI_Assisted try loop is assisted by AI.
   */
  public boolean writeToFile(Board board, String filePath) {
    JsonObject boardJson = serializeBoard(board);

    try (java.io.FileWriter writer = new java.io.FileWriter(filePath)) {
      Gson gson = new GsonBuilder().setPrettyPrinting().create();
      writer.write(gson.toJson(boardJson));
      return true;
    } catch (IOException e) {
      System.err.println("Error writing JSON file: " + e.getMessage());
      return false;
    }
  }

  /**
   * Serializes a Board object to a JSON object.
   *
   * @param board the Board object to serialize
   * @return a JsonObject representing the Board
   */
  private JsonObject serializeBoard(Board board) {
    JsonObject boardJson = new JsonObject();
    boardJson.addProperty("name", board.getName());
    boardJson.addProperty("description", board.getDescription());

    JsonArray tilesJsonArray = new JsonArray();
    for (int i = 0; i <= 90; i++) {
      JsonObject tileJson = new JsonObject();
      tileJson.addProperty("id", i);

      if (i < 90) {
        tileJson.addProperty("nextTile", i + 1);
      }

      CommonTile commonTile = board.getTile(i);
      addTileActionToJson(tileJson, commonTile, i);

      tilesJsonArray.add(tileJson);
    }

    boardJson.add("tiles", tilesJsonArray);
    return boardJson;
  }

  /**
   * Adds action information to a tile's JSON object based on its type.
   *
   * @param tileJson   the JSON object representing the tile
   * @param commonTile the tile to serialize
   * @param tileId     the ID of the tile
   */
  private void addTileActionToJson(JsonObject tileJson, CommonTile commonTile, int tileId) {
    if (commonTile instanceof LadderDownTile ladderDownTile) {
      JsonObject actionJson = new JsonObject();
      actionJson.addProperty("type", "LadderDownAction");
      actionJson.addProperty("destinationTileId", ladderDownTile.getDestination());
      actionJson.addProperty("description",
          "Ladder from " + tileId + " to " + ladderDownTile.getDestination());
      tileJson.add("action", actionJson);
    } else if (commonTile instanceof LadderUpTile ladderUpTile) {
      JsonObject actionJson = new JsonObject();
      actionJson.addProperty("type", "LadderUpAction");
      actionJson.addProperty("destinationTileId", ladderUpTile.getDestination());
      actionJson.addProperty("description",
          "Ladder from " + tileId + " to " + ladderUpTile.getDestination());
      tileJson.add("action", actionJson);
    } else if (commonTile instanceof FrozenTile) {
      JsonObject actionJson = new JsonObject();
      actionJson.addProperty("type", "FrozenAction");
      actionJson.addProperty("description",
          "Player gets frozen on tile " + tileId + " for 1 turn");
      tileJson.add("action", actionJson);
    } else if (commonTile instanceof RandomTeleportTile) {
      JsonObject actionJson = new JsonObject();
      actionJson.addProperty("type", "RandomTeleportAction");
      actionJson.addProperty("description",
          "Player gets teleported to a random tile from " + tileId);
      tileJson.add("action", actionJson);
    } else if (commonTile instanceof WinningTile) {
      JsonObject actionJson = new JsonObject();
      actionJson.addProperty("type", "WinningAction");
      actionJson.addProperty("description", "Player wins the game on tile " + tileId);
      tileJson.add("action", actionJson);
    }
  }
}
