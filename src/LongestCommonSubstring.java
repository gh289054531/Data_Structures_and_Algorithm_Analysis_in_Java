public class LongestCommonSubstring {

	/**
	 * ������Ӵ���ʱ�临�Ӷ�O(N^2)��
	 * ������X=<x1, x2, ��, xm>��Y=<y1, y2, ��, yn>��һ�������������Z=<z1, z2, ��, zk>����
         1> �� xm=yn���� zk=xm=yn����Zk-1��Xm-1��Yn-1������������У� 
         2> �� xm��yn�� zk��xm ���� Z�� Xm-1�� Y������������У�
         3> �� xm��yn�� zk��yn ���� Z�� X�� Yn-1������������У�
	 * @param s1
	 * @param s2
	 * @return
	 */
	public static String cal(String s1, String s2) {
		if (s1 == null || s2 == null) {
			return null;
		}
		if (s1.length() == 0 || s2.length() == 0) {
			return "";
		}
		int maxLen = 0;
		int maxi = 0, maxj = 0;
		int[][] len = new int[s1.length() + 1][s2.length() + 1];
		for (int i = 1; i <= s1.length(); i++) {
			for (int j = 1; j <= s2.length(); j++) {
				if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
					len[i][j] = len[i - 1][j - 1] + 1;
					if (len[i][j] > maxLen) {
						maxLen = len[i][j];
						maxi = i;
						maxj = j;
					}
				} else {
					len[i][j] = 0;
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		while (len[maxi][maxj] != 0) {
			sb.append(s1.charAt(maxi));
			maxi--;
			maxj--;
		}
		return sb.reverse().toString();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(LongestCommonSubstring.cal("bab", "caba"));
	}

}
