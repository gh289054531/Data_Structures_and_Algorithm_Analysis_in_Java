/**
 * 给定数组A，i和j是数组下标并且j>i，求max(A[j]-A[i])
 * 时间复杂度O(N)
 */
public class FindMaxDiff {

	public static int cal(int[] A) {
		if (A == null || A.length == 0) {
			return 0;
		}
		int i = 0;
		int curMax = Integer.MIN_VALUE, curMin = Integer.MAX_VALUE;
		int maxDiff = 0;
		while (i < A.length-1) {
			while (i + 1 < A.length && A[i] >= A[i + 1]) {
				i++;
			}
			if (A[i] < curMin) {
				curMin = A[i];
				curMax = A[i];
			}
			while (i + 1 < A.length && A[i] <= A[i + 1]) {
				i++;
			}
			if (A[i] > curMax) {
				curMax = A[i];
				maxDiff = Math.max(maxDiff, curMax - curMin);
			}
		}
		return maxDiff;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a1 = new int[] { 1, 3, 7, -9, 2 };
		System.out.println(FindMaxDiff.cal(a1));
		int[] a2 = new int[] { 11, 3,3,7,7 ,7, -1, 2,2 };
		System.out.println(FindMaxDiff.cal(a2));
	}

}
