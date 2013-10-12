public class SplayTree<Anytype extends Comparable<? super Anytype>> {
	SplayNode<Anytype> root;

	public SplayTree() {
		this.root = null;
	}

	public SplayTree(Anytype x) {
		this.root = new SplayNode<Anytype>(x);
	}

	public void insert(Anytype x) {
		this.root = insert(x, this.root);
	}

	public SplayNode<Anytype> insert(Anytype x, SplayNode<Anytype> node) {
		if (node == null) {
			return new SplayNode<Anytype>(x);
		}
		int result = node.theElement.compareTo(x);
		if (result > 0) {
			node.left = insert(x, node.left);
		} else if (result < 0) {
			node.right = insert(x, node.right);
		}
		return node;
	}

	public boolean contains(Anytype x) {
		ReturnResult rr=contains(x, this.root);
		this.root=rr.node;
		return rr.flag;
	}

	public ReturnResult contains(Anytype x, SplayNode<Anytype> node) {
		boolean flag;
		ReturnResult rr = null;
		if (node == null) {
			flag = false;
		} else {
			int result = node.theElement.compareTo(x);
			if (result > 0) {
				rr = contains(x, node.left);
				flag = rr.flag;
				node.left = rr.node;
			} else if (result < 0) {
				rr = contains(x, node.right);
				flag = rr.flag;
				node.right = rr.node;
			} else {
				flag = true;
				rr = new ReturnResult(flag, node);
			}
		}
		if (flag == true) {
			SplayNode<Anytype> newNode = node;
			int result = node.theElement.compareTo(x);
			if (result > 0 && node.left.theElement.compareTo(x) == 0) {// x是node的左子节点
				if (node == this.root) {// node是根结点
					newNode = rotateLeftRoot(node);
				}
			}
			if (result < 0 && node.right.theElement.compareTo(x) == 0) {// x是node的右子节点
				if (node == this.root) {// node是根结点
					newNode = rotateRightRoot(node);
				}
			}
			if (result > 0 && node.left.theElement.compareTo(x) < 0) {// x是node的左子节点的右子节点
				newNode = doubleRotateWithLeftChild(node);
			}
			if (result > 0 && node.left.theElement.compareTo(x) > 0) {// x是node的左子节点的左子节点
				newNode = splayLeft(node);
			}
			if (result < 0 && node.right.theElement.compareTo(x) < 0) {// x是node的右子节点的右子节点
				newNode = splayRight(node);
			}
			if (result < 0 && node.right.theElement.compareTo(x) > 0) {// x是node的右子节点的左子节点
				newNode = doubleRotateWithRightChild(node);
			}
			node = newNode;
		}
		rr.node = node;
		return rr;
	}

	private SplayNode<Anytype> splayLeft(SplayNode<Anytype> node) {
		node.left = rotateWithLeftChild(node.left);
		node = rotateWithLeftChild(node);
		node.right = rotateWithLeftChild(node.right);
		return node;
	}

	private SplayNode<Anytype> splayRight(SplayNode<Anytype> node) {
		node.right = rotateWithRightChild(node.right);
		node = rotateWithRightChild(node);
		node.left = rotateWithRightChild(node.left);
		return node;
	}

	private SplayNode<Anytype> rotateWithLeftChild(SplayNode<Anytype> node2) {
		SplayNode<Anytype> node1 = node2.left;
		node2.left = node1.right;
		node1.right = node2;
		return node1;
	}

	private SplayNode<Anytype> rotateWithRightChild(SplayNode<Anytype> node2) {
		SplayNode<Anytype> node1 = node2.right;
		node2.right = node1.left;
		node1.left = node2;
		return node1;
	}

	private SplayNode<Anytype> doubleRotateWithLeftChild(
			SplayNode<Anytype> node3) {
		node3.left = rotateWithRightChild(node3.left);
		return rotateWithLeftChild(node3);
	}

	private SplayNode<Anytype> doubleRotateWithRightChild(
			SplayNode<Anytype> node3) {
		node3.right = rotateWithLeftChild(node3.right);
		return rotateWithRightChild(node3);
	}

	public SplayNode<Anytype> rotateLeftRoot(SplayNode<Anytype> node) {
		SplayNode<Anytype> node1 = node.left;
		node.left = node1.right;
		node1.right = node;
		return node1;
	}

	public SplayNode<Anytype> rotateRightRoot(SplayNode<Anytype> node) {
		SplayNode<Anytype> node1 = node.right;
		node.right = node1.left;
		node1.left = node;
		return node1;
	}

	public void preOrderPrint() {
		this.preOrderPrint(this.root);
		System.out.println();
	}

	private void preOrderPrint(SplayNode<Anytype> node) {
		System.out.print(node.theElement + "\t");
		if (node.left != null) {
			preOrderPrint(node.left);
		}
		if (node.right != null) {
			preOrderPrint(node.right);
		}
	}

	public static void main(String[] args) {
		int[] test = new int[] { 7, 6, 5, 4, 3, 2, 1 };
		SplayTree<Integer> st = new SplayTree<Integer>();
		for (int i = 0; i < test.length; i++) {
			st.insert(test[i]);
		}
		st.preOrderPrint();
		st.contains(1);
		st.preOrderPrint();
	}

	class ReturnResult {
		boolean flag;
		SplayNode<Anytype> node;

		public ReturnResult(boolean flag, SplayNode<Anytype> node) {
			this.flag = flag;
			this.node = node;
		}
	}

}

class SplayNode<Anytype> {
	Anytype theElement;
	SplayNode<Anytype> left;
	SplayNode<Anytype> right;

	public SplayNode(Anytype theElement) {
		this(theElement, null, null);
	}

	public SplayNode(Anytype theElement, SplayNode<Anytype> lt,
			SplayNode<Anytype> rt) {
		this.theElement = theElement;
		this.left = lt;
		this.right = rt;
	}

	public String toString() {
		return theElement.toString();
	}
}