/**
 * 快速排序，应该是所有常用方法最快的一种 最优和平均时间复杂度O(N*logN)，最坏O(N^2)。 不需要额外空间。
 * Java中基本元素排序用的是改进的快速排序。
 */
public class QuickSort {
	private static <T extends Comparable<? super T>> T Median(T[] input, int left, int right) {
		int middle = (right + left) / 2;
		if (input[middle].compareTo(input[left]) < 0) {
			SwapRef(input, middle, left);
		}
		if (input[right].compareTo(input[left]) < 0) {
			SwapRef(input, right, left);
		}
		if (input[right].compareTo(input[middle]) < 0) {
			SwapRef(input, middle, right);
		}
		SwapRef(input, middle, right);
		return input[right];
	}

	private static <T extends Comparable<? super T>> void SwapRef(T[] input, int i, int j) {
		T temp = input[i];
		input[i] = input[j];
		input[j] = temp;
	}

	private static <T extends Comparable<? super T>> void QuickSort(T[] input, int left, int right) {
		if (right > left) {
			T pivot = Median(input, left, right);
			int i = left, j = right - 1;
			while (true) {
				while (i < right && input[i].compareTo(pivot) < 0) {
					i++;
				}
				while (j >= left && input[j].compareTo(pivot) > 0) {
					j--;
				}
				if (i < j) {
					SwapRef(input, i++, j--);
				} else {
					break;
				}
			}
			SwapRef(input, right, i);
			QuickSort(input, left, i - 1);
			QuickSort(input, i + 1, right);
		}
	}

	public static <T extends Comparable<? super T>> void QuickSort(T[] input) {
		if (input == null) {
			return;
		}
		QuickSort(input, 0, input.length - 1);
	}

	public static void main(String[] args) {
		Integer[] test1 = new Integer[] { 213, 123, 432, 54, 56765, 5324, 8768, 234 };
		QuickSort.QuickSort(test1);
		for (int i = 0; i < test1.length; i++) {
			System.out.println(test1[i]);
		}
	}

}
