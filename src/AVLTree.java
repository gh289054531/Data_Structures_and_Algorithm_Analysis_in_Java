/**
 * AVL树（二叉平衡树）实现,查询、插入、删除的时间复杂度都是O(N*LogN)
 * 
 * @author zhangpeng
 * @param <Anytype>
 */
public class AVLTree<Anytype extends Comparable<? super Anytype>> {
	AVLNode<Anytype> root;

	public AVLTree() {
		this.root = null;
	}

	/**
	 * 可以重复插入
	 */
	public void insert(Anytype x) {
		this.root = this.insert(x, this.root);
	}

	public AVLNode<Anytype> insert(Anytype x, AVLNode<Anytype> node) {
		if (node == null) {
			return new AVLNode<Anytype>(x);
		}
		int result = node.theElement.compareTo(x);
		if (result <= 0) {
			node.right = insert(x, node.right);
		} else if (result > 0) {
			node.left = insert(x, node.left);
		}
		return balance(node);
	}

	/**
	 * 如果要删除的结点值有多个，随机删除
	 */
	public void remove(Anytype x) {
		this.root = this.remove(x, this.root);
	}

	public AVLNode<Anytype> remove(Anytype x, AVLNode<Anytype> node) {
		if (node == null) {
			return node;
		}
		int result = node.theElement.compareTo(x);
		if (result < 0) {
			node.right = remove(x, node.right);
		} else if (result > 0) {
			node.left = remove(x, node.left);
		} else if (node.left != null && node.right != null) {// 从右子树找最小指放到当前节点，删除右子树最小值
			node.theElement = findMin(node.right).theElement;
			node.right = remove(node.theElement, node.right);
		} else {
			node = node.left == null ? node.right : node.left;
		}
		return balance(node);
	}

	public AVLNode<Anytype> findMin() {
		return findMin(root);
	}

	private AVLNode<Anytype> findMin(AVLNode<Anytype> node) {
		if (node == null) {
			return node;
		}
		while (node.left != null) {
			node = node.left;
		}
		return node;
	}

	public AVLNode<Anytype> findMax() {
		return findMax(root);
	}

	private AVLNode<Anytype> findMax(AVLNode<Anytype> node) {
		if (node == null) {
			return node;
		}
		while (node.right != null) {
			node = node.right;
		}
		return node;
	}

	public boolean contains(Anytype x) {
		if (x == null) {
			return false;
		}
		AVLNode<Anytype> node = root;
		while (node != null) {
			if (node.theElement.compareTo(x) > 0) {
				node = node.left;
			} else if (node.theElement.compareTo(x) < 0) {
				node = node.right;
			} else {
				return true;
			}
		}
		return false;
	}

	/**
	 * 调整结构使平衡
	 */
	private AVLNode<Anytype> balance(AVLNode<Anytype> node) {
		if (node == null) {
			return node;
		}
		if (AVLNode.height(node.left) - AVLNode.height(node.right) > 1) {
			if (AVLNode.height(node.left.left) > AVLNode.height(node.left.right)) {
				node = rotateWithLeftChild(node);
			} else {
				node = doubleRotateWithLeftChild(node);
			}
		}
		if (AVLNode.height(node.right) - AVLNode.height(node.left) > 1) {
			if (AVLNode.height(node.right.right) > AVLNode.height(node.right.left)) {
				node = rotateWithRightChild(node);
			} else {
				node = doubleRotateWithRightChild(node);
			}
		}
		node.height = Math.max(AVLNode.height(node.left), AVLNode.height(node.right)) + 1;
		return node;
	}

	private AVLNode<Anytype> rotateWithLeftChild(AVLNode<Anytype> node2) {
		AVLNode<Anytype> node1 = node2.left;
		node2.left = node1.right;
		node1.right = node2;
		node2.height = Math.max(AVLNode.height(node2.left), AVLNode.height(node2.right)) + 1;
		node1.height = Math.max(AVLNode.height(node1.left), node2.height) + 1;
		return node1;
	}

	private AVLNode<Anytype> rotateWithRightChild(AVLNode<Anytype> node2) {
		AVLNode<Anytype> node1 = node2.right;
		node2.right = node1.left;
		node1.left = node2;
		node2.height = Math.max(AVLNode.height(node2.left), AVLNode.height(node2.right)) + 1;
		node1.height = Math.max(AVLNode.height(node1.right), node2.height) + 1;
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

	/**
	 * 返回所有包含的结点值,升序排列
	 */
	public String toString() {
		return middleOrderToString(root);
	}

	private String middleOrderToString(AVLNode<Anytype> node) {
		if (node == null) {
			return "";
		}
		String cur = "";
		if (node.left != null) {
			cur = cur + middleOrderToString(node.left) + " ";
		}
		cur = cur + node.theElement.toString() + " ";
		if (node.right != null) {
			cur = cur + middleOrderToString(node.right) + " ";
		}
		return cur.trim();
	}

	public void preOrderPrint() {
		this.preOrderPrint(this.root);
		System.out.println();
	}

	public void preOrderPrint(AVLNode<Anytype> node) {
		if (node == null) {
			return;
		}
		System.out.print(node.theElement + "\t");
		if (node.left != null) {
			preOrderPrint(node.left);
		}
		if (node.right != null) {
			preOrderPrint(node.right);
		}
	}

	public int size() {
		return size(root);
	}

	private int size(AVLNode<Anytype> node) {
		if (node == null) {
			return 0;
		}
		return size(node.left) + size(node.right) + 1;
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
		avltree1.insert(1);
		avltree1.preOrderPrint();
		avltree1.insert(5);
		avltree1.preOrderPrint();
		avltree1.remove(1);
		avltree1.preOrderPrint();
		avltree1.remove(6);
		avltree1.preOrderPrint();
		avltree1.insert(5);
		avltree1.preOrderPrint();
		System.out.println("--------------------------");

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

	public static <Anytype extends Comparable<? super Anytype>> int height(AVLNode<Anytype> node) {
		return node == null ? -1 : node.height;
	}

	public String toString() {
		return theElement.toString();
	}
}