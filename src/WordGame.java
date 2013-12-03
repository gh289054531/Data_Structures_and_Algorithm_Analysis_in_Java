import java.util.HashMap;

/*
 * Ӣ�ﵥ������Ϸ�������������У�ÿ����ÿ�δ���ɾ������һ����ĸ�����ʣ�����ĸ�������ϸ񵥵������ģ����ֵ���a < b < c <....<z)���������ʤ���������˶��㹻���������ȿ�ʼ��������Ӯô��
 ���룺 һ����Ӣ��Сд��ĸ�����Ȳ�����15,��֤�ʼ��״̬����һ���ϸ��������С�
 �����1��ʾ�׿���Ӯ��0��ʾ�ײ���Ӯ��
 ����: ���� bad�� ��׿���ɾ��b����a,ʣ�����ad����bd������Ӯ�ˣ����1��
 ����: ���� aaa�� ���ֻ��ɾ��1��a����ɾ��һ��a,ʣ��1��a���һ�ʤ�����0��
 */
public class WordGame {
	public static int who(String word) {
		HashMap<String, Boolean> jiaMap = new HashMap<String, Boolean>(), yiMap = new HashMap<String, Boolean>();
		return isJiaWin(word.toLowerCase(), 0, jiaMap, yiMap) == true ? 1 : 0;
	}

	// id=0��ʾ��,id=1��ʾ�ң���������ֵ��ʾ��Ӯ����
	private static boolean isJiaWin(String in, int id, HashMap<String, Boolean> jiaMap, HashMap<String, Boolean> yiMap) {
		if (isIncrease(in) == true) {
			return id == 1 ? true : false;
		}
		for (int i = 0; i < in.length(); i++) {
			String substr = new StringBuilder(in).deleteCharAt(i).toString();
			if (id == 0) {// �ײ�������һ���Ӵ�����true���ͷ���true
				boolean temp;
				if (jiaMap.containsKey(substr) == true) {
					temp = jiaMap.get(substr);
				} else {
					temp = isJiaWin(substr, id ^ 1, jiaMap, yiMap);
				}
				if (temp == true) {
					return true;
				}
			} else {// �Ҳ�����ֻ�е����е��Ӵ������ؼ�Ӯ��˵����Ӯ��ֻҪ��һ���Ӵ����ؼ��䣬��ô�ͷ���false
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
