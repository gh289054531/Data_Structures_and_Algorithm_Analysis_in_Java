public class Fibonacci {
	/**
	 * O(n)
	 * @param n
	 * @return
	 */
	public static int fibonacci(int n) {
		if(n<0){
			return -1;
		}
		if (n <= 1) {
			return n;
		}
		int pre = 1;
		int prepre = 1;
		int result = 0;
		for (int i = 2; i <= n; i++) {
			result = pre + prepre;
			prepre = pre;
			pre = result;
		}
		return result;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(Fibonacci.fibonacci(0));
		System.out.println(Fibonacci.fibonacci(1));
		System.out.println(Fibonacci.fibonacci(2));
		System.out.println(Fibonacci.fibonacci(3));
		System.out.println(Fibonacci.fibonacci(4));
		System.out.println(Fibonacci.fibonacci(5));
		System.out.println(Fibonacci.fibonacci(6));

	}

}
