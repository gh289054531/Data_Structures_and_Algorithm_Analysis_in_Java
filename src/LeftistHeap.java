public class LeftistHeap<Anytype extends Comparable<? super Anytype>> {
	private Node<Anytype> root;

	public LeftistHeap() {
		this.root = null;
	}

	public void merge(LeftistHeap<Anytype> rhs) {
		if (this == rhs) {
			return;
		}
		root = merge(this.root, rhs.root);
		rhs.root = null;
	}

	public Node<Anytype> merge(Node<Anytype> h1, Node<Anytype> h2) {
		if (h1 == null) {
			return h2;
		}
		if (h2 == null) {
			return h1;
		}
		if (h1.theElement.compareTo(h2.theElement) < 0) {
			return merge1(h1, h2);
		} else {
			return merge1(h2, h1);
		}
	}

	public Node<Anytype> merge1(Node<Anytype> h1, Node<Anytype> h2) {
		if (h1.left == null) {
			h1.left = h2;
		} else {
			h1.right = merge(h1.right, h2);
			if (h1.right.npl > h1.left.npl) {
				swapChild(h1);
			}
			h1.npl = h1.right.npl + 1;
		}
		return h1;
	}

	private void swapChild(Node<Anytype> node) {
		Node<Anytype> temp = node.right;
		node.right = node.left;
		node.left = temp;
	}

	public void insert(Anytype x) {
		this.root = this.merge(new Node<Anytype>(x), this.root);
	}

	public void preOrderPrint() {
		this.preOrderPrint(this.root);
		System.out.println();
	}

	public void preOrderPrint(Node<Anytype> node) {
		System.out.print(node.theElement + "\t");
		if (node.left != null) {
			preOrderPrint(node.left);
		}
		if (node.right != null) {
			preOrderPrint(node.right);
		}
	}

	public Anytype deleteMin() {
		if (this.root == null) {
			throw new RuntimeException();
		}
		Anytype min = this.root.theElement;
		this.root = merge(root.left, root.right);
		return min;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LeftistHeap<Integer> lh = new LeftistHeap<Integer>();
		lh.insert(1);
		lh.insert(3);
		lh.insert(7);
		lh.insert(2);
		System.out.println(lh.deleteMin());
		lh.preOrderPrint();
	}

}

class Node<Anytype extends Comparable<? super Anytype>> {
	Anytype theElement;
	Node<Anytype> left;
	Node<Anytype> right;
	int npl;

	public Node(Anytype theElement) {
		this(theElement, null, null);
	}

	public Node(Anytype theElement, Node<Anytype> lt, Node<Anytype> rt) {
		this.theElement = theElement;
		this.left = lt;
		this.right = rt;
		this.npl = 0;
	}
}