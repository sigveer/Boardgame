package com.gruppe24.boardgames.laddergame.view;

import com.gruppe24.boardgames.DashboardGui;
import com.gruppe24.boardgames.commonclasses.CommonDice;
import com.gruppe24.boardgames.laddergame.controller.BoardController;
import com.gruppe24.boardgames.laddergame.controller.LadderGameController;
import com.gruppe24.boardgames.laddergame.models.Player;
import com.gruppe24.boardgames.laddergame.models.board.Board;
import com.gruppe24.boardgames.laddergame.models.board.BoardType;
import com.gruppe24.exeptions.InvalidBoardException;
import com.gruppe24.exeptions.InvalidPlayerException;
import com.gruppe24.observerpattern.GameLogger;
import com.gruppe24.observerpattern.GameSubject;
import com.gruppe24.utils.StyleUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
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
  BoardController boardController;
  LadderGameController ladderGameController;
  private final List<Player> players;
  private static final int tileSize = 75;
  private int currentPlayerIndex = 0;
  private Label diceResultLabel;
  private Label currentPlayerLabel;
  private Label ladderUpOrDownCheck;
  private Label isFrozenLabel;
  private final CommonDice dice = new CommonDice(2);
  private Button diceRollButton;
  private final GameLogger gameLogger = new GameLogger();

  /**
   * Constructor for LadderGame.
   *
   * @param players   list of players
   * @param boardType type of board
   */
  public LadderGameApp(List<Player> players, BoardType boardType) {
    if (players == null || players.isEmpty()) {
      throw new InvalidPlayerException();
    }
    if (boardType == null) {
      throw new InvalidBoardException();
    }
    this.boardController = new BoardController(boardType);
    this.ladderGameController = new LadderGameController();
    this.board = boardController.getBoard();
    this.players = players;

    GameSubject.gameSubjectInstance().registerListener(gameLogger);
  }

  /**
   * JSON-Constructor for LadderGame with a custom board.
   *
   * @param players     list of players
   * @param customBoard the custom board to use
   */
  public LadderGameApp(List<Player> players, Board customBoard) {
    if (players == null || players.isEmpty()) {
      throw new InvalidPlayerException();
    }
    if (customBoard == null) {
      throw new InvalidBoardException();
    }
    this.boardController = new BoardController(customBoard);
    this.ladderGameController = new LadderGameController();
    this.board = customBoard;
    this.players = players;
    GameSubject.gameSubjectInstance().registerListener(gameLogger);
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

    BoardController boardController = new BoardController(BoardType.CLASSIC);
    this.ladderGameController.initializeGame(boardController);

    players.forEach(player -> player.setIcon(player.getIcon()));

    primaryStage.setTitle("Laddergame Classic");

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

    boardContainer.getChildren().addAll(gridPane, ladderPane);

    // Control panel (right section)
    VBox controlPanel = new VBox(15);
    controlPanel.setAlignment(Pos.CENTER);
    controlPanel.setPrefWidth(250);
    controlPanel.setMinWidth(200);
    StyleUtils.stylePanel(controlPanel);

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
    diceRollButton = diceRoll;
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

    // Ladder resize handlers
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
   * Draws the board with tiles and ladders.
   *
   * @param gridPane   the grid pane to draw on
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
   * Method to draw the ladders on board, and enabeling to resize the window.
   *
   * @param ladderPane the pane for ladders.
   * @param image      the image of the ladder.
   * @param startTile  the starting tile.
   * @param endTile    the ending tile.
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
   * Rolls the dice, updates dice display, calculates the move using the controller, and initiates
   * the animation.
   */
  private void rollDiceAndMove(GridPane gridPane, Stage primaryStage, Pane dicePane) {
    // Disable the button at the start of the turn
    diceRollButton.setDisable(true);

    Player currentPlayer = players.get(currentPlayerIndex);

    if (handleFrozenPlayer(currentPlayer, dicePane)) {
      diceRollButton.setDisable(false); // Re-enable if player was frozen
      return; // Player was frozen, skip turn
    }

    int diceValue = rollAndDisplayDice(dicePane);

    int previousPosition = currentPlayer.getPosition();
    int targetPosition = calculateTargetPosition(currentPlayer, diceValue);

    animateAndMove(gridPane, currentPlayer, previousPosition, targetPosition,
        diceValue, primaryStage);
  }

  /**
   * Handles logic for frozen players, skipping their turn if needed.
   *
   * @return true if player was frozen and turn should be skipped
   */
  private boolean handleFrozenPlayer(Player currentPlayer, Pane dicePane) {
    if (currentPlayer.isFrozen()) {
      currentPlayer.setFrozen(false); // Unfreeze for the next turn

      advanceToNextPlayer();

      // Reset UI elements
      dicePane.getChildren().clear();
      return true;
    }
    return false;
  }

  /**
   * Moves turn to the next player.
   */
  private void advanceToNextPlayer() {
    currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
    currentPlayerLabel.setText("Current Player: " + players.get(currentPlayerIndex).getName());

    if (players.get(currentPlayerIndex).isFrozen()) {
      isFrozenLabel.setText(players.get(currentPlayerIndex).getName()
          + " is frozen and will skip their turn!");
    } else {
      isFrozenLabel.setText("");
    }
    ladderUpOrDownCheck.setText("");
  }

  /**
   * Rolls dice and updates the visual dice display.
   *
   * @return the total value rolled on the dice
   */
  private int rollAndDisplayDice(Pane dicePane) {
    int diceValue = dice.rollSum();

    diceResultLabel.setText("Rolled: " + diceValue);

    int diceValue1 = dice.getDie(0);

    ImageView dice1Iv = new ImageView(new Image(dice.dicePath(diceValue1)));
    dice1Iv.setX(40);
    dice1Iv.setY(0);
    dice1Iv.setFitHeight(75);
    dice1Iv.setFitWidth(75);

    int diceValue2 = dice.getDie(1);

    ImageView dice2Iv = new ImageView(new Image(dice.dicePath(diceValue2)));
    dice2Iv.setX(125);
    dice2Iv.setY(0);
    dice2Iv.setFitHeight(75);
    dice2Iv.setFitWidth(75);

    dicePane.getChildren().clear();
    dicePane.getChildren().addAll(dice1Iv, dice2Iv);

    return diceValue;
  }

  /**
   * Calculates the player's target position after considering teleport tiles.
   */
  private int calculateTargetPosition(Player currentPlayer, int diceValue) {
    int previousPosition = currentPlayer.getPosition();
    int boardSize = 90;
    int initialLandingPos = previousPosition + diceValue;
    boolean overshoot = initialLandingPos > boardSize;
    int targetPositionBeforeSpecial = overshoot
        ? boardSize - (initialLandingPos - boardSize) : initialLandingPos;

    // Special handling for teleport tiles
    if (board.getTileType(targetPositionBeforeSpecial) == 3) {
      ladderGameController.handlePlayerTurn(currentPlayer, diceValue);

      int positionBeforeTeleport = currentPlayer.getPosition();

      board.getTile(positionBeforeTeleport).perform(currentPlayer);

      return currentPlayer.getPosition();
    } else {
      ladderGameController.handlePlayerTurn(currentPlayer, diceValue);
      return currentPlayer.getPosition();
    }
  }

  /**
   * Animates player movement and handles special tile effects.
   *
   * @AI_Based Next 8 methods has been based on recommendations from AI. This method is the mother
   * if many sub-methods; createMovementAnimationFrames, calculateInitialLandingPosition,
   * handleSpecialTileEffects.
   */
  private void animateAndMove(GridPane gridPane, Player player, int fromPosition, int toPosition,
      int diceSum, Stage primaryStage) {
    // Clear previous messages
    ladderUpOrDownCheck.setText("");
    isFrozenLabel.setText("");

    Timeline stepTimeline = new Timeline();
    stepTimeline.getKeyFrames().addAll(createMovementAnimationFrames(player, fromPosition,
        diceSum, gridPane));

    // Set up animation completion handler
    stepTimeline.setOnFinished(event -> {
      int targetPositionBeforeSpecial = calculateInitialLandingPosition(fromPosition, diceSum);
      handleSpecialTileEffects(gridPane, player, targetPositionBeforeSpecial,
          toPosition, primaryStage);
    });

    stepTimeline.play();
  }

  /**
   * Method that creates frames for TimeFrame.
   *
   * @AI_Based Method that returns KeyFrames, calculated by an algorythm for finding the row and
   * column. If overshoot is true, another algorythm is used to calculate its frames moving
   * backwards
   */
  private List<KeyFrame> createMovementAnimationFrames(Player player, int fromPosition,
      int diceSum, GridPane gridPane) {
    int boardSize = 90;
    int initialLandingPos = fromPosition + diceSum;
    boolean overshoot = initialLandingPos > boardSize;

    List<KeyFrame> keyFrames = new ArrayList<>();
    double delay = 0.0;
    double animationSpeed = 0.3;
    int currentAnimatedPos;

    // Animate forward movement
    int stepsForward = Math.min(diceSum, boardSize - fromPosition);
    for (int i = 1; i <= stepsForward; i++) {
      currentAnimatedPos = fromPosition + i;
      int finalCurrentAnimatedPos = currentAnimatedPos;
      delay += animationSpeed;
      keyFrames.add(new KeyFrame(Duration.seconds(delay), e -> {
        int row = 9 - (finalCurrentAnimatedPos - 1) / 9;
        int col = (9 - row) % 2 == 0
            ? (finalCurrentAnimatedPos - 1) % 9 :
            8 - (finalCurrentAnimatedPos - 1) % 9;
        addPlayerPieceToGrid(gridPane, player, col, row);
      }));
    }

    // Animate overshoot if needed
    if (overshoot) {
      ladderUpOrDownCheck.setText("Overshoot! Bouncing back...");
      int stepsBack = initialLandingPos - boardSize;
      for (int i = 1; i <= stepsBack; i++) {
        currentAnimatedPos = boardSize - i;
        int finalCurrentAnimatedPos = currentAnimatedPos;
        delay += animationSpeed;
        keyFrames.add(new KeyFrame(Duration.seconds(delay), e -> {
          int row = 9 - (finalCurrentAnimatedPos - 1) / 9;
          int col = (9 - row) % 2 == 0
              ? (finalCurrentAnimatedPos - 1) % 9 :
              8 - (finalCurrentAnimatedPos - 1) % 9;
          addPlayerPieceToGrid(gridPane, player, col, row);
        }));
      }
    }

    return keyFrames;
  }

  /**
   * Method that creates final frame before any special tile.
   *
   * @AI_Based Returns landing position before any ladder/special tile. Or calculates the overshoot
   * value.
   */
  private int calculateInitialLandingPosition(int fromPosition, int diceSum) {
    int boardSize = 90;
    int initialLandingPos = fromPosition + diceSum;
    boolean overshoot = initialLandingPos > boardSize;
    return overshoot ? boardSize - (initialLandingPos - boardSize) : initialLandingPos;
  }

  /**
   * Post-animation handler for special tile effects, not including ladders.
   *
   * @AI_Based Uses if-statements to check what special tile it is, and gives the apporopriate
   * response.
   */
  private void handleSpecialTileEffects(GridPane gridPane, Player player,
      int targetPositionBeforeSpecial, int toPosition, Stage primaryStage) {

    int tileType = board.getTileType(targetPositionBeforeSpecial);

    Runnable finishTurnAction = () -> {
      if (tileType == 4) {
        board.getTile(targetPositionBeforeSpecial).perform(player);
        isFrozenLabel.setText(player.getName() + " landed on a frozen tile! Will skip next turn.");
      }

      checkWinner(player, toPosition, primaryStage);
      advanceToNextPlayer();

      // Re-enable the button when the turn is complete
      diceRollButton.setDisable(false);
    };

    if (toPosition != targetPositionBeforeSpecial || tileType == 3) {

      // Pause before showing special tile effect
      Timeline pauseTimeline = new Timeline(new KeyFrame(Duration.seconds(0.4)));
      pauseTimeline.setOnFinished(pauseEvent -> {
        displaySpecialTileEffectMessage(tileType, toPosition);
        movePlayerToFinalPosition(gridPane, player, toPosition);
        finishTurnAction.run();
      });
      pauseTimeline.play();
    } else {
      finishTurnAction.run();
    }
  }

  /**
   * PART OF handleSpecialTileEffects(). Checks and logs if player has won.
   */
  private void checkWinner(Player player, int position, Stage primaryStage) {
    if (position == ladderGameController.getWinCondition()) {
      Alert alert = new Alert(AlertType.INFORMATION);
      alert.setTitle("Game Over");
      alert.setHeaderText(null);
      alert.setContentText(player.getName() + " has won the game!");

      Platform.runLater(() -> {
        GameSubject.gameSubjectInstance().removeListener(this.gameLogger);
        alert.showAndWait();
        new DashboardGui().start(primaryStage);
      });
    }
  }

  /**
   * PART OF handleSpecialTileEffects(). Displays the text upon ladder-animation.
   */
  private void displaySpecialTileEffectMessage(int tileType, int toPosition) {
    String finalMessage = "";
    if (tileType == 1) {
      finalMessage = "Climbed up to " + toPosition + "!";
    } else if (tileType == 2) {
      finalMessage = "Slided down to " + toPosition + "!";
    } else if (tileType == 3) {
      finalMessage = "Teleported to " + toPosition + "!";
    }

    final String message = finalMessage;
    Platform.runLater(() -> ladderUpOrDownCheck.setText(message));
  }

  /**
   * PART OF handleSpecialTileEffects(). Moves player to final position after animation.
   */
  private void movePlayerToFinalPosition(GridPane gridPane, Player player, int toPosition) {
    int finalRow = 9 - (toPosition - 1) / 9;
    int finalCol = (9 - finalRow) % 2 == 0
        ? (toPosition - 1) % 9 :
        8 - (toPosition - 1) % 9;
    addPlayerPieceToGrid(gridPane, player, finalCol, finalRow);
  }

  /**
   * COMMON METHOD: USED MULTIPLE PLACES. Used to add player to grid.
   *
   * @AI_Based Actually places the player piece on the grid (board).
   */
  private void addPlayerPieceToGrid(GridPane gridPane, Player player, int col, int row) {
    gridPane.getChildren().remove(player.getPlayerPiece());

    StackPane cellContainer = new StackPane();
    cellContainer.getChildren().add(player.getPlayerPiece());
    StackPane.setAlignment(player.getPlayerPiece(), Pos.CENTER);

    gridPane.add(cellContainer, col, row);
  }
}