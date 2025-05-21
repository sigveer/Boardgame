package com.gruppe24.exeptions;

/**
 * File handling exception for error upon file handling.
 */
public class FileHandlingException extends Exception {

  /**
   * Exception with custom message and throwable cause.
   *
   * @param message the error message.
   * @param cause the reason for the error.
   */
  public FileHandlingException(String message, Throwable cause) {
    super(message, cause);
  }
}
