import java.util.Arrays;

/**
 * ����ѡ�񣬴�N������ѡ����k����� �O(N^2),ƽ��O(N)�����巽�������ڿ������� Ҳ�����ö�ʵ��
 * ����ѡ��pivot������������ֵ�ָ�����õ�������ȡ��ֻ�������������һ�ݴ��롣
 * �����������Ҫ������Ԫ�ض��Ž��ڴ棬�������������ö�ʵ��ֻ��Ҫk��С�Ķѡ�
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
		if (input == null || k < 1 || k > input.length) {
			return null;
		}
		QuickSelect(input, 0, input.length - 1, input.length + 1 - k);
		return input[input.length - k];
	}

	public static void main(String[] args) {
		Integer[] test1 = new Integer[] { 213, 123, 432, 54, 56765, 5324, 8768, 234 };
		System.out.println(QuickSelect.QuickSelect(test1, 1));
		System.out.println(QuickSelect.QuickSelect(test1, 2));
		System.out.println(QuickSelect.QuickSelect(test1, 3));
		System.out.println(QuickSelect.QuickSelect(test1, 4));
		System.out.println(QuickSelect.QuickSelect(test1, 5));
		System.out.println(QuickSelect.QuickSelect(test1, 6));
		System.out.println(QuickSelect.QuickSelect(test1, 7));
		System.out.println(QuickSelect.QuickSelect(test1, 8));
	}

}
