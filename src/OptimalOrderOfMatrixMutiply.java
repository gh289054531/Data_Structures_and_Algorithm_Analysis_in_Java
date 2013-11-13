/**
 * 计算矩阵的最优计算顺序,时间复杂度O(N^3)，动态规划
 * 
 */
public class OptimalOrderOfMatrixMutiply {
	/**
	 * 编号从1开始，也就是说数组索引为0的位置不存放信息
	 * 
	 * @param c
	 *            c[i]存放第i个矩阵的列数目，特别的c[0]存放第1个矩阵的行数目，矩阵编号从1开始而不是从0开始
	 * @param m
	 *            m[i][j]是从第i到第j个矩阵的最优乘法次数，特别的m[i][i]=0
	 * @param lastChange
	 *            lastChange[i][j]表示从第i个矩阵到第j个矩阵应该从哪个矩阵处分开为两段，分别计算这两段的最优乘法次数，
	 *            再把这两段相乘，第lastChange[i][j]个矩阵被分到左边段
	 * @throws Exception
	 */
	public static void optOrder(int[] c, long[][] m, int[][] lastChange) throws Exception {
		if (c.length < 3) {
			throw new Exception("矩阵个数少于2个");
		}
		int n = c.length - 1;// 矩阵个数
		for (int i = 1; i <= n; i++) {
			m[i][i] = 0;
		}
		for (int k = 1; k < n; k++) {// k是left和right之间的距离，这个距离由1开始扩大直到n-1，这么做是因为当距离增大时，需要知道距离小时的m值
			for (int left = 1; left <= n - k; left++) {
				int right = left + k;
				m[left][right] = Long.MAX_VALUE;
				for (int i = left; i < right; i++) {
					long temp = m[left][i] + m[i + 1][right] + c[left - 1] * c[i] * c[right];
					if (temp < m[left][right]) {
						m[left][right] = temp;
						lastChange[left][right] = i;
					}
				}
			}
		}
	}

	public static String printOrder(int[][] lastChange, int left, int right) {
		if (left == right) {
			return left+"";
		}
		return "(" + printOrder(lastChange, left, lastChange[left][right]) + "*" + printOrder(lastChange, lastChange[left][right] + 1, right) + ")";
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] c = new int[] { 3, 5, 7, 2, 2 };
		long[][] m = new long[c.length][c.length];
		int[][] lastChange = new int[c.length][c.length];
		try {
			OptimalOrderOfMatrixMutiply.optOrder(c, m, lastChange);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(OptimalOrderOfMatrixMutiply.printOrder(lastChange, 1, c.length - 1));
	}

}
