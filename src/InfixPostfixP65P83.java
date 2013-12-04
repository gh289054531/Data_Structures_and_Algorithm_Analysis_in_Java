import java.util.ArrayList;
import java.util.Stack;

/**
 * P67 计算后缀表达式值、中缀表达式转后缀表达式、后缀表达式转为中缀表达式
 */
public class InfixPostfixP65P83 {
	/**
	 * 计算后缀表达式的值
	 */
	public static Double postfix(String[] input) {
		Stack<Double> ops = new Stack<Double>();
		for (int i = 0; i < input.length; i++) {
			String cur = input[i];
			switch (cur) {// 这种写法只能在java7用
			case "+":
				Double x1 = ops.pop();
				Double y1 = ops.pop();
				ops.push(x1 + y1);
				break;
			case "-":
				Double x2 = ops.pop();
				Double y2 = ops.pop();
				ops.push(y2 - x2);
				break;
			case "*":
				Double x3 = ops.pop();
				Double y3 = ops.pop();
				ops.push(x3 * y3);
				break;
			case "/":
				Double x4 = ops.pop();
				Double y4 = ops.pop();
				ops.push(y4 / x4);
				break;
			default:
				ops.push(Double.valueOf(cur));
				break;
			}
		}
		return ops.pop();
	}

	/**
	 * 把中缀表达式转换为后缀表达式
	 */
	public static String[] InfixToPostfix(String[] input) {
		Stack<String> ops = new Stack<String>();
		ArrayList<String> vals = new ArrayList<String>();
		for (int i = 0; i < input.length; i++) {
			String cur = input[i].trim();
			if (!cur.equals("+") && !cur.equals("*") && !cur.equals("(") && !cur.equals(")")) {
				vals.add(cur);
			} else if (cur.equals("+")) {
				while (ops.isEmpty() == false && ops.peek().equals("(") == false) {
					String top = ops.peek();
					if (top.equals("*") || top.equals("+")) {
						vals.add(ops.pop());
					}
				}
				ops.push(cur);
			} else if (cur.equals("*")) {
				ops.push(cur);
			} else if (cur.equals("(")) {
				ops.push(cur);
			} else if (cur.equals(")")) {
				while (ops.peek().equals("(") == false) {
					vals.add(ops.pop());
				}
				ops.pop();
			}
		}
		while (!ops.isEmpty()) {
			vals.add(ops.pop());
		}
		String[] result = new String[vals.size()];
		vals.toArray(result);
		return result;
	}

	/**
	 * 后缀表达式转为中缀表达式,后缀表达式无括号
	 */
	public static String postfixToInfix(String[] input) {
		Stack<String> vals = new Stack<String>();
		for (int i = 0; i < input.length; i++) {
			String cur = input[i].trim();
			if (!cur.equals("*") && !cur.equals("+")) {
				vals.push(cur);
			} else {
				String right = vals.pop();
				String left = vals.pop();
				String tree = "(" + left + cur + right + ")";
				vals.push(tree);
			}
		}
		return vals.pop().trim();
	}

	public static void main(String[] args) {
		String test = "1 + 2 * 3 + ( 4 * 5 + 6 ) * 7";
		String[] inputOfInfix = test.split(" ");
		String[] inputOfPostfix = InfixPostfixP65P83.InfixToPostfix(inputOfInfix);
		System.out.println(InfixPostfixP65P83.postfix(inputOfPostfix));

		String test1 = "4.99 + 5.99 + 6.99 * 1.06";
		String[] inputOfInfix1 = test1.split(" ");
		String[] inputOfPostfix1 = InfixPostfixP65P83.InfixToPostfix(inputOfInfix1);
		System.out.println(InfixPostfixP65P83.postfix(inputOfPostfix1));

		String test2 = "a b + c d e + * *";
		String[] inputOfInfix2 = test2.split(" ");
		String inputOfPostfix2 = InfixPostfixP65P83.postfixToInfix(inputOfInfix2);
		System.out.println(inputOfPostfix2);
	}
}
