public class PowP36 {
	/**
	 * 分治，时间复杂度O(logN), T(N)=2*T(N/2)+O(c),时间复杂度O(N*logN)
	 * 
	 * @param x
	 * @param n
	 * @return
	 */
	public static long pow(long x, int n) {
		if (n == 0) {
			return 1;
		}
		if (n == 1) {
			return x;
		}
		if (n % 2 == 0) {
			long temp = pow(x, n / 2);
			return temp * temp;
		}
		if (n % 2 == 1) {
			long temp = pow(x, (n - 1) / 2);
			return temp * temp * x;
		}
		return 1;
	}

	public static void main(String[] args) {
		System.out.println(PowP36.pow(3, 5));
	}
}
