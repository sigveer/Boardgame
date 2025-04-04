package com.gruppe24.FileHandling;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.gruppe24.BoardGames.LadderGame.Models.Board.Board;
import com.gruppe24.BoardGames.LadderGame.Models.Board.Tile.FrozenTile;
import com.gruppe24.BoardGames.LadderGame.Models.Board.Tile.LadderDownTile;
import com.gruppe24.BoardGames.LadderGame.Models.Board.Tile.LadderUpTile;
import com.gruppe24.BoardGames.LadderGame.Models.Board.Tile.RandomTeleportTile;
import com.gruppe24.BoardGames.LadderGame.Models.Board.Tile.Tile;
import java.io.IOException;

public class JSONBoardWriter implements FileWriter {

  @Override
  public boolean writeToFile(Object object, String filePath) {
    if (!(object instanceof Board)) {
      System.err.println("Error: Object is not a Board");
      return false;
    }

    Board board = (Board) object;
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

  private JsonObject serializeBoard(Board board) {
    JsonObject boardJson = new JsonObject();
    boardJson.addProperty("name", board.getName());
    boardJson.addProperty("description", board.getDescription());

    JsonArray tilesJsonArray = new JsonArray();
    // Create tiles based on board size (assuming 90 tiles)
    for (int i = 0; i < 90; i++) {
      JsonObject tileJson = new JsonObject();
      tileJson.addProperty("id", i);

      // Add nextTile for all tiles except the last one
      if (i < 89) {
        tileJson.addProperty("nextTile", i + 1);
      }

      // Add actions for different tile types
      Tile tile = board.getTile(i);
      if (tile instanceof LadderUpTile) {
        LadderUpTile ladderUpTile = (LadderUpTile) tile;
        JsonObject actionJson = new JsonObject();
        actionJson.addProperty("type", "LadderUpAction");
        actionJson.addProperty("destinationTileId", ladderUpTile.getDestination());
        actionJson.addProperty("description", "Ladder from " + i + " to " + ladderUpTile.getDestination());
        tileJson.add("action", actionJson);
      }
      else if (tile instanceof LadderDownTile) {
        LadderDownTile ladderDownTile = (LadderDownTile) tile;
        JsonObject actionJson = new JsonObject();
        actionJson.addProperty("type", "LadderDownAction");
        actionJson.addProperty("destinationTileId", ladderDownTile.getDestination());
        actionJson.addProperty("description", "Ladder from " + i + " to " + ladderDownTile.getDestination());
        tileJson.add("action", actionJson);
      }
      else if (tile instanceof FrozenTile) {
        JsonObject actionJson = new JsonObject();
        actionJson.addProperty("type", "FrozenAction");
        actionJson.addProperty("description", "Player gets frozen on tile " + i + " for 1 turn");
        tileJson.add("action", actionJson);
      }
      else if (tile instanceof RandomTeleportTile) {
        JsonObject actionJson = new JsonObject();
        actionJson.addProperty("type", "RandomTeleportAction");
        actionJson.addProperty("description", "Player gets teleported to a random tile from " + i);
        tileJson.add("action", actionJson);
      }

      tilesJsonArray.add(tileJson);
    }

    boardJson.add("tiles", tilesJsonArray);
    return boardJson;
  }
}