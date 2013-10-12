public class AVLTree<Anytype extends Comparable<? super Anytype>> {
	AVLNode<Anytype> root;

	public AVLTree() {
		this.root = null;
	}

	public void insert(Anytype x) {
		this.root = this.insert(x, this.root);
	}

	public AVLNode<Anytype> insert(Anytype x, AVLNode<Anytype> node) {
		if (node == null) {
			return new AVLNode<Anytype>(x);
		}
		int result = node.theElement.compareTo(x);
		if (result < 0) {
			node.right = insert(x, node.right);
			if ((node.height(node.right) - node.height(node.left)) == 2) {
				if (x.compareTo(node.right.theElement) > 0) {
					node = rotateWithRightChild(node);
				} else {
					node = doubleRotateWithRightChild(node);
				}
			}
		} else if (result > 0) {
			node.left = insert(x, node.left);
			if ((node.height(node.left) - node.height(node.right)) == 2) {
				if (x.compareTo(node.left.theElement) < 0) {
					node = rotateWithLeftChild(node);
				} else {
					node = doubleRotateWithLeftChild(node);
				}
			}
		}
		node.height = Math.max(node.height(node.left), node.height(node.right)) + 1;
		return node;
	}

	private AVLNode<Anytype> rotateWithLeftChild(AVLNode<Anytype> node2) {
		AVLNode<Anytype> node1 = node2.left;
		node2.left = node1.right;
		node1.right = node2;
		node2.height = Math.max(node2.height(node2.left), node2.height(node2.right)) + 1;
		node1.height = Math.max(node1.height(node1.left), node2.height) + 1;
		return node1;
	}

	private AVLNode<Anytype> rotateWithRightChild(AVLNode<Anytype> node2) {
		AVLNode<Anytype> node1 = node2.right;
		node2.right = node1.left;
		node1.left = node2;
		node2.height = Math.max(node2.height(node2.left), node2.height(node2.right)) + 1;
		node1.height = Math.max(node1.height(node1.right), node2.height) + 1;
		return node1;
	}

	private AVLNode<Anytype> doubleRotateWithLeftChild(AVLNode<Anytype> node3) {
		node3.left = rotateWithRightChild(node3.left);
		return rotateWithLeftChild(node3);
	}

	private AVLNode<Anytype> doubleRotateWithRightChild(AVLNode<Anytype> node3) {
		node3.right = rotateWithLeftChild(node3.right);
		return rotateWithRightChild(node3);
	}

	public void preOrderPrint() {
		this.preOrderPrint(this.root);
		System.out.println();
	}

	public void preOrderPrint(AVLNode<Anytype> node) {
		System.out.print(node.theElement + "\t");
		if (node.left != null) {
			preOrderPrint(node.left);
		}
		if (node.right != null) {
			preOrderPrint(node.right);
		}
	}

	public static void main(String[] args) {
		AVLTree<Integer> avltree1 = new AVLTree<Integer>();
		int[] test1 = new int[] { 5, 2, 8, 1, 4, 7, 3 };
		for (int i = 0; i < test1.length; i++) {
			avltree1.insert(test1[i]);
		}
		avltree1.preOrderPrint();
		avltree1.insert(6);
		avltree1.preOrderPrint();

		AVLTree<Integer> avltree2 = new AVLTree<Integer>();
		int[] test2 = new int[] { 3, 2, 1, 4, 5, 6, 7, 16, 15, 14, 13, 12, 11, 10, 8, 9 };
		for (int i = 0; i < test2.length; i++) {
			avltree2.insert(test2[i]);
			avltree2.preOrderPrint();
		}
	}

}

class AVLNode<Anytype extends Comparable<? super Anytype>> {
	Anytype theElement;
	AVLNode<Anytype> left;
	AVLNode<Anytype> right;
	int height;

	public AVLNode(Anytype theElement) {
		this(theElement, null, null);
	}

	public AVLNode(Anytype theElement, AVLNode<Anytype> lt, AVLNode<Anytype> rt) {
		this.theElement = theElement;
		this.left = lt;
		this.right = rt;
	}

	public int height(AVLNode<Anytype> node) {
		return node == null ? -1 : node.height;
	}
}