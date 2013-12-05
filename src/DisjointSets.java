/**
 * ���ཻ�����࣬���������ʾ���ṹ����������find��union�� ���ð���С�ϲ���·��ѹ���� M��union��find������ʱ�临�Ӷ���O(M*logN)
 */
public class DisjointSets {

	public int[] s = null;

	public DisjointSets(int num) throws Exception {
		if (num < 1) {
			throw new Exception("�������С�������0");
		}
		s = new int[num];
		for (int i = 0; i < num; i++) {
			s[i] = -1;
		}
	}

	/**
	 * ����·��ѹ����path compression����find������·��ѹ��find(x)Ч����ʹ�ô�x���������н��ĸ��ڵ�Ϊ�������������ĸ߶ȡ�
	 * ����M�β�����Ҫ���O(M*logN)ʱ��
	 * 
	 * @param x
	 * @return ����λ��
	 */
	public int find(int x) {
		if (s[x] < 0) {
			return x;
		} else {
			return s[x] = find(s[x]);
		}
	}

	/**
	 * ����С�󲢣�union by size���ý�С������Ϊ�ϴ�����������������֤�������߶ȶ�������logN�� s�и��������С�ĸ�ֵ����ʼΪ-1��
	 * ����M�β�����ҪO(M)ƽ��ʱ��
	 * 
	 * @param root1
	 * @param root2
	 */
	public void union(int root1, int root2) {
		int size = s[root1] + s[root2];
		if (s[root1] < s[root2]) {
			s[root2] = root1;
			s[root1] = size;
		} else {
			s[root1] = root2;
			s[root2] = size;
		}
	}

	public static void main(String[] args) throws Exception {
		DisjointSets ds = new DisjointSets(8);
		if (ds.find(4) != ds.find(5)) {
			ds.union(ds.find(4), ds.find(5));
		}
		if (ds.find(6) != ds.find(7)) {
			ds.union(ds.find(6), ds.find(7));
		}
		if (ds.find(4) != ds.find(7)) {
			ds.union(ds.find(4), ds.find(7));
		}
		for (int i = 0; i < ds.s.length; i++) {
			System.out.print(ds.s[i] + " ");
		}
		System.out.println(ds.find(5) == ds.find(6));
	}

}
