package com.gruppe24.filehandling;

import com.gruppe24.boardgames.laddergame.models.Player;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * Utility class for saving player data to CSV format.
 */
public class CsvPlayerWriter {

  /**
   * Saves a list of players to a CSV file.
   *
   * @param players The list of players to save
   * @param stage   The stage to display the file chooser
   * @return true if saving was successful, false otherwise
   */
  public static boolean savePlayers(List<Player> players, Stage stage) {
    FileChooser fileChooser = FileHandler.createFileChooser("Save Players", true);
    File file = fileChooser.showSaveDialog(stage);

    if (file == null) {
      return false;
    }

    try (FileWriter writer = new FileWriter(file)) {

      writer.write("Name,IconPath\n");

      for (Player player : players) {
        int iconIndex = player.getIconIndex();
        writer.write(player.getName() + "," + iconIndex + "\n");
      }

      return true;
    } catch (IOException e) {
      e.printStackTrace();
      //implementer seinere
      return false;
    }
  }
}