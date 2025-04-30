package com.gruppe24.filehandling;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.gruppe24.boardgames.laddergame.models.board.Board;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * JsonBoardReader is a class that implements the FileReader interface to read a board configuration
 * from a JSON file. It parses the JSON data and constructs a Board object based on the information
 * provided in the file.
 */
public class JsonBoardReader {

  private Board currentBoard;

  public Object readFromFile(String filePath) {
    try (FileReader reader = new FileReader(filePath)) {
      JsonObject boardJson = JsonParser.parseReader(reader).getAsJsonObject();

      if (!boardJson.has("name") || !boardJson.has("description")) {
        System.err.println("Error: Missing required fields (name or description)");
        return null;
      }

      String name = boardJson.get("name").getAsString();
      String description = boardJson.get("description").getAsString();

      HashMap<Integer, Integer> ladderUp = new HashMap<>();
      HashMap<Integer, Integer> ladderDown = new HashMap<>();
      HashMap<Integer, Boolean> winningTile = new HashMap<>();
      HashMap<Integer, Boolean> frozenTiles = new HashMap<>();
      HashMap<Integer, Boolean> randomTeleportTiles = new HashMap<>();

      if (!boardJson.has("tiles")) {
        System.err.println("Error: Missing tiles array");
        return null;
      }

      JsonArray tilesJsonArray = boardJson.getAsJsonArray("tiles");

      for (JsonElement tileElement : tilesJsonArray) {
        JsonObject tileJson = tileElement.getAsJsonObject();

        if (!tileJson.has("id")) {
          System.err.println("Error: Tile missing ID");
          continue;
        }

        int tileId = tileJson.get("id").getAsInt();

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
            case "WinningAction":
              winningTile.put(tileId, true);
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

      return new Board(ladderUp, ladderDown, winningTile, frozenTiles, randomTeleportTiles, name, description);

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

  /**
   * Method that loads a custom board from a JSON file.
   *
   * @param stage the stage to use for the file chooser
   * @return the loaded custom board
   *
   * @AI_Based
   */
  public Board loadCustomBoard(Stage stage) {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Open JSON Board File");
    fileChooser.getExtensionFilters().add(
        new FileChooser.ExtensionFilter("JSON Files", "*.json"));
    File boardsDirectory = new File("src/main/resources/boards");
    if (boardsDirectory.exists()) {
      fileChooser.setInitialDirectory(boardsDirectory);
    }
    File selectedFile = fileChooser.showOpenDialog(stage);
    if (selectedFile != null) {
      try {
        JsonBoardReader reader = new JsonBoardReader();
        Board customBoard = (Board) reader.readFromFile(selectedFile.getPath());
        if (customBoard != null) {
          this.currentBoard = customBoard;
          return customBoard;
        }
      } catch (Exception e) {
        // Logge feil, ingve
      }
    }
    return null;
  }
}