import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BigIntegerMultiply {
	public static String Mutiply(String a, String b) throws Exception {
		if (a == null || b == null || a.length() == 0 || b.length() == 0) {
			return null;
		}
		Pattern p = Pattern.compile("[0-9]+");
		Matcher m1 = p.matcher(a);
		Matcher m2 = p.matcher(b);
		if (m1.matches() == false || m2.matches() == false) {
			throw new Exception("Input string is not a valid bignumber:\t" + a + "\t" + b);
		}
		int[] result = new int[a.length() + b.length()];
		int i;
		for (i = b.length() - 1; i >= 0; i--) {
			int jinwei = 0;
			int j;
			for (j = a.length() - 1; j >= 0; j--) {
				int cur = (a.charAt(j) - '0') * (b.charAt(i) - '0') + jinwei + result[a.length() - i + b.length() - j - 2];
				result[a.length() - i + b.length() - j - 2] = cur % 10;
				jinwei = cur / 10;
			}
			if (jinwei != 0) {
				result[a.length() - i + b.length() - j - 2] = jinwei;
			}
		}
		StringBuilder s = new StringBuilder(result.length);
		for (int m = 0; m < result.length; m++) {
			s.append(result[m]);
		}
		return s.reverse().toString();
	}

	public static void main(String[] args) {
		try {
			System.out.println(BigIntegerMultiply.Mutiply("11111111111111111111111", "99111111111111111111"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
