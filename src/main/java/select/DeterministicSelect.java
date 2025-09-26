package select;

import util.Metrics;

public class DeterministicSelect {

    // Поиск k-го наименьшего элемента (0-based index)
    public static int select(int[] arr, int k, Metrics metrics) {
        if (arr == null || arr.length == 0) throw new IllegalArgumentException("Array is empty");
        if (k < 0 || k >= arr.length) throw new IllegalArgumentException("Invalid k");

        return deterministicSelect(arr, 0, arr.length - 1, k, metrics);
    }

    private static int deterministicSelect(int[] arr, int left, int right, int k, Metrics metrics) {
        // если массив маленький → сортируем вставками
        if (right - left <= 10) {
            insertionSort(arr, left, right, metrics);
            return arr[left + k];
        }

        // шаг 1: делим на группы по 5 элементов
        int n = right - left + 1;
        int numMedians = (int) Math.ceil((double) n / 5);
        int[] medians = new int[numMedians];

        for (int i = 0; i < numMedians; i++) {
            int subLeft = left + i * 5;
            int subRight = Math.min(subLeft + 4, right);
            insertionSort(arr, subLeft, subRight, metrics);
            int medianIndex = subLeft + (subRight - subLeft) / 2;
            medians[i] = arr[medianIndex];
        }

        // шаг 2: рекурсивно ищем медиану медиа́н
        int pivot = deterministicSelect(medians, 0, numMedians - 1, numMedians / 2, metrics);

        // шаг 3: делим массив вокруг pivot
        int pivotIndex = partition(arr, left, right, pivot, metrics);

        int rank = pivotIndex - left; // позиция pivot относительно left

        if (k == rank) return arr[pivotIndex];
        else if (k < rank) return deterministicSelect(arr, left, pivotIndex - 1, k, metrics);
        else return deterministicSelect(arr, pivotIndex + 1, right, k - rank - 1, metrics);
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

    private static int partition(int[] arr, int left, int right, int pivot, Metrics metrics) {
        // ищем pivot и ставим в конец
        int pivotIndex = left;
        for (int i = left; i <= right; i++) {
            if (arr[i] == pivot) {
                pivotIndex = i;
                break;
            }
        }
        swap(arr, pivotIndex, right);

        int storeIndex = left;
        for (int i = left; i < right; i++) {
            metrics.comparisons++;
            if (arr[i] < pivot) {
                swap(arr, i, storeIndex);
                storeIndex++;
            }
        }
        swap(arr, storeIndex, right);
        return storeIndex;
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}

