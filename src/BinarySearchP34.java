import java.util.Arrays;

public class BinarySearchP34 {
	final static double DELT = 0.000001;

	public static <Anytype extends Comparable> int bsearch(Anytype[] array,
			Anytype target) {
		int start = 0;
		int end = array.length - 1;
		while (start <= end) {
			int middle = (start + end) / 2;
			int result = array[middle].compareTo(target);
			if (result == 0) {
				return middle;
			} else if (result > 0) {
				end = middle - 1;
			} else {
				start = middle + 1;
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		Number n1 = new I();
		n1.value = 1;
		Number n2 = new F();
		n2.value = 3.0f;
		Number n3 = new D();
		n3.value = 5.0;
		Number n4 = new I();
		n4.value = 0;
		Number n5 = new F();
		n5.value = -3.0f;
		Number[] array = new Number[] { n1, n2, n3, n4, n5 };
		Arrays.sort(array);
		for (Number n : array) {
			System.out.println(n.calValue());
		}
		Number target = new I();
		target.value = 3;
		System.out.println(BinarySearchP34.bsearch(array, target));
	}
}

interface CalValue {
	public Double calValue();
}

abstract class Number<Value> implements Comparable<Number>, CalValue {
	Value value;

	@Override
	public int compareTo(Number o) {
		if (Math.abs(this.calValue() - o.calValue()) < BinarySearchP34.DELT) {
			return 0;
		} else if (this.calValue() > o.calValue()) {
			return 1;
		} else {
			return -1;
		}
	}

	@Override
	public abstract Double calValue();
}

class D extends Number {
	@Override
	public Double calValue() {
		return (Double) value;
	}
}

class I extends Number {
	@Override
	public Double calValue() {
		return new Double((Integer) value);
	}
}

class F extends Number {
	@Override
	public Double calValue() {
		return new Double((Float) value);
	}
}
