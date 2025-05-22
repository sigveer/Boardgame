package com.gruppe24.exeptions;

/**
 * NullpointerException that is thrown when a player is null or invalid.
 */
public class InvalidPlayerException extends NullPointerException {

  /**
   * Exception with predefined message.
   */
  public InvalidPlayerException() {
    super("Player cannot be null or invalid");
  }

  /**
   * Exception with custom message.
   *
   * @param message the error message.
   */
  public InvalidPlayerException(String message) {
    super(message);
  }
}
