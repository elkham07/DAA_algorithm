package sort;

import org.junit.jupiter.api.Test;
import util.Metrics;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class MergeSortTest {

    @Test
    void testSmallArray() {
        int[] arr = {5, 3, 8, 1, 9, 2};
        int[] expected = Arrays.copyOf(arr, arr.length);
        Arrays.sort(expected);

        Metrics metrics = new Metrics();
        MergeSort.sort(arr, metrics);

        assertArrayEquals(expected, arr);
        System.out.println("MergeSort - Comparisons: " + metrics.comparisons + ", Depth: " + metrics.depth);
    }

    @Test
    void testLargeArray() {
        int n = 10000;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = (int) (Math.random() * n);

        int[] expected = Arrays.copyOf(arr, arr.length);
        Arrays.sort(expected);

        Metrics metrics = new Metrics();
        MergeSort.sort(arr, metrics);

        assertArrayEquals(expected, arr);
        System.out.println("MergeSort Large - Depth: " + metrics.depth);
    }
}
