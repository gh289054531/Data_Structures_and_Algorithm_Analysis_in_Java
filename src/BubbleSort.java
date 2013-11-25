/**
 * 冒泡排序，时间复杂度最好：O(N),平均和最坏都是O(N^2)，稳定
 */
public class BubbleSort {
	public static <T extends Comparable<? super T>> void BubbleSort(T[] input) {
		if (input == null) {
			return;
		}
		for (int i = 0; i < input.length - 1; i++) {
			for (int j = 0; j < input.length - 1 - i; j++) {
				if (input[j].compareTo(input[j + 1]) > 0) {
					T temp = input[j];
					input[j] = input[j + 1];
					input[j + 1] = temp;
				}
			}
		}
	}

	public static void main(String[] args) {
		Integer[] test1 = new Integer[] { 81, 94, 11, 96, 12, 35, 17, 95, 28, 58, 41, 75, 15 };
		BubbleSort.BubbleSort(test1);
		for (int i = 0; i < test1.length; i++) {
			System.out.println(test1[i]);
		}
	}

}
