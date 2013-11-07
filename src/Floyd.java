public class Floyd {
	
	/**
	 * Floyd�㷨���������е�Եľ��룬�����ߣ�������ֵȦ��ʱ�临�Ӷ�O(N^3)
	 * ��Dijstra�㷨��ȣ�Dijstra�������ߺ͸�ֵȦ������Dijstra�ǵ�Դ�㡣
	 * 
	 * @param a ͼ���ڽӾ���
	 * @param d ��ž���
	 * @param path ���·����path[i][j]=x,��ʾ��i��j���뾭��x
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
