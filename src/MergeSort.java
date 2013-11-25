/**
 * 最优情况是数组已经有序O(N)，平均和最坏都是O(N*logN)，稳定
 * 需要额外空间O(N)
 * 归并排序是常用排序算法中需要比较次数最少的，分治思想
 * 在JAVA中，泛型进行比较操作操作昂贵，而数组元素移动很快（因为移动的是引用），适合归并排序。
 * 因此泛型的Collections.sort()方法是一种归并排序的改进版本
 * 但是基本元素的排序Arrays.sort()方法是改进的快速排序
 */
public class MergeSort {
	public static <T extends Comparable<? super T>> void Merge(T[] input, T[] temp, int left, int middle, int right) {
		int tempPos = left;
		int i = left, j = middle + 1;
		for (; i <= middle && j <= right;) {
			if (input[i].compareTo(input[j]) <= 0) {
				temp[tempPos++] = input[i++];
			}
			if (input[i].compareTo(input[j]) > 0) {
				temp[tempPos++] = input[j++];
			}
		}
		while (i <= middle) {
			temp[tempPos++] = input[i++];
		}
		while (j <= right) {
			temp[tempPos++] = input[j++];
		}
		System.arraycopy(temp, left, input, left, right - left + 1);
	}

	public static <T extends Comparable<? super T>> void MergeSort(T[] input, T[] temp, int left, int right) {
		if (left < right) {
			int middle = (left + right) / 2;
			MergeSort(input, temp, left, middle);
			MergeSort(input, temp, middle + 1, right);
			Merge(input, temp, left, middle, right);
		}
	}

	public static <T extends Comparable<? super T>> void MergeSort(T[] input) {
		if (input == null) {
			return;
		}
		T[] temp = (T[]) new Comparable[input.length];
		MergeSort(input, temp, 0, input.length - 1);
	}


	public static void main(String[] args) {
		Integer[] test1 = new Integer[] { 213, 123, 432, 54, 56765, 5324, 8768, 234 };
		MergeSort.MergeSort(test1);
		for (int i = 0; i < test1.length; i++) {
			System.out.println(test1[i]);
		}
	}

}
