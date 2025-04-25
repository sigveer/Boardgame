package com.gruppe24.utils;

import javafx.scene.control.Button;

/**
 * {@code StyleUtils} is a utility class for styling buttons.
 */
public class StyleUtils {

  /**
   * Method to style the normal button.
   *
   * @AI_Assisted Some -fx- properties were suggested by the AI.
   */
  public static void styleNormalButton(Button normalButton) {
    String normalStyle =
        "-fx-background-color: #3a3aee;"
            + "-fx-text-fill: white;"
            + "-fx-padding: 10px 20px;"
            + "-fx-font-size: 16px;"
            + "-fx-border-radius: 5px;"
            + "-fx-border-color: #120B4E;"
            + "-fx-border-width: 3px;"
            + "-fx-background-radius: 5px;";

    String hoverStyle =
        "-fx-background-color: #5a5aff;"
            + "-fx-text-fill: white;"
            + "-fx-padding: 10px 20px;"
            + "-fx-font-size: 16px;"
            + "-fx-border-radius: 5px;"
            + "-fx-border-color: #120B4E;"
            + "-fx-border-width: 3px;"
            + "-fx-background-radius: 5px;";

    String pressedStyle =
        "-fx-background-color: #1C0F90;"
            + "-fx-text-fill: white;"
            + "-fx-padding: 10px 20px;"
            + "-fx-font-size: 16px;"
            + "-fx-border-radius: 5px;"
            + "-fx-border-color: #120B4E;"
            + "-fx-border-width: 3px;"
            + "-fx-background-radius: 5px;"
            + "-fx-translate-y: 2px;";

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