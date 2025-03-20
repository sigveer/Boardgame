package com.gruppe24.Utils;

import javafx.scene.control.Button;


/**
 * {@code StyleUtils} is a utility class for styling buttons.
 *
 * @Author Ingve, Sigveer
 * @Date: 15.03.2025
 * @Version: 1.0
 */
public class StyleUtils {
  public static void styleNormalButton(Button normalButton) {
    String normalStyle =
        "-fx-background-color: linear-gradient(to bottom, #4a6cd4, #283a60); " +
            "-fx-text-fill: white; " +
            "-fx-font-size: 16px; " +
            "-fx-padding: 10px 20px; " +
            "-fx-border-radius: 5px; " +
            "-fx-background-radius: 5px; " +
            "-fx-border-color: #1f2a44; " +
            "-fx-border-width: 2px; " +
            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 8, 0, 2, 2);";

    String hoverStyle =
        "-fx-background-color: linear-gradient(to bottom, #5c7ee8, #2e4370); " +
            "-fx-text-fill: white; " +
            "-fx-font-size: 16px; " +
            "-fx-padding: 10px 20px; " +
            "-fx-border-radius: 5px; " +
            "-fx-background-radius: 5px; " +
            "-fx-border-color: #1f2a44; " +
            "-fx-border-width: 2px; " +
            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 10, 0, 3, 3);";

    String pressedStyle =
        "-fx-background-color: linear-gradient(to top, #283a60, #3a5590); " +
            "-fx-text-fill: white; " +
            "-fx-font-size: 16px; " +
            "-fx-padding: 12px 20px 8px 20px; " +
            "-fx-border-radius: 5px; " +
            "-fx-background-radius: 5px; " +
            "-fx-border-color: #1f2a44; " +
            "-fx-border-width: 2px; " +
            "-fx-effect: innershadow(gaussian, rgba(0,0,0,0.7), 5, 0, 0, 0);";

    normalButton.setStyle(normalStyle);

    normalButton.setOnMouseEntered(e -> normalButton.setStyle(hoverStyle));
    normalButton.setOnMouseExited(e -> normalButton.setStyle(normalStyle));
    normalButton.setOnMousePressed(e -> normalButton.setStyle(pressedStyle));
    normalButton.setOnMouseReleased(e -> normalButton.setStyle(hoverStyle));
  }


  /**
   * Method to style the Tic Tac Toe button
   *
   * @param tttButton is the button to be styled
   * @Author Sigveer
   * @Date: 15.03.2025
   * @Version: 1.0
   */
  public static void styleTTTButton(Button tttButton) {
    String normalStyle =
        "-fx-background-color: white; " +
            "-fx-border-color: #cccccc; " +
            "-fx-border-width: 1px; " +
            "-fx-font-size: 75px; " +
            "-fx-text-fill: black;";

    tttButton.setStyle(normalStyle);
  }



  //--------Colour---------

  public static String textRED() {
    return "\u001B[31m";
  }

  public static String textBLUE() {
    return "\u001B[34m";
  }

  public static String textGREEN() {
    return "\u001B[32m";
  }

  public static String textRESET() {
    return "\u001B[0m";
  }

  public static String textROSE() {
    return textRED() + """
        
            _,--._.-,
           /\\_r-,\\_ )
        .-.) _;='_/ (.;
         \\ \\'     \\/S )
          L.'-. _.'|-'
         <_`-'\\'_.'/
           `'-._( \\
        """ + textGREEN() +
        """
                ___   \\\\,      ___
                \\ .'-. \\\\   .-'_. /
                 '._' '.\\\\/.-'_.'
                    '--``\\('--'
                          \\\\
                          `\\\\,
                            \\|⠀⠀⠀⠀⠀⠀⠀
            """ + textRESET();
  }
}