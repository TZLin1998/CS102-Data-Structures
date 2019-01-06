package project6;

import java.util.*;

/**
 * This class represent a Binary Search Tree, which implements the Collection<E> Interface.
 * @author Tianze Lin 
 * @version 12/5/2018
 */

public class BST<E extends Comparable<E>> implements Collection<E> {
	
	//data field
	private Node<E> root; 
	
	//methods from Collection<E> Interface
	
	/**
     * Returns the number of elements in this BST
     * @return the number of elements in this BST
     */
	@Override
	public int size() {
		return size(root);
	}
	
	/**
     * A private method that calculates the number of nodes in the tree recursively.
     * @param n the root of the tree
     * @return the number of nodes in the tree
     */
	private int size(Node<E> n) {
		//base case
		if (n == null) {
			return 0;
		}
		//recursive call
		return 1 + size(n.left) + size(n.right);
	}

	/**
     * Returns true if this collection contains no elements.
     * @return true if this collection contains no elements.
     */
	@Override
	public boolean isEmpty() {
		return size(root) == 0;
	}

	/**
     * Returns true if this collection contains the specified element. 
     * More formally, returns true if and only if this collection contains 
     * at least one element e such that Objects.equals(o, e).
     * @param o element whose presence in this collection is to be tested
     * @return true if this collection contains the specified element
     */
	@SuppressWarnings("unchecked")
	@Override
	public boolean contains(Object o) {
		if(isEmpty()) {
			return false;
		}
		if(o == null) {
			return false;
		}
		if(o.getClass() != root.value.getClass()) {
			return false;
		}
		E key = (E) o;
		return contains(key, root);
	}
	
	/**
     * A private method that returns true if this collection contains the specified element. 
     * @param key element whose presence in this collection is to be tested
     * @param n root of the BST
     * @return true if this collection contains the specified element
     */
	private boolean contains(E key, Node<E> n) {
		if(n == null) {
			return false;
		}
		if(key.equals(n.value)) {
			return true;
		}
		if(key.compareTo(n.value)<0) {
			return contains(key, n.left);
		}else {
			return contains(key, n.right);
		}
	}

	/**
     * Returns an iterator over the elements in this collection.
     * Specifically, returns an in order iterator.
     * @return an Iterator over the elements in this collection
     */
	@Override
	public Iterator<E> iterator() {
		return new BSTIterator(root);
	}

	/**
     * Returns an array containing all of the elements in this collection.
     * @return an array, whose runtime component type is Object, 
     * containing all of the elements in this collection
     */
	@Override
	public Object[] toArray() {
		Object[] data = new Object[this.size()];
		Iterator<E> itr = this.iterator();
		for(int i=0; i<this.size(); i++) {
			data[i] = itr.next();
		}
		return data;
	}
	
	/**
     * Returns an array containing all of the elements in this collection; 
     * the runtime type of the returned array is that of the specified array.
     * @param a the array into which the elements of this collection are to be stored, if it is big enough; 
     * otherwise, a new array of the same runtime type is allocated for this purpose.
     * @return an array containing all of the elements in this collection
     * @throw NullPointerException if the specified array is null
     */
	@SuppressWarnings("unchecked")
	@Override
	public <T> T[] toArray(T[] a) {
		if (a.length < this.size()) { //create a new array of larger size
            a = (T[])java.lang.reflect.Array.newInstance(
                                a.getClass().getComponentType(), this.size());
		}
		
        int i = 0;
        Object[] result = a;
        Iterator<E> itr = this.iterator();
        while(itr.hasNext()) { //add elements into the array
        	try {
        		result[i++] = itr.next();
        	}catch(ArrayStoreException e) {
        		return null;
        	}
        }

        if (a.length > size()) {
            a[size()] = null;
        }
        return a;
	}

	/**
     * Ensures that this collection contains the specified element (optional operation). 
     * Returns true if this collection changed as a result of the call. 
     * (Returns false if this collection does not permit duplicates and already contains the specified element.)
     * @param e element whose presence in this collection is to be ensured
     * @return true if this collection changed as a result of the call
     */
	@Override
	public boolean add(E e) {
		if(contains(e)) {
			return false;
		}
		root = add(root, e);
		return true;
	}

	/**
     * A private method that ensures that this collection contains the specified element. 
     * Returns true if this collection changed as a result of the call. 
     * (Returns false if this collection does not permit duplicates and already contains the specified element.)
     * @param root the root of the BST
     * @param e element whose presence in this collection is to be ensured
     * @return true if this collection changed as a result of the call
     */
	private Node<E> add(Node<E> root, E e) {
		//base case: if current tree is empty, add the element
		if (root == null) { 
            root = new Node<E>(e); 
            return root; 
        } 
		//recursive call: if e is smaller than root, move to the left subtree; 
		//otherwise move to the right subtree
		if (e.compareTo(root.value)<0) 
			root.left = add(root.left, e); 
	    else if (e.compareTo(root.value)>0) 
	    	root.right = add(root.right, e);
		return root;
	}
	
	/**
     * Removes a single instance of the specified element from this collection, if it is present.
     * @param o element to be removed from this collection, if present
     * @return true if an element was removed as a result of this call
     */
	@SuppressWarnings("unchecked")
	@Override
	public boolean remove(Object o) {
		if(o == null) {
			throw new NullPointerException("the specified element can't be null");
		}
		if(o.getClass() != root.value.getClass()) {
			return false;
		}
		if(!this.contains(o)) {
			return false;
		}
		root = remove(root, (E) o);
		return true;
	}
	
	/**
     * A private method that removes a single instance of the specified element from this collection
     * @param root the root of this collection
     * @param e element to be removed from this collection, if present
     * @return the root of the collection after removing the specified element
     */
	private Node<E> remove(Node<E> root, E e){
		if(root == null) {
			return root;
		}
		if(e.compareTo(root.value)<0) {
			root.left = remove(root.left, e); 
		}else if (e.compareTo(root.value)>0) {
			root.right = remove(root.right, e); 
		}else {
			// node with only one child or no child 
            if (root.left == null){
                return root.right;
            }else if (root.right == null) {
                return root.left; 
            }
            // node with two children: Get the smallest element in the right subtree)
            root.value = last(root.right).value; 
            // Delete the in order successor 
            root.right = remove(root.right, root.value); 
		}
		return root;
	}

	/**
     * Returns true if this collection contains all of the elements in the specified collection.
     * @param c collection to be checked for containment in this collection
     * @return true if this collection contains all of the elements in the specified collection.
     */
	@Override
	public boolean containsAll(Collection<?> c) {
		if(c == null) {
			return false;
		}
		Object[] obj = c.toArray();
		for(int i=0; i<obj.length; i++) {
			if(!this.contains(obj[i])) {
				return false;
			}
		}
		return true;
	}
	
	/**
     * Compares the specified object with this collection for equality. 
     * @param o object to be compared for equality with this collection
     * @return true if the specified object is equal to this collection
     */
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object o) {
		if(o == null) {
			return false;
		}
		if(o.getClass() != this.getClass()) {
			return false;
		}
		BST<E> bst = (BST<E>) o;
		Iterator<E> cur = this.iterator();
		if(bst.size() != this.size()) {
			return false;
		}
		while(cur.hasNext()) {
			if(!bst.contains(cur.next())) {
				return false;
			}
		}
		return true;
	}
	
	/**
     * @throw UnsupportedOperationException the addAll operation is not supported by this collection
     */
	@Override
	public boolean addAll(Collection<? extends E> c) {
		throw new UnsupportedOperationException("addAll() is not supported.");  	
	}

	/**
     * @throw UnsupportedOperationException the removeAll operation is not supported by this collection
     */
	@Override
	public boolean removeAll(Collection<?> c) {
		throw new UnsupportedOperationException("removeAll() is not supported.");  
	}

	/**
     * @throw UnsupportedOperationException the retainAll operation is not supported by this collection
     */
	@Override
	public boolean retainAll(Collection<?> c) {
		throw new UnsupportedOperationException("retainAll() is not supported.");  
	}

	/**
	 * Removes all of the elements from this collection
     */
	@Override
	public void clear() {
		root = null;
	}
	
	
	//some other methods of BST
	
	
	/**
     * Returns the reference to the element stored in the tree with a value equal to the value
	 * passed as the parameter or null if no equal element exist in this tree.
     * @param value an element to search for this tree
     * @return the reference to the element equal to the parameter value or
	 * null if not such element exists
     */
	public E get(E value) {
		return get(root, value);
	}
	
	/**
     * A private method that returns the reference to the element stored 
     * in the tree with a value equal to the value
	 * passed as the parameter or null if no equal element exist in this tree
	 * @param e the root of this collection
     * @param value an element to search for this tree
     * @return the reference to the element equal to the parameter value or
	 * null if not such element exists
	 * @throw IllegalArgumentException if value is null
     */
	private E get(Node<E> e, E value) {
		if (value == null) {
			throw new IllegalArgumentException("Cannot accept null value");
		}
        if (e == null) {
        	return null;
        }
        int cmp = value.compareTo(e.value);
        if(cmp < 0) { //move to the left subtree
        	return get(e.left, value);
        }else if(cmp > 0) { //move to the right subtree
        	return get(e.right, value);
        }else{
        	return e.value;
        }
    }
	
	/**
     * Returns a string representation of this collection. 
     * @return a string representation of this collection. 
     */
	public String toString() {
		Iterator<E> itr = this.iterator();
		String str = "[";
		while(itr.hasNext()) {
			str += String.valueOf(itr.next());
			str += ", ";
		}
		str = str.substring(0,str.length()-2);
		str +="]";
		return str;
		
	}
	
	/**
     * Returns a string representation of this collection in a tree-format.
     * @return a string representation of this collection in a tree-format.
     * @author Joanna Klukowska
     */
	public String toStringTreeFormat() {
		StringBuilder s = new StringBuilder();
		preOrderPrint(root, 0, s);
		return s.toString();
	}
	
	/**
     * Uses pre - order traversal to produce a tree - like representation of this BST 
     * @param tree the root of the current subtree
     * @param  level  level  ( depth )  of  the  current  recursive  call  in  the  tree  to
	 * determine  the  indentation  of  each  item
	 * @param  output  the  string  that  accumulated  the  string  representation  of  this
	 * BST
     * @author Joanna Klukowska
     */
	private void preOrderPrint(Node<E> tree, int level, StringBuilder output) {
		if(tree != null){
			String spaces = "\n";
			if(level > 0) {
				for(int i = 0; i < level - 1; i++)
					spaces += "   ";
				spaces += "|--";
			}
			output.append(spaces);
			output.append(tree.value);
			preOrderPrint(tree.left, level + 1, output);
			preOrderPrint(tree.right , level + 1, output);
		}else{	// print  the  null  children
			String spaces = "\n";
			if(level > 0) {
				for(int i = 0; i < level - 1; i++)
					spaces += "   ";
				spaces += "|--";
			}
			output.append(spaces);
			output.append("null");
		}
	}
	
	/**
     * Returns an iterator over the elements in this collection. The elements should be 
     * returned in the order determined by the pre order traversal of the tree
     */
	public Iterator<E> preorderIterator(){
		return new preOrderIterator(root);
	}
	
	/**
     * Returns an iterator over the elements in this collection. The elements should be 
     * returned in the order determined by the post order traversal of the tree
     */
	public Iterator<E> postorderIterator(){
		return new postOrderIterator(root);
	}
	
	
	//Methods in the TreeSet<E> Interface
	
	
	/**
     * Returns the least element in this set greater than or equal to the given element, 
     * or null if there is no such element
     * @param e the given element
     * @return the least element in this set greater than or equal to the given element, 
     * or null if there is no such element
     */
	@SuppressWarnings("unchecked")
	public E ceiling(E e) {
		if(e == null) {
			return null;
		}
		Object[] array = this.toArray();
		Arrays.sort(array);
		for(int i=0; i<array.length; i++) {
			if((e.compareTo((E) array[i])<0 || e.compareTo((E) array[i])==0)) {
				return (E) array[i];
			}
		}
		return null;
	}
	
	/**
     * Returns a shallow copy of this tree instance
     * @return a shallow copy of this tree instance
     */
	public Object clone() {
		BST<E> bst = this;
		return bst;
	}
	
	/**
     * Returns the first (lowest) element currently in this set.
     * @return the first (lowest) element currently in this set.
     */
	public E first() {
		return first(root).value;
	}
	
	/**
     * Returns the node that its value is lowest element currently in this set.
     * @return the node that its value is lowest element currently in this set.
     */
	private Node<E> first(Node<E> x) {
		if (x.left == null) {
			return x; 
		}
        else{
        	return first(x.left); 
        }
	}
	
	/**
     * Returns the greatest element in this set less than or equal to the given element, 
     * or null if there is no such element
     * @param e the given element
     * @return the greatest element in this set less than or equal to the given element, 
     * or null if there is no such element
     */
	@SuppressWarnings("unchecked")
	public E floor(E e) {
		if(e == null) {
			return null;
		}
		Object[] array = this.toArray();
		Arrays.sort(array);
		for(int i=array.length-1; i>-1; i--) {
			if((e.compareTo((E) array[i])>0 || e.compareTo((E) array[i])==0)) {
				return (E) array[i];
			}
		}
		return null;
	}
	
	/**
     * Returns the least element in this set strictly greater than the given element, 
     * or null if there is no such element
     * @param e the given element
     * @return the greatest element in this set strictly greater the given element, 
     * or null if there is no such element
     */
	@SuppressWarnings("unchecked")
	public E higher(E e) {
		if(e == null) {
			return null;
		}
		Object[] array = this.toArray();
		Arrays.sort(array);
		for(int i=0; i<array.length; i++) {
			if(e.compareTo((E) array[i])<0) {
				return (E) array[i];
			}
		}
		return null;
	}
	
	/**
     * Returns the last (highest) element currently in this set
     * @return Returns the last (highest) element currently in this set
     */
	public E last() {
		return last(root).value;
	}
	
	/**
     * A private method that returns the last (highest) element currently in this set
     * @param x the root of the set
     * @return Returns the last (highest) element currently in this set
     */
	private Node<E> last(Node<E> x) {
		if(x == null) {
			return null;
		}
		if (x.right == null) {
			return x; 
		}
        else{
        	return last(x.right); 
        }
	}
	
	/**
	 * Returns the greatest element in this set strictly less than the given element, 
	 * or null if there is no such element.
     * @param x the given element
     * @return the greatest element in this set strictly less than the given element, 
	 * or null if there is no such element.
     */
	@SuppressWarnings("unchecked")
	public E lower(E e) {
		if(e == null) {
			return null;
		}
		Object[] array = this.toArray();
		Arrays.sort(array);
		for(int i=array.length-1; i>-1; i--) {
			if(e.compareTo((E) array[i])>0) {
				return (E) array[i];
			}
		}
		return null;
	}
	
	/**
	 * This internal class represent the node of Binary Search Tree
	 * @author Tianze Lin 
	 * @version 10/20/2018
	 */
	@SuppressWarnings("hiding")
	private class Node<E>{
	    E value;
	    Node<E> left;
	    Node<E> right;
	    
	    /**
	     * Constructs an Node
	     * @param input the value of this node
	     */
	    public Node(E input) {
	        this.value = input;
	        left = null;
	        right = null;
	    }
	}
	
	/**
	 * This internal class represent the in order iterator of Binary Search Tree
	 * @author Tianze Lin 
	 * @version 10/20/2018
	 */
	public class BSTIterator implements Iterator<E>{
		Stack<Node<E>> stack;
		
		/**
	     * Constructs a BSTIterator
	     * @param root the root of BST
	     */
		public BSTIterator(Node<E> root) {
			stack = new Stack<Node<E>>();
			while (root != null) {
				stack.push(root);
				root = root.left;
			}
		}
		
		/**
	     * Returns true if the next node is not null
	     * @return true if the next node is not null
	     */
		public boolean hasNext() {
			return !stack.isEmpty();
		}
	 
		/**
	     * Returns the current element and moves the iterator to next node
	     * @return the current element
	     */
		public E next() {
			Node<E> node = stack.pop();
			E result = node.value;
			if (node.right != null) {
				node = node.right;
				while (node != null) {
					stack.push(node);
					node = node.left;
				}
			}
			return result;
		}
		
		/**
	     * Remove is unsupported.
	     * @throw UnsupportedOperationException remove() is not supported.
	     */
		@Override  
		public void remove() {  
			throw new UnsupportedOperationException("remove() is not supported.");  
		}  
	}
	
	/**
	 * This internal class represent the pre order iterator of Binary Search Tree
	 * @author Tianze Lin 
	 * @version 10/20/2018
	 */
	public class preOrderIterator implements Iterator<E>{
		Stack<Node<E>> stack;
	 
		/**
	     * Constructs a pre order iterator of BST
	     * @param root the root of BST
	     */
		public preOrderIterator(Node<E> root) {  
			stack = new Stack<Node<E>>();
		     if (root != null) {  
		    	 stack.push(root); 
		     }  
		}  
	 
		/**
	     * Returns true if the next node is not null
	     * @return true if the next node is not null
	     */
		public boolean hasNext() {
			return !stack.isEmpty();
		}
	 
		/**
	     * Returns the current element and moves the iterator to next node
	     * @return the current element
	     */
		public E next() {  
		     if (!hasNext()) {  
		       throw new NoSuchElementException("All nodes have been visited!");  
		     }  
		   
		     Node<E> res = stack.pop(); // retrieve and remove the top of the stack
		     if (res.right != null) {
		    	 stack.push(res.right);  
		     }
		     if (res.left != null) {
		    	 stack.push(res.left);  
		     }
		     return res.value;  
		}  
		
		/**
	     * Remove is unsupported.
	     * @throw UnsupportedOperationException remove() is not supported.
	     */
		@Override  
		public void remove() {  
			throw new UnsupportedOperationException("remove() is not supported.");  
		}  
	}
	
	/**
	 * This internal class represent the post order iterator of Binary Search Tree
	 * @author Tianze Lin 
	 * @version 10/20/2018
	 */
	public class postOrderIterator implements Iterator<E>{
		Stack<Node<E>> stack = new Stack<Node<E>>();
		/**
	     * Find first leaf in BST whose root is e and store its ascendant in the stack
	     * @param e the root of first lef in BST
	     */
		private void findLeaf(Node<E> e) {  
		     while (e != null) {  
		       stack.push(e);  
		       if (e.left != null) {  
		         e = e.left;  
		       } else {  
		         e = e.right;  
		       }  
		     }  
		}
		
		/**
	     * Constructs a pre order iterator of BST
	     * @param root the root of BST
	     */
		public postOrderIterator(Node<E> root) {  
			findLeaf(root);
		}  
	 
		/**
	     * Returns true if the next node is not null
	     * @return true if the next node is not null
	     */
		public boolean hasNext() {
			return !stack.isEmpty();
		}
	 
		/**
	     * Returns the current element and moves the iterator to next node
	     * @return the current element
	     */
		public E next() {  
			if (!hasNext()) {  
			       throw new NoSuchElementException("All nodes have been visited!");  
			}    
			Node<E> res = stack.pop(); //retrieve of the current element
			if (!stack.isEmpty()) {  
				Node<E> top = stack.peek();  
			    if (res == top.left) {  
			         findLeaf(top.right); // find next leaf in right sub-tree 
			    }  
			}  
			return res.value;  
		}  
		
		/**
	     * Remove is unsupported.
	     * @throw UnsupportedOperationException remove() is not supported.
	     */
		@Override  
		public void remove() {  
			throw new UnsupportedOperationException("remove() is not supported.");  
		}  
	}
}
