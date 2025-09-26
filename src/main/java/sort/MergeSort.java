package sort;

import util.Metrics;

public class MergeSort {

    public static void sort(int[] arr, Metrics metrics) {
        int[] buffer = new int[arr.length]; // буфер для слияния
        mergeSort(arr, buffer, 0, arr.length - 1, metrics);
    }

    private static void mergeSort(int[] arr, int[] buffer, int left, int right, Metrics metrics) {
        metrics.enterRecursion();

        // Cut-off для маленьких массивов → insertion sort
        if (right - left <= 10) {
            insertionSort(arr, left, right, metrics);
        } else {
            int mid = left + (right - left) / 2;
            mergeSort(arr, buffer, left, mid, metrics);
            mergeSort(arr, buffer, mid + 1, right, metrics);
            merge(arr, buffer, left, mid, right, metrics);
        }

        metrics.exitRecursion();
    }

    private static void insertionSort(int[] arr, int left, int right, Metrics metrics) {
        for (int i = left + 1; i <= right; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= left) {
                metrics.comparisons++;
                if (arr[j] > key) {
                    arr[j + 1] = arr[j];
                    metrics.swaps++;
                    j--;
                } else {
                    break;
                }
            }
            arr[j + 1] = key;
        }
    }

    private static void merge(int[] arr, int[] buffer, int left, int mid, int right, Metrics metrics) {
        int i = left, j = mid + 1, k = left;
        while (i <= mid && j <= right) {
            metrics.comparisons++;
            if (arr[i] <= arr[j]) {
                buffer[k++] = arr[i++];
            } else {
                buffer[k++] = arr[j++];
            }
        }
        while (i <= mid) buffer[k++] = arr[i++];
        while (j <= right) buffer[k++] = arr[j++];

        for (i = left; i <= right; i++) {
            arr[i] = buffer[i];
        }
    }
}
