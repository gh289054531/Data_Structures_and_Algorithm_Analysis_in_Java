/**
 * 选择问题，利用快速选择，选取pivot采用取五分化中项的中项，即把序列分为M组，每组5个元素，对每个组进行组内排序得到中项，然后对M个组按中项进行排序，
 * 取中间那个组的中项作为pivot。 是采用划分思路解决问题最优的方法了。 但是依然需要把N个元素都放入内存。 时间复杂度最好、平均、最差都是O(N)。
 */
public class QuickSelect1 {
	public static <T extends Comparable<? super T>> T QuickSelect(T[] input, int k) {
		if (input == null || k < 1 || k > input.length) {
			return null;
		}
		QuickSelect(input, 0, input.length - 1, input.length+1-k);
		return input[input.length-k];
	}

	private static <T extends Comparable<? super T>> void QuickSelect(T[] input, int left, int right, int k) {
		if (left >= right) {
			return;
		}
		T pivot = SelectPivot(input, left, right);
		int i = left, j = right;
		while (i < j) {
			while (input[i].compareTo(pivot) < 0) {
				i++;
			}
			while (input[j].compareTo(pivot) > 0) {
				j--;
			}
			SwapRef(input, i++, j--);
		}
		if (k <= i) {
			QuickSelect(input, left, i - 1, k);
		}
		if (k >= i + 2) {
			QuickSelect(input, i + 1, right, k);
		}
		if (k == i + 1) {
			return;
		}
	}

	private static <T extends Comparable<? super T>> void SwapRef(T[] input, int i, int j) {
		T temp = input[i];
		input[i] = input[j];
		input[j] = temp;
	}

	/**
	 * 五分化中项的中项
	 */
	private static <T extends Comparable<? super T>> T SelectPivot(T[] input, int left, int right) {
		if (left == right) {
			return input[0];
		}
		int m = (right - left + 1) / 5;
		if (m == 0) {
			m++;
		}
		T[] median = (T[]) new Comparable[m];
		int index = 0;
		for (int i = 0; i < m; i += 5) {
			if (i != m - 1) {
				for (int l = i + 1; l < i + 5; l++) {
					T temp = input[l];
					int p = l;
					for (; p > i && temp.compareTo(input[p - 1]) < 0; p--) {
						input[p] = input[p - 1];
					}
					input[p] = temp;
				}
				median[index++] = input[i + 2];
			} else {
				for (int l = i + 1; l <= right; l++) {
					T temp = input[l];
					int p = l;
					for (; p > i && temp.compareTo(input[p - 1]) < 0; p--) {
						input[p] = input[p - 1];
					}
					input[p] = temp;
				}
				median[index++] = input[(left + right) / 2];
			}
		}
		return SelectPivot(median, 0, median.length - 1);
	}

	public static void main(String[] args) {
		Integer[] test1 = new Integer[] { 213, 123, 432, 54, 56765, 5324, 8768, 234 };
		System.out.println(QuickSelect1.QuickSelect(test1, 1));
		System.out.println(QuickSelect1.QuickSelect(test1, 2));
		System.out.println(QuickSelect1.QuickSelect(test1, 3));
		System.out.println(QuickSelect1.QuickSelect(test1, 4));
		System.out.println(QuickSelect1.QuickSelect(test1, 5));
		System.out.println(QuickSelect1.QuickSelect(test1, 6));
		System.out.println(QuickSelect1.QuickSelect(test1, 7));
		System.out.println(QuickSelect1.QuickSelect(test1, 8));
	}

}
