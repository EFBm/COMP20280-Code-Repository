package projectCode20280;

import java.util.Iterator;

public class CircularlyLinkedList<E> implements List<E> {

	private class Node<E> {
		
		private E element;
		
		private Node<E> next;
		
		public Node(E e, Node<E> n) {
			element = e;
			next = n;
		}
		
		public E getElement() { 
			return element; 
		}
		
		public Node<E> getNext() {
			return next;
		}
		
		public void setNext(Node<E> n) {
			next = n;
		}
		
	}
	
	private Node<E> tail = null; // we store tail but not head
	
	private int size = 0; // number of nodes in the list
	
	public CircularlyLinkedList() {
		tail = new Node<E> (null, tail);
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
		Node<E> current = tail;
		Node<E> previous = null;
		
		// iterate to desired position i
		for(int j = 0; j < i + 1; j++) {
			previous = current;
			current = current.getNext();
		}
		
		// return the element in that position
		return current.getElement();	
		}

	@Override
	public void add(int i, E e) {
		Node<E> addNode = new Node<E>(e, null);
		
		// head null means this will be the head
		if(tail == null) {
			tail = addNode;
		}
		
		// if head is present and desired position is at 0,
		// basically addNode is the new head
		if(tail != null && i == 0) {
			Node<E> current = tail.getNext();
			addNode.setNext(current);
			tail.setNext(addNode);
		}
		
		Node<E> current = tail;
		Node<E> previous = null;
		
		// iterate to the desired position i
		for(int j = 0; j < i+1; j++) {
			previous = current;
			current = current.getNext();
		}
		
		// connect prev node to the new node and connect
		// the new node to the next node
		previous.setNext(addNode);
		addNode.setNext(current);
		size++;
			

	}

	@Override
	public E remove(int i) {
		if(tail == null) {
			throw new RuntimeException("cannot delete");
		}
		
		Node<E> current = tail.getNext();
		Node<E> previous = null;
		
		// iterate till the desired position i
		for(int j = 0; j < i; j++) {
			previous = current;
			current = current.getNext();
		}
		
		// remove node a position i by connecting the prev 
		// node to the next node of the current node
		previous.setNext(current.getNext());
		
		size--;
		
		return null;
	}

	// remove node after tail
	@Override
	public E removeFirst() {
		Node<E> firstNode = tail.getNext();

		tail.setNext(firstNode.getNext());
		size--;
		return null;
	}

	// remove tail and make previous node the last instead
	@Override
	public E removeLast() {
		Node<E> firstNode = tail.getNext();
		Node<E> prevLast = tail;
		
		for(int i = 0; i < size - 1; i++) {
			prevLast = prevLast.getNext();
		}
		
		prevLast.setNext(firstNode);
		tail = prevLast;
		
		size--;
		return null;
	}

	private class ListIterator implements Iterator<E> {
		Node<E> curr;
		
		public ListIterator() {
			curr = tail;
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

	// add node after tail
	@Override
	public void addFirst(E e) {
		Node<E> newNode = new Node<E>(e, null);
		
		// no tail means create new list
		if(tail == null) {
			tail = newNode;	
			newNode.setNext(newNode);	
		}
		else {
			newNode.setNext(tail.getNext());
			tail.setNext(newNode);
		}
		size++;
	}

	// add before tail
	@Override
	public void addLast(E e) {
		Node<E> newNode = new Node<E>(e, null);
		// if tail is null newest is the tail
		if(tail.getElement() == null) {
			tail = newNode;	
			tail.setNext(newNode);
		}
		else {
			newNode.setNext(tail.getNext());
			tail.setNext(newNode);
			tail = newNode;
		}
		size++;

	}

	// make the first element the tail
	public void rotate() {
		tail = tail.getNext();
	}
	
	@Override
	// to print out the whole linkedlist
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("[");
		// start at head
		Node<E> current = tail.getNext();
		//pointer hop
		for(int i = 0; i < size; i++) {
			s.append(current.getElement());
			s.append("-> ");
			current = current.getNext();
			
		}
		
		s.append("]");
		
		return s.toString();
	}
	
	public static void main(String[] args) {
		CircularlyLinkedList<Integer> ll = new CircularlyLinkedList<Integer>();
		for(int i = 10; i < 20; ++i) {
			ll.addLast(i);
		}

		System.out.println(ll);

		ll.removeFirst();
		System.out.println(ll);

		ll.removeLast();
		System.out.println(ll);

		ll.removeFirst();
		System.out.println(ll);
		ll.rotate();
		System.out.println(ll);

		ll.removeLast();
		ll.rotate();
		System.out.println(ll);

		ll.add(2, 222);
		System.out.println(ll);
		
		ll.remove(2);
		System.out.println(ll);
		
		System.out.println(ll.get(2));
		
//		for (Integer e : ll) {
//			System.out.println("value: " + e);
//		}
		
/*
		String[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("");

		CircularlyLinkedList<String> sll = new CircularlyLinkedList<String>();
		for (String s : alphabet) {
			sll.addFirst(s);
			sll.addLast(s);	
		}
		System.out.println(sll.toString());
		
		sll.add(2, "test");
		System.out.println(sll.toString());
		System.out.println(sll.size());
		sll.remove(2);
		System.out.println(sll.size());
		System.out.println(sll.toString());

		sll.removeFirst(); 
		System.out.println(sll.toString());
		
		sll.removeLast(); 
		System.out.println(sll.toString());

		sll.remove(2); 
		System.out.println(sll.toString()); 
		
		System.out.println(sll.get(2));
		
		for (String s : sll) {
			System.out.print(s + ", ");
		}
		
		*/
	}
}
