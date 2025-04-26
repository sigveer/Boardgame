package com.gruppe24.boardgames.laddergame.controller;

import com.gruppe24.boardgames.laddergame.models.board.Board;
import com.gruppe24.filehandling.JsonBoardReader;
import java.io.File;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


public class BoardController {

  private Board currentBoard;

  public Board loadCustomBoard(Stage stage) {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Open JSON Board File");
    fileChooser.getExtensionFilters().add(
        new FileChooser.ExtensionFilter("JSON Files", "*.json"));
    File boardsDir = new File("src/main/resources/boards");
    if (boardsDir.exists()) {
      fileChooser.setInitialDirectory(boardsDir);
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