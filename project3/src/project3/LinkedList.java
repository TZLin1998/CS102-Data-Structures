package project3;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/**
 * This class represent a LinkedList, which implements the Collection<E> Interface.
 * @author Tianze Lin 
 * @version 10/20/2018
 */

public class LinkedList<E> implements Collection<E> {
	
	//data field
	int size = 0;
	Node head = null;
	
	/**
     * Constructs an empty list.
     */
    public LinkedList() {
    }
    
    /**
     * Returns the index of the first occurrence of the specified element
     * in this list, or -1 if this list does not contain the element.
     * More formally, returns the lowest index {@code i} such that
     * {@code Objects.equals(o, get(i))},
     * or -1 if there is no such index.
     *
     * @param o element to search for
     * @return the index of the first occurrence of the specified element in
     *         this list, or -1 if this list does not contain the element
     */
	public int indexOf(Object o) {
		int index = 0;
		if (o == null) {
            for (Node x = head; x != null; x = x.next) {
                if (x.element == null)
                    return index;
                index++;
            }
        } else {
            for (Node x = head; x != null; x = x.next) {
                if (o.equals(x.element))
                    return index;
                index++;
            }
        }
        return -1;
    }
	
	/**
     * Returns the element at the specified position in this list.
     *
     * @param index index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
	public E get(int index) {
		//check if the index is valid
		if(index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Index: "+index+", Size: "+size);
		}
		Node x = head;
		for (int i = 0; i < index; i++) {
            	x = x.next;
		}
        return x.element;
	}
	
	/**
     * Returns a string representation of this LinkedList.  
     * The string representation consists of a list of the collection's elements in the 
     * order they are returned by its iterator, enclosed in square brackets ("[]").  
     * Adjacent elements are separated by the characters ", " (comma and space).
     * @return Returns a string representation of this collection.
     */
	public String toString() {
		 String result = "[";
         Node current = head;
         if(head == null) {
        	 return "[]";
         }
         result += head.getItem();
         while(current.getNext() != null){
        	 result += ", ";
             current = current.getNext();
             result += String.valueOf(current.getItem());
         }
         result += "]";
         return result;
	}
	
	/**
     * Sorts the specified list into ascending order, according to the
     * {@linkplain Comparable natural ordering} of its elements.
     */
	@SuppressWarnings("unchecked")
	public void sort() {
		Object [] array = toArray();
		Arrays.sort(array);
		this.clear();
		for(Object o : array ) {
			this.add( (E)o );
		}
	}
	
	/**
     * Returns the number of elements in this list.
     *
     * @return the number of elements in this list
     */
	@Override
	public int size() {
		int i=0;
		Iter a = new Iter();
		while(a.hasNext()) {
			a.next();
			i++;
		}
		return i;
		
	}

	/**
     * Returns true if this collection contains no elements
     *
     * @return true if this collection contains no elements
     */
	@Override
	public boolean isEmpty() {
		if(head == null) {
			return true;
		}
		return false;
	}

	/**
     * Returns {@code true} if this list contains the specified element.
     * More formally, returns {@code true} if and only if this list contains
     * at least one element {@code e} such that
     * {@code Objects.equals(o, e)}.
     *
     * @param o element whose presence in this list is to be tested
     * @return {@code true} if this list contains the specified element
     */
	@Override
	public boolean contains(Object o) {
		int a = this.indexOf(o);
		if(a != -1) {
			return true;
		}
		return false;
	}

	/**
     * Returns a list-iterator of the elements in this list (in proper
     * sequence), starting at the head in the list.
     * 
     * @return a ListIterator of the elements in this list (in proper
     *         sequence), starting at the specified position in the list
     */
	@Override
	public Iterator<E> iterator() {
		Iterator<E> iterator = new Iter();
		return iterator;
	}

	 /**
     * Returns an array containing all of the elements in this list
     * in proper sequence (from first to last element).
     *
     * <p>The returned array will be "safe" in that no references to it are
     * maintained by this list.  (In other words, this method must allocate
     * a new array).  The caller is thus free to modify the returned array.
     *
     * <p>This method acts as bridge between array-based and collection-based
     * APIs.
     *
     * @return an array containing all of the elements in this list
     *         in proper sequence
     */
	@Override
	public Object[] toArray() {
		Object[] data = new Object[size];
		Node cur = head;
		for(int i=0; i<size; i++) {
			data[i] = cur.element;
			cur = cur.next;
		}
		return data;
	}

	/**
     * Returns an array containing all of the elements in this list in
     * proper sequence (from first to last element); the runtime type of
     * the returned array is that of the specified array.  If the list fits
     * in the specified array, it is returned therein.  Otherwise, a new
     * array is allocated with the runtime type of the specified array and
     * the size of this list.
     *
     * <p>If the list fits in the specified array with room to spare (i.e.,
     * the array has more elements than the list), the element in the array
     * immediately following the end of the list is set to {@code null}.
     * (This is useful in determining the length of the list <i>only</i> if
     * the caller knows that the list does not contain any null elements.)
     *
     * <p>Like the {@link #toArray()} method, this method acts as bridge between
     * array-based and collection-based APIs.  Further, this method allows
     * precise control over the runtime type of the output array, and may,
     * under certain circumstances, be used to save allocation costs.
     *
     * <p>Suppose {@code x} is a list known to contain only strings.
     * The following code can be used to dump the list into a newly
     * allocated array of {@code String}:
     *
     * <pre>
     *     String[] y = x.toArray(new String[0]);</pre>
     *
     * Note that {@code toArray(new Object[0])} is identical in function to
     * {@code toArray()}.
     *
     * @param a the array into which the elements of the list are to
     *          be stored, if it is big enough; otherwise, a new array of the
     *          same runtime type is allocated for this purpose.
     * @return an array containing the elements of the list
     * @throws ArrayStoreException if the runtime type of the specified array
     *         is not a supertype of the runtime type of every element in
     *         this list
     * @throws NullPointerException if the specified array is null
     */
	@SuppressWarnings("unchecked")
	@Override
	public <T> T[] toArray(T[] a) {
		if (a.length < size) {
            a = (T[])java.lang.reflect.Array.newInstance(
                                a.getClass().getComponentType(), size);
		}
		
        int i = 0;
        Object[] result = a;
        for (Node x = head; x != null; x = x.next) {
            result[i++] = x.element;
        }

        if (a.length > size) {
            a[size] = null;
        }
        return a;
	}

	 /**
     * Appends the specified element to the end of this list.
     *
     * <p>This method is equivalent to {@link #add}.
     *
     * @param e the element to add
     */
	@Override
	public boolean add(E e) {
		Node newNode = new Node(e, null);
		Node cur = head;
		if(head == null) {
			head = newNode;
		}else {
			while(cur.next != null) {
				cur = cur.next;
			}
			cur.next = newNode;
		}
		size++;
		return true;
	}

	/**
     * Removes the first occurrence of the specified element from this list,
     * if it is present.  If this list does not contain the element, it is
     * unchanged.  More formally, removes the element with the lowest index
     * {@code i} such that
     * {@code Objects.equals(o, get(i))}
     * (if such an element exists).  Returns {@code true} if this list
     * contained the specified element (or equivalently, if this list
     * changed as a result of the call).
     *
     * @param o element to be removed from this list, if present
     * @return {@code true} if this list contained the specified element
     */
	@Override
	public boolean remove(Object o) {
		if(head == null) {
			return false;
		}
		Node cur = head;
		if(head.element.equals(o)) {
			head = head.next;
			return true;
		}
		while(cur.next != null) {
			if(cur.next.element.equals(o)) {
				cur.next = cur.next.next;
				size--;
				return true;
			}
			cur = cur.next;
		}
		return false;
	}

	/**
     * Returns {@code true} if this collection contains all of the elements
     * in the specified collection.
     *
     * @param  c collection to be checked for containment in this collection
     * @return {@code true} if this collection contains all of the elements
     *         in the specified collection
     * @throws NullPointerException if the specified collection contains one
     *         or more null elements and this collection does not permit null
     *         elements
     *         (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>),
     *         or if the specified collection is null.
     */
	@Override
	public boolean containsAll(Collection<?> c) {
		if(c == null) {
			throw new NullPointerException("c cannot be null");
		}
		Object[] ob = c.toArray();
		for(int i=0; i<ob.length;i++) {
			if(!this.contains(ob[i])) {
				return false;
			}
		}
		return true;
	}

	/**
     * Adds all of the elements in the specified collection to this collection
     * (optional operation).  The behavior of this operation is undefined if
     * the specified collection is modified while the operation is in progress.
     * (This implies that the behavior of this call is undefined if the
     * specified collection is this collection, and this collection is
     * nonempty.)
     *
     * @param c collection containing elements to be added to this collection
     * @return {@code true} if this collection changed as a result of the call
     */
	@SuppressWarnings("unchecked")
	@Override
	public boolean addAll(Collection<? extends E> c) {
		Object[] ob = c.toArray();
		for(int i=0; i<ob.length;i++) {
			try {
				E data = (E) ob[i];
				add(data);
				size++;
			}catch(ClassCastException e) {
				return false;
			}
		}
		return true;
	}

	 /**
     * Removes all of this collection's elements that are also contained in the
     * specified collection (optional operation).  After this call returns,
     * this collection will contain no elements in common with the specified
     * collection.
     *
     * @param c collection containing elements to be removed from this collection
     * @return {@code true} if this collection changed as a result of the
     *         call
     */
	@Override
	public boolean removeAll(Collection<?> c) {
		Object[] ob = c.toArray();
		for(int i=0; i<ob.length;i++) {
			remove(ob[i]);
			size--;
		}
		return true;
	}

	/**
     * Retains only the elements in this collection that are contained in the
     * specified collection (optional operation).  In other words, removes from
     * this collection all of its elements that are not contained in the
     * specified collection.
     *
     * @param c collection containing elements to be retained in this collection
     * @return {@code true} if this collection changed as a result of the call
     */
	@SuppressWarnings("unchecked")
	@Override
	public boolean retainAll(Collection<?> c) {
		clear();
		return addAll((Collection<? extends E>) c);
	}

	 /**
     * Removes all of the elements from this collection (optional operation).
     * The collection will be empty after this method returns.
     */
	@Override
	public void clear() {
		head = null;
		size = 0;
	}
	
	  /**
     * Returns the hash code value for this collection.  While the
     * {@code Collection} interface adds no stipulations to the general
     * contract for the {@code Object.hashCode} method, programmers should
     * take note that any class that overrides the {@code Object.equals}
     * method must also override the {@code Object.hashCode} method in order
     * to satisfy the general contract for the {@code Object.hashCode} method.
     * In particular, {@code c1.equals(c2)} implies that
     * {@code c1.hashCode()==c2.hashCode()}.
     *
     * @return the hash code value for this collection
     *
     * @see Object#hashCode()
     * @see Object#equals(Object)
     */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((head == null) ? 0 : head.hashCode());
		result = prime * result + size;
		return result;
	}

	/**
     * Compares the specified object with this collection for equality. <p>
     *
     * @param obj object to be compared for equality with this collection
     * @return {@code true} if the specified object is equal to this
     * collection
     */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof LinkedList)) {
			return false;
		}
		@SuppressWarnings("unchecked")
		LinkedList<E> other = (LinkedList<E>) obj;
		if (head == null) {
			if (other.head != null) {
				return false;
			}
		} 
		if (size != other.size) {
			return false;
		}
		for(int i=0; i<this.size(); i++) {
			if(!this.get(i).equals(other.get(i))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * This internal class represents the node of a LinkedList.
	 * @author Tianze Lin 
	 * @version 10/20/2018
	 */
	
	private class Node {
        E element;
        Node next;

        /**
         * Constructs an empty list.
         * @param e element of this node 
         * @param n the next node
         */
        public Node(E e, Node n) {
            element = e;
            next = n;
        }
        
        /**
         * Returns the element at this node
         * @return the element at this node
         */
        public E getItem() {
        	return this.element;
        }
        
        /**
         * Returns the next element at this node
         * @return the next element at this node
         */
        public Node getNext() {
        	return this.next;
        }
    } 
	
	/**
	 * This internal class represents the iterator of a LinkedList.
	 * It implements the Iterator<E> Interface.
	 * @author Tianze Lin 
	 * @version 10/20/2018
	 */
	
	private class Iter implements Iterator<E>{
		
		Node pointer;
		
		/**
	     * Constructs a list-iterator of the elements in this list (in proper
	     * sequence), starting at the head in the list.
	     */
		public Iter() {
			pointer = head;
		}
		
		/**
	     * Returns true if the next node is not null
	     * @return true if the next node is not null
	     */
		public boolean hasNext() {
			if(pointer != null) {
				return true;
			}else {
				return false;
			}
		}
		
		/**
	     * Returns the current element and moves the iterator to next node
	     * @return the current element
	     */
		public E next() {
			E temp = pointer.element;
			pointer = pointer.next;
			//return the current element and points to the next element
			return temp;
		}
	}

}
