package com.avocado.sudoko.global.io;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

// File myObj = new File("filename.txt"); // Specify the filename (path!)

@Component
@AllArgsConstructor
public class FileReadWriter {
    /**
     * Writes the provided content to a specified file.
     *
     * @param file    The File object representing the path to be written.
     * @param content The string data to write into the file.
     * @param append  If true, the data will be added to the end of the file;
     *                if false, the file content will be overwritten.
     * @return true if the file was written successfully, false otherwise.
     */
    public boolean write(
            File file,
            String content,
            boolean append
    ) {
        try (FileWriter writer = new FileWriter(file, append)) {
            // write the in the file
            writer.write(content);

            // return success
            return true;
        } catch (IOException e) {
            // log the error to debug permissions or disk space
            System.err.println("Critical IO Error: " + e.getMessage());

            // return failure
            return false;
        }
    }

    /**
     * Atomically creates a new, empty file named by this abstract pathname if
     * and only if a file with this name does not yet exist.
     *
     * @param file            The File object representing the path to be created.
     * @param fallbackMessage The message to log if the file already exists.
     * @return true if the file was successfully created;
     * false if the file already exists or an error occurred.
     */
    public boolean createFile(File file, String fallbackMessage) {
        // try to create the file
        try {
            if (file.createNewFile()) {
                // return success
                return true;
            } else {
                // file already exists
                System.out.println(fallbackMessage);

                // return failure
                return false;
            }
        } catch (IOException e) {
            // log the error to debug permissions or disk space
            System.err.println("Critical IO Error: " + e.getMessage());

            // return failure
            return false;
        }
    }

    /**
     * Reads the content of a file and returns it as a single String.
     *
     * @param file            The File object to be read.
     * @param fallbackMessage A custom message to log if the file is missing.
     * @return The content of the file as a String, or null if an error occurs.
     */
    public void readFile(File file, String fallbackMessage) {
        // try-with-resources: Scanner will be closed automatically
        try (Scanner reader = new Scanner(file)) {
            // read the data
            while (reader.hasNextLine()) {
                // read the line
                String data = reader.nextLine();

                // todo: return the data
                System.out.println(data);
            }
        } catch (FileNotFoundException e) {
            // log the error to debug permissions or disk space
            System.err.println("Critical FileNotFoundException Error: " + e.getMessage());
        }
    }

    /**
     * Deletes the specified file from the file system.
     *
     * @param file            The File object representing the file to be deleted.
     * @param fallbackMessage A custom message to log if the deletion fails.
     * @return true if the file was successfully deleted; false otherwise.
     */
    public boolean deleteFile(File file, String fallbackMessage) {
        // check if file exists before trying to delete
        if (!file.exists()) {
            // print failure message
            System.err.println("File does not exist - " + file.getPath());

            // return failure
            return false;
        }

        // try to delete the file
        if (file.delete()) {
            System.out.println("Deleted the file: " + file.getName());
            // return success
            return true;
        } else {
            // log the error
            System.out.println(fallbackMessage);

            // return failure
            return false;
        }

    }
}
