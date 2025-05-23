package com.gruppe24.boardgames;

import com.gruppe24.boardgames.laddergame.controller.LadderGameController;
import com.gruppe24.boardgames.laddergame.models.Player;
import com.gruppe24.boardgames.laddergame.models.board.Board;
import com.gruppe24.boardgames.laddergame.models.board.BoardType;
import com.gruppe24.boardgames.laddergame.view.LadderGameApp;
import com.gruppe24.boardgames.monopolylite.view.MonopolyApp;
import com.gruppe24.exeptions.FileHandlingException;
import com.gruppe24.exeptions.InvalidPlayerException;
import com.gruppe24.filehandling.FileHandler;
import com.gruppe24.filehandling.JsonBoardReader;
import com.gruppe24.observerpattern.EventType;
import com.gruppe24.observerpattern.GameLogger;
import com.gruppe24.observerpattern.GameSubject;
import com.gruppe24.utils.StyleUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
  private LadderGameController ladderGameController;
  private Stage stage;
  private final GameLogger gameLogger = new GameLogger();

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
    GameLogger.getLogger().log(Level.INFO, "Dashboard started");
    GameSubject.gameSubjectInstance().registerListener(gameLogger);

    this.stage = primaryStage;
    this.ladderGameController = new LadderGameController();
    this.players = ladderGameController.getPlayerList();

    BorderPane mainLayout = new BorderPane();
    mainLayout.setStyle("-fx-background-color: #3a5ad7;");

    Label title = new Label("Game Dashboard!");
    title.setStyle("-fx-font-size: 40px; -fx-text-fill: #ffffff; -fx-font-weight: bold;");
    BorderPane titlePane = new BorderPane();
    titlePane.setCenter(title);
    mainLayout.setTop(titlePane);

    GridPane gamesGrid = createGamesGrid(primaryStage);
    mainLayout.setCenter(gamesGrid);

    VBox playerMenu = createPlayerMenu();
    mainLayout.setLeft(playerMenu);

    Scene mainScene = new Scene(mainLayout, 1000, 850);
    primaryStage.setScene(mainScene);
    primaryStage.show();
  }

  /**
   * Creates the start meny grids for the four options.
   *
   * @param primaryStage primary stage.
   * @return the games grids.
   */
  private GridPane createGamesGrid(Stage primaryStage) {
    GridPane gamesGrid = new GridPane();
    gamesGrid.setAlignment(Pos.CENTER);
    gamesGrid.setVgap(20);
    gamesGrid.setHgap(20);

    VBox classicLadderGameBox = createGameBox("Classic Laddergame",
        "pictures/boardpictures/classicBoard.jpg", event -> {
          new LadderGameApp(ladderGameController
              .getPlayerList(), BoardType.CLASSIC).start(primaryStage);

          GameSubject.gameSubjectInstance()
              .notifyListener(EventType.GAME_STARTED, ladderGameController.getPlayerList());
        });

    VBox specialLadderGameBox = createGameBox("Special Laddergame",
        "pictures/boardpictures/specialBoard.jpg", event -> {
          new LadderGameApp(ladderGameController
              .getPlayerList(), BoardType.SPECIAL).start(primaryStage);

          GameSubject.gameSubjectInstance()
              .notifyListener(EventType.GAME_STARTED, ladderGameController.getPlayerList());
        });

    VBox monopolyLiteBox = createGameBox("Monopoly Lite",
        "pictures/boardpictures/MonopolyLite.png", event ->
            new MonopolyApp().start(primaryStage)
    );

    VBox jsonBoardBox = createGameBox("Use your own JSON Board",
        "pictures/boardpictures/ownJsonBoard.jpg", event ->
            loadCustomBoard(primaryStage));

    gamesGrid.add(classicLadderGameBox, 0, 0);
    gamesGrid.add(specialLadderGameBox, 1, 0);

    gamesGrid.add(monopolyLiteBox, 0, 1);
    gamesGrid.add(jsonBoardBox, 1, 1);

    return gamesGrid;
  }

  /**
   * Creates a game box with a title and a button to start the game.
   *
   * @param title the title of the game
   * @return the VBox containing the game information
   * @AI_Assisted javafx.event.EventHandler, new Insets
   */
  private VBox createGameBox(String title, String imagePath, EventHandler<ActionEvent> action) {
    VBox gameBox = new VBox(10);
    StyleUtils.stylePanel(gameBox);
    gameBox.setAlignment(Pos.CENTER);
    gameBox.setPrefSize(300, 300);
    gameBox.setPadding(new Insets(5));

    Label gameLabel = new Label(title);
    gameLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: #ffffff; -fx-font-weight: bold;");

    ImageView gameImage = new ImageView(imagePath);
    gameImage.setFitWidth(200);
    gameImage.setFitHeight(200);

    Button playbutton = new Button("Play");
    StyleUtils.styleNormalButton(playbutton);
    playbutton.setOnAction(action);

    gameBox.getChildren().addAll(gameLabel, gameImage, playbutton);
    return gameBox;
  }

  /**
   * Creates the player menu on the left side of the screen.
   *
   * @AI_Assisted Try-catch for loading and saving players
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
    StyleUtils.styleNormalButton(addPlayerButton);
    addPlayerButton.setOnAction(e -> {
      try {
        ladderGameController.addPlayer();
      } catch (IllegalArgumentException ex) {
        GameLogger.getLogger().log(Level.WARNING, "Error adding player, {0}", ex.getMessage());
        return;
      }
      updatePlayerList();
    });

    Button removePlayerButton = new Button("-");
    StyleUtils.styleNormalButton(removePlayerButton);
    removePlayerButton.setOnAction(e -> {
      try {
        ladderGameController.removePlayer();
      } catch (IllegalArgumentException ex) {
        GameLogger.getLogger().log(Level.WARNING, "Error removing player, {0}", ex.getMessage());
        return;
      }
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
      try {
        boolean success = FileHandler.savePlayers(playersToSave, stage);
        if (success) {
          showAlert("Players saved successfully");
        } else {
          showAlert("Failed to save players");
        }
      } catch (FileHandlingException ex) {
        showAlert("Error saving players: " + ex.getMessage());
        GameLogger.getLogger().log(Level.WARNING, "Error saving players: {0}", ex.getMessage());
      }
    });

    Button loadPlayersButton = new Button("Load Players");
    StyleUtils.styleNormalButton(loadPlayersButton);
    loadPlayersButton.setOnAction(e -> {
      try {
        List<Player> loadedPlayers = FileHandler.loadPlayers(stage);
        if (loadedPlayers != null && !loadedPlayers.isEmpty()) {
          populateFieldsWithPlayers(loadedPlayers);
          showAlert("Players loaded successfully");
        } else {
          showAlert("No players loaded");
        }
      } catch (FileHandlingException ex) {
        showAlert("Error loading players: " + ex.getMessage());
        GameLogger.getLogger().log(Level.WARNING, "Error loading players: {0}", ex.getMessage());
      }
    });

    buttonBox.getChildren().addAll(removePlayerButton, addPlayerButton);

    HBox saveLoadBox = new HBox(10);
    saveLoadBox.setAlignment(Pos.CENTER);
    saveLoadBox.getChildren().addAll(savePlayersButton, loadPlayersButton);

    playerMenu.getChildren().addAll(playerLabel, buttonBox, playerList, saveLoadBox);
    updatePlayerList();
    return playerMenu;
  }

  /**
   * Updates the list of players in GUI.
   */
  private void updatePlayerList() {
    this.players = ladderGameController.getPlayerList();

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
   * @param player    the player to display
   * @param iconIndex the index of the player
   * @return the HBox containing the player information
   * @AI_Assisted nameField.textProperty().addListener(...)
   */
  private HBox createPlayerBox(Player player, int iconIndex) {
    HBox playerBox = new HBox(10);
    playerBox.setAlignment(Pos.CENTER_LEFT);
    playerBox.setPadding(new Insets(5));
    playerBox.setStyle("-fx-background-color: #FFFFFF33; -fx-background-radius: 5;");

    ImageView playerIcon = new ImageView(player.getPlayerPiece().getImage());
    playerIcon.setFitWidth(60);
    playerIcon.setFitHeight(60);

    TextField nameField = new TextField(player.getName());
    nameField.textProperty().addListener((observable, oldValue,
        newValue) -> player.setName(newValue));

    Button changeIconButton = new Button("⟳");
    StyleUtils.styleNormalButton(changeIconButton);
    changeIconButton.setOnAction(e -> {
      ladderGameController.cyclePlayerIcon(iconIndex);
      updatePlayerList();
    });

    playerBox.getChildren().addAll(playerIcon, nameField, changeIconButton);
    return playerBox;
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
    ladderGameController.setPlayersList(loadedPlayers);

    this.players = ladderGameController.getPlayerList();

    updatePlayerList();
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

  /**
   * Loads a custom board from JSON.
   *
   * @param primaryStage the primary stage.
   */
  private void loadCustomBoard(Stage primaryStage) {
    JsonBoardReader jsonBoardReader = new JsonBoardReader();
    Board customBoard = jsonBoardReader.loadCustomBoard(primaryStage);
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
   * @param board        the custom board
   */
  private void startLadderGameWithCustomBoard(Stage primaryStage, Board board) {
    List<Player> activePlayers = collectPlayersFromFields();

    try {
      new LadderGameApp(activePlayers, board).start(primaryStage);
    } catch (Exception e) {
      showAlert("Error starting game: " + e.getMessage());
    }
  }
}
