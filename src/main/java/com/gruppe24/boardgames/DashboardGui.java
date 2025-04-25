package com.gruppe24.boardgames;

import static com.gruppe24.boardgames.laddergame.models.board.BoardType.CLASSIC;
import static com.gruppe24.utils.StyleUtils.styleNormalButton;

import com.gruppe24.boardgames.laddergame.models.Player;
import com.gruppe24.boardgames.laddergame.models.board.BoardType;
import com.gruppe24.boardgames.laddergame.view.LadderGameApp;
import com.gruppe24.boardgames.tictactoe.TicTacToeApp;
import com.gruppe24.utils.StyleUtils;
import com.gruppe24.utils.Validators;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * This class is the main menu for the game. It allows the user to select which game to play.
 */
public class DashboardGui extends Application {

  private List<Player> players;

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

    initializePlayers();

    GridPane gamesGrid = createGamesGrid(primaryStage);
    mainLayout.setCenter(gamesGrid);

    VBox playerMenu = createPlayerMenu();
    mainLayout.setLeft(playerMenu);

    primaryStage.setScene(mainScene);
    primaryStage.show();
  }

  private VBox createPlayerMenu() {
    VBox playerMenu = new VBox(20);
    StyleUtils.stylePanel(playerMenu);
    playerMenu.setMinWidth(300);
    playerMenu.setAlignment(Pos.TOP_CENTER);
    return playerMenu;
  }


  private void initializePlayers() {
    players = new ArrayList<>();
    Player testPlayer = new Player("Test Player", Color.BLUE);
    players.add(testPlayer);
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
        "pictures/boardpictures/specialBoard.jpg", event -> {});

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
