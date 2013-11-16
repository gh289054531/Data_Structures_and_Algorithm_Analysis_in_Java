public class LongestCommonSequence {

	/**
	 * 求最长公共子序列，动态规划，时间复杂度O(n^2)。
	 * 序列str1和序列str2，长度分别为m和n；
       ·创建1个二维数组L[m+1][n+1]；
       ·初始化L数组内容为0
       ·m和n分别从1开始，m++，n++循环：
           - 如果str1[m] == str2[n]，则L[m,n] = L[m - 1, n -1] + 1；
           - 如果str1[m] != str2[n]，则L[m,n] = max{L[m,n - 1]，L[m - 1, n]}
       ·最后从L[m,n]中的数字一定是最大的，且这个数字就是最长公共子序列的长度
       ·从数组L中找出一个最长的公共子序列
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
