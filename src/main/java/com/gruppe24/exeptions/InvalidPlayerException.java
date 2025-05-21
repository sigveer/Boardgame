package com.gruppe24.exeptions;

public class InvalidPlayerException extends NullPointerException {

  public InvalidPlayerException() {
    super("Player cannot be null or invalid");
  }

  public InvalidPlayerException(String message) {
    super(message);
  }
}
