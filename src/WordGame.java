import java.util.HashMap;

/*
 * 英语单词玩游戏两个人轮流进行，每个人每次从中删掉任意一个字母，如果剩余的字母序列是严格单调递增的（按字典序a < b < c <....<z)，则这个人胜利。两个人都足够聪明，甲先开始，问他能赢么？
 输入： 一连串英文小写字母，长度不超过15,保证最开始的状态不是一个严格单增的序列。
 输出：1表示甲可以赢，0表示甲不能赢。
 例如: 输入 bad， 则甲可以删掉b或者a,剩余的是ad或者bd，他就赢了，输出1。
 又如: 输入 aaa， 则甲只能删掉1个a，乙删掉一个a,剩余1个a，乙获胜，输出0。
 */
public class WordGame {
	public static int who(String word) {
		HashMap<String, Boolean> jiaMap = new HashMap<String, Boolean>(), yiMap = new HashMap<String, Boolean>();
		return isJiaWin(word.toLowerCase(), 0, jiaMap, yiMap) == true ? 1 : 0;
	}

	// id=0表示甲,id=1表示乙，函数返回值表示甲赢或输
	private static boolean isJiaWin(String in, int id, HashMap<String, Boolean> jiaMap, HashMap<String, Boolean> yiMap) {
		if (isIncrease(in) == true) {
			return id == 1 ? true : false;
		}
		for (int i = 0; i < in.length(); i++) {
			String substr = new StringBuilder(in).deleteCharAt(i).toString();
			if (id == 0) {// 甲操作，有一个子串返回true，就返回true
				boolean temp;
				if (jiaMap.containsKey(substr) == true) {
					temp = jiaMap.get(substr);
				} else {
					temp = isJiaWin(substr, id ^ 1, jiaMap, yiMap);
				}
				if (temp == true) {
					return true;
				}
			} else {// 乙操作，只有当所有的子串都返回甲赢才说明甲赢，只要有一个子串返回甲输，那么就返回false
				boolean temp;
				if (yiMap.containsKey(substr) == true) {
					temp = yiMap.get(substr);
				} else {
					temp = isJiaWin(substr, id ^ 1, jiaMap, yiMap);
				}
				if (temp == false) {
					return false;
				}
			}
		}
		return id == 1 ? true : false;
	}

	private static boolean isIncrease(String s) {
		for (int i = 0; i < s.length() - 1; i++) {
			if (s.charAt(i) >= s.charAt(i + 1)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(who("Test"));
	}

}
