package com.gruppe24.filehandling;

/**
 * Interface for writing objects to a file.
 */
public interface FileWriter {

  /**
   * Writes the given object to a file at the specified path.
   *
   * @param object the object to write
   * @param filePath the path of the file to write to
   * @return true if the write was successful, false otherwise
   */
  boolean writeToFile(Object object, String filePath);
}