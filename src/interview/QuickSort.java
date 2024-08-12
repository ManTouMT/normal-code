package interview;

public class QuickSort {
    public static void main(String[] args) {
//        int[] arr = {5432, 5432, 5432, 1234, 1234, 1234, 7890, 7890, 7890, 3456, 3456, 3456, 9876, 9876, 9876, 2345, 2345, 2345, 6789, 6789, 6789, 1111, 1111, 1111, 2222, 2222, 2222, 3333, 3333, 3333, 4444, 4444, 4444, 5555, 5555, 5555, 6666, 6666, 6666, 7777, 7777, 7777, 8888, 8888, 8888, 9999, 9999, 9999, 1000, 1000, 1000, 2000, 2000, 2000, 3000, 3000, 3000, 4000, 4000, 4000, 5000, 5000, 5000, 6000, 6000, 6000, 7000, 7000, 7000, 8000, 8000, 8000, 9000, 9000, 9000, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116};
        int[] arr = {8,4,3,9,10,5,3,4};
        QuickSort quickSort = new QuickSort();
        quickSort.quickSort(arr, 0, arr.length - 1);
        for (int j : arr) {
            System.out.println(j);
        }
    }

    private void quickSort(int[] arr, int left, int right) {
        if(left >= right) return;
        int pivot = partition(arr, left, right);
        quickSort(arr, left, pivot - 1);
        quickSort(arr, pivot + 1, right);
    }

    private int partition(int[] arr, int left, int right) {
        int firstMaxValue = left + 1;
        for(int i = left + 1; i <= right; i++) {
            if(arr[i] < arr[left]) {
                if(i != firstMaxValue) {
                    swap(arr, i, firstMaxValue);
                }
                firstMaxValue++;
            }
        }
        swap(arr, left, firstMaxValue - 1);
        return firstMaxValue - 1;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
