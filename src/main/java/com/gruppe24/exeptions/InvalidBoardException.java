package com.gruppe24.exeptions;

/**
 * Nullpointexception for board type.
 */
public class InvalidBoardException extends NullPointerException {

  /**
   * Exception with predefined message.
   */
  public InvalidBoardException() {
    super("Board type cannot be null");
  }

  /**
   * Exception with custom message.
   *
   * @param message the error message.
   */
  public InvalidBoardException(String message) {
    super(message);
  }
}
