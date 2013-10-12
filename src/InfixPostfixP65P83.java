import java.util.ArrayList;
import java.util.Stack;

public class InfixPostfixP65P83 {
	// 计算后缀表达式的值
	public static Double postfix(String[] input) {
		Stack<Double> stack = new Stack<Double>();
		for (int i = 0; i < input.length; i++) {
			String cur = input[i];
			switch (cur) {
			case "+":
				Double x1 = stack.pop();
				Double y1 = stack.pop();
				stack.push(x1 + y1);
				break;
			case "-":
				Double x2 = stack.pop();
				Double y2 = stack.pop();
				stack.push(y2 - x2);
				break;
			case "*":
				Double x3 = stack.pop();
				Double y3 = stack.pop();
				stack.push(x3 * y3);
				break;
			case "/":
				Double x4 = stack.pop();
				Double y4 = stack.pop();
				stack.push(y4 / x4);
				break;
			default:
				stack.push(Double.valueOf(cur));
				break;
			}
		}
		return stack.pop();
	}

	// 把中缀表达式转换为后缀表达式
	public static String[] InfixToPostfix(String[] input) {
		Stack<String> stack = new Stack<String>();
		ArrayList<String> postfix = new ArrayList<String>();
		int index = 0;
		for (int i = 0; i < input.length; i++) {
			String cur = input[i].trim();
			if (!cur.equals("+") && !cur.equals("*") && !cur.equals("(")
					&& !cur.equals(")")) {
				postfix.add(cur);
			} else if (cur.equals("+")) {
				while (stack.isEmpty() == false) {
					String top = stack.peek();
					if (top.equals("*") || top.equals("+")) {
						postfix.add(stack.pop());
					}
					if (top.equals("(")) {
						break;
					}
				}
				stack.push(cur);
			} else if (cur.equals("*")) {
				stack.push(cur);
			} else if (cur.equals("(")) {
				stack.push(cur);
			} else if (cur.equals(")")) {
				while (true) {
					String top = stack.peek();
					if (!top.equals("(")) {
						postfix.add(stack.pop());
					} else {
						stack.pop();
						break;
					}
				}
			}
		}
		while (!stack.isEmpty()) {
			postfix.add(stack.pop());
		}
		String[] result = new String[postfix.size()];
		postfix.toArray(result);
		return result;
	}

	//后缀表达式转为中缀表达式
	public static String postfixToInfix(String[] input) {
		Stack<String> stack = new Stack<String>();
		for (int i = 0; i < input.length; i++) {
			String cur = input[i].trim();
			if (!cur.equals("*") && !cur.equals("+")) {
				stack.push(cur);
			} else {
				String right = stack.pop();
				String left = stack.pop();
				String tree = "("+left + cur + right+")";
				stack.push(tree);
			}
		}
		return stack.pop().trim();
	}

	public static void main(String[] args) {
		String test = "1 + 2 * 3 + ( 4 * 5 + 6 ) * 7";
		String[] inputOfInfix = test.split(" ");
		String[] inputOfPostfix = InfixPostfixP65P83
				.InfixToPostfix(inputOfInfix);
		System.out.println(InfixPostfixP65P83.postfix(inputOfPostfix));

		String test1 = "4.99 + 5.99 + 6.99 * 1.06";
		String[] inputOfInfix1 = test1.split(" ");
		String[] inputOfPostfix1 = InfixPostfixP65P83
				.InfixToPostfix(inputOfInfix1);
		System.out.println(InfixPostfixP65P83.postfix(inputOfPostfix1));

		String test2 = "a b + c d e + * *";
		String[] inputOfInfix2 = test2.split(" ");
		String inputOfPostfix2 = InfixPostfixP65P83
				.postfixToInfix(inputOfInfix2);
		System.out.println(inputOfPostfix2);
	}
}
