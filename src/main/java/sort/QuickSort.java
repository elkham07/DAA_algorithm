package sort;

import util.Metrics;

public class QuickSort {

    public static void sort(int[] arr, Metrics metrics) {
        quickSort(arr, 0, arr.length - 1, metrics);
    }

    private static void quickSort(int[] arr, int low, int high, Metrics metrics) {
        metrics.enterRecursion();

        if (low < high) {
            int pivotIndex = partition(arr, low, high, metrics);
            quickSort(arr, low, pivotIndex - 1, metrics);
            quickSort(arr, pivotIndex + 1, high, metrics);
        }

        metrics.exitRecursion();
    }

    private static int partition(int[] arr, int low, int high, Metrics metrics) {
        int pivot = arr[high]; // последний элемент как опорный
        int i = low - 1;

        for (int j = low; j < high; j++) {
            metrics.comparisons++;
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j, metrics);
            }
        }
        swap(arr, i + 1, high, metrics);
        return i + 1;
    }

    private static void swap(int[] arr, int i, int j, Metrics metrics) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        metrics.swaps++;
    }
}

