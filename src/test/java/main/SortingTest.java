package main;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class SortingTest {
    @Test
    public void SortingTest() {
        Integer[] intArray = {5, 2, 8, 1, 9};
        GenericUtils.sortDescending(intArray);
        Integer[] expectedIntArray = {9, 8, 5, 2, 1};
        assertEquals(Arrays.asList(expectedIntArray), Arrays.asList(intArray));
    }
}