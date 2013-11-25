import java.util.PriorityQueue;

/**
 * 选择问题即：寻找N个元素中的第K个最大者。
 * 顺序读取数组中的前K个元素，构建小根堆。小根堆的特点是根元素最小，并且一次调整（deleteMin）操作的时间复杂度为log(2,K)。
 * 接下来从数组中取下一个元素，如果该元素不比堆顶元素大，则丢弃；否则用它替换堆顶元素，然后调整小根堆。
 * 当把数组中的元素全部读出来后，小根堆中保留的就是前K大的元素。
 * 初始建堆操作需要K*log(2,K)--这是最多的操作次数，从数组中读取后N-K个元素和堆顶元素一一比较，最坏的情况是每次都要替换堆顶元素，都要调整小根堆
 * ，复杂度为(N-K)*log(2,K)。总的复杂度为O(N*log(2,K)),即O(N)。 这种方法的好处是空间复杂度是O(K)
 */
public class KthSelectionUsingHeap {
	public static <T extends Comparable<? super T>> T selectKth(T[] input, int k) {
		if (input == null || k > input.length || k < 1) {
			return null;
		}
		PriorityQueue<T> pq = new PriorityQueue<T>();
		int size = 0;
		for (int i = 0; i < input.length; i++) {
			if (size < k) {
				pq.add(input[i]);
				size++;
			} else {
				if (input[i].compareTo(pq.peek()) > 0) {
					pq.remove();
					pq.add(input[i]);
				}
			}
		}
		return pq.peek();
	}

	public static void main(String[] args) {
		Integer[] test1 = new Integer[] { 213, 123, 432, 54, 56765, 5324, 8768, 234 };
		System.out.println(KthSelectionUsingHeap.selectKth(test1, 1));
		System.out.println(KthSelectionUsingHeap.selectKth(test1, 2));
		System.out.println(KthSelectionUsingHeap.selectKth(test1, 3));
		System.out.println(KthSelectionUsingHeap.selectKth(test1, 4));
		System.out.println(KthSelectionUsingHeap.selectKth(test1, 5));
		System.out.println(KthSelectionUsingHeap.selectKth(test1, 6));
		System.out.println(KthSelectionUsingHeap.selectKth(test1, 7));
		System.out.println(KthSelectionUsingHeap.selectKth(test1, 8));
	}

}
