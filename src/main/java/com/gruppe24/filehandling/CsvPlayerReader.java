package com.gruppe24.filehandling;

import com.gruppe24.boardgames.laddergame.models.Player;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * Utility class for loading player data from CSV files.
 */
public class CsvPlayerReader {


  /**
   * Loads player data from a CSV file.
   *
   * @param stage The stage to use for the file chooser dialog.
   * @return A list of players loaded from the CSV file, or null if an error occurs.
   *
   * @AI_Based "While" loop is based on AI logic and structure.
   */
  public static List<Player> loadPlayers(Stage stage) {
    if (stage == null) {
      throw new IllegalArgumentException("Stage is null in CsvPlayerReader");
    }
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
          int iconIndex = Integer.parseInt(parts[1]);

          Player player = new Player(name, iconIndex);
          players.add(player);
        }
      }

      return players;
    } catch (IOException e) {
      e.printStackTrace();
      //implementer seinere
      return null;
    }
  }
}