import java.util.PriorityQueue;

public class HufManTree<T> {

	/**
	 * 建立堆1次,删除2*n-2次,插入n-1次，因此时间复杂度O(n*logn)
	 * 
	 * @param chars
	 * @param freq
	 * @return
	 */
	public HufManNode<T> makeTree(T[] chars, int[] freq) {
		if (chars == null || freq == null || chars.length == 0 || freq.length == 0 || chars.length != freq.length) {
			return null;
		}
		PriorityQueue<HufManNode<T>> pq = new PriorityQueue<HufManNode<T>>();
		for (int i = 0; i < chars.length; i++) {
			HufManNode<T> node = new HufManNode<T>(chars[i], freq[i]);
			pq.add(node);
		}
		while (pq.size() != 1) {
			HufManNode<T> node1 = pq.poll();
			HufManNode<T> node2 = pq.poll();
			HufManNode<T> merge = new HufManNode<>(node1, node2);
			pq.add(merge);
		}
		return pq.poll();
	}

	public void calCode(HufManNode<T> node, String code) {
		if (node.left == null && node.right == null) {
			node.code = code;
			System.out.println(node.value + ":" + node.code);
			return;
		}
		calCode(node.left, code + "0");
		calCode(node.right, code + "1");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Character[] chars = new Character[] { 'a', 'e', 'i', 's', 't', ' ', '\n' };
		int[] freq = new int[] { 10, 15, 12, 3, 4, 13, 1 };
		HufManTree<Character> tree = new HufManTree<Character>();
		HufManNode<Character> root = tree.makeTree(chars, freq);
		tree.calCode(root, "");
	}

}

class HufManNode<T> implements Comparable<HufManNode<T>> {
	T value;
	Integer freq;
	HufManNode<T> left = null;
	HufManNode<T> right = null;
	String code;

	public HufManNode(T value, int freq) {
		this.value = value;
		this.freq = freq;
	}

	public HufManNode(HufManNode<T> left, HufManNode<T> right) {
		this.freq = left.freq + right.freq;
		this.left = left;
		this.right = right;
	}

	@Override
	public int compareTo(HufManNode<T> o) {
		return this.freq.compareTo(o.freq);
	}
}
