public class CountingSort {
	/**
	 * 输入的数都小于M(0<=x<M)时可以使用计数排序，时间复杂度O(N+M) 如果M>N*logN,则使用基于比较的排序比较好；
	 * 如果M特别大，也不适合用这个算法
	 */
	public static void sort(int[] input, int m) {
		if (input == null || input.length < 2) {
			return;
		}

		int[] bucket = new int[m];
		for (int i = 0; i < input.length; i++) {
			bucket[input[i]] = bucket[input[i]] + 1;
		}
		for (int i = 1; i < m; i++) {
			bucket[i] = bucket[i - 1] + bucket[i];
		}
		int[] temp = new int[input.length];
		for (int i = input.length - 1; i >= 0; i--) {
			temp[bucket[input[i]] - 1] = input[i];
			bucket[input[i]] = bucket[input[i]] - 1;
		}
		System.arraycopy(temp, 0, input, 0, input.length);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] input1 = new int[] { 1, 3, 4, 6, 4, 3, 8, 9, 7, 7, 2, 5, 1, 0, 0 };
		CountingSort.sort(input1, 10);
		for (int i = 0; i < input1.length; i++) {
			System.out.println(input1[i]);
		}
	}
}
