package com.gruppe24.BoardGames.LadderGame.View;

import com.gruppe24.BoardGames.LadderGame.Models.Board.BoardType;
import com.gruppe24.BoardGames.LadderGame.Models.Player;
import com.gruppe24.Utils.StyleUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Setup extends Application {

  private List<Player> players;
  private BoardType boardType = BoardType.CLASSIC;

  public Setup(){
    this.players = new ArrayList<>();
  }

  public void setBoardType(BoardType boardType) {
    this.boardType = boardType;
  }

  @Override
  public void start(Stage primaryStage) {
    primaryStage.setTitle("Laddergame setup...");

    GridPane gridPane = new GridPane();
    Scene scene = new Scene(gridPane, 1000, 850);
    gridPane.setAlignment(Pos.CENTER);
    gridPane.setVgap(25);
    gridPane.setHgap(20);
    gridPane.setStyle("-fx-background-color: #2e49ae;");

    //text user amount
    Text text = new Text("How many players are there?:");
    text.setStyle("-fx-font-size: 20px; -fx-fill: white; -fx-font-weight: bold;");
    gridPane.add(text,1,1,6,1);


    //text name of users
    Text nameText = new Text("Write down the names in the bars");
    nameText.setStyle("-fx-font-size: 20px; -fx-fill: white; -fx-font-weight: bold;");
    gridPane.add(nameText,3,4,6,1);

    //textfield for names
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

    //comboxes for colour
    ComboBox<String> colorComboBox1 = new ComboBox<>();
    colorComboBox1.getItems().addAll("Red","Blue","Green","Yellow");
    colorComboBox1.setValue("Red"); //default
    gridPane.add(colorComboBox1,1,5);
    ComboBox<String> colorComboBox2 = new ComboBox<>();
    colorComboBox2.getItems().addAll("Red","Blue","Green","Yellow");
    colorComboBox2.setValue("Blue"); //default
    gridPane.add(colorComboBox2,1,6);
    ComboBox<String> colorComboBox3 = new ComboBox<>();
    colorComboBox3.getItems().addAll("Red","Blue","Green","Yellow");
    colorComboBox3.setValue("Green"); //default
    gridPane.add(colorComboBox3,1,7);
    ComboBox<String> colorComboBox4 = new ComboBox<>();
    colorComboBox4.getItems().addAll("Red","Blue","Green","Yellow");
    colorComboBox4.setValue("Yellow"); //default
    gridPane.add(colorComboBox4,1,8);

    //Collection of names and colours
    List<TextField> names = Arrays.asList(nameTextField1,nameTextField2,nameTextField3,nameTextField4);
    List<ComboBox<String>> colors = Arrays.asList(colorComboBox1,colorComboBox2,colorComboBox3,colorComboBox4);

    //calculating amount of players
    final int[] AmountPlayers = {0};
    Button nextButton = new Button("SUBMIT");
    StyleUtils.styleNormalButton(nextButton);
    gridPane.add(nextButton,9,8);
    nextButton.setOnAction(event -> {
      int tempAmountPlayers = countPlayers(nameTextField1,nameTextField2,nameTextField3,nameTextField4);
      AmountPlayers[0] = tempAmountPlayers;

      //getting players
      players.clear(); //removing potential static
      for(int i = 0; i < AmountPlayers[0]; i++){
        String name = names.get(i).getText();
        Color color = getColorFromString(colors.get(i).getValue());
        players.add(new Player(name,color));
      }

      new LadderGame(players, boardType).start(primaryStage);
    });


    primaryStage.setScene(scene);
    primaryStage.show();
  }

  //Intellij-assisted
  public Color getColorFromString(String colorName) {
    // Convert the input colorName to lowercase to handle case insensitivity
    return switch (colorName.toLowerCase()) {
      case "red" -> Color.DARKRED;   // If the input is "red", return Color.RED
      case "blue" -> Color.BLUE;  // If the input is "blue", return Color.BLUE
      case "green" -> Color.GREEN; // If the input is "green", return Color.GREEN
      case "yellow" -> Color.YELLOW; // If the input is "yellow", return Color.YELLOW
      default ->
          Color.RED; // If the input doesn't match any known color, return Color.RED by default
    };
  }

  /**
   * TextField... is the equivelent to TextField[], saying it is an array. The difference is that
   * with ... it is unnessessary to put the textFields inside a package i.e example[] = {a,b,c,...},
   * and just put a,b,c in the parameter
   * @param textField
   * @return
   */
  public int countPlayers(TextField... textField){ //AI-based
    int playerCount = 0;
    for(TextField text : textField){
      if(!text.getText().isEmpty()){
        playerCount++;
      }
    } return playerCount;
  }
}
