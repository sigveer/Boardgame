package com.gruppe24.boardgames.laddergame.view;

import com.gruppe24.boardgames.DashboardGui;
import com.gruppe24.boardgames.laddergame.controller.BoardController;
import com.gruppe24.boardgames.laddergame.controller.PlayerController;
import com.gruppe24.boardgames.laddergame.models.Dice;
import com.gruppe24.boardgames.laddergame.models.Player;
import com.gruppe24.boardgames.laddergame.models.board.Board;
import com.gruppe24.boardgames.laddergame.models.board.BoardType;
import com.gruppe24.utils.StyleUtils;
import com.gruppe24.utils.Validators;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Class that represents the LadderGame.
 */
public class LadderGameApp extends Application {

  private final Board board;
  BoardController boardController = new BoardController();
  PlayerController playerController = new PlayerController(boardController);
  private final List<Player> players;
  private static final int tileSize = 75;
  private int currentPlayerIndex = 0;
  private Label diceResultLabel;
  private Label currentPlayerLabel;
  private Label ladderUpOrDownCheck;
  private Label isFrozenLabel;
  private final Dice dice = new Dice(2);

//  /**
//   * Constructor for LadderGame.
//   *
//   * @param players list of players
//   */
//  public LadderGameApp(List<Player> players) {
//    this(players, BoardType.CLASSIC);
//  }

  /**
   * Constructor for LadderGame.
   *
   * @param players list of players
   * @param boardType type of board
   */
  public LadderGameApp(List<Player> players, BoardType boardType) {
    if (players == null || players.isEmpty()) {
      throw new IllegalArgumentException("Parameter list of players cannot be empty");
    }
    if (boardType == null) {
      throw new IllegalArgumentException("Parameter boardType cannot be empty");
    }
    this.boardController = new BoardController(boardType);
    this.board = boardController.getBoard();
    this.players = players;
  }

  /**
   * Constructor for LadderGame with a custom board.
   *
   * @param players list of players
   * @param customBoard the custom board to use
   */
  public LadderGameApp(List<Player> players, Board customBoard) {
    if (players == null || players.isEmpty()) {
      throw new IllegalArgumentException("Parameter list of players cannot be empty");
    }
    if (customBoard == null) {
      throw new IllegalArgumentException("Parameter customBoard cannot be empty");
    }
    this.boardController = new BoardController(customBoard);
    this.board = customBoard;
    this.players = players;
  }

  /**
   * Main method to start the application.
   *
   * @param primaryStage the primary stage.
   */
  @Override
  public void start(Stage primaryStage) {
    if (primaryStage == null) {
      throw new IllegalArgumentException("Parameter Stage cannot be empty");
    }
    Validators.getLogger().log(Level.INFO, "LadderGame started");

    for (Player player : players) {
      player.initializePlayerPiece(player.getIcon());
    }

    primaryStage.setTitle("Laddergame Classic");

    // Create main layout container using BorderPane
    BorderPane mainLayout = new BorderPane();
    mainLayout.setStyle("-fx-background-color: #3a5ad7;");

    // Board container (left/center section)
    StackPane boardContainer = new StackPane();

    // GridPane for tiles
    GridPane gridPane = new GridPane();
    gridPane.setAlignment(Pos.CENTER);

    // Pane for ladders
    Pane ladderPane = new Pane();
    ladderPane.setMouseTransparent(true);

    // Add components to board container
    boardContainer.getChildren().addAll(gridPane, ladderPane);

    // Control panel (right section)
    VBox controlPanel = new VBox(15);
    controlPanel.setAlignment(Pos.CENTER);
    controlPanel.setPrefWidth(250);
    controlPanel.setMinWidth(200);
    StyleUtils.stylePanel(controlPanel);

    // Set up labels
    currentPlayerLabel = new Label("Current Player: " + players.get(currentPlayerIndex).getName());
    currentPlayerLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: white;");
    currentPlayerLabel.setWrapText(true);
    currentPlayerLabel.setMaxWidth(230);

    diceResultLabel = new Label("Roll the dice!");
    diceResultLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: white;");

    ladderUpOrDownCheck = new Label("");
    ladderUpOrDownCheck.setStyle("-fx-font-size: 16px; -fx-text-fill: white;");

    isFrozenLabel = new Label("");
    isFrozenLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: white;");

    // Dice display area
    Pane dicePane = new Pane();
    dicePane.setPrefHeight(150);

    // Buttons
    Button diceRoll = new Button("Roll Dice");
    diceRoll.setOnAction(event -> rollDiceAndMove(gridPane, primaryStage, dicePane));
    StyleUtils.styleNormalButton(diceRoll);

    Button backToMenu = new Button("Back to Menu");
    backToMenu.setOnAction(event -> new DashboardGui().start(primaryStage));
    StyleUtils.styleNormalButton(backToMenu);

    controlPanel.getChildren().addAll(
        currentPlayerLabel,
        diceResultLabel,
        ladderUpOrDownCheck,
        isFrozenLabel,
        dicePane,
        diceRoll,
        backToMenu
    );

    mainLayout.setCenter(boardContainer);
    mainLayout.setRight(controlPanel);

    Scene scene = new Scene(mainLayout, 1000, 850);

    // Window resize handlers
    scene.widthProperty().addListener((obs, oldVal, newVal) -> {
      ladderPane.getChildren().clear();
      drawBoard(gridPane, ladderPane);
    });

    scene.heightProperty().addListener((obs, oldVal, newVal) -> {
      ladderPane.getChildren().clear();
      drawBoard(gridPane, ladderPane);
    });

    drawBoard(gridPane, ladderPane);

    primaryStage.setScene(scene);
    primaryStage.show();
  }

  /**
   * Method to draw the ladders on board, and enabeling to resize the window.
   *
   * @param ladderPane the pane for ladders.
   * @param image the image of the ladder.
   * @param startTile the starting tile.
   * @param endTile the ending tile.
   */
  public void drawLadder(Pane ladderPane, Image image, Node startTile, Node endTile) {
    Bounds startBounds = startTile.localToScene(startTile.getBoundsInLocal());
    Bounds endBounds = endTile.localToScene(endTile.getBoundsInLocal());

    double startX = (startBounds.getMinX() + startBounds.getMaxX()) / 2;
    double startY = (startBounds.getMinY() + startBounds.getMaxY()) / 2;
    double endX = (endBounds.getMinX() + endBounds.getMaxX()) / 2;
    double endY = (endBounds.getMinY() + endBounds.getMaxY()) / 2;

    //converting scene coordinates to ladderPanes local ones
    Point2D start = ladderPane.sceneToLocal(startX, startY);
    Point2D end = ladderPane.sceneToLocal(endX, endY);

    double dx = end.getX() - start.getX();
    double dy = end.getY() - start.getY();
    double length = Math.hypot(dx, dy);
    double angle = Math.toDegrees(Math.atan2(dy, dx) - 80);

    ImageView ladderView = new ImageView(image);
    ladderView.setPreserveRatio(false);
    ladderView.setFitHeight(length);
    ladderView.setFitWidth(length / 10);

    ladderView.setRotate(angle);

    ladderView.setTranslateX((start.getX() + end.getX()) / 2 - ladderView.getFitWidth() / 2);
    ladderView.setTranslateY((start.getY() + end.getY()) / 2 - length / 2);

    ladderPane.getChildren().add(ladderView);
  }

  /**
   * Draws the board with tiles and ladders.
   *
   * @param gridPane the grid pane to draw on
   * @param ladderPane the pane for ladders
   */
  public void drawBoard(GridPane gridPane, Pane ladderPane) {
    Map<Integer, Node> tileNodeMap = new HashMap<>();

    for (int row = 9; row >= 0; row--) {
      for (int col = 0; col < 9; col++) {
        int tileNumber = (9 - row) % 2 == 0 ? (9 - row) * 9 + col + 1 : (9 - row + 1) * 9 - col;

        StackPane stackPane = new StackPane();
        stackPane.setPrefSize(tileSize, tileSize);

        int tileType = board.getTileType(tileNumber);
        switch (tileType) {
          case -3 -> stackPane.setStyle("-fx-background-color: #F4DA16; "
              + "-fx-border-width: 1.5; -fx-border-color: black;"); // Winning Tile
          case 1 -> stackPane.setStyle("-fx-background-color: #009E22; "
              + "-fx-border-width: 1.5; -fx-border-color: black;"); // Ladder Up
          case 2 -> stackPane.setStyle("-fx-background-color: #E02929; "
              + "-fx-border-width: 1.5; -fx-border-color: black;"); // Ladder Down

          case 3 -> {
            stackPane.setStyle("-fx-background-color: #9D41FF; "
                + "-fx-border-width: 1.5; -fx-border-color: black;"); // Random Teleport
            ImageView teleportImage = new ImageView(new Image(
                "pictures/tilepictures/questionmark.png"));
            teleportImage.setFitWidth(40);
            teleportImage.setFitHeight(40);
            StackPane.setAlignment(teleportImage, Pos.CENTER);
            stackPane.getChildren().add(teleportImage);
          }
          case 4 -> {
            stackPane.setStyle("-fx-background-color: #7CCAEF; "
                + "-fx-border-width: 1.5; -fx-border-color: black;"); // Frozen Tile
            ImageView snowflakeImage = new ImageView(new Image(
                "pictures/tilepictures/snowflake.png"));
            snowflakeImage.setFitWidth(60);
            snowflakeImage.setFitHeight(60);
            StackPane.setAlignment(snowflakeImage, Pos.CENTER);
            stackPane.getChildren().add(snowflakeImage);
          }
          default -> stackPane.setStyle("-fx-background-color: #FDF2F2; "
              + "-fx-border-width: 1.5; -fx-border-color: black;"); // Normal Tile
        }

        //Landing-tile upon special tiles
        for (Integer value : board.getLadderUp().values()) {
          if (tileNumber == value) {
            stackPane.setStyle("-fx-background-color: #9CEA95; "
                + "-fx-border-width: 1.5; -fx-border-color: black;");
          }
        }
        for (Integer value : board.getLadderDown().values()) {
          if (tileNumber == value) {
            stackPane.setStyle("-fx-background-color: #FF9090; "
                + "-fx-border-width: 1.5; -fx-border-color: black;");
          }
        }

        Label numberLabel = new Label(Integer.toString(tileNumber));
        numberLabel.setStyle("-fx-font-weight: bold;");
        numberLabel.setTranslateX(26);
        numberLabel.setTranslateY(30);
        stackPane.getChildren().add(numberLabel);

        stackPane.setAlignment(Pos.CENTER);
        gridPane.add(stackPane, col, row);
        tileNodeMap.put(tileNumber, stackPane);
      }
    }

    Platform.runLater(() -> {
      Image ladderUpImage = new Image("pictures/Ladder.png");

      for (Entry<Integer, Integer> entry : board.getLadderUp().entrySet()) {
        drawLadder(ladderPane, ladderUpImage, tileNodeMap.get(entry.getKey()),
            tileNodeMap.get(entry.getValue()));
      }
      for (Entry<Integer, Integer> entry : board.getLadderDown().entrySet()) {
        drawLadder(ladderPane, ladderUpImage, tileNodeMap.get(entry.getKey()),
            tileNodeMap.get(entry.getValue()));
      }
    });
  }

  /**
   * Rolls the dice and moves the current player.
   *
   * @param gridPane the grid pane to draw on
   * @param primaryStage the primary stage
   * @param dicePane the pane for dices
   */
  private void rollDiceAndMove(GridPane gridPane, Stage primaryStage, Pane dicePane) {
    Player currentPlayer = players.get(currentPlayerIndex);

    if (currentPlayer.isFrozen()) {
      currentPlayer.setFrozen(false);
      isFrozenLabel.setText(currentPlayer.getName() + " is frozen and skips this turn!");

      currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
      currentPlayerLabel.setText("Current Player: " + players.get(currentPlayerIndex).getName());
      return;
    }

    //Get the original position; for animation
    int previousPosition = currentPlayer.getPosition();

    //Update game-logic with dice-roll and display dices
    int diceValue = dice.rollSum();
    int diceValue1 = dice.getDie(0);
    int diceValue2 = dice.getDie(1);

    String dice1Path = dice.dicePath(diceValue1);
    Image dice1 = new Image(dice1Path);
    ImageView dice1Iv = new ImageView(dice1);
    dice1Iv.setX(40);
    dice1Iv.setY(0);
    dice1Iv.setFitHeight(75);
    dice1Iv.setFitWidth(75);

    String dice2Path = dice.dicePath(diceValue2);
    Image dice2 = new Image(dice2Path);
    ImageView dice2Iv = new ImageView(dice2);
    dice2Iv.setX(125);
    dice2Iv.setY(0);
    dice2Iv.setFitHeight(75);
    dice2Iv.setFitWidth(75);

    diceResultLabel.setText("Totalsum: " + diceValue);
    dicePane.getChildren().clear(); //removes old dice
    dicePane.getChildren().addAll(dice1Iv, dice2Iv);

    playerController.handlePlayerTurn(currentPlayer, diceValue);

    //Update animation with the new position
    int newPosition = currentPlayer.getPosition();
    animateAndMove(gridPane, currentPlayer, previousPosition, newPosition, primaryStage);

    //Switch player
    currentPlayerLabel.setText("Current Player: " + players.get(currentPlayerIndex).getName());
    currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
  }

  /**
   * Checks if the current player has won.
   *
   * @param player the current player
   * @param position the current position of the player
   * @param primaryStage the primary stage
   */
  private void checkWinner(Player player, int position, Stage primaryStage) {
    if (boardController.isWinningPosition(position)) {
      Alert alert = new Alert(AlertType.INFORMATION);
      alert.setTitle("Game Over");
      alert.setHeaderText(null);
      alert.setContentText(player.getName() + " has won the game!");

      Platform.runLater(() -> {
        alert.showAndWait();
        new DashboardGui().start(primaryStage);
      });
    }
  }

  /**
   * Adds the player piece to the grid.
   *
   * @param gridPane the grid pane to draw on
   * @param player the current player
   * @param col the column index
   * @param row the row index
   */
  private void addPlayerPieceToGrid(GridPane gridPane, Player player, int col, int row) {
    gridPane.getChildren().remove(player.getPlayerPiece());

    StackPane cellContainer = new StackPane();
    cellContainer.getChildren().add(player.getPlayerPiece());
    StackPane.setAlignment(player.getPlayerPiece(), Pos.CENTER);

    gridPane.add(cellContainer, col, row);
  }

  /**
   * Animates the player piece and moves it to the new position.
   *
   * @param gridPane the grid pane to draw on
   * @param player the current player
   * @param fromPosition the original position
   * @param toPosition the new position
   */
  private void animateAndMove(GridPane gridPane, Player player, int fromPosition, int toPosition, Stage primaryStage) {
    int tileType = boardController.getCheckTileType();

    // --------------- Overshoot ---------------------
    if (fromPosition < 90 && toPosition < 90 && (fromPosition + dice.getSum() > 90)) {
      Timeline bounceTimeline = new Timeline();
      List<KeyFrame> keyFrames = new ArrayList<>();

      // First, animate forward movement to tile 90
      int currentPos = fromPosition;
      double delay = 0.0;

      // Move forward to 90
      while (currentPos < 90) {
        currentPos++;
        int row = 9 - (currentPos - 1) / 9;
        int col = (9 - row) % 2 == 0 ? (currentPos - 1) % 9 : 8 - (currentPos - 1) % 9;

        keyFrames.add(new KeyFrame(
            Duration.seconds(delay += 0.3),
            event -> {
              addPlayerPieceToGrid(gridPane, player, col, row);
              ladderUpOrDownCheck.setText("Overshoot! Bouncing back");
            }
        ));
      }

      // Move backward to final position
      currentPos = 90;
      while (currentPos > toPosition) {
        currentPos--;
        int row = 9 - (currentPos - 1) / 9;
        int col = (9 - row) % 2 == 0 ? (currentPos - 1) % 9 : 8 - (currentPos - 1) % 9;

        keyFrames.add(new KeyFrame(
            Duration.seconds(delay += 0.3),
            event -> {
              addPlayerPieceToGrid(gridPane, player, col, row);
              ladderUpOrDownCheck.setText("Overshoot! Bounced back");
            }
        ));
      }


      bounceTimeline.getKeyFrames().addAll(keyFrames);
      bounceTimeline.play();
      ladderUpOrDownCheck.setText("");
      return;
    }

    // --------------- Frozen Tile ---------------------
    if (tileType == 4) {
      Timeline timeline = new Timeline();
      timeline.setCycleCount(1);

      int steps = toPosition - fromPosition;
      int currentPosition = fromPosition;
      isFrozenLabel.setText("Player Frozen!");

      for (int i = 0; i < steps; i++) {
        currentPosition++;
        // Calculate into coordinates
        int row = 9 - (currentPosition - 1) / 9;
        int col = (9 - row) % 2 == 0 ? (currentPosition - 1) % 9 : 8 - (currentPosition - 1) % 9;

        KeyFrame keyFrame = new KeyFrame(
            Duration.seconds((i + 1) * 0.3),
            event -> {
              addPlayerPieceToGrid(gridPane, player, col, row);
            }
        );
        timeline.getKeyFrames().add(keyFrame);
      }
      timeline.play();
      return;
    }
    isFrozenLabel.setText("");

    // --------------- Teleport Tile ------------------
    if (tileType == 3) {
      int specialTilePosition = boardController.getSpecialTilePosition();

      // First, directly move to teleport tile position
      int teleportRow = 9 - (specialTilePosition - 1) / 9;
      int teleportCol = (9 - teleportRow) % 2 == 0 ? (specialTilePosition - 1) % 9 : 8 - (specialTilePosition - 1) % 9;

      Timeline moveToTeleportTl = new Timeline(new KeyFrame(
          Duration.seconds(0.3),

          e -> {
            addPlayerPieceToGrid(gridPane, player, teleportCol, teleportRow);
            ladderUpOrDownCheck.setText("Teleporting...");
          }
      ));

      // After reaching teleport, wait then jump to destination
      moveToTeleportTl.setOnFinished(e -> {

        // Wait briefly, then teleport to destination
        Timeline pauseTl = new Timeline(new KeyFrame(Duration.seconds(0.5)));

        pauseTl.setOnFinished(event -> {
          // Move directly to final destination
          int finalRow = 9 - (toPosition - 1) / 9;
          int finalCol = (9 - finalRow) % 2 == 0 ? (toPosition - 1) % 9 : 8 - (toPosition - 1) % 9;


          addPlayerPieceToGrid(gridPane, player, finalCol, finalRow);
          ladderUpOrDownCheck.setText("Teleported to " + toPosition + "!");
        });
        pauseTl.play();
      });
      moveToTeleportTl.play();
      return;
    }


//    if (tileType == 3) {
//      Timeline teleportTL = new Timeline();
//      teleportTL.setCycleCount(1);
//
//      int specialTilePosition = gameController.getSpecialTilePosition();
//      int stepsToSpecial = specialTilePosition-fromPosition;
//
//      snakeOrLadderCheck.setText("Teleporting...");
//
//      List<KeyFrame> keyFrameList = new ArrayList<>();
//      int currentPosition = fromPosition;
//
//      int absSteps = Math.abs(stepsToSpecial);
//      for(int i = 0; i <= absSteps; i++){
//        currentPosition = fromPosition + i;
//
//        //calculate coordinates for grid
//        int row = 9 - (currentPosition - 1) / 9;
//        int col = (9 - row) % 2 == 0 ? (currentPosition - 1) % 9 : 8 - (currentPosition - 1) % 9;
//
//        KeyFrame keyFrame = new KeyFrame(
//            Duration.seconds((i+1)*0.3),
//            event -> {
//              gridPane.getChildren().remove(player.getPlayerPiece());
//              gridPane.add(player.getPlayerPiece(), col, row);
//            }
//        ); keyFrameList.add(keyFrame);
//      } teleportTL.getKeyFrames().addAll(keyFrameList);
//
//      teleportTL.setOnFinished(event -> {
//        int finalRow = 9 - (toPosition - 1) / 9;
//        int finalCol = (9 - finalRow) % 2 == 0 ? (toPosition - 1) % 9 : 8 - (toPosition - 1) % 9;
//
//        gridPane.getChildren().remove(player.getPlayerPiece());
//        gridPane.add(player.getPlayerPiece(), finalCol, finalRow);
//        snakeOrLadderCheck.setText("Teleported to " + toPosition + "!");
//      });
//      teleportTL.play();
//      return;
//    }

    // ---------------- Ladder Up or Down -------------------
    else if (tileType == 1 || tileType == 2) {
      Timeline ladderTl = new Timeline();
      ladderTl.setCycleCount(1);

      int specialTileStartPosition = boardController.getSpecialTilePosition();
      int stepsToSpecial = specialTileStartPosition - fromPosition;
      boolean isForward = stepsToSpecial > 0; //check if ladder Up or Down


      // Feedback upon hitting ladders up or downs
      ladderUpOrDownCheck.setText(tileType == 1 ? "Climbing up!" : "Sliding down...");

      // Create a list of keyframes
      List<KeyFrame> keyFrames = new ArrayList<>();
      int currentPosition = fromPosition;

      int absSteps = Math.abs(stepsToSpecial);
      for (int i = 0; i <= absSteps + 1; i++) {
        currentPosition = fromPosition + (isForward ? i : -i); //So it does not matter if it goes
        // backward or forwards

        // Calculate the current position coordinates
        int row = 9 - (currentPosition - 1) / 9;
        int col = (9 - row) % 2 == 0 ? (currentPosition - 1) % 9 :  8 - (currentPosition - 1) % 9;
        if (col == -1) {
          col = 0;
        }

        // Add a keyframe for current step
        int finalCol = col;
        KeyFrame keyFrame = new KeyFrame(
            Duration.seconds((i + 1) * 0.3),
            event -> {
              addPlayerPieceToGrid(gridPane, player, finalCol, row);
            }
        );
        keyFrames.add(keyFrame);
      }
      ladderTl.getKeyFrames().addAll(keyFrames);

      // After animation, teleport to final destination
      ladderTl.setOnFinished(event -> {
        int finalRow = 9 - (toPosition - 1) / 9;
        int finalCol = (9 - finalRow) % 2 == 0 ? (toPosition - 1) % 9 : 8 - (toPosition - 1) % 9;
        addPlayerPieceToGrid(gridPane, player, finalCol, finalRow);
        checkWinner(player, toPosition, primaryStage);
      });
      ladderTl.play();
      return;
    }
    // --------------- Normal tiles -----------------
    Timeline timeline = new Timeline();
    timeline.setCycleCount(1);

    int steps = toPosition - fromPosition;
    int currentPosition = fromPosition;
    ladderUpOrDownCheck.setText("");

    for (int i = 0; i < steps; i++) {
      currentPosition++;
      // Calculate into coordinates
      int row = 9 - (currentPosition - 1) / 9;
      int col = (9 - row) % 2 == 0 ? (currentPosition - 1) % 9 : 8 - (currentPosition - 1) % 9;

      KeyFrame keyFrame = new KeyFrame(
          Duration.seconds((i + 1) * 0.3),
          event -> {
            addPlayerPieceToGrid(gridPane, player, col, row);
          }
      );
      timeline.getKeyFrames().add(keyFrame);
    }
    timeline.setOnFinished(event -> {
      checkWinner(player, toPosition, primaryStage);
    });
    timeline.play();
  }
}