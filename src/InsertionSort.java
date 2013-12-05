/**
 * 插入排序,稳定 有序时最好O(N),平均和最坏O(N^2)
 */
public class InsertionSort {
	public static <T extends Comparable<? super T>> void Sort(T[] input) {
		if (input == null || input.length < 2) {
			return;
		}
		for (int i = 1; i < input.length; i++) {
			int j = i - 1;
			T temp = input[i];
			for (; j >= 0 && temp.compareTo(input[j]) < 0; j--) {
				input[j + 1] = input[j];
			}
			input[j + 1] = temp;
		}
	}

	public static void main(String[] args) {
		Integer[] test1 = new Integer[] { 1, 2, 3, 2, 5 };
		InsertionSort.Sort(test1);
		for (int i = 0; i < test1.length; i++) {
			System.out.println(test1[i]);
		}

		Integer[] test2 = new Integer[] {};
		InsertionSort.Sort(test2);
		for (int i = 0; i < test2.length; i++) {
			System.out.println(test2[i]);
		}
	}

}
