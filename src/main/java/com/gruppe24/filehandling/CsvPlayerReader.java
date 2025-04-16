package com.gruppe24.filehandling;

import com.gruppe24.boardgames.laddergame.models.Player;
import com.gruppe24.utils.ColorUtil;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * CSVPlayerReader is a class that implements the FileReader interface to read player data from a
 * CSV file. The CSV file should contain player names and their corresponding colors.
 */
public class CsvPlayerReader implements com.gruppe24.filehandling.FileReader {

  @Override
  public Object readFromFile(String filePath) {
    List<Player> players = new ArrayList<>();

    try (BufferedReader reader = new BufferedReader(new java.io.FileReader(filePath))) {
      String line;
      while ((line = reader.readLine()) != null) {
        String[] parts = line.split(",");
        if (parts.length == 2) {
          String name = parts[0];
          String colorStr = parts[1];
          Color color = ColorUtil.getColorFromString(colorStr);
          players.add(new Player(name, color));
        }
      }
      return players;
    } catch (IOException e) {
      System.err.println("Error reading CSV file: " + e.getMessage());
      return new ArrayList<Player>();
    }
  }

  /**
   * Loads players from a CSV file using a file chooser dialog.
   *
   * @param stage the stage to use for the file chooser dialog
   * @return a list of players loaded from the CSV file, or null if no file was selected
   */
  public static List<Player> loadPlayers(Stage stage) {
    if (stage == null) {
      return null;
    }

    FileChooser fileChooser = FileHandler.createFileChooser("Load Players", false);
    File file = fileChooser.showOpenDialog(stage);

    if (file != null) {
      CsvPlayerReader reader = new CsvPlayerReader();
      Object result = reader.readFromFile(file.getAbsolutePath());

      if (result instanceof List<?>) {
        @SuppressWarnings("unchecked")
        List<Player> loadedPlayers = (List<Player>) result;
        return loadedPlayers;
      }
    }
    return null;
  }
}