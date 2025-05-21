package com.gruppe24.exeptions;

public class InvalidBoardException extends NullPointerException {

  public InvalidBoardException() {
    super("Board type cannot be null");
  }
  
  public InvalidBoardException(String message) {
    super(message);
  }

  public InvalidBoardException(int position) {
    super("Invalid board position: " + position);
  }
}
