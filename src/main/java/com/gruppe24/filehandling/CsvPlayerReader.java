package com.gruppe24.filehandling;

import com.gruppe24.boardgames.laddergame.models.Player;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * CSVPlayerReader is a class that implements the FileReader interface to read player data from a
 * CSV file. The CSV file should contain player names and their corresponding colors.
 */
public class CsvPlayerReader implements com.gruppe24.filehandling.FileReader {


  /**
   * Reads player data from a CSV file.
   *
   * @param filePath the path to the CSV file
   * @return a list of players read from the file
   *
   * @AI_Based The while loop logic in this method is based on AI suggestion
   */
  @Override
  public Object readFromFile(String filePath) {
    List<Player> players = new ArrayList<>();

    try (BufferedReader reader = new BufferedReader(new java.io.FileReader(filePath))) {
      String line;
      while ((line = reader.readLine()) != null) {
        String[] parts = line.split(",");
        if (parts.length == 2) {
          String name = parts[0];
          String imageName = parts[1];
          Image image = loadImageFromName(imageName);
          players.add(new Player(name, image));
        }
      }
      return players;
    } catch (IOException e) {
      System.err.println("Error reading CSV file: " + e.getMessage());
      return new ArrayList<Player>();
    }
  }

  /**
   * Loads an image from the specified name.
   *
   * @param imageName the name of the image file
   * @return the loaded Image object
   *
   * @AI_Based
   */
  private Image loadImageFromName(String imageName) {
    // Construct the full path based on the image name
    String fullPath = "pictures/pngIcons/" + imageName;
    try {
      return new Image(
          Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(fullPath)));
    } catch (Exception e) {
      System.err.println("Error loading image: " + fullPath);
     //Deafult
      return new Image(Objects.requireNonNull(
          getClass().getClassLoader().getResourceAsStream("pictures/pngIcons/mario.png")));
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