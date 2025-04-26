package com.gruppe24.filehandling;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.gruppe24.boardgames.laddergame.models.board.Board;
import com.gruppe24.boardgames.laddergame.models.board.tiles.FrozenTile;
import com.gruppe24.boardgames.laddergame.models.board.tiles.LadderDownTile;
import com.gruppe24.boardgames.laddergame.models.board.tiles.LadderUpTile;
import com.gruppe24.boardgames.laddergame.models.board.tiles.RandomTeleportTile;
import com.gruppe24.boardgames.laddergame.models.board.tiles.WinningTile;
import com.gruppe24.boardgames.laddergame.models.board.tiles.Tile;
import java.io.IOException;

/**
 * JSONBoardWriter is a class that implements the FileWriter interface to write a Board object to a
 * JSON file.
 */
public class JsonBoardWriter implements FileWriter {

  //fjerne instanceof
  @Override
  public boolean writeToFile(Object object, String filePath) {
    if (!(object instanceof Board board)) {
      System.err.println("Error: Object is not a Board");
      return false;
    }

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

      //fjerne instanceof
      Tile tile = board.getTile(i);
      if (tile instanceof LadderUpTile ladderUpTile) {
        JsonObject actionJson = new JsonObject();
        actionJson.addProperty("type", "LadderUpAction");
        actionJson.addProperty("destinationTileId", ladderUpTile.getDestination());
        actionJson.addProperty("description",
            "Ladder from " + i + " to " + ladderUpTile.getDestination());
        tileJson.add("action", actionJson);
      } else if (tile instanceof LadderDownTile ladderDownTile) {
        JsonObject actionJson = new JsonObject();
        actionJson.addProperty("type", "LadderDownAction");
        actionJson.addProperty("destinationTileId", ladderDownTile.getDestination());
        actionJson.addProperty("description",
            "Ladder from " + i + " to " + ladderDownTile.getDestination());
        tileJson.add("action", actionJson);
      } else if (tile instanceof FrozenTile) {
        JsonObject actionJson = new JsonObject();
        actionJson.addProperty("type", "FrozenAction");
        actionJson.addProperty("description",
            "Player gets frozen on tile " + i + " for 1 turn");
        tileJson.add("action", actionJson);
      } else if (tile instanceof RandomTeleportTile) {
        JsonObject actionJson = new JsonObject();
        actionJson.addProperty("type", "RandomTeleportAction");
        actionJson.addProperty("description",
            "Player gets teleported to a random tile from " + i);
        tileJson.add("action", actionJson);
      } else if (tile instanceof WinningTile) {
        JsonObject actionJson = new JsonObject();
        actionJson.addProperty("type", "WinningAction");
        actionJson.addProperty("description", "Player wins the game on tile " + i);
        tileJson.add("action", actionJson);
      }

      tilesJsonArray.add(tileJson);
    }

    boardJson.add("tiles", tilesJsonArray);
    return boardJson;
  }
}