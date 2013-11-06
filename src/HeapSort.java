/**
 * ������
 * ƽ�����ʱ�临�Ӷȶ���O(N*logN)
 * һ���ʵ�ֻ���ҪO(N)�Ŀռ�
 * ��ʵ��������һ����ʵ��ÿ��deleteMin֮��������С����1�������Сֵ���Էŵ�������Բ���Ҫ����ռ�
 */
public class HeapSort {
	private static int LeftChild(int i) {
		return 2 * i + 1;
	}

	/*
	 * �󶥶ѣ�����
	 */
	public static <T extends Comparable<? super T>> void PushDown(T[] input, int index, int length) {
		T temp = input[index];
		int child = LeftChild(index);
		int i = index;
		for (; child < length;) {
			if (child != length - 1 && input[child].compareTo(input[child + 1]) < 0) {
				child++;
			}
			if (temp.compareTo(input[child]) < 0) {
				input[i] = input[child];
			} else {
				break;
			}
			i = child;
			child = LeftChild(child);
		}
		input[i] = temp;
	}

	private static <T extends Comparable<? super T>> void SwapRef(T[] input, int i, int j) {
		T temp = input[i];
		input[i] = input[j];
		input[j] = temp;
	}

	public static <T extends Comparable<? super T>> void HeapSort(T[] input) {
		if (input == null || input.length == 0) {
			return;
		}
		// ������,����������������Ե���Ϊ�ѽṹ����Ҫ����������Ԫ�ؿ�ʼ���������ܴ�ͷ��ʼ����
		for (int i = input.length / 2; i >= 0; i--) {
			PushDown(input, i, input.length);
		}
		// ���򣬴�С����
		for (int i = input.length - 1; i > 0; i--) {
			SwapRef(input, 0, i);
			PushDown(input, 0, i);
		}
	}

	public static void main(String[] args) {
		Integer[] test1 = new Integer[] { 1, 2, 4, 654, 123, 643, 213, 432, 65, -635, 42 };
		HeapSort.HeapSort(test1);
		for (int i = 0; i < test1.length; i++) {
			System.out.println(test1[i]);
		}

		Integer[] test2 = new Integer[] {};
		HeapSort.HeapSort(test2);
		for (int i = 0; i < test2.length; i++) {
			System.out.println(test2[i]);
		}
	}

}
