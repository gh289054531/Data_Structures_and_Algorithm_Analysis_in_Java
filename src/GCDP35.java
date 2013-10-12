public class GCDP35 {
	public static long gcd(long m, long n) {
		if (m < n) {
			long temp = m;
			m = n;
			n = temp;
		}
		while (n != 0) {
			long rem = m % n;
			m = n;
			n = rem;
		}
		return m;
	}

	public static void main(String[] args) {
		System.out.println(GCDP35.gcd(1989, 1590));
	}
}
