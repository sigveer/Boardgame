package com.gruppe24.FileHandling;

import com.gruppe24.BoardGames.LadderGame.Models.Player;
import com.gruppe24.Utils.ColorUtil;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class CSVPlayerReader implements com.gruppe24.FileHandling.FileReader {

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


  public static List<Player> loadPlayers(Stage stage) {
    if (stage == null) {
      return null;
    }

    FileChooser fileChooser = FileHandler.createFileChooser("Load Players", false);
    File file = fileChooser.showOpenDialog(stage);

    if (file != null) {
      CSVPlayerReader reader = new CSVPlayerReader();
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