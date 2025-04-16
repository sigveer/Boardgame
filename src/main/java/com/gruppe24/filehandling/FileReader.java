package com.gruppe24.filehandling;

/**
 * Interface for reading data from a file.
 */
public interface FileReader {

  /**
   * Reads data from a file and returns it as an object.
   *
   * @param filePath the path to the file to read
   * @return the data read from the file
   */
  Object readFromFile(String filePath);
}
