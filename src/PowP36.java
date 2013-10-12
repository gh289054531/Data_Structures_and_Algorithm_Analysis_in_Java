public class PowP36 {
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
