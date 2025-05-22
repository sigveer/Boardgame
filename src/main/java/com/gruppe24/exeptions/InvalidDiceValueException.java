package com.gruppe24.exeptions;

/**
 * ArithmeticException that is thrown when a dice value is invalid.
 */
public class InvalidDiceValueException extends ArithmeticException {

  /**
   * Throw if dice managed to get out of bounds.
   *
   * @param message the error message.
   */
  public InvalidDiceValueException(String message) {
    super(message);
  }
}
