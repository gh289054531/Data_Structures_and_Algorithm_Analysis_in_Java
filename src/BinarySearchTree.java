public class BinarySearchTree<Anytype extends Comparable<? super Anytype>> {
	private BinaryNode<Anytype> root;

	public BinarySearchTree(BinaryNode<Anytype> root) {
		this.root = root;
	}

	public BinarySearchTree() {
		this.root = null;
	}

	public boolean isEmpty() {
		return this.root == null ? true : false;
	}

	public void makeEmpty() {
		this.root = null;
	}

	public boolean contains(Anytype target) {
		return contains(target, this.root);
	}

	public boolean contains(Anytype target, BinaryNode<Anytype> node) {
		if (node == null) {
			return false;
		}
		int result = target.compareTo(node.theElement);
		if (result < 0) {
			return contains(target, node.left);
		} else if (result > 0) {
			return contains(target, node.right);
		} else {
			return true;
		}
	}

	public Anytype findMin() {
		if (this.isEmpty() == true) {
			return null;
		}
		return findMin(this.root).theElement;
	}

	public BinaryNode<Anytype> findMin(BinaryNode<Anytype> node) {
		if (node == null) {
			return null;
		} else if (node.left == null) {
			return node;
		} else {
			return this.findMin(node.left);
		}
	}

	public Anytype findMax() {
		if (this.isEmpty() == true) {
			return null;
		}
		return this.findMax(this.root).theElement;
	}

	public BinaryNode<Anytype> findMax(BinaryNode<Anytype> node) {
		if (node == null) {
			return null;
		} else if (node.right == null) {
			return node;
		} else {
			return this.findMax(node.right);
		}
	}

	public void insert(Anytype x) {
		this.root = insert(x, root);
	}

	public BinaryNode<Anytype> insert(Anytype x, BinaryNode<Anytype> node) {
		if (node == null) {
			return new BinaryNode<Anytype>(x);
		}
		int result = node.theElement.compareTo(x);
		if (result < 0) {
			node.right = this.insert(x, node.right);
		} else {
			node.left = this.insert(x, node.left);
		}
		return node;
	}

	public void delete(Anytype x) {
		this.root = delete(x, this.root);
	}

	public BinaryNode<Anytype> delete(Anytype x, BinaryNode<Anytype> node) {
		if (node == null) {
			return node;
		}
		int result = x.compareTo(node.theElement);
		if (result > 0) {
			node.right = this.delete(x, node.right);
		} else if (result < 0) {
			node.left = this.delete(x, node.left);
		} else if (node.left != null && node.right != null) {
			node.theElement = this.findMin(node.right).theElement;
			node.right = this.delete(node.theElement, node.right);
		} else {
			node = (node.left == null) ? node.right : node.left;
		}
		return node;
	}

	public void inOrderPrint() {
		this.inOrderPrint(this.root);
		System.out.println();
	}

	public void inOrderPrint(BinaryNode<Anytype> node) {
		if (node.left != null) {
			inOrderPrint(node.left);
		}
		System.out.print(node.theElement + "\t");
		if (node.right != null) {
			inOrderPrint(node.right);
		}
	}

	public static void main(String[] args) {
		BinarySearchTree<Integer> bstree = new BinarySearchTree<>();
		int[] test = new int[] { 6, 2, 1, 4, 3, 8 };
		for (int i = 0; i < test.length; i++) {
			bstree.insert(test[i]);
		}
		bstree.inOrderPrint();
		System.out.println(bstree.contains(2));
		System.out.println(bstree.contains(10));
		System.out.println(bstree.findMin());
		System.out.println(bstree.findMax());
		bstree.delete(2);
		bstree.inOrderPrint();
	}
}

class BinaryNode<Anytype> {
	Anytype theElement;
	BinaryNode<Anytype> left;
	BinaryNode<Anytype> right;

	public BinaryNode(Anytype theElement) {
		this(theElement, null, null);
	}

	public BinaryNode(Anytype theElement, BinaryNode<Anytype> lt, BinaryNode<Anytype> rt) {
		this.theElement = theElement;
		this.left = lt;
		this.right = rt;
	}
}