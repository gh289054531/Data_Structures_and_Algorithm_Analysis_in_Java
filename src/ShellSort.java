/**
 * 希尔排序，增量序列的选取并非最优
 * 常见的增量序列见书P186
 * 最坏是O(N^2)，一般情况最好可以达到O(N^1.3)左右，不稳定
 * 即便对万级的数组排序也比较快
 */
public class ShellSort {
	public static <T extends Comparable<? super T>> void ShellSort(T[] input) {
		if (input == null) {
			return;
		}
		for (int gap = input.length / 2; gap > 0; gap /= 2) {
			for (int i = gap; i < input.length; i++) {
				T cur = input[i];
				int j = i;
				for (; j >= gap && cur.compareTo(input[j - gap]) < 0; j -= gap) {
					input[j] = input[j - gap];
				}
				input[j] = cur;
			}
		}
	}

	public static void main(String[] args) {
		Integer[] test1 = new Integer[] { 81, 94, 11, 96, 12, 35, 17, 95, 28, 58, 41, 75, 15 };
		ShellSort(test1);
		for (int i = 0; i < test1.length; i++) {
			System.out.println(test1[i]);
		}

		Integer[] test2 = new Integer[] {};
		ShellSort(test2);
		for (int i = 0; i < test2.length; i++) {
			System.out.println(test2[i]);
		}
	}

}
