package projectCode20280;

import java.util.Arrays;

public class ArrayQueue<E> implements Queue<E> {

	public static final int CAPACITY = 1000;
	
	private E[] queue;
	
	private int front;
	
	private int rear;
	
	private int currentSize;
	
	public ArrayQueue() {
		this(CAPACITY);
		front = -1;
		rear = -1;
		currentSize = 0;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayQueue(int capacity) {
		front = -1;
		rear = -1;
		currentSize = 0;
		
		queue = (E[]) new Object[capacity];
	}
	
	public static void main(String[] args) {
		ArrayQueue<Integer> a = new ArrayQueue(5);
		
		a.enqueue(10);
		a.enqueue(11);
		a.enqueue(12);
		a.enqueue(13);
		a.enqueue(14);
		
		a.dequeue();
		
		a.enqueue(10);
		
		System.out.println(a);

	}

	@Override
	public int size() {
		return currentSize;
	}

	@Override
	public boolean isEmpty() {
		return currentSize == 0;
	}

	public boolean isFull() {
        return (currentSize == queue.length);
    }
	
	@Override
	public void enqueue(E e) {
		if(isFull()) {
			throw new IllegalStateException("Queue is full.");
		}
		else {
			rear = (rear + 1) % queue.length;
			queue[rear] = e;
			currentSize++;
		}
		
		if(front == -1) {
			front = rear;	
		}
		
	}

	@Override
	public E first() {
		return queue[front];
	}

	@Override
	public E dequeue()  {
		E dequeueElement;
		if(isEmpty( )) {
			throw new IllegalStateException( "Nothing to dequeue" );
		}
		else {
			dequeueElement = queue[front];
			queue[front] = null;
			front = (front + 1) % queue.length;
			currentSize--;
		}
		return dequeueElement;
	}
	
	public String toString() {
		return "CircularQueue" + Arrays.toString(queue);
    }
}
