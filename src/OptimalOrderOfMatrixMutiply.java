/**
 * �����������ż���˳��,ʱ�临�Ӷ�O(N^3)����̬�滮
 * 
 */
public class OptimalOrderOfMatrixMutiply {
	/**
	 * ��Ŵ�1��ʼ��Ҳ����˵��������Ϊ0��λ�ò������Ϣ
	 * 
	 * @param c
	 *            c[i]��ŵ�i�����������Ŀ���ر��c[0]��ŵ�1�����������Ŀ�������Ŵ�1��ʼ�����Ǵ�0��ʼ
	 * @param m
	 *            m[i][j]�Ǵӵ�i����j����������ų˷��������ر��m[i][i]=0
	 * @param lastChange
	 *            lastChange[i][j]��ʾ�ӵ�i�����󵽵�j������Ӧ�ô��ĸ����󴦷ֿ�Ϊ���Σ��ֱ���������ε����ų˷�������
	 *            �ٰ���������ˣ���lastChange[i][j]�����󱻷ֵ���߶�
	 * @throws Exception
	 */
	public static void optOrder(int[] c, long[][] m, int[][] lastChange) throws Exception {
		if (c.length < 3) {
			throw new Exception("�����������2��");
		}
		int n = c.length - 1;// �������
		for (int i = 1; i <= n; i++) {
			m[i][i] = 0;
		}
		for (int k = 1; k < n; k++) {// k��left��right֮��ľ��룬���������1��ʼ����ֱ��n-1����ô������Ϊ����������ʱ����Ҫ֪������Сʱ��mֵ
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
