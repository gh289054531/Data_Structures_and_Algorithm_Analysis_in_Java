public class LongestCommonSequence {

	/**
	 * ������������У���̬�滮��ʱ�临�Ӷ�O(n^2)��
	 * ����str1������str2�����ȷֱ�Ϊm��n��
       ������1����ά����L[m+1][n+1]��
       ����ʼ��L��������Ϊ0
       ��m��n�ֱ��1��ʼ��m++��n++ѭ����
           - ���str1[m] == str2[n]����L[m,n] = L[m - 1, n -1] + 1��
           - ���str1[m] != str2[n]����L[m,n] = max{L[m,n - 1]��L[m - 1, n]}
       ������L[m,n]�е�����һ�������ģ���������־�������������еĳ���
       ��������L���ҳ�һ����Ĺ���������
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
		int[][] len = new int[s1.length() + 1][s2.length() + 1];
		for (int i = 1; i <= s1.length(); i++) {
			for (int j = 1; j <= s2.length(); j++) {
				if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
					len[i][j] = len[i - 1][j - 1] + 1;
				} else {
					len[i][j] = Math.max(len[i][j - 1], len[i - 1][j]);
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		int i = s1.length(), j = s2.length();
		while (i > 0 && j > 0) {
			if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
				sb.append(s1.charAt(i - 1));
				i--;
				j--;
			} else {
				if (len[i][j - 1] > len[i - 1][j]) {
					j--;
				} else {
					i--;
				}
			}
		}
		return sb.reverse().toString();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(LongestCommonSequence.cal("123456", "34589"));
		System.out.println(LongestCommonSequence.cal("ABCBDAB", "BDCABA"));
	}

}
