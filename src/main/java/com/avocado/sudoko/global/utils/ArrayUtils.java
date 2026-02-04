package com.avocado.sudoko.global.utils;

import org.springframework.stereotype.Component;

@Component
public class ArrayUtils {
    // function to copy from array and paste it in another array
    private void arrayCopy(Object[] array, Object[] copyArray) {
        // iterate over each element in the array
        for (int i = 0; i < array.length; i++) {
            // copy the cells in each row
            System.arraycopy(
                    array[i], // array to be copied from
                    0, // starting position in source array from where to copy (first index)
                    copyArray[i], // array to be copied in
                    0, // starting position in destination array, where to paste in (first index as well)
                    array.length // total no. of components to be copied (9 cells in each row)
            );
        }
    }
}
