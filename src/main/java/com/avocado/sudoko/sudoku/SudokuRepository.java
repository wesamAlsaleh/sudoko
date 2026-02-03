package com.avocado.sudoko.sudoku;

import com.avocado.sudoko.global.exceptions.FileWriteException;
import com.avocado.sudoko.global.exceptions.SudokuFileNotCreatedException;
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
    public void save(Sudoku sudoku) {
        // prepare the file name using the UUID
        var fileName = sudoku.getUuid() + ".txt";

        // prepare the file object
        File file = new File(dbPaths.getPuzzlesPath() + "sds", fileName);

        // create a file using the prepared name
        if (!fileReadWriter.createFile(file, "Failed to create a puzzle file")) {
            // if not created throw an exception
            throw new SudokuFileNotCreatedException(
                    String.format(
                            "Failed to create a puzzle file with the name of: %s in \"%s\"",
                            fileName,
                            dbPaths.getPuzzlesPath()
                    )
            );
        }

        // write in the created file and return the status of the operation
        if (!fileReadWriter.write(
                file,
                sudoku.getPuzzle(),
                false
        )) {
            // if failed to write throw an exception
            throw new FileWriteException(String.format("Failed to write puzzle file with the name of: %s", fileName));
        }
    }

}
