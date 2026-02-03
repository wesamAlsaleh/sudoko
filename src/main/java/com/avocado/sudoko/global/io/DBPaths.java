package com.avocado.sudoko.global.io;

import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.nio.file.Paths;


/**
 * Utility class for managing and resolving file system paths within the application.
 * This class centralizes the directory structure for Sudoku puzzles and solutions
 * to ensure consistency across different Operating Systems.
 */
@Component
public class DBPaths {
    /**
     * The root directory for all application data.
     * Set to a relative path "data" at the project root.
     */
    private static final Path DB_ROOT = Paths.get("data");

    /**
     * Resolves a directory or file name against the data root.
     * <p>
     * Example:
     * <pre>{@code
     * // If DB_ROOT is "data"
     * String path = getDirectoryPath("puzzles");
     * // returns "data/puzzles" (on Unix) or "data\puzzles" (on Windows)
     * }</pre>
     *
     * @param directoryName The name of the directory or file.
     * @return The path string for the resolved directory or file.
     */
    public String getDirectoryPath(String directoryName) {
        return DB_ROOT.resolve(directoryName).toString();
    }

    /**
     * Provides the path where generated Sudoku puzzles are stored.
     *
     * @return The path string for the "puzzles" directory.
     */
    public String getPuzzlesPath() {
        return getDirectoryPath("puzzles");
    }

    /**
     * Provides the path where the solutions for the puzzles are stored.
     *
     * @return The path string for the "puzzles_solution" directory.
     */
//    public String getPuzzlesSolutionPath() {
//        return getDirectoryPath("puzzles_solution");
//    }
}
