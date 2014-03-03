public class CountingSort {
	/**
	 * ���������С��M(0<=x<M)ʱ����ʹ�ü�������ʱ�临�Ӷ�O(N+M) ���M>N*logN,��ʹ�û��ڱȽϵ�����ȽϺã�
	 * ���M�ر��Ҳ���ʺ�������㷨
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
