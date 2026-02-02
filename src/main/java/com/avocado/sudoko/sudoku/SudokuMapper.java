package com.avocado.sudoko.sudoku;

import com.avocado.sudoko.sudoku.dtos.SudokuDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface SudokuMapper {
    // method to map from Entity to DTO
    @Mappings({
            @Mapping(target = "gameId", source = "uuid"),
            @Mapping(target = "puzzleString", source = "puzzle")
    })
    SudokuDto toDto(Sudoku sudoku);

    // method to map from DTO to Entity
    @Mappings({
            @Mapping(target = "uuid", source = "gameId"),
            @Mapping(target = "puzzle", source = "puzzleString")
    })
    Sudoku toEntity(SudokuDto sudokuDto);
}
