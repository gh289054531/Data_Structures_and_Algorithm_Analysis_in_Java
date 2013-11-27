/**
 * ���������ȶ�.ʱ�临�Ӷ�O(d*N),d�����ֵ�λ�����ռ临�Ӷ�O(K+N),K�ǻ��� ��N�ϴ�d��Сʱ����
 */
public class RadixSort {
	/**
	 * Ҫ����ʮ���ƷǸ���������
	 * 
	 * @param input
	 * @param d
	 */
	public static int[] sort(int[] input, int d) {
		if (input == null) {
			return null;
		}
		int position = 0;// low to high digit,LSD��Least significant digital��
		while (position < d) {
			int[] temp = new int[input.length];
			int[] count = new int[10];// temp array for countingsort
			for (int i = 0; i < input.length; i++) {// CountingSort
				count[getDigit(input[i], position)] += 1;
			}
			for (int m = 1; m < 10; m++) {// like bucket sort
				count[m] += count[m - 1];
			}
			for (int i = input.length - 1; i >= 0; i--) {
				int digit = getDigit(input[i], position);
				int newIndex = count[digit] - 1;
				temp[newIndex] = input[i];
				count[digit] -= 1;
			}
			position++;
			input = temp;
		}
		return input;
	}

	public static int getDigit(int num, int position) {
		int i = 0;
		while (i++ < position) {
			num = num / 10;
		}
		return num % 10;
	}

	public static void main(String[] args) {
		int[] input = new int[] { 342, 58, 576, 356 };
		input = RadixSort.sort(input, 3);
		for (int i = 0; i < input.length; i++) {
			System.out.println(input[i]);
		}
	}

}
