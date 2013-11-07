public class BestBinarySearchTree {
	/**
	 * Ѱ�����Ŷ��������,ʱ�临�Ӷ�O(N^3)
	 * 
	 * @param c
	 *            ������c[left][right]��ʾ�ӵ��ʵĵ�left����left��0��ʼ���ʿ�ʼ��right���ʣ�����right��
	 *            ���������ŵĶ����������������ȡʱ�䣬������
	 * @param w
	 *            ������ĵ���
	 * @param p
	 *            ���ʵĸ���
	 * @param lastChange
	 *            lastChange[left][right]��ʾ�Ӵ�left��right�ĵ���Ӧ��ѡȡ�ĸ�������Ϊ��
	 */
	public static void FindBestTree(double[][] c, String[] w, double[] p, int[][] lastChange) {
		int n = w.length;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				c[i][j] = Double.MAX_VALUE;
			}
		}
		for (int k = 0; k < n; k++) {
			for (int left = 0; left < n - k; left++) {
				int right = left + k;
				for (int i = left; i <= right; i++) {
					double sump = 0;
					for (int j = left; j <= right; j++) {
						sump += p[j];
					}
					double temp = getC(c, left, i - 1) + getC(c, i + 1, right) + sump;
					if (temp < getC(c, left, right)) {
						lastChange[left][right] = i;
						c[left][right] = temp;
					}
				}
			}
		}
	}

	private static double getC(double[][] c, int i, int j) {
		if (i > j) {
			return 0.0;
		} else {
			return c[i][j];
		}
	}

	public static Node1 BuildTree(int[][] lastChange, String[] w, int left, int right) {
		if (left > right) {
			return null;
		}
		int i = lastChange[left][right];
		Node1 cur = new Node1(w[i]);
		cur.left = BuildTree(lastChange, w, left, i - 1);
		cur.right = BuildTree(lastChange, w, i + 1, right);
		return cur;
	}

	public static void Traverse(Node1 root) {
		if (root == null) {
			return;
		}
		System.out.println(root.word);
		Traverse(root.left);
		Traverse(root.right);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] w = new String[] { "a", "am", "and", "egg", "if", "the", "two" };
		double[] p = new double[] { 0.22, 0.18, 0.20, 0.05, 0.25, 0.02, 0.08 };
		double[][] c = new double[w.length][w.length];
		int[][] lastChange = new int[w.length][w.length];
		BestBinarySearchTree.FindBestTree(c, w, p, lastChange);
		Node1 root = BestBinarySearchTree.BuildTree(lastChange, w, 0, w.length - 1);
		BestBinarySearchTree.Traverse(root);
	}

}

class Node1 {
	String word;
	Node1 left = null, right = null;

	public Node1(String word) {
		this.word = word;
	}
}
