import java.util.EmptyStackException;
import java.util.Stack;

public class BalanceSymbolP65 {
	public static boolean check(String input) {
		Stack<Character> stack = new Stack<Character>();
		for (int i = 0; i < input.length(); i++) {
			Character cur = input.charAt(i);
			if (cur.equals('[') || cur.equals('(') || cur.equals('{')) {
				stack.push(cur);
			} else if (cur.equals(')') || cur.equals(']') || cur.equals('}')) {
				Character top;
				try {
					top = stack.pop();
				} catch (EmptyStackException e) {
					e.printStackTrace();
					return false;
				}
				switch (cur.charValue()) {
				case ']':
					if (top.equals('[') == false) {
						return false;
					}
					break;
				case '}':
					if (top.equals('{') == false) {
						return false;
					}
					break;
				case ')':
					if (top.equals('(') == false) {
						return false;
					}
					break;
				}
			}
		}
		return true;
	}

	public static void main(String[] args) {
		String test1 = "char[] x=new char[]{'I','l','o','v','e','x','y','y'};";
		System.out.println(BalanceSymbolP65.check(test1));
		String test2 = "[(])";
		System.out.println(BalanceSymbolP65.check(test2));
	}
}
