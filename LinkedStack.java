package projectCode20280;

public class LinkedStack<E> implements Stack<E> {

	private SinglyLinkedList<E> list = new SinglyLinkedList<>();
	
	public LinkedStack() {
		
	}
	

	public static void main(String[] args) {
		LinkedStack a = new LinkedStack();
		
		a.push("A");
		a.push("B");
		
		System.out.println(a.top());
		
		System.out.println(a.pop());
		
		System.out.println(a.top());
		
		LinkedStack<Integer> s = new LinkedStack<>();
		for(int i = 0; i < 10; ++i)
			s.push(i);
		System.out.println(s.size());
		System.out.println(s.toString());
	}

	public String toString() {
		return list.toString();
	}
	
	@Override
	public int size() {
		return list.size();
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public void push(E e) {
		list.addFirst(e);

	}

	@Override
	public E top() {
		return list.get(0);
	}

	@Override
	public E pop() {
		E e = list.get(0);
		list.removeFirst();
		return e;
	}
}