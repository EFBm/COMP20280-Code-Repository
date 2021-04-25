package projectCode20280;

public class LinkedCircularQueue<E> implements Queue<E> {

private SinglyLinkedList<E> list = new SinglyLinkedList<>();
	
	public int size;
	
	private Node<E> front;
	private Node<E> rear;
	
	public LinkedCircularQueue() {
		front = null;
		rear = null;
		size = 0;
	}

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
	
	public static void main(String[] args) {
		LinkedQueue<Integer> a = new LinkedQueue<Integer>();
		
		a.enqueue(1);
		a.enqueue(2);
		a.enqueue(3);
		System.out.println(a);
		
		a.dequeue();
		System.out.println(a);
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
	public void enqueue(E e) {
		Node<E> addNode = new Node(e, null);
		
		if(front == null) {
			front = addNode;
			rear = addNode;
		}
		else {
			rear.setNext(addNode);
			rear = addNode;
			rear.setNext(front);
			size++;
		}
		
	}

	@Override
	public E first() {
		return front.getElement();
	}

	@Override
	public E dequeue() {
		if(isEmpty()) {
			throw new IllegalStateException("Queue is empty.");
		}
		
		E first;
		
		first = (E) front.getElement();
		front = front.getNext();
		rear.setNext(front);
		size--;
		
		return first;
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
			s.append("-> ");
			
			if(current.getNext() == null) {
				s.append("null");
			}
			
			current = current.getNext();
		}
		
		s.append("]");
		
		return s.toString();
	}
}
