/**
 * 求平方根，牛顿迭代法， http://my.oschina.net/qihh/blog/93723
 */
public class Sqrt {
	public static double sqrt(double c) {
		if (c < 0) {
			return Double.NaN;
		}
		double err = 1e-15;
		double t = c;
		while (Math.abs(t * t - c) > err) {
			t = (c / t + t) / 2;
		}
		return t;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(Sqrt.sqrt(65.45));
	}

}
