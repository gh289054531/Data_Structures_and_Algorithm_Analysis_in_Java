import java.util.PriorityQueue;

/**
 * ѡ�����⼴��Ѱ��N��Ԫ���еĵ�K������ߡ�
 * ˳���ȡ�����е�ǰK��Ԫ�أ�����С���ѡ�С���ѵ��ص��Ǹ�Ԫ����С������һ�ε�����deleteMin��������ʱ�临�Ӷ�Ϊlog(2,K)��
 * ��������������ȡ��һ��Ԫ�أ������Ԫ�ز��ȶѶ�Ԫ�ش����������������滻�Ѷ�Ԫ�أ�Ȼ�����С���ѡ�
 * ���������е�Ԫ��ȫ����������С�����б����ľ���ǰK���Ԫ�ء�
 * ��ʼ���Ѳ�����ҪK*log(2,K)--�������Ĳ����������������ж�ȡ��N-K��Ԫ�غͶѶ�Ԫ��һһ�Ƚϣ���������ÿ�ζ�Ҫ�滻�Ѷ�Ԫ�أ���Ҫ����С����
 * �����Ӷ�Ϊ(N-K)*log(2,K)���ܵĸ��Ӷ�ΪO(N*log(2,K)),��O(N)�� ���ַ����ĺô��ǿռ临�Ӷ���O(K)
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
