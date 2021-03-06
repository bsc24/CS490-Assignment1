package question_1;

public class Recursive_BST {
	
	private Node root;
	private int size;
	
	// Constructors
	public Recursive_BST() {
		root = null;
	}
	
	
	// Other methods
	public Node getRoot() {		// Gets the root, NOT the root's value
		return root;
	}
	
	
	public int getSize() {
		return size;
	}
	
	
	public boolean has(float number) {
		return hasHelper(number, root);
	}
	
	private boolean hasHelper(float number, Node current) {
		if (current == null)
			return false;
		else if (number < current.value)
			return hasHelper(number, current.leftChild);
		else if (number > current.value)
			return hasHelper(number, current.rightChild);
		else
			return true;
	}
	
	
	// ASSIGNMENT METHODS
	
	// Done
	public boolean insertRec(float number) {
		// Return true on successful insertion, return false when failing to insert
		
		if (this.has(number))
			return false;		// We can't insert a value already in the tree.
		else {
			root = insertRecHelper(number, root);
			return true;
		}
	}
	
	private Node insertRecHelper(float number, Node current) {
		if (current == null) {
			size++;
			return new Node(number);
		} else if (number < current.value)
			current.leftChild = insertRecHelper(number, current.leftChild);
		else
			current.rightChild = insertRecHelper(number, current.rightChild);
		// Don't need to check whether it's equal, only call this function if the value is not in the tree already
		
		return current;
	}
	
	
	// Done
	public boolean deleteRec(float number) {
		// Return true on successful deletion, return false when failing to delete
		
		if (!this.has(number))
			return false;
		
		root = deleteRecHelper(number, root);
		size--;
		return true;
	}
	
	public Node deleteRecHelper(float number, Node current) {
		if (number < current.value)
			current.leftChild = deleteRecHelper(number, current.leftChild);
		else if (number > current.value)
			current.rightChild = deleteRecHelper(number, current.rightChild);
		else {		// number == current.value
			if (current.leftChild == null && current.rightChild == null)	// We are removing a leaf node
				return null;
			else if (current.leftChild == null)		// We are removing a node with only right children
				return current.rightChild;
			else if (current.rightChild == null)	// We are removing a node with only left children
				return current.leftChild;
			else {		// We are removing a node with two children
				current.value = findMinRecHelper(current.rightChild);
				current.rightChild = deleteRecHelper(current.value, current.rightChild);	// This *should* be removing a leaf node
			}
		}
		
		return current;
	}
	
	
	// Done
	public float findNextRec(float number) throws Exception {
		float holder = findNextRecHelper(number, root);
		if (holder == number)
			throw new Exception();
		else
			return holder;
	}
	
	private float findNextRecHelper(float number, Node current) throws Exception {
		if (current == null)
			throw new Exception();
		
		float holder;
		
		if (number < current.value) {
			holder = findNextRecHelper(number, current.leftChild);
		}
		else if (number > current.value) {
			holder = findNextRecHelper(number, current.rightChild);
		}
		else {	// Otherwise, number == current.value
			if (current.rightChild == null)
				return number;
			else
				return findMinRecHelper(current.rightChild);	// The next node will be the min value in the right subtree
		}
		
		if ((current.value < holder || holder == number) && current.value > number)
			holder = current.value;
		
		return holder;
	}
	
	
	// Done
	public float findPrevRec(float number) throws Exception {
		float holder = findPrevRecHelper(number, root);
		if (holder == number)
			throw new Exception();
		else
			return holder;
	}
	
	private float findPrevRecHelper(float number, Node current) throws Exception {
		if (current == null)
			throw new Exception();
		
		float holder;
		
		if (number < current.value) {
			holder = findPrevRecHelper(number, current.leftChild);
		}
		else if (number > current.value) {
			holder = findPrevRecHelper(number, current.rightChild);
		}
		else {	// Otherwise, number == current.value
			if (current.leftChild == null)
				return number;
			else
				return findMaxRecHelper(current.leftChild);	// The next node will be the min value in the right subtree
		}
		
		if ((current.value > holder || holder == number) && current.value < number)
			holder = current.value;
		
		return holder;
	}
	
	
	// Done
	public float findMinRec() throws Exception {
		if (root == null) {
			System.out.println("No values in the BST.");
			throw new Exception();
		}
		else
			return findMinRecHelper(root);
	}
	
	private float findMinRecHelper(Node current) {
		if (current.leftChild == null)
			return current.value;
		else
			return findMinRecHelper(current.leftChild);
	}
	
	
	// Done
	public float findMaxRec() throws Exception {
		if (root == null) {
			System.out.println("No values in the BST.");
			throw new Exception();
		}
		else
			return findMaxRecHelper(root);
	}
	
	private float findMaxRecHelper(Node current) {
		if (current.rightChild == null)
			return current.value;
		else
			return findMaxRecHelper(current.rightChild);
	}
}
