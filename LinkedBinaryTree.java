package projectCode20280;

/**
 * Concrete implementation of a binary tree using a node-based, linked
 * structure.
 */
public class LinkedBinaryTree<E> extends AbstractBinaryTree<E> {

	/** Nested static class for a binary tree node. */
	protected static class Node<E> implements Position<E> {
		private E element;
		private Node<E> parent;
		private Node<E> left;
		private Node<E> right;

	  
	  public Node(E e, Node<E> above, Node<E> leftChild, Node<E> rightChild) {
		  element = e;
		  parent = above;
		  left = leftChild;
		  right = rightChild;
	    }

		public E getElement() {
			return element;
		}

		public void setElement(E element) {
			this.element = element;
		}

		public Node<E> getParent() {
			return parent;
		}

		public void setParent(Node<E> parent) {
			this.parent = parent;
		}

		public Node<E> getLeft() {
			return left;
		}

		public void setLeft(Node<E> left) {
			this.left = left;
		}

		public Node<E> getRight() {
			return right;
		}

		public void setRight(Node<E> right) {
			this.right = right;
		}

		public String toString() {
			return new StringBuilder("").append(element).append("").toString();
		}
	}

	/** Factory function to create a new node storing element e. */
	protected Node<E> createNode(E e, Node<E> parent, Node<E> left, Node<E> right) {
		return new Node<E>(e, parent, left, right);
	}

	// LinkedBinaryTree instance variables
	/** The root of the binary tree */
	protected Node<E> root = null; // root of the tree

	/** The number of nodes in the binary tree */
	private int size = 0; // number of nodes in the tree

	// constructor
	/** Construts an empty binary tree. */
	public LinkedBinaryTree() {
	} // constructs an empty binary tree

	// nonpublic utility
	/**
	 * Verifies that a Position belongs to the appropriate class, and is not one
	 * that has been previously removed. Note that our current implementation does
	 * not actually verify that the position belongs to this particular list
	 * instance.
	 *
	 * @param p a Position (that should belong to this tree)
	 * @return the underlying Node instance for the position
	 * @throws IllegalArgumentException if an invalid position is detected
	 */
	protected Node<E> validate(Position<E> p) throws IllegalArgumentException {
		if (!(p instanceof Node))
			throw new IllegalArgumentException("Not valid position type");
		Node<E> node = (Node<E>) p; // safe cast
		if (node.getParent() == node) // our convention for defunct node
			throw new IllegalArgumentException("p is no longer in the tree");
		return node;
	}

	// accessor methods (not already implemented in AbstractBinaryTree)
	/**
	 * Returns the number of nodes in the tree.
	 * 
	 * @return number of nodes in the tree
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Returns the root Position of the tree (or null if tree is empty).
	 * 
	 * @return root Position of the tree (or null if tree is empty)
	 */
	@Override
	public Position<E> root() {
		return root;
	}

	/**
	 * Returns the Position of p's parent (or null if p is root).
	 *
	 * @param p A valid Position within the tree
	 * @return Position of p's parent (or null if p is root)
	 * @throws IllegalArgumentException if p is not a valid Position for this tree.
	 */
	@Override
	public Position<E> parent(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		return node.getParent();
	}

	/**
	 * Returns the Position of p's left child (or null if no child exists).
	 *
	 * @param p A valid Position within the tree
	 * @return the Position of the left child (or null if no child exists)
	 * @throws IllegalArgumentException if p is not a valid Position for this tree
	 */
	@Override
	public Position<E> left(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		return node.getLeft();
	}

	/**
	 * Returns the Position of p's right child (or null if no child exists).
	 *
	 * @param p A valid Position within the tree
	 * @return the Position of the right child (or null if no child exists)
	 * @throws IllegalArgumentException if p is not a valid Position for this tree
	 */
	@Override
	public Position<E> right(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		return node.getRight();
	}

	// update methods supported by this class
	/**
	 * Places element e at the root of an empty tree and returns its new Position.
	 *
	 * @param e the new element
	 * @return the Position of the new element
	 * @throws IllegalStateException if the tree is not empty
	 */
	public Position<E> addRoot(E e) throws IllegalStateException {
		if (!isEmpty()) {
			throw new IllegalStateException("Tree is not empty");
		}
		root = createNode(e, null, null, null);
		size = 1;
		return root;

	}

	public void insert(E e) {
		// recursively add from root
		root = addRecursive(root, e);
		++size;
	}

	// recursively add Nodes to binary tree in proper position
	private Node<E> addRecursive(Node<E> p, E e) {
//		if (root == null) {
//			return createNode(e, null, null, null);
//		}
//
//		else if (e.compareTo(p.getElement()) < 0) {
//			if (p.getLeft() == null) {
//				addLeft(p, e);
//			}
//			addRecursive(p.getLeft(), e);
//		} else if (e.compareTo(p.getElement()) > 0) {
//			if (p.getRight() == null) {
//				addRight(p, e);
//			}
//			addRecursive(p.getRight(), e);
//		}
//
//		return p;
		
		return null;
	}

	/**
	 * Creates a new left child of Position p storing element e and returns its
	 * Position.
	 *
	 * @param p the Position to the left of which the new element is inserted
	 * @param e the new element
	 * @return the Position of the new element
	 * @throws IllegalArgumentException if p is not a valid Position for this tree
	 * @throws IllegalArgumentException if p already has a left child
	 */
	public Position<E> addLeft(Position<E> p, E e) throws IllegalArgumentException {
		Node<E> parent = validate(p);
		if (parent.getLeft() != null) {
			throw new IllegalArgumentException("p already has a left child");
		}
		Node<E> child = createNode(e, parent, null, null);
		parent.setLeft(child);
		++size;
		return child;
	}

	/**
	 * Creates a new right child of Position p storing element e and returns its
	 * Position.
	 *
	 * @param p the Position to the right of which the new element is inserted
	 * @param e the new element
	 * @return the Position of the new element
	 * @throws IllegalArgumentException if p is not a valid Position for this tree.
	 * @throws IllegalArgumentException if p already has a right child
	 */
	public Position<E> addRight(Position<E> p, E e) throws IllegalArgumentException {
		Node<E> parent = validate(p);
		if (parent.getRight() != null) {
			throw new IllegalArgumentException("p already has a right child");
		}
		Node<E> child = createNode(e, parent, null, null);
		parent.setRight(child);
		++size;
		return child;
	}

	/**
	 * Replaces the element at Position p with element e and returns the replaced
	 * element.
	 *
	 * @param p the relevant Position
	 * @param e the new element
	 * @return the replaced element
	 * @throws IllegalArgumentException if p is not a valid Position for this tree.
	 */
	public E set(Position<E> p, E e) throws IllegalArgumentException {
		Node<E> node = validate(p);
		node.setElement(e);
		return e;
	}

	/**
	 * Attaches trees t1 and t2, respectively, as the left and right subtree of the
	 * leaf Position p. As a side effect, t1 and t2 are set to empty trees.
	 *
	 * @param p  a leaf of the tree
	 * @param t1 an independent tree whose structure becomes the left child of p
	 * @param t2 an independent tree whose structure becomes the right child of p
	 * @throws IllegalArgumentException if p is not a valid Position for this tree
	 * @throws IllegalArgumentException if p is not a leaf
	 */
	public void attach(Position<E> p, LinkedBinaryTree<E> t1, LinkedBinaryTree<E> t2) throws IllegalArgumentException {
		LinkedBinaryTree<E> newTree = new LinkedBinaryTree<E>();
		Node<E> node = validate(p);
		newTree.root = node;
		newTree.root.setRight(t2.root);
		newTree.root.setLeft(t1.root);
	}

	/**
	 * Removes the node at Position p and replaces it with its child, if any.
	 *
	 * @param p the relevant Position
	 * @return element that was removed
	 * @throws IllegalArgumentException if p is not a valid Position for this tree.
	 * @throws IllegalArgumentException if p has two children.
	 */
	public E remove(Position<E> p) throws IllegalArgumentException {
//		own implementation but teacher's way is better
//		Node<E> node = validate(p);
//		Node<E> parent = node.getParent();
//
//		if (node.getLeft() != null && node.getRight() != null) {
////			 throw new IllegalArgumentException("p has two children, cannot remove");
//			Node<E> r = node.getLeft();
//			while (r.getRight() != null) {
//				r = r.getRight();
//			}
//
//			node.setElement(r.getElement());
//
//			r.getParent().setRight(r.getLeft());
//			r.setLeft(node);
//
//		}
//		else {
//			if (node.getLeft() == null) {
//				node.getRight().setParent(parent);
//				if (parent.getLeft().getElement() == node.getElement()) {
//					parent.setLeft(node.getRight());
//				} else {
//					parent.setRight(node.getRight());
//				}
//			} else {
//				node.getLeft().setParent(parent);
//				if (parent.getRight().getElement() == node.getElement()) {
//					parent.setRight(node.getLeft());
//				} else {
//					parent.setLeft(node.getLeft());
//				}
//			}
//
//			node.setParent(node);
//		}
//
//		size--;
//		return node.getElement();
		Node<E> n = (Node<E>) p;
		if(numChildren(n) == 2) {
			throw new IllegalArgumentException("p has two children, cannot remove!");
		}
		Node<E> child = null;
		if(n.getLeft() != null) {
			child = n.getLeft();
		}
		else {
			child = n.getRight();
		}
		
		if(child != null) {
			child.setParent(n.getParent());
		}
		
		if(n == root) {
			root = child;
		}
		else {
			Node<E> parent = n.getParent();
			if(n == parent.getLeft()) {
				parent.setLeft(child);
			}
			else {
				parent.setRight(child);
			}
		}
		
		--size;
		E old = n.getElement();
		
		return old;

	}

	public Node<E> search(Position<E> p, E e) {
//		Node<E> node = validate(p);
//		if (node.getElement() == null || node.getElement() == e) {
//			return node;
//		}
//
//		if (node.getElement().compareTo(e) > 0)
//			return search(node.getLeft(), e);
//
//		return search(node.getRight(), e);
		return null;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		
		for (Position<E> p : positions()) {
			if(p.getElement() == null) {
				sb.append("");
			}
			else {
				sb.append(p.getElement());
				sb.append(", ");
			}
			
			}
		
		sb.append("]");
		return sb.toString();
	}
	
	public void createLevelOrder(E[] arr) {
		root = createLevelOrderHelper(arr, root, 0);
	}
	
	private Node<E> createLevelOrderHelper(E[] arr, Node<E> p, int i){
		
		if(i < arr.length) {
			Node<E> n = createNode(arr[i], p, null, null);
			n.left = createLevelOrderHelper(arr, n.left, 2*i + 1);
			n.right = createLevelOrderHelper(arr, n.right, 2*i+2);
			++size;
			return n;
		}
		
		return p;
	}

	public static void main(String[] args) {
		LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<Integer>();

		Integer [] arr = new Integer[] {1,2,3,4,5,6,7,8,9,10,11,12};
		bt.createLevelOrder(arr);
		System.out.println(bt.toString());
		//assertEquals("[8, 4, 9, 2, 10, 5, 11, 1, 12, 6, 3, 7]", bt.toString());
		
//		LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<Integer>();
//		Integer [] arr = { 12, 25, 31, 58, 36, 42, 90, 62, 75 };
//		
//		bt.createLevelOrder(arr);
//		
//		System.out.println("bt inorder: " + bt.size() + " " + bt.inorder());
//		System.out.println("bt preorder: " + bt.size() + " " + bt.preorder());
//		
//		System.out.println("bt height: " + bt.height(bt.root));
//		System.out.println("bt depth: " + bt.depth(bt.root));
//		
//		for (int i : arr) {
//			bt.insert(i);
//		}
//		//System.out.println("bt: " + bt.size() + " " + bt.inorder());
//		//System.out.println(bt.search(bt.root, 90).getElement());
//		Node<Integer> node = bt.search(bt.root, 90);
//		bt.set(node, 10);
//		System.out.println("bt: " + bt.size() + " " + bt);
//		bt.remove(node);
//		System.out.println("bt: " + bt.size() + " " + bt);
//		node = bt.search(bt.root, 58);
//		bt.remove(node);
//		System.out.println("bt: " + bt.size() + " " + bt);
		
	}

}
