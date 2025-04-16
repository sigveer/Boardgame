package com.gruppe24.filehandling;

import com.gruppe24.boardgames.laddergame.models.Player;
import com.gruppe24.utils.ColorUtil;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * CSVPlayerWriter is a utility class for writing player data to a CSV file.
 * It implements the FileWriter interface and provides functionality to save
 * player information in a specific format.
 */
public class CsvPlayerWriter implements com.gruppe24.filehandling.FileWriter {

  @Override
  public boolean writeToFile(Object object, String filePath) {
    if (!(object instanceof List<?> list)) {
      System.err.println("Error: Object is not a List");
      return false;
    }

    if (list.isEmpty() || !(list.getFirst() instanceof Player)) {
      System.err.println("Error: List does not contain Player objects");
      return false;
    }

    @SuppressWarnings("unchecked")
    List<Player> players = (List<Player>) list;

    try (FileWriter writer = new FileWriter(filePath)) {
      for (Player player : players) {
        String colorName = ColorUtil.getColorName(player.getPlayerPiece().getFill());
        writer.write(player.getName() + "," + colorName + "\n");
      }
      return true;
    } catch (IOException e) {
      System.err.println("Error writing CSV file: " + e.getMessage());
      return false;
    }
  }

  /**
   * Saves a list of players to a CSV file.
   *
   * @param players the list of players to save
   * @param stage the stage for the file chooser dialog
   * @return true if the file was saved successfully, false otherwise
   */
  public static boolean savePlayers(List<Player> players, Stage stage) {
    if (players == null || players.isEmpty() || stage == null) {
      return false;
    }

    FileChooser fileChooser = FileHandler.createFileChooser("Save Players", true);
    File file = fileChooser.showSaveDialog(stage);

    if (file != null) {
      String filePath = file.getAbsolutePath();
      if (!filePath.toLowerCase().endsWith(".csv")) {
        filePath += ".csv";
      }

      CsvPlayerWriter writer = new CsvPlayerWriter();
      return writer.writeToFile(players, filePath);
    }
    return false;
  }
}