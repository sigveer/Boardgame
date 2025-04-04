package com.gruppe24.BoardGames.LadderGame.View;

import com.gruppe24.BoardGames.LadderGame.Models.Board.BoardType;
import com.gruppe24.BoardGames.LadderGame.Models.Player;
import com.gruppe24.FileHandling.CSVPlayerReader;
import com.gruppe24.FileHandling.CSVPlayerWriter;
import com.gruppe24.Utils.ColorUtil;
import com.gruppe24.Utils.StyleUtils;
import com.gruppe24.Utils.Validators;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.FileChooser;
import java.io.File;

public class Setup extends Application {

  private List<Player> players;
  private BoardType boardType = BoardType.CLASSIC;

  public Setup(){
    this.players = new ArrayList<>();
  }

  public void setBoardType(BoardType boardType) {
    if(boardType == null){
      throw new IllegalArgumentException("Parameter boardType cannot be empty");
    }
    this.boardType = boardType;
  }

  @Override
  public void start(Stage primaryStage) {
    if(primaryStage == null){
      throw new IllegalArgumentException("Parameter Stage cannot be empty");
    }
    Validators.getLogger().log(Level.INFO, "Setup started");

    primaryStage.setTitle("Laddergame setup...");

    GridPane gridPane = new GridPane();
    Scene scene = new Scene(gridPane, 1000, 850);
    gridPane.setAlignment(Pos.CENTER);
    gridPane.setVgap(25);
    gridPane.setHgap(20);
    gridPane.setStyle("-fx-background-color: #2e49ae;");

    Text text = new Text("How many players are there?:");
    text.setStyle("-fx-font-size: 20px; -fx-fill: white; -fx-font-weight: bold;");
    gridPane.add(text,1,1,6,1);

    Text nameText = new Text("Write down the names in the bars");
    nameText.setStyle("-fx-font-size: 20px; -fx-fill: white; -fx-font-weight: bold;");
    gridPane.add(nameText,3,4,6,1);

    TextField nameTextField1 = new TextField();
    nameTextField1.setPromptText("write name here");
    gridPane.add(nameTextField1,3,5);
    TextField nameTextField2 = new TextField();
    nameTextField2.setPromptText("write name here");
    gridPane.add(nameTextField2,3,6);
    TextField nameTextField3 = new TextField();
    nameTextField3.setPromptText("write name here");
    gridPane.add(nameTextField3,3,7);
    TextField nameTextField4 = new TextField();
    nameTextField4.setPromptText("write name here");
    gridPane.add(nameTextField4,3,8);
    TextField nameTextField5 = new TextField();
    nameTextField5.setPromptText("write name here");
    gridPane.add(nameTextField5,3,9);

    ComboBox<String> colorComboBox1 = new ComboBox<>();
    colorComboBox1.getItems().addAll("Red","Blue","Green","Yellow","Purple");
    colorComboBox1.setValue("Red"); //default
    gridPane.add(colorComboBox1,1,5);
    ComboBox<String> colorComboBox2 = new ComboBox<>();
    colorComboBox2.getItems().addAll("Red","Blue","Green","Yellow","Purple");
    colorComboBox2.setValue("Blue"); //default
    gridPane.add(colorComboBox2,1,6);
    ComboBox<String> colorComboBox3 = new ComboBox<>();
    colorComboBox3.getItems().addAll("Red","Blue","Green","Yellow","Purple");
    colorComboBox3.setValue("Green"); //default
    gridPane.add(colorComboBox3,1,7);
    ComboBox<String> colorComboBox4 = new ComboBox<>();
    colorComboBox4.getItems().addAll("Red","Blue","Green","Yellow","Purple");
    colorComboBox4.setValue("Yellow"); //default
    gridPane.add(colorComboBox4,1,8);
    ComboBox<String> colorComboBox5 = new ComboBox<>();
    colorComboBox5.getItems().addAll("Red","Blue","Green","Yellow","Purple");
    colorComboBox5.setValue("Purple"); //default
    gridPane.add(colorComboBox5,1,9);

    //Collection of names and colours
    List<TextField> names = Arrays.asList(nameTextField1,nameTextField2,nameTextField3,nameTextField4,nameTextField5);
    List<ComboBox<String>> colors = Arrays.asList(colorComboBox1,colorComboBox2,colorComboBox3,colorComboBox4,colorComboBox5);

    //calculating amount of players
    Button SubmitButton = new Button("SUBMIT");
    StyleUtils.styleNormalButton(SubmitButton);
    gridPane.add(SubmitButton,7,9);
    SubmitButton.setOnAction(event -> {
      players.clear();

      for(int i = 0; i < names.size(); i++){
        String name = names.get(i).getText();
        if(!name.isEmpty()){
          Color color = ColorUtil.getColorFromString(colors.get(i).getValue());
          players.add(new Player(name,color));
        }
      }

      new LadderGame(players, boardType).start(primaryStage);
    });

    Button savePlayersButton = new Button("Save Players");
    StyleUtils.styleNormalButton(savePlayersButton);
    gridPane.add(savePlayersButton, 7, 5);
    savePlayersButton.setOnAction(event -> {
      List<Player> playersToSave = new ArrayList<>();
      for (int i = 0; i < names.size(); i++) {
        String name = names.get(i).getText();
        if (!name.isEmpty()) {
          Color color = ColorUtil.getColorFromString(colors.get(i).getValue());
          playersToSave.add(new Player(name, color));
        }
      }

      if (playersToSave.isEmpty()) {
        showAlert("No players to save");
        return;
      }

      // Create file chooser for saving
      FileChooser fileChooser = new FileChooser();
      fileChooser.setTitle("Save Players");
      fileChooser.getExtensionFilters().add(
          new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
      fileChooser.setInitialFileName("custom_players.csv");
      fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

      // Show save dialog
      File file = fileChooser.showSaveDialog(primaryStage);

      if (file != null) {
        // Save the players to the selected file
        CSVPlayerWriter writer = new CSVPlayerWriter();
        boolean success = writer.writeToFile(playersToSave, file.getAbsolutePath());

        if (success) {
          showAlert("Players saved successfully");
        } else {
          showAlert("Failed to save players");
        }
      }
    });


    Button loadPlayersButton = new Button("Load Players");
    StyleUtils.styleNormalButton(loadPlayersButton);
    gridPane.add(loadPlayersButton, 7, 6);
    loadPlayersButton.setOnAction(event -> {
      FileChooser fileChooser = new FileChooser();
      fileChooser.setTitle("Load Players");
      fileChooser.getExtensionFilters().add(
          new FileChooser.ExtensionFilter("CSV Files", "*.csv")
      );
      fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

      // Show open dialog
      File file = fileChooser.showOpenDialog(primaryStage);

      if (file != null) {
        CSVPlayerReader reader = new CSVPlayerReader();
        List<Player> loadedPlayers = (List<Player>) reader.readFromFile(file.getAbsolutePath());

        if (loadedPlayers != null && !loadedPlayers.isEmpty()) {
          // Clear existing fields
          for (TextField field : names) {
            field.clear();
          }

          // Populate fields with loaded players
          for (int i = 0; i < Math.min(loadedPlayers.size(), names.size()); i++) {
            Player player = loadedPlayers.get(i);
            names.get(i).setText(player.getName());

            // Set the correct color in ComboBox
            String colorName = ColorUtil.getColorName(player.getPlayerPiece().getFill());
            colors.get(i).setValue(colorName.substring(0, 1).toUpperCase() + colorName.substring(1));
          }

          showAlert("Players loaded successfully");
        } else {
          showAlert("No players found in the file or file format is incorrect");
        }
      }
    });

    primaryStage.setScene(scene);
    primaryStage.show();
  }

  private void showAlert(String message) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Player Management");
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
  }
}

