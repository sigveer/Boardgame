package com.gruppe24.utils;

import javafx.scene.control.Button;


/**
 * {@code StyleUtils} is a utility class for styling buttons.
 */
public class StyleUtils {

  /**
   * Method to style the normal button.
   */
  public static void styleNormalButton(Button normalButton) {
    String normalStyle =
        "-fx-background-color: linear-gradient(to bottom, #4a6cd4, #283a60); "
            + "-fx-text-fill: white; "
            + "-fx-font-size: 16px; "
            + "-fx-padding: 10px 20px; "
            + "-fx-border-radius: 5px; "
            + "-fx-background-radius: 5px; "
            + "-fx-border-color: #1f2a44; "
            + "-fx-border-width: 2px; "
            + "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 8, 0, 2, 2);";

    String hoverStyle =
        "-fx-background-color: linear-gradient(to bottom, #5c7ee8, #2e4370); "
            + "-fx-text-fill: white; "
            + "-fx-font-size: 16px; "
            + "-fx-padding: 10px 20px; "
            + "-fx-border-radius: 5px; "
            + "-fx-background-radius: 5px; "
            + "-fx-border-color: #1f2a44; "
            + "-fx-border-width: 2px; "
            + "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 10, 0, 3, 3);";

    String pressedStyle =
        "-fx-background-color: linear-gradient(to top, #283a60, #3a5590); "
            + "-fx-text-fill: white; "
            + "-fx-font-size: 16px; "
            + "-fx-padding: 12px 20px 8px 20px; "
            + "-fx-border-radius: 5px; "
            + "-fx-background-radius: 5px; "
            + "-fx-border-color: #1f2a44; "
            + "-fx-border-width: 2px; "
            + "-fx-effect: innershadow(gaussian, rgba(0,0,0,0.7), 5, 0, 0, 0);";

    normalButton.setStyle(normalStyle);

    normalButton.setOnMouseEntered(e -> normalButton.setStyle(hoverStyle));
    normalButton.setOnMouseExited(e -> normalButton.setStyle(normalStyle));
    normalButton.setOnMousePressed(e -> normalButton.setStyle(pressedStyle));
    normalButton.setOnMouseReleased(e -> normalButton.setStyle(hoverStyle));
  }


  /**
   * Method to style the Tic Tac Toe button.
   *
   * @param tttButton is the button to be styled
   */
  public static void styletttButton(Button tttButton) {
    String normalStyle =
        "-fx-background-color: white; "
            + "-fx-border-color: #cccccc; "
            + "-fx-border-width: 1px; "
            + "-fx-font-size: 75px; "
            + "-fx-text-fill: black;";

    tttButton.setStyle(normalStyle);
  }



  //--------Colour---------

  /**
   * Method to style the text in red.
   *
   * @return the ANSI escape code for red text
   */
  public static String textRed() {
    return "\u001B[31m";
  }

  /**
   * Method to style the text in blue.
   *
   * @return the ANSI escape code for blue text
   */
  public static String textBlue() {
    return "\u001B[34m";
  }

  /**
   * Method to style the text in green.
   *
   * @return the ANSI escape code for green text
   */
  public static String textGreen() {
    return "\u001B[32m";
  }

  /**
   * Method for resetting the text color.
   *
   * @return the ANSI escape code for resetting text color
   */
  public static String textReset() {
    return "\u001B[0m";
  }

  /**
   * Method for printing a rose in ASCII art.
   *
   * @return the ASCII art of a rose
   */
  public static String textRose() {
    return textRed() + """
        
            _,--._.-,
           /\\_r-,\\_ )
        .-.) _;='_/ (.;
         \\ \\'     \\/S )
          L.'-. _.'|-'
         <_`-'\\'_.'/
           `'-._( \\
        """
        + textGreen()
        + """
                ___   \\\\,      ___
                \\ .'-. \\\\   .-'_. /
                 '._' '.\\\\/.-'_.'
                    '--``\\('--'
                          \\\\
                          `\\\\,
                            \\|⠀⠀⠀⠀⠀⠀⠀
            """ + textReset();
  }
}