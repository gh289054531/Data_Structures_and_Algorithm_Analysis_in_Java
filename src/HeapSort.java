/**
 * 堆排序
 * 平均和最坏时间复杂度都是O(N*logN)
 * 一般的实现还需要O(N)的空间
 * 本实现利用了一个事实：每次deleteMin之后堆数组大小减少1，因此最小值可以放到这里，所以不需要额外空间
 */
public class HeapSort {
	private static int LeftChild(int i) {
		return 2 * i + 1;
	}

	/*
	 * 大顶堆，下推
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
		// 构建堆,这里是让随机数组自调整为堆结构，需要从数组最后的元素开始调整而不能从头开始调整
		for (int i = input.length / 2; i >= 0; i--) {
			PushDown(input, i, input.length);
		}
		// 排序，从小到大
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
