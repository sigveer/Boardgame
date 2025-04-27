package com.gruppe24.boardgames;

import static com.gruppe24.boardgames.laddergame.models.board.BoardType.CLASSIC;
import static com.gruppe24.boardgames.laddergame.models.board.BoardType.SPECIAL;
import static com.gruppe24.utils.StyleUtils.styleNormalButton;

import com.gruppe24.boardgames.laddergame.controller.BoardController;
import com.gruppe24.boardgames.laddergame.controller.PlayerController;
import com.gruppe24.boardgames.laddergame.models.Player;
import com.gruppe24.boardgames.laddergame.models.board.Board;
import com.gruppe24.boardgames.laddergame.view.LadderGameApp;
import com.gruppe24.boardgames.tictactoe.TicTacToeApp;
import com.gruppe24.filehandling.CsvPlayerReader;
import com.gruppe24.filehandling.CsvPlayerWriter;
import com.gruppe24.filehandling.JsonBoardReader;
import com.gruppe24.utils.StyleUtils;
import com.gruppe24.utils.Validators;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * This class is the main menu for the game. It allows the user to select which game to play.
 */
public class DashboardGui extends Application {

  private List<Player> players;
  private VBox playerList;
  private PlayerController playerController;
  private Stage stage;
  private BoardController boardController = new BoardController();

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

    this.stage = primaryStage;

    this.playerController = new PlayerController(boardController);
    this.players = playerController.getPlayers();

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
    addPlayerButton.setOnAction(e -> {
      playerController.addPlayer();
      updatePlayerList();
    });

    Button removePlayerButton = new Button("-");
    styleNormalButton(removePlayerButton);
    removePlayerButton.setOnAction(e -> {
      playerController.removePlayer();
      updatePlayerList();
    });

    Button savePlayersButton = new Button("Save Players");
    StyleUtils.styleNormalButton(savePlayersButton);
    savePlayersButton.setOnAction(e -> {
      List<Player> playersToSave = collectPlayersFromFields();
      if (playersToSave.isEmpty()) {
        showAlert("No players to save");
        return;
      }
      boolean success = CsvPlayerWriter.savePlayers(playersToSave, stage);
      if (success) {
        showAlert("Players saved successfully");
      } else {
        showAlert("Failed to save players");
      }
    });

    Button loadPlayersButton = new Button("Load Players");
    StyleUtils.styleNormalButton(loadPlayersButton);
    loadPlayersButton.setOnAction(e -> {
      List<Player> loadedPlayers = CsvPlayerReader.loadPlayers(stage);
      if (loadedPlayers != null && !loadedPlayers.isEmpty()) {
        populateFieldsWithPlayers(loadedPlayers);
        showAlert("Players loaded successfully");
      } else {
        showAlert("No players loaded");
      }
    });

    buttonBox.getChildren().addAll(addPlayerButton, removePlayerButton);

    HBox saveLoadBox = new HBox(10);
    saveLoadBox.setAlignment(Pos.CENTER);
    saveLoadBox.getChildren().addAll(savePlayersButton, loadPlayersButton);

    playerMenu.getChildren().addAll(playerLabel, buttonBox, playerList, saveLoadBox);

    updatePlayerList();

    return playerMenu;
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
   * Collects players from the current player list.
   *
   * @return A list of players with their current names and icons.
   */
  private List<Player> collectPlayersFromFields() {
    List<Player> collectedPlayers = new ArrayList<>();

    for (Player player : players) {
      if (player != null && !player.getName().isEmpty()) {
        collectedPlayers.add(player);
      }
    }

    return collectedPlayers;
  }

  /**
   * Updates the player list with loaded players.
   *
   * @param loadedPlayers The list of players to populate the fields with.
   */
  private void populateFieldsWithPlayers(List<Player> loadedPlayers) {
    players.clear();
    players.addAll(loadedPlayers);

    updatePlayerList();
  }

  /**
   * Creates a player box with an icon, name field, and change image button.
   *
   * @param player the player to display
   * @param index  the index of the player
   * @return the HBox containing the player information
   * @AI_Assisted nameField.setOnAction([x]).....
   */
  private HBox createPlayerBox(Player player, int index) {
    HBox playerBox = new HBox(10);
    playerBox.setAlignment(Pos.CENTER_LEFT);
    playerBox.setPadding(new Insets(5));
    playerBox.setStyle("-fx-background-color: #FFFFFF33; -fx-background-radius: 5;");

    ImageView playerIcon = new ImageView(player.getPlayerPiece().getImage());
    playerIcon.setFitWidth(60);
    playerIcon.setFitHeight(60);

    TextField nameField = new TextField(player.getName());
    nameField.textProperty().addListener((observable, oldValue, newValue) -> {
      player.setName(newValue);
    });

    Button changeIconButton = new Button("⟳");
    styleNormalButton(changeIconButton);
    changeIconButton.setOnAction(e -> {
      playerController.cyclePlayerIcon(player, index);
      updatePlayerList();
    });

    playerBox.getChildren().addAll(playerIcon, nameField, changeIconButton);

    return playerBox;
  }

  private GridPane createGamesGrid(Stage primaryStage) {
    GridPane gamesGrid = new GridPane();
    gamesGrid.setAlignment(Pos.CENTER);
    gamesGrid.setVgap(20);
    gamesGrid.setHgap(20);

    VBox classicLadderGameBox = createGameBox("Classic Laddergame",
        "pictures/boardpictures/classicBoard.jpg", event ->
            new LadderGameApp(playerController.getPlayers(), CLASSIC).start(primaryStage));

    VBox specialLadderGameBox = createGameBox("Special Laddergame",
        "pictures/boardpictures/specialBoard.jpg", event ->
            new LadderGameApp(playerController.getPlayers(), SPECIAL).start(primaryStage));

    VBox ticTacToeGameBox = createGameBox("Tic Tac Toe",
        "pictures/boardpictures/TicTacToePicture.jpg", event ->
            new TicTacToeApp().start(primaryStage));

    VBox jsonBoardBox = createGameBox("Use your own JSON Board",
        "pictures/boardpictures/ownJsonBoard.jpg", event ->
            loadCustomBoard(primaryStage));

    gamesGrid.add(classicLadderGameBox, 0, 0);
    gamesGrid.add(specialLadderGameBox, 1, 0);

    gamesGrid.add(ticTacToeGameBox, 0, 1);
    gamesGrid.add(jsonBoardBox, 1, 1);

    return gamesGrid;
  }

  /**
   * Creates a game box with a title and a button to start the game.
   *
   * @param title
   * @return
   * @AI_Assisted javafx.event.EventHandler, ImageView, new Insets,
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

    ImageView gameImage = new ImageView(imagePath);

    gameImage.setFitWidth(200);
    gameImage.setFitHeight(200);

    Button playbutton = new Button("Play");
    styleNormalButton(playbutton);
    playbutton.setOnAction(action);

    gameBox.getChildren().addAll(gameLabel, gameImage, playbutton);
    return gameBox;
  }

  /**
   * Displays an alert with the given message.
   *
   * @param message The message to be displayed in the alert.
   */
  private void showAlert(String message) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Player Management");
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
  }

  private void loadCustomBoard(Stage primaryStage) {
    Board customBoard = boardController.loadCustomBoard(primaryStage);
    if (customBoard != null) {
      startLadderGameWithCustomBoard(primaryStage, customBoard);
    } else {
      showAlert("Failed to load board from file");
    }
  }

  /**
   * Starts the LadderGameApp with a custom board.
   *
   * @param primaryStage the primary stage
   * @param board the custom board
   */
  private void startLadderGameWithCustomBoard(Stage primaryStage, Board board) {
    // Get players for the game
    List<Player> activePlayers = collectPlayersFromFields();

    // Start game with custom board
    try {
      new LadderGameApp(activePlayers, board).start(primaryStage);
    } catch (Exception e) {
      showAlert("Error starting game: " + e.getMessage());
    }
  }
}
