package projectCode20280;

import java.util.Iterator;

public class DoublyLinkedList<E> implements List<E> {

	private class Node<E> {
		private E element; // reference to the element stored at this node
		
		private Node<E> prev; // reference to the previous node in the list
		
		private Node<E> next; // reference to the subsequent node in the list
		
		public Node(E e, Node<E> p, Node<E> n) {
			element = e;
			prev = p;
			next = n;
		}

		// accessor and mutator methods
		public E getElement() {
			return element;
		}

		public Node<E> getPrev() {
			return prev;
		}

		public void setPrev(Node<E> prev) {
			this.prev = prev;
		}

		public Node<E> getNext() {
			return next;
		}

		public void setNext(Node<E> next) {
			this.next = next;
		}
		
	}
	
	private Node<E> header; // header sentinel
	
	private Node<E> trailer; // trailer sentinel
	
	private int size = 0; // number of elements in the list
	
	
	// constructs a new empty list
	public DoublyLinkedList() {
		header = new Node<>(null, null, null); // create header
		trailer = new Node<>(null, header, null); // trailer is preceeded by header
		header.setNext(trailer); // header is followed by trailer
	}
	
	public E first() {
		return header.getNext().getElement();
	}
	
	public E last() {
		return trailer.getPrev().getElement();
	}
	
	/*
		Adds an element to the linked list in between the given nodes.
		The given predecessor and successor should be neighboring each
		other prior to the call.
		
		@param predecessor 	node just before the location where the new element is inserted
		@param successor	node just after the location where the new element is inserted
	*/
	private void addBetween(E e, Node<E> predecessor, Node<E> successor) {
		// create and link new node
		Node<E> newest = new Node<>(e, predecessor, successor);
		predecessor.setNext(newest);
		successor.setPrev(newest);
		size++;
	}
	
	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public E get(int i) {
		Node<E> current = header;
		Node<E> previous = null;
		for(int j = 0; j < i; j++) {
			previous = current;
			current = current.getNext();
		}
		
		return current.getElement();
	}

	@Override
	public void add(int i, E e) {
		Node<E> current = header;
		Node<E> previous = null;
		
		for(int j = 0; j < i; j++) {
			previous = current;
			current = current.getNext();
		}
		
		addBetween(e, current.getPrev(), current.getNext());
		size++;
	}

	// Removes the given node from a specific position in the list
	@Override
	public E remove(int i) {
		// initialize pointers towards the current and previous nodes in linkedlist
		Node<E> current = header;
		Node<E> previous = null;
		
		// iterate to desired positon i
		for(int j = 0; j < i; j++) {
			previous = current;
			current = current.getNext();
		}
		
		// remove by breaking nodes and reconnecting respectively
		Node<E> predecessor = current.getPrev();
		Node<E> successor = current.getNext();
		predecessor.setNext(successor);
		successor.setPrev(predecessor);
		size--;
		
		return null;
	}

	private class ListIterator implements Iterator<E> {
		Node<E> curr;
		
		public ListIterator() {
			curr = header;
		}
		
		@Override
		public boolean hasNext() {
			return curr != null;
		}

		@Override
		public E next() {
			E res = (E) curr.getElement();
			curr = curr.getNext();
			return res;
		}
		
	}
	
	@Override
	public Iterator<E> iterator() {
		return new ListIterator();
	}



	// removes and returns the first element of the list/
	// @return the removed element (or null if empty)
	@Override
	public E removeFirst() {
		if(isEmpty()) {
			return null;	// nothing to remove
		}
		
		// first element is beyond header
		Node<E> current = header.getNext();
		Node<E> successor = current.getNext();
		
		// removing...
		header.setNext(successor);
		successor.setPrev(header);			
		
		size--;
		return null;
	}

	@Override
	public E removeLast() {
		if(isEmpty()) {
			return null;	//nothing to remove
		}
		
		// last element is before trailer
		Node<E> current = trailer.getPrev();
		Node<E> predecessor = current.getPrev();
		
		//removing...
		trailer.setPrev(predecessor);
		predecessor.setNext(trailer);
		
		size--;
		return null;
	}
	

	@Override
	public void addFirst(E e) {
		addBetween(e, header, header.getNext()); // place just after the header
		size++;
		
	}

	@Override
	public void addLast(E e) {
		addBetween(e, trailer.getPrev(), trailer); // place just before the trailer
		size++;
		
	}
	
	@Override
	// to print out the whole linkedlist
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("[");
		// start at head
		Node<E> current = header;
		//pointer hop
		while(current != null) {
			s.append(current.getElement());
			s.append(", ");
			
			if(current.getNext() == null) {
				s.setLength(s.length() - 2);
			}
			
			current = current.getNext();
		}
		
		s.append("]");
		
		return s.toString();
	}
	
	public static void main(String[] args) {
		   DoublyLinkedList<Integer> ll = new DoublyLinkedList<Integer>();
           ll.addFirst(0);
           ll.addFirst(1);
           ll.addFirst(2);
           ll.addLast(-1);
           System.out.println(ll);

           ll.removeFirst();
           System.out.println(ll);

           ll.removeLast();
           System.out.println(ll);
           
           ll.add(2, 99);
           System.out.println(ll);
           
           ll.remove(2);
           System.out.println(ll);
           
           ll.addFirst(2);
           System.out.println(ll);
           
           System.out.println(ll.get(1));
           
           for(Integer e: ll) {
                   System.out.println("value: " + e);
           }
	}

	

	
}
