package com.gruppe24.filehandling;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.gruppe24.boardgames.commonclasses.CommonTile;
import com.gruppe24.boardgames.laddergame.models.board.Board;
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

      commonTile.addActionToJson(tileJson, i);

      tilesJsonArray.add(tileJson);
    }

    boardJson.add("tiles", tilesJsonArray);
    return boardJson;
  }
}