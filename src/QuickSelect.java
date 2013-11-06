import java.util.Arrays;

/**
 * 快速选择，从N个数中选出第k大的数 最坏O(N^2),平均O(N)。具体方法类似于快速排序。
 * 也可以用堆实现，时间复杂度为O(N+k*logN)
 */
public class QuickSelect {
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

	private static <T extends Comparable<? super T>> void QuickSelect(T[] input, int left, int right, int k) {
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
	}

	public static <T extends Comparable<? super T>> T QuickSelect(T[] input, int k) {
		if (input == null || k > input.length || k < 1) {
			return null;
		}
		T[] temp = Arrays.copyOf(input, input.length);
		QuickSelect(temp, 0, temp.length - 1, k);
		return temp[k - 1];
	}

	public static void main(String[] args) {
		Integer[] test1 = new Integer[] { 213, 123, 432, 54, 56765, 5324, 8768, 234 };
		Integer result = QuickSelect.QuickSelect(test1, 8);
		System.out.println(result);
	}

}
