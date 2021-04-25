package projectCode20280;

import java.util.Iterator;

public class SinglyLinkedList<E> implements List<E> {
	
	private int size = 0;
	private Node<E> head = new Node<E> (null,null);
	
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
		
		public void setElement(E element) {
			this.element = element;
		}

		public void setNext(Node<E> n) {
			next = n;
		}
		
	}
	
	public int sizeIncr() {
		return size++;
	}
	
	public int sizeDecr() {
		return size--;
	}
	
	
	@Override
	public boolean isEmpty() {
		return size == 0;
	}
	
	public E first() {
		return head.getElement();
	}

	public E last() {
		Node<E> p = head;
		if(head.getNext() == null) {
			return head.getElement();
		}
		else {
			// iterate to the end of the list
			while(p.getNext() != null) {
				p = p.getNext();
			}
		}
		return p.getElement();
	}

	@Override
	// get element of specific position
	public E get(int i) {
		Node<E> current = head;
		Node<E> previous = null;
		
		// iterate to desired position i
		for(int j = 0; j < i; j++) {
			previous = current;
			current = current.getNext();
		}
		
		// return the element in that position
		return current.getElement();
	}

	@Override
	public void add(int i, E e) {
		// create a new node
		Node<E> addNode = new Node<E>(e, null);
		
		// head null means this will be the head
		if(head == null) {
			head = addNode;
		}
		
		// if head is present and desired position is at 0,
		// basically addNode is the new head
		if(head != null && i == 0) {
			addNode.setNext(head);
			head = addNode;
		}
		
		Node<E> current = head;
		Node<E> previous = null;
		
		// iterate to the desired position i
		for(int j = 0; j < i; j++) {
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
		if(head == null) {
			throw new RuntimeException("cannot delete");
		}
		
		Node<E> current = head;
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

	private class ListIterator implements Iterator<E> {
		Node<E> curr;
		
		public ListIterator() {
			curr = head;
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
	

	@Override
	public int size() {
		return size;
	}	
	

	@Override
	public E removeFirst() {
		E removed = null;
		if(head.getNext() == null) {
			removed = head.getElement();
			head.setElement(null);
		}
		else {
			removed = head.getElement();
			head = head.getNext();
		}
		size--;
		return removed;
	}

	@Override
	public E removeLast() {
		E removed = null;
		if(head.getElement() == null) {
			throw new RuntimeException("cannot delete");
		}
		
		Node<E> current = head;
		Node<E> previous = null;
		
		// iterate till the last node while storing what is previous
		while(current.getNext() != null) {
			previous = current;
			current = current.getNext();
			removed = current.getElement();
		}
		
		// make previous node the last node 
		previous.setNext(null);
		size--;
		return removed;
	}

	@Override
	// add at beginning of list
	public void addFirst(E e) {
		if(head.getElement() == null) {
			head.setElement(e);
		}
		else {
			head = new Node<E>(e, head);
		}
		size++;
		
	}

	@Override
	// add at the end of list
	public void addLast(E e) {
		Node<E> newest = new Node<E>(e, null);
		Node<E> last = head;
		
		// if head is null newest is the head
		if(head.getElement() == null) {
			head.setElement(e);
			head.setNext(null);
		}
		else {
			// iterate to last node
			while(last.getNext() != null) {
				last = last.getNext();
			}
			last.setNext(newest);
		}
		size++;
		
	}
	
	@Override
	// to print out the whole linkedlist
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("[");
		// start at head
		Node<E> current = head;
		//pointer hop
		while(current != null) {
			if(current.getNext() == null) {
				s.append(current.getElement());
				break;
			}
			
			s.append(current.getElement());
			s.append(", ");
			
			current = current.getNext();
		}
		
		s.append("]");
		
		return s.toString();
	}
	
	// changing the direction of the pointers from the last element to the first
	public Node reverse(Node curr, Node prev) 
    { 
  
        // when last node mark it as head
        if (curr.next == null) { 
            head = curr; 
  
            // next to prev node 
            curr.next = prev; 
  
            return head; 
        } 
  
        // Save curr->next node for recursive call 
        Node next1 = curr.next; 
  
        // and update next 
        curr.next = prev; 
  
        reverse(next1, curr); 
        return head; 
    }  
	
	 public SinglyLinkedList<E> recursiveCopy(Node<E> node, SinglyLinkedList<E> linkedList) {
         if (node == null) {
             return linkedList;
         }
         else {
        	 linkedList.addLast(node.getElement());
         }
         return recursiveCopy(node.getNext(), linkedList);
     }
	
	public static void main(String[] args) {
		
		String[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("");

		SinglyLinkedList<String> sll = new SinglyLinkedList<String>();
		SinglyLinkedList<String> sllCopy = new SinglyLinkedList<String>();

		for (String s : alphabet) {
			sll.addFirst(s);
			sll.addLast(s);	
		}
		System.out.println(sll.toString());
		
		sll.recursiveCopy(sll.head, sllCopy);
		System.out.println(sllCopy);
		
		System.out.println(sll.last());
		System.out.println(sll.first());
		
		sll.addFirst("hiAtFirst");
		System.out.println(sll.first());
		
		sll.add(2, "test");
		System.out.println(sll.toString());
		System.out.println(sll.size());
		
		sll.reverse(sll.head, null);
		System.out.println(sll.toString());
		
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
		
//		for (String s : sll) {
//			System.out.print(s + ", ");
//		}
		
		SinglyLinkedList<Integer> ll = new SinglyLinkedList<>();
		//for(int i = 0; i < 5; ++i) ll.addLast(i);
		ll.addFirst(-1);
		System.out.println(ll.toString());
		System.out.println(ll.last());
	}

	


}
