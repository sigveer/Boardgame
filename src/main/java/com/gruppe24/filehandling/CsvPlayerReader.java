package com.gruppe24.filehandling;

import com.gruppe24.boardgames.laddergame.models.Player;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * Utility class for loading player data from CSV files.
 */
public class CsvPlayerReader implements com.gruppe24.filehandling.FileReader {

  public static List<Player> loadPlayers(Stage stage) {
    FileChooser fileChooser = FileHandler.createFileChooser("Load Players", false);
    File file = fileChooser.showOpenDialog(stage);

    if (file == null) {
      return null;
    }

    List<Player> players = new ArrayList<>();

    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

      reader.readLine();

      String line;
      while ((line = reader.readLine()) != null) {
        String[] parts = line.split(",", 2);
        if (parts.length >= 2) {
          String name = parts[0];
          String iconPath = parts[1];

          Image icon = loadImageFromPath(iconPath);

          Player player = new Player(name, icon);
          player.setIconPath(iconPath);

          players.add(player);
        }
      }

      return players;
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  private static Image loadImageFromPath(String path) {
    try {
      ClassLoader classLoader = CsvPlayerReader.class.getClassLoader();
      return new Image(Objects.requireNonNull(classLoader.getResourceAsStream(path)));
    } catch (Exception e) {
      e.printStackTrace();

      String defaultPath = Player.getIconPaths()[0];
      ClassLoader classLoader = CsvPlayerReader.class.getClassLoader();
      return new Image(Objects.requireNonNull(classLoader.getResourceAsStream(defaultPath)));
    }
  }

  @Override
  public Object readFromFile(String filePath) {
    return null;
  }
}