package com.avocado.sudoko.sudoku;

import com.avocado.sudoko.global.io.DBPaths;
import com.avocado.sudoko.global.io.FileReadWriter;
import com.avocado.sudoko.sudoku.dtos.SudokuDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
@AllArgsConstructor
public class SudokuRepository {
    private final DBPaths dbPaths;
    private final FileReadWriter fileReadWriter;

    // function to save the solution puzzle in a file
    public boolean save(Sudoku sudoku) {
        // prepare the file name using the UUID
        var fileName = sudoku.getUuid() + ".txt";

        // prepare the file object
        File file = new File(dbPaths.getPuzzlesPath(), fileName);

        // create a file using the prepared name
        if (!fileReadWriter.createFile(file, "Failed to create a puzzle file")) {
            // if not created return false
            return false;
        }

        System.out.println("Created puzzle file: " + fileName);

        // write in the created file and return the status of the operation
        return fileReadWriter.write(
                file,
                sudoku.getPuzzle(),
                false
        );
    }

}
