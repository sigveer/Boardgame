package com.gruppe24.exeptions;


public class InvalidDiceValueException extends ArithmeticException {

//  public InvalidDiceValueException() {
//    super("Invalid dice value");
//  }


  public InvalidDiceValueException(String message) {
    super(message);
  }
}
