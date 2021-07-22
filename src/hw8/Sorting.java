package hw8;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Random;

public class Sorting {
	
	public <T> void bubblesort(T[] arr, Comparator<T> comparator) {
		for (int i = arr.length; i >= 0; i--) {
			for (int j = 0; j < i - 1; j++) {
				if (comparator.compare(arr[j],arr[j+1]) > 0) {
					swap(arr, j, j +1);
				}
			}
		}
	}
	public static <T> void insertionSort(T[] arr, Comparator<T> comparator) {
		if (arr == null || comparator == null) {
			throw new IllegalArgumentException("Null array or comparator");
		}
		
		for (int i = 1; i < arr.length; i++) {
			T temp = arr[i];
			int j = i - 1;
			while (j >= 0 && comparator.compare(arr[j], temp) > 0) {
				arr[j + 1]= arr[j--];
			}
			arr[j + 1] = temp;
		}
	}
	
	public static <T> void selectionSort(T[] arr, Comparator<T> comparator) {
		if (arr == null || comparator == null) {
			throw new IllegalArgumentException("Null array or comparator");
		}
		for (int i = 0; i < arr.length - 1; i ++) {
			int min = i;
			for (int j = i + 1; j < arr.length; j++) {
				if (comparator.compare(arr[j], arr[min]) < 0) {
					min = j;
				}
			}
			swap(arr, min, i);
		}
	}
	
	public static <T> void mergeSort(T[] arr, Comparator<T> comparator) {
		if (arr == null || comparator == null) {
			throw new IllegalArgumentException("Null array or comparator");
		}
		
		if (arr.length > 1) {
			T[] left = (T[]) new Object[arr.length / 2];
			T[] right = (T[]) new Object[arr.length - left.length];
			
			for (int i = 0; i < left.length; i++) {
				left[i] = arr[i];
			}
			
			for (int i = 0; i < right.length; i++) {
				right[i] = arr[i + left.length];
			}
			
			mergeSort(left, comparator);
			mergeSort(right, comparator);
			merge(arr, comparator, left, right);
		}
	}
	
	private static <T> void merge(T[] arr, Comparator<T> comparator, T[] leftArr, T[] rightArr) {
		int i = 0;
		int j = 0;
		for (int k = 0; k < arr.length; k++) {
			if (j >= rightArr.length || (i < leftArr.length && comparator.compare(leftArr[i], rightArr[j]) <= 0)) {
				arr[k] = leftArr[i++];
			} else {
				arr[k] = rightArr[j++];
			}
		}
	}
	
	private static<T> void swap(T[] arr, int a, int b) {
		T temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}
	
	public static void lsdRadixSort(int[] arr) {
		if (arr == null) {
			throw new IllegalArgumentException("Array can't be null");
		}
		LinkedList<Integer>[] buckets = new LinkedList[19];
		
		for (int i = 0; i < 19; i++) {
			buckets[i] = new LinkedList<>();
		}
		
		int mod = 10;
		int div = 1;
		boolean cont = true;
		while(cont) {
			cont= false;
			for (int num : arr) {
				int bucket = num / div;
				
				if (bucket / 10 != 0) {
					cont = true;
				}
				
				if (buckets[bucket % mod + 9] == null) {
					buckets[bucket % mod + 9] = new LinkedList<>();
				}
				buckets[bucket % mod + 9].add(num);
			}
			int arrIdx = 0;
			for (LinkedList<Integer> bucket : buckets) {
				if (bucket != null) {
					for (int num : bucket) {
						arr[arrIdx++] = num;
					}
					bucket.clear();
				}
			}
			div *= 10;
		}
	}
}
