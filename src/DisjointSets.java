/**
 * 不相交集合类，采用数组表示树结构，基本操作find和union。
 * 采用按大小合并、路径压缩
 */
public class DisjointSets {

	int[] s = null;

	public DisjointSets(int num) throws Exception {
		if (num < 1) {
			throw new Exception("集合类大小必须大于0");
		}
		s = new int[num];
		for (int i = 0; i < num; i++) {
			s[i] = -1;
		}
	}

	/**
	 * 采用路径压缩（path compression）的find操作，路径压缩find(x)效果是使得从x到根的所有结点的父节点为根，降低了树的高度。
	 * 最差时间复杂度O(logN)
	 * 
	 * @param x
	 * @return 根的位置
	 */
	public int find(int x) {
		if (s[x] < 0) {
			return x;
		} else {
			return s[x] = find(s[x]);
		}
	}

	/**
	 * 按高度求并，union by size，让较小的树成为较大树的树的子树，保证所有树高度都不超过logN。 s中根存放树大小的负值，初始为-1。
	 * 时间复杂度O(1)
	 * 
	 * @param root1
	 * @param root2
	 */
	public void union(int root1, int root2) {
		int size = s[root1] + s[root2];
		if (s[root1] < s[root2]) {
			s[root1] = root2;
			s[root2] = size;
		} else {
			s[root2] = root1;
			s[root1] = size;
		}
	}

	public static void main(String[] args) throws Exception {
		DisjointSets ds = new DisjointSets(8);
		ds.union(4, 5);
		ds.union(6, 7);
		ds.union(4, 6);
		for (int i = 0; i < ds.s.length; i++) {
			System.out.print(ds.s[i] + " ");
		}
	}

}
