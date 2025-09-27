package sort;

import org.junit.jupiter.api.Test;
import select.DeterministicSelect;
import util.Metrics;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class selectTest {

    @Test
    void testSmallArray() {
        int[] arr = {12, 3, 5, 7, 19, 1};
        Metrics metrics = new Metrics();

        int k = 2;
        int result = DeterministicSelect.select(arr, k, metrics);

        assertEquals(5, result);
        System.out.println("Select - Comparisons: " + metrics.comparisons);
    }

    @Test
    void testLargeArray() {
        int n = 10000;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = (int) (Math.random() * n);

        Metrics metrics = new Metrics();
        int k = n / 2;

        int result = DeterministicSelect.select(arr, k, metrics);

        System.out.println("Select Large - k=" + k + ", result=" + result + ", Comparisons=" + metrics.comparisons);
    }
}
