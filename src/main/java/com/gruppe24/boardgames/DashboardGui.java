package com.gruppe24.boardgames;

import static com.gruppe24.boardgames.laddergame.models.board.BoardType.CLASSIC;
import static com.gruppe24.boardgames.laddergame.models.board.BoardType.SPECIAL;
import static com.gruppe24.utils.StyleUtils.styleNormalButton;

import com.gruppe24.boardgames.laddergame.models.Player;
import com.gruppe24.boardgames.laddergame.view.LadderGameApp;
import com.gruppe24.utils.StyleUtils;
import com.gruppe24.utils.Validators;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * This class is the main menu for the game. It allows the user to select which game to play.
 */
public class DashboardGui extends Application {

  private List<Player> players;
  private VBox playerList;

  /**
   * The main method is the entry point of the application.
   *
   * @param primaryStage the primary stage for this application.
   */
  @Override
  public void start(Stage primaryStage) {
    if (primaryStage == null) {
      throw new IllegalArgumentException("Parameter Stage cannot be empty");
    }
    Validators.getLogger().log(Level.INFO, "Dashboard started");

    BorderPane mainLayout = new BorderPane();
    mainLayout.setStyle("-fx-background-color: #3a5ad7;");
    Scene mainScene = new Scene(mainLayout, 1000, 850);

    Label title = new Label("Game Dashboard!");
    title.setStyle("-fx-font-size: 40px; -fx-text-fill: #ffffff; -fx-font-weight: bold;");
    BorderPane titlePane = new BorderPane();
    titlePane.setCenter(title);
    mainLayout.setTop(titlePane);

    GridPane gamesGrid = createGamesGrid(primaryStage);
    mainLayout.setCenter(gamesGrid);

    VBox playerMenu = createPlayerMenu();
    mainLayout.setLeft(playerMenu);

    primaryStage.setScene(mainScene);
    primaryStage.show();
  }

  /**
   * Creates the player menu on the left side of the screen.
   *
   * @AI_Assisted
   */
  private VBox createPlayerMenu() {
    VBox playerMenu = new VBox(20);
    StyleUtils.stylePanel(playerMenu);
    playerMenu.setMinWidth(300);
    playerMenu.setAlignment(Pos.TOP_CENTER);

    Label playerLabel = new Label("Players");
    playerLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: #ffffff; -fx-font-weight: bold;");

    playerList = new VBox(10);
    playerList.setAlignment(Pos.TOP_CENTER);

    HBox buttonBox = new HBox(10);
    buttonBox.setAlignment(Pos.BOTTOM_CENTER);

    Button addPlayerButton = new Button("+");
    styleNormalButton(addPlayerButton);
    addPlayerButton.setOnAction(e -> {addPlayer();});

    Button removePlayerButton = new Button("-");
    styleNormalButton(removePlayerButton);
    removePlayerButton.setOnAction(e -> {removePlayer();});

    Button savePlayersButton = new Button("Save Players");
    styleNormalButton(savePlayersButton);

    Button loadPlayersButton = new Button("Load Players");
    styleNormalButton(loadPlayersButton);

    buttonBox.getChildren().addAll(addPlayerButton, removePlayerButton);

    HBox saveLoadBox = new HBox(10);
    saveLoadBox.setAlignment(Pos.CENTER);
    saveLoadBox.getChildren().addAll(savePlayersButton, loadPlayersButton);

    playerMenu.getChildren().addAll(playerLabel, playerList, buttonBox, saveLoadBox);

    if (players == null) {
      addPlayer();
    }

    return playerMenu;
  }

  private void addPlayer() {
    if (players == null) {
      players = new ArrayList<>();
    }
    if (players.size() >= 5) {
      return;
    }

    Player player = new Player("Player " + (players.size() + 1), getNextIcon());
    players.add(player);
    updatePlayerList();
  }

  private void removePlayer() {
    if (players.size() > 1) {
      players.removeLast();
      updatePlayerList();
    }
  }

  private Image getNextIcon() {
    String[] iconPaths = {
        "pictures/jpgIcons/mario.jpg",
        "pictures/jpgIcons/luigi.jpg",
        "pictures/jpgIcons/wario.jpg",
        "pictures/jpgIcons/waluigi.jpg",
        "pictures/jpgIcons/donkeykong.jpg",
    };
    String selectedPath = iconPaths[players.size() % iconPaths.length];
    return new Image(
        Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(selectedPath)));
  }

  private void updatePlayerList() {
    playerList.getChildren().clear();

    for (int i = 0; i < players.size(); i++) {
      Player player = players.get(i);
      HBox playerBox = createPlayerBox(player, i);
      playerList.getChildren().add(playerBox);
    }
  }

  /**
   * Creates a player box with an icon, name field, and change image button.
   *
   * @param player the player to display
   * @param index  the index of the player
   * @return the HBox containing the player information
   *
   * @AI_Assisted javafx.scene.shape.Circle, nameField.setOnAction([x]),
   */
  private HBox createPlayerBox(Player player, int index) {
    HBox playerBox = new HBox(10);
    playerBox.setAlignment(Pos.CENTER_LEFT);
    playerBox.setPadding(new Insets(5));
    playerBox.setStyle("-fx-background-color: #FFFFFF33; -fx-background-radius: 5;");

    ImageView playerIcon = new ImageView(player.getPlayerPiece().getImage());
    playerIcon.setFitWidth(30);
    playerIcon.setFitHeight(30);

    javafx.scene.shape.Circle clip = new javafx.scene.shape.Circle(15);
    clip.setCenterX(15);
    clip.setCenterY(15);
    playerIcon.setClip(clip);

    TextField nameField = new TextField(player.getName());
    nameField.setOnAction(event -> {
      player.setName(nameField.getText());
    });

    playerBox.getChildren().addAll(playerIcon, nameField);
    return playerBox;
  }

  private GridPane createGamesGrid(Stage primaryStage) {
    GridPane gamesGrid = new GridPane();
    gamesGrid.setAlignment(Pos.CENTER);
    gamesGrid.setVgap(20);
    gamesGrid.setHgap(20);

    VBox classicLadderGameBox = createGameBox("Classic Laddergame",
        "pictures/boardpictures/classicBoard.jpg", event ->
            new LadderGameApp(players, CLASSIC).start(primaryStage));

    VBox specialLadderGameBox = createGameBox("Special Laddergame",
        "pictures/boardpictures/specialBoard.jpg", event ->
            new LadderGameApp(players, SPECIAL).start(primaryStage));

    gamesGrid.add(classicLadderGameBox, 0, 0);
    gamesGrid.add(specialLadderGameBox, 1, 0);

    return gamesGrid;
  }

  /**
   * Creates a game box with a title and a button to start the game.
   *
   * @AI_Assisted javafx.event.EventHandler, ImageView, new Insets,
   *
   * @param title
   * @return
   */
  private VBox createGameBox(String title, String imagePath,
      javafx.event.EventHandler<javafx.event.ActionEvent> action) {

    VBox gameBox = new VBox(10);
    gameBox.setAlignment(Pos.CENTER);
    StyleUtils.stylePanel(gameBox);
    gameBox.setPrefSize(300, 300);
    gameBox.setPadding(new Insets(5));

    Label gameLabel = new Label(title);
    gameLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: #ffffff; -fx-font-weight: bold;");

    ImageView gameImage = new ImageView(imagePath);;
    gameImage.setFitWidth(200);
    gameImage.setFitHeight(200);

    Button playbutton = new Button("Play");
    styleNormalButton(playbutton);
    playbutton.setOnAction(action);

    gameBox.getChildren().addAll(gameLabel, gameImage, playbutton);
    return gameBox;
  }

}
