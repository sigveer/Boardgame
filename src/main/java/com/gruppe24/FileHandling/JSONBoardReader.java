package com.gruppe24.FileHandling;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.gruppe24.BoardGames.LadderGame.Models.Board.Board;
import com.gruppe24.BoardGames.LadderGame.Models.Board.BoardFactory;
import com.gruppe24.BoardGames.LadderGame.Models.Board.BoardType;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class JSONBoardReader implements com.gruppe24.FileHandling.FileReader {

  @Override
  public Object readFromFile(String filePath) {
    try (FileReader reader = new FileReader(filePath)) {
      // Parse the JSON file
      JsonObject boardJson = JsonParser.parseReader(reader).getAsJsonObject();

      // Get the board name and description
      if (!boardJson.has("name") || !boardJson.has("description")) {
        System.err.println("Error: Missing required fields (name or description)");
        return null;
      }

      String name = boardJson.get("name").getAsString();
      String description = boardJson.get("description").getAsString();

      // Create hashmaps for different tile types
      HashMap<Integer, Integer> ladderUp = new HashMap<>();
      HashMap<Integer, Integer> ladderDown = new HashMap<>();
      HashMap<Integer, Boolean> frozenTiles = new HashMap<>();
      HashMap<Integer, Boolean> randomTeleportTiles = new HashMap<>();

      // Check if tiles array exists
      if (!boardJson.has("tiles")) {
        System.err.println("Error: Missing tiles array");
        return null;
      }

      // Get the tiles array
      JsonArray tilesJsonArray = boardJson.getAsJsonArray("tiles");

      // Process each tile
      for (JsonElement tileElement : tilesJsonArray) {
        JsonObject tileJson = tileElement.getAsJsonObject();

        if (!tileJson.has("id")) {
          System.err.println("Error: Tile missing ID");
          continue;
        }

        int tileId = tileJson.get("id").getAsInt();

        // Check if tile has an action
        if (tileJson.has("action")) {
          JsonObject actionJson = tileJson.getAsJsonObject("action");

          if (!actionJson.has("type")) {
            System.err.println("Error: Action missing type");
            continue;
          }

          String actionType = actionJson.get("type").getAsString();

          switch (actionType) {
            case "LadderUpAction":
              if (actionJson.has("destinationTileId")) {
                int destinationTileId = actionJson.get("destinationTileId").getAsInt();
                ladderUp.put(tileId, destinationTileId);
              }
              break;
            case "LadderDownAction":
              if (actionJson.has("destinationTileId")) {
                int destinationTileId = actionJson.get("destinationTileId").getAsInt();
                ladderDown.put(tileId, destinationTileId);
              }
              break;
            case "FrozenAction":
              frozenTiles.put(tileId, true);
              break;
            case "RandomTeleportAction":
              randomTeleportTiles.put(tileId, true);
              break;
            default:
              System.err.println("Unknown action type: " + actionType);
          }
        }
      }

      // Determine if this is a special board based on presence of special tiles
      BoardType boardType = (!frozenTiles.isEmpty() || !randomTeleportTiles.isEmpty())
          ? BoardType.SPECIAL : BoardType.CLASSIC;

      Board board = BoardFactory.createBoard(boardType);

      // Apply the custom configuration
      return new Board(ladderUp, ladderDown, name, description);

    } catch (FileNotFoundException e) {
      System.err.println("Error: File not found: " + e.getMessage());
      return null;
    } catch (JsonSyntaxException e) {
      System.err.println("Error: Invalid JSON format: " + e.getMessage());
      return null;
    } catch (IOException e) {
      System.err.println("Error reading JSON file: " + e.getMessage());
      return null;
    } catch (Exception e) {
      System.err.println("Unexpected error: " + e.getMessage());
      return null;
    }
  }
}