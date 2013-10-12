public class MaxSubSequenceP33 {
	public static int max(int[] seq) {
		int curMax = 0;
		int max = 0;
		for (int i = 0; i < seq.length; i++) {
			curMax += seq[i];
			if (curMax < 0) {
				curMax = 0;
			} else if (curMax > max) {
				max = curMax;
			}
		}
		return max;
	}

	public static void main(String[] args) {
		int[] data = new int[] { -2, 11, -4, 13, -5, -2 };
		System.out.println(MaxSubSequenceP33.max(data));
	}
}
