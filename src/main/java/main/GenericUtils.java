package main;

import java.util.Comparator;

public class GenericUtils {

    public static <T extends Comparable<T>> void sortDescending(T[] array) {
        sortDescending(array, (a, b) -> b.compareTo(a));
    }
    public static <T> T[] sortDescending(T[] array, Comparator<? super T> comparator) {
        if (array == null || array.length <= 1) {
            // Array is already sorted or empty so no need to sort
            return array;
        }

        for (int i=0; i<array.length-1; i++) {
            for (int j=i+1; j<array.length; j++) {
                if (comparator.compare(array[i], array[j]) <0) {
                    //Swamp if out of order
                    T temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
        return array;
    }

}
