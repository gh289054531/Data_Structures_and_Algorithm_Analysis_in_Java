import java.util.Arrays;

/**
 * 最长递增子序列（LIS），时间复杂度O(N^2)
 * 像LCS一样，从后向前分析，很容易想到，第i个元素之前的最长递增子序列的长度要么是1（单独成一个序列），要么就是第i-1个元素之前的最长递增子序列加1
 * 可以有状态方程：len[i] = max{1,len[k]+1},其中，对于任意的k<=i-1，input[i] > input[k]
 */
public class LongestIncreseSequence1 {
	public static <T extends Comparable<? super T>> int LIS(T[] input, int[] len, int[] pre) {
		if (input == null || len == null) {
			return 0;
		}
		int maxLen = 0;
		for (int i = 0; i < input.length; i++) {
			len[i] = 1;
			pre[i] = -1;
			for (int j = 0; j < i; j++) {
				if (input[j].compareTo(input[i]) < 0 && len[i] < len[j] + 1) {// 注意判断条件的意义
					len[i] = len[j] + 1;
					pre[i] = j;
					maxLen = len[i];
				}
			}
		}
		return maxLen;
	}

	public static <T extends Comparable<? super T>> void printpath(T[] input, int[] pre, int index) {
		if (index == -1) {
			return;
		}
		printpath(input, pre, pre[index]);
		System.out.print(input[index] + " ");
	}

	public static void main(String[] args) {
		Integer[] input1 = new Integer[] { 1, -1, 2, -3, 9, 4, -5, 6, -7 };
		int[] len = new int[input1.length];
		int[] pre = new int[input1.length];
		int maxlen = LongestIncreseSequence1.LIS(input1, len, pre);
		System.out.println(maxlen);
		LongestIncreseSequence1.printpath(input1, pre, Arrays.binarySearch(len, maxlen));
	}
}
