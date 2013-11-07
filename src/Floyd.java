public class Floyd {
	
	/**
	 * Floyd算法，计算所有点对的距离，允许负边，不允许负值圈。时间复杂度O(N^3)
	 * 与Dijstra算法相比，Dijstra不允许负边和负值圈，而且Dijstra是单源点。
	 * 
	 * @param a 图的邻接矩阵
	 * @param d 存放距离
	 * @param path 存放路径，path[i][j]=x,表示从i到j必须经过x
	 */
	public static void AllPairsDist(double[][] a, double[][] d, int[][] path) {
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a.length; j++) {
				d[i][j] = a[i][j];
				path[i][j] = -1;
			}
		}
		for (int k = 0; k < a.length; k++) {
			for (int i = 0; i < a.length; i++) {
				for (int j = 0; j < a.length; j++) {
					if (d[i][j] > d[i][k] + d[k][j]) {
						d[i][j] = d[i][k] + d[k][j];
						path[i][j] = k;
					}
				}
			}
		}
	}

	public static String PrintPath(int[][] path, int i, int j) {
		if (i < 0 || i > path.length - 1 || j < 0 || j > path.length - 1) {
			return null;
		}
		if (i == j) {
			return "";
		}
		if (path[i][j] == -1) {
			return "";
		}
		return PrintPath(path, i, path[i][j]) + " " + path[i][j] + " " + PrintPath(path, path[i][j], j);
	}


	public static void main(String[] args) {
		double[][] a = new double[][] { { 0, 2, Double.MAX_VALUE, 1, Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE }, { Double.MAX_VALUE, 0, Double.MAX_VALUE, 3, 10, Double.MAX_VALUE, Double.MAX_VALUE }, { 4, Double.MAX_VALUE, 0, Double.MAX_VALUE, Double.MAX_VALUE, 5, Double.MAX_VALUE },
				{ Double.MAX_VALUE, Double.MAX_VALUE, 2, 0, 2, 8, 4 }, { Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE, 0, Double.MAX_VALUE, 6 }, { Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE, 0, Double.MAX_VALUE },
				{ Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE, 1, 0 } };
		double[][] d = new double[a.length][a.length];
		int[][] path = new int[a.length][a.length];
		Floyd.AllPairsDist(a, d, path);
		System.out.println(Floyd.PrintPath(path, 0, 0));
		System.out.println(Floyd.PrintPath(path, 0, 1));
		System.out.println(Floyd.PrintPath(path, 0, 2));
		System.out.println(Floyd.PrintPath(path, 0, 3));
		System.out.println(Floyd.PrintPath(path, 0, 4));
		System.out.println(Floyd.PrintPath(path, 0, 5));
		System.out.println(Floyd.PrintPath(path, 0, 6));
		System.out.println(Floyd.PrintPath(path, 3, 0));
		System.out.println(Floyd.PrintPath(path, 1, 0));
		System.out.println(Floyd.PrintPath(path, 1, 2));
		System.out.println(Floyd.PrintPath(path, 1, 3));
	}
}
