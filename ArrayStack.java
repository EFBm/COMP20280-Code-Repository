package projectCode20280;

public class ArrayStack<E> implements Stack<E> {
	
	public static final int CAPACITY = 1000;
	
	private E[] data;
	
	private int t = -1;
	
	public ArrayStack() {
		this(CAPACITY);
	}
	
	@SuppressWarnings("unchecked")
	public ArrayStack(int capacity) {
		data = (E[]) new Object[capacity];
	}

	public static void main(String[] args) {
		ArrayStack a = new ArrayStack(10);
		a.push(100);
		System.out.println(a.top());
		a.push(420);
		System.out.println(a.top());
		a.pop();
		System.out.println(a.top());
	}

	@Override
	public int size() {
		return (t + 1);
	}

	@Override
	public boolean isEmpty() {
		return (t == -1);
	}

	@Override
	public void push(E e) {
		if(size() == CAPACITY) {
			throw new IllegalStateException("Stack is full.");
		}
		
		t += 1;
		
		data[t] = e;
		
	}

	@Override
	public E top() {
		return data[t];
	}

	@Override
	public E pop() {
		if(isEmpty() == true) {
			return null;
		}
		else {
			t -= 1;
		}
		return null;
	}

}
