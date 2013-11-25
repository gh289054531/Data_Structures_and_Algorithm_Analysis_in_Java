import java.util.Arrays;

/**
 * ����������У�LIS����ʱ�临�Ӷ�O(N^2)
 * ��LCSһ�����Ӻ���ǰ�������������뵽����i��Ԫ��֮ǰ������������еĳ���Ҫô��1��������һ�����У���Ҫô���ǵ�i-1��Ԫ��֮ǰ������������м�1
 * ������״̬���̣�len[i] = max{1,len[k]+1},���У����������k<=i-1��input[i] > input[k]
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
				if (input[j].compareTo(input[i]) < 0 && len[i] < len[j] + 1) {// ע���ж�����������
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
