/**
 * 0/1背包问题
 * 
 * f[i][j]=max{f[i-1][j],f[i-1][j-W(i)]+V(i)} ，参数i:第i个物品， 参数j:背包的容量
 * 
 */
public class PackageProblem0_1 {

	public static int Max = 10;// 最大容量
	public static int[] W = new int[] { 3, 4, 5 };// 重量
	public static int[] V = new int[] { 4, 5, 6 };// 价值

	public static void Package(int[][] f) {
		for (int i = 1; i <= W.length; i++) {
			for (int j = W[i - 1]; j <= Max; j++) {
				f[i][j] = Math.max(f[i - 1][j], f[i - 1][j - W[i - 1]] + V[i - 1]);
			}
		}

		boolean[] choose = new boolean[W.length];
		int m = Max;
		for (int i = W.length; i > 0; i--) {
			if (f[i - 1][m] < f[i][m]) {
				choose[i - 1] = true;
				m -= W[i - 1];
			}
		}
		for (int i = 0; i < choose.length; i++) {// 输出被选中的物品的下标
			if (choose[i] == true) {
				System.out.println(i);
			}
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] f = new int[W.length + 1][Max + 1];
		Package(f);
	}
}
