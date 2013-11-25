/**
 * ѡ�����⣬���ÿ���ѡ��ѡȡpivot����ȡ��ֻ����������������з�ΪM�飬ÿ��5��Ԫ�أ���ÿ���������������õ����Ȼ���M���鰴�����������
 * ȡ�м��Ǹ����������Ϊpivot�� �ǲ��û���˼·����������ŵķ����ˡ� ������Ȼ��Ҫ��N��Ԫ�ض������ڴ档 ʱ�临�Ӷ���á�ƽ��������O(N)��
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
	 * ��ֻ����������
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
