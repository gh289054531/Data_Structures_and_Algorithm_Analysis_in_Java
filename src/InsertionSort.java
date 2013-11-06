/**
 * 插入排序
 * 有序时最好O(N),平均和最坏O(N^2)
 */
public class InsertionSort {
	public static <T extends Comparable<? super T>> void InsertionSort(T[] input) {
		for (int i = 0; i < input.length; i++) {
			T temp = input[i];
			int j = i;
			for (; j > 0 && input[j].compareTo(input[j - 1]) < 0; j--) {
				input[j] = input[j - 1];
			}
			input[j] = temp;
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Integer[] test1 = new Integer[] { 1, 2, 3, 2, 5 };
		InsertionSort(test1);
		for (int i = 0; i < test1.length; i++) {
			System.out.println(test1[i]);
		}

		Integer[] test2 = new Integer[] {};
		InsertionSort(test2);
		for (int i = 0; i < test2.length; i++) {
			System.out.println(test2[i]);
		}
	}

}
