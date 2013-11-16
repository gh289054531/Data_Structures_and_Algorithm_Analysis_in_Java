public class LongestCommonSubstring {

	/**
	 * 最长公共子串，时间复杂度O(N^2)。
	 * 设序列X=<x1, x2, …, xm>和Y=<y1, y2, …, yn>的一个最长公共子序列Z=<z1, z2, …, zk>，则：
         1> 若 xm=yn，则 zk=xm=yn，且Zk-1是Xm-1和Yn-1的最长公共子序列； 
         2> 若 xm≠yn且 zk≠xm ，则 Z是 Xm-1和 Y的最长公共子序列；
         3> 若 xm≠yn且 zk≠yn ，则 Z是 X和 Yn-1的最长公共子序列；
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
