package com.gruppe24.boardgames.laddergame.view;

import com.gruppe24.boardgames.laddergame.controller.GameController;
import com.gruppe24.boardgames.laddergame.models.Dice;
import com.gruppe24.boardgames.laddergame.models.Player;
import com.gruppe24.boardgames.laddergame.models.board.Board;
import com.gruppe24.boardgames.laddergame.models.board.BoardType;
import com.gruppe24.boardgames.laddergame.models.board.tiles.FrozenTile;
import com.gruppe24.boardgames.laddergame.models.board.tiles.LadderDownTile;
import com.gruppe24.boardgames.laddergame.models.board.tiles.LadderUpTile;
import com.gruppe24.boardgames.laddergame.models.board.tiles.RandomTeleportTile;
import com.gruppe24.utils.StyleUtils;
import com.gruppe24.utils.Validators;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Class that represents the LadderGame.
 */
public class LadderGame extends Application {

  private final Board board;
  private final GameController gameController;
  private final List<Player> players;
  private static final int tileSize = 75;
  private int currentPlayerIndex = 0;
  private Label diceResultLabel;
  private Label currentPlayerLabel;
  private Label ladderUpOrDownCheck;
  private Label isFrozenLabel;
  private final Dice dice = new Dice(2);

  /**
   * Constructor for LadderGame.
   *
   * @param players list of players
   */
  public LadderGame(List<Player> players) {
    this(players, BoardType.CLASSIC);
  }

  /**
   * Constructor for LadderGame.
   *
   * @param players list of players
   * @param boardType type of board
   */
  public LadderGame(List<Player> players, BoardType boardType) {
    if (players == null || players.isEmpty()) {
      throw new IllegalArgumentException("Parameter list of players cannot be empty");
    }
    if (boardType == null) {
      throw new IllegalArgumentException("Parameter boardType cannot be empty");
    }
    this.gameController = new GameController(boardType);
    this.board = gameController.getBoard();
    this.players = players;
  }

  @Override
  public void start(Stage primaryStage) {
    if (primaryStage == null) {
      throw new IllegalArgumentException("Parameter Stage cannot be empty");
    }
    Validators.getLogger().log(Level.INFO, "LadderGame started");

    primaryStage.setTitle("Laddergame Classic");

    //GridPane for tiles
    GridPane gridPane = new GridPane();
    gridPane.setAlignment(Pos.CENTER);
    gridPane.setVgap(1);
    gridPane.setHgap(1);
    gridPane.setStyle("-fx-background-color: #607ee4;");

    //Pane for ladders and dices
    Pane ladderPane = new Pane();
    ladderPane.setMouseTransparent(true);
    Pane dicePane = new Pane();
    dicePane.setMouseTransparent(true);

    //Scene
    Scene scene = new Scene(new StackPane(gridPane, ladderPane, dicePane), 1000, 850); //AI-suggestion

    //Title Label
    Label title = new Label("Board Games!");
    title.setStyle("-fx-font-size: 40px; -fx-text-fill: #ffffff; -fx-font-weight: bold;");

    //Draw Board
    drawBoard(gridPane, ladderPane); //drawing the board

    //Labels
    currentPlayerLabel = new Label("Current Player: " + players.get(currentPlayerIndex).getName());
    currentPlayerLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: white;");

    diceResultLabel = new Label("Roll the dice!");
    diceResultLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: white;");

    ladderUpOrDownCheck = new Label("");
    ladderUpOrDownCheck.setStyle("-fx-font-size: 16px; -fx-text-fill: white;");

    isFrozenLabel = new Label("");
    isFrozenLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: white;");

    //Buttons
    Button diceRoll = new Button("Roll Dice");
    diceRoll.setOnAction(event -> rollDiceAndMove(gridPane, primaryStage, dicePane));
    StyleUtils.styleNormalButton(diceRoll);

    Button backToMenu = new Button("Back to Menu");
    backToMenu.setOnAction(event -> new LadderGameMenuGui().start(primaryStage));
    StyleUtils.styleNormalButton(backToMenu);

    //Vertical Box for control panel
    VBox controlPanel = new VBox(10);
    controlPanel.setAlignment(Pos.CENTER);
    controlPanel.setPrefWidth(200);
    controlPanel.setMinWidth(200);

    currentPlayerLabel.setWrapText(true); // Enable text wrapping
    currentPlayerLabel.setMaxWidth(190);

    diceResultLabel.setMaxWidth(190);
    ladderUpOrDownCheck.setMaxWidth(190);
    isFrozenLabel.setMaxWidth(190);

    controlPanel.getChildren().addAll(currentPlayerLabel, diceResultLabel, ladderUpOrDownCheck,
        isFrozenLabel, diceRoll, backToMenu);

    gridPane.add(controlPanel, 11, 0, 1, 5);

    primaryStage.setScene(scene);
    primaryStage.setFullScreen(true); // can not resize the window. A temporary fix?
    primaryStage.show();
  }

  /**
   * Draws the board with tiles and ladders.
   *
   * @param gridPane the grid pane to draw on
   * @param ladderPane the pane for ladders
   */
  public void drawBoard(GridPane gridPane, Pane ladderPane) {
    for (int row = 9; row >= 0; row--) {
      for (int col = 0; col < 9; col++) {
        int tileNumber = (9 - row) % 2 == 0 ? (9 - row) * 9 + col + 1 : (9 - row + 1) * 9 - col;

        StackPane stackPane = new StackPane();
        Rectangle tile = new Rectangle(tileSize, tileSize);
        tile.setStroke(Color.BLACK);
        tile.setStrokeWidth(1);

        if (tileNumber == 90) {
          tile.setFill(Color.YELLOW);
        } else if (board.getTile(tileNumber) instanceof RandomTeleportTile) {
          tile.setFill(Color.PURPLE);
        } else if (board.getTile(tileNumber) instanceof FrozenTile) {
          tile.setFill(Color.LIGHTBLUE);
        } else if (board.getTile(tileNumber) instanceof LadderUpTile) {
          tile.setFill(Color.GREEN);
        } else if (board.getTile(tileNumber) instanceof LadderDownTile) {
          tile.setFill(Color.RED);
        } else {
          tile.setFill(Color.WHITE);
        }

        //Landing-tile upon special tiles
        for (Integer value : board.getLadderUp().values()) {
          if (tileNumber == value) {
            tile.setFill(Color.LIGHTGREEN);
          }
        }
        for (Integer value : board.getLadderDown().values()) {
          if (tileNumber == value) {
            tile.setFill(Color.LIGHTPINK);
          }
        }

        Label numberLabel = new Label(Integer.toString(tileNumber));
        numberLabel.setStyle("-fx-font-weight: bold;");
        numberLabel.setTranslateX(26);
        numberLabel.setTranslateY(30);

        stackPane.getChildren().addAll(tile, numberLabel);
        stackPane.setAlignment(Pos.CENTER);

        gridPane.add(stackPane, col, row);
      }
    }

    //Upward Ladders
    Image ladderUpImage = new Image("Pictures/Ladder.png");

    ImageView ladderUpView1 = new ImageView(ladderUpImage);
    ladderUpView1.setFitWidth(180);
    ladderUpView1.setFitHeight(425);
    ladderUpView1.setX(380);
    ladderUpView1.setY(465);
    ladderUpView1.setRotate(25);

    ImageView ladderUpView2 = new ImageView(ladderUpImage);
    ladderUpView2.setFitWidth(150);
    ladderUpView2.setFitHeight(150);
    ladderUpView2.setX(830);
    ladderUpView2.setY(700);
    ladderUpView2.setRotate(30);

    ImageView ladderUpView3 = new ImageView(ladderUpImage);
    ladderUpView3.setFitWidth(220);
    ladderUpView3.setFitHeight(270);
    ladderUpView3.setX(290);
    ladderUpView3.setY(365);
    ladderUpView3.setRotate(35);

    ImageView ladderUpView4 = new ImageView(ladderUpImage);
    ladderUpView4.setFitWidth(190);
    ladderUpView4.setFitHeight(240);
    ladderUpView4.setX(740);
    ladderUpView4.setY(310);
    ladderUpView4.setRotate(18);

    ImageView ladderUpView5 = new ImageView(ladderUpImage);
    ladderUpView5.setFitWidth(200);
    ladderUpView5.setFitHeight(300);
    ladderUpView5.setX(640);
    ladderUpView5.setY(170);
    ladderUpView5.setRotate(15);

    ImageView ladderUpView6 = new ImageView(ladderUpImage);
    ladderUpView6.setFitWidth(200);
    ladderUpView6.setFitHeight(230);
    ladderUpView6.setX(802);
    ladderUpView6.setY(90);
    ladderUpView6.setRotate(15);

    ImageView ladderUpView7 = new ImageView(ladderUpImage);
    ladderUpView7.setFitWidth(200);
    ladderUpView7.setFitHeight(230);
    ladderUpView7.setX(565);
    ladderUpView7.setY(80);
    ladderUpView7.setRotate(15);

    ladderPane.getChildren().addAll(ladderUpView1, ladderUpView2, ladderUpView3, ladderUpView4,
        ladderUpView5, ladderUpView6, ladderUpView7);

    //Downward Ladder
    Image ladderDownImage = new Image("Pictures/Ladder.png");

    ImageView ladderDownView1 = new ImageView(ladderDownImage);
    ladderDownView1.setFitWidth(200);
    ladderDownView1.setFitHeight(250);
    ladderDownView1.setX(405);
    ladderDownView1.setY(590);
    ladderDownView1.setRotate(20);

    ImageView ladderDownView2 = new ImageView(ladderDownImage);
    ladderDownView2.setFitWidth(200);
    ladderDownView2.setFitHeight(200);
    ladderDownView2.setX(555);
    ladderDownView2.setY(650);
    ladderDownView2.setRotate(25);

    ImageView ladderDownView3 = new ImageView(ladderDownImage);
    ladderDownView3.setFitWidth(200);
    ladderDownView3.setFitHeight(450);
    ladderDownView3.setX(830);
    ladderDownView3.setY(260);
    ladderDownView3.setRotate(1);

    ImageView ladderDownView4 = new ImageView(ladderDownImage);
    ladderDownView4.setFitWidth(150);
    ladderDownView4.setFitHeight(150);
    ladderDownView4.setX(665);
    ladderDownView4.setY(500);
    ladderDownView4.setRotate(-40);

    ImageView ladderDownView5 = new ImageView(ladderDownImage);
    ladderDownView5.setFitWidth(150);
    ladderDownView5.setFitHeight(250);
    ladderDownView5.setX(280);
    ladderDownView5.setY(325);
    ladderDownView5.setRotate(20);

    ImageView ladderDownView6 = new ImageView(ladderDownImage);
    ladderDownView6.setFitWidth(150);
    ladderDownView6.setFitHeight(175);
    ladderDownView6.setX(430);
    ladderDownView6.setY(125);
    ladderDownView6.setRotate(20);

    ImageView ladderDownView7 = new ImageView(ladderDownImage);
    ladderDownView7.setFitWidth(175);
    ladderDownView7.setFitHeight(800);
    ladderDownView7.setX(515);
    ladderDownView7.setY(100);
    ladderDownView7.setRotate(-35);
    ladderDownView7.setScaleX(-1); //inverts picture


    ladderPane.getChildren().addAll(ladderDownView1, ladderDownView2, ladderDownView3,
        ladderDownView4, ladderDownView5, ladderDownView6, ladderDownView7);
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

    String dice1Path = dicePath(diceValue1);
    Image dice1 = new Image(dice1Path);
    ImageView dice1Iv = new ImageView(dice1);
    dice1Iv.setX(1030);
    dice1Iv.setY(450);
    dice1Iv.setFitHeight(75);
    dice1Iv.setFitWidth(75);

    String dice2Path = dicePath(diceValue2);
    Image dice2 = new Image(dice2Path);
    ImageView dice2Iv = new ImageView(dice2);
    dice2Iv.setX(1030);
    dice2Iv.setY(530);
    dice2Iv.setFitHeight(75);
    dice2Iv.setFitWidth(75);

    diceResultLabel.setText("Totalsum: " + diceValue);
    dicePane.getChildren().clear(); //removes old dice
    dicePane.getChildren().addAll(dice1Iv, dice2Iv);

    gameController.handlePlayerTurn(currentPlayer, diceValue);

    //Update animation with the new position
    int newPosition = currentPlayer.getPosition();
    animateAndMove(gridPane, currentPlayer, previousPosition, newPosition);

    //Check winner
    if (gameController.checkAndHandleWin(newPosition)) {
      new WinnerScreen(currentPlayer).start(primaryStage);
      return;
    }

    //Switch player
    currentPlayerLabel.setText("Current Player: " + players.get(currentPlayerIndex).getName());
    currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
  }

  /**
   * Animates the player piece and moves it to the new position.
   *
   * @param gridPane the grid pane to draw on
   * @param player the current player
   * @param fromPosition the original position
   * @param toPosition the new position
   */
  private void animateAndMove(GridPane gridPane, Player player, int fromPosition, int toPosition) {
    int tileType = gameController.getCheckTileType();

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
              gridPane.getChildren().remove(player.getPlayerPiece());
              gridPane.add(player.getPlayerPiece(), col, row);
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
      int specialTilePosition = gameController.getSpecialTilePosition();

      // First, directly move to teleport tile position
      int teleportRow = 9 - (specialTilePosition - 1) / 9;
      int teleportCol = (9 - teleportRow) % 2 == 0 ? (specialTilePosition - 1) % 9 : 8 - (specialTilePosition - 1) % 9;

      Timeline moveToTeleportTl = new Timeline(new KeyFrame(
          Duration.seconds(0.3),

          e -> {
            gridPane.getChildren().remove(player.getPlayerPiece());
            gridPane.add(player.getPlayerPiece(), teleportCol, teleportRow);
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


          gridPane.getChildren().remove(player.getPlayerPiece());
          gridPane.add(player.getPlayerPiece(), finalCol, finalRow);
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

      int specialTileStartPosition = gameController.getSpecialTilePosition();
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
              gridPane.getChildren().remove(player.getPlayerPiece());
              gridPane.add(player.getPlayerPiece(), finalCol, row);
            }
        );
        keyFrames.add(keyFrame);
      }
      ladderTl.getKeyFrames().addAll(keyFrames);

      // After animation, teleport to final destination
      ladderTl.setOnFinished(event -> {
        int finalRow = 9 - (toPosition - 1) / 9;
        int finalCol = (9 - finalRow) % 2 == 0 ? (toPosition - 1) % 9 : 8 - (toPosition - 1) % 9;

        gridPane.getChildren().remove(player.getPlayerPiece());
        gridPane.add(player.getPlayerPiece(), finalCol, finalRow);
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
            gridPane.getChildren().remove(player.getPlayerPiece());
            gridPane.add(player.getPlayerPiece(), col, row);
          }
      );
      timeline.getKeyFrames().add(keyFrame);
    }
    timeline.play();
  }

  /**
   * Returns the path to the dice image.
   *
   * @param dice the number on the dice
   * @return the path to the dice image
   */
  public String dicePath(int dice) {
    return "Pictures/Dices/dice" + dice + ".png";
  }

}