package projectCode20280;

public class LinkedDeque<E> implements Deque<E> {
	
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
	
	DoublyLinkedList<E> list = new DoublyLinkedList();
	
	public int size;
	
	private Node<E> front;
	private Node<E> rear;
	
	public LinkedDeque() {
		front = new Node<>(null, null, null); // create header
		rear = new Node<>(null, front, null); // trailer is preceeded by header
		front.setNext(rear); // header is followed by trailer
	}
	
	public static void main(String[] args) {
		LinkedDeque<Integer> a = new LinkedDeque<Integer>();
		
		a.addFirst(1);
		a.addFirst(2);
		System.out.println(a.first());
		System.out.println(a.last());
		
		a.addLast(3);
		System.out.println(a);
		System.out.println(a.first());
		System.out.println(a.last());
		
		a.removeFirst();
		System.out.println(a);
		a.removeLast();
		System.out.println(a);
	}

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
	public E first() {
		E element;
		element = front.getNext().getElement();
		
		return element;
	}

	@Override
	public E last() {
		E element;
		element = rear.getPrev().getElement();
		
		return element;
	}

	@Override
	public void addFirst(E e) {
		addBetween(e, front, front.getNext()); // place just after the header
		size++;
	}

	@Override
	public void addLast(E e) {
		addBetween(e, rear.getPrev(), rear); // place just before the trailer
		size++;
	}

	@Override
	public E removeFirst() {
		if(isEmpty()) {
			throw new IllegalStateException("List is empty.");
		}
		
		// first element is beyond header
		Node<E> current = front.getNext();
		Node<E> successor = current.getNext();
		
		// removing...
		front.setNext(successor);
		successor.setPrev(front);			
		
		size--;
		return null;
	}

	@Override
	public E removeLast() {
		if(isEmpty()) {
			throw new IllegalStateException("List is empty.");
		}
		
		// last element is before trailer
		Node<E> current = rear.getPrev();
		Node<E> predecessor = current.getPrev();
		
		//removing...
		rear.setPrev(predecessor);
		predecessor.setNext(rear);
		
		size--;
		return null;
	}
	
	@Override
	// to print out the whole linkedlist
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("[");
		// start at head
		Node<E> current = front;
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

}
