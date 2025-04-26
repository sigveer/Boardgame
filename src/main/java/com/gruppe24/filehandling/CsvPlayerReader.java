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


  /**
   * Loads player data from a CSV file.
   *
   * @param stage The stage to use for the file chooser dialog.
   * @return A list of players loaded from the CSV file, or null if an error occurs.
   *
   * @AI_Based "While" loop is based on AI logic and structure.
   */
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

  /**
   * Loads an image from the specified path. If the image cannot be loaded, a default image is used.
   *
   * @param path The path to the image file.
   * @return The loaded image or a default image if loading fails.
   */
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