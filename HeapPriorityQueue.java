package projectCode20280;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Random;

/**
 * An implementation of a priority queue using an array-based heap.
 */

public class HeapPriorityQueue<K,V> extends AbstractPriorityQueue<K,V> {

	/** Creates an empty priority queue based on the natural ordering of its keys. */
  public HeapPriorityQueue() { super(); }
  
  protected ArrayList<Entry<K, V>> heap = new ArrayList<>();

  /**
   * Creates an empty priority queue using the given comparator to order keys.
   * @param comp comparator defining the order of keys in the priority queue
   */
  public HeapPriorityQueue(Comparator<K> comp) { super(comp); }

  /**
   * Creates a priority queue initialized with the respective
   * key-value pairs.  The two arrays given will be paired
   * element-by-element. They are presumed to have the same
   * length. (If not, entries will be created only up to the length of
   * the shorter of the arrays)
   * @param keys an array of the initial keys for the priority queue
   * @param values an array of the initial values for the priority queue
   */
  public HeapPriorityQueue(K[] keys, V[] values) {
	  super();
	  for(int j = 0; j < Math.min(keys.length, values.length); j++) {
		  heap.add(new PQEntry<>(keys[j], values[j]));
	  }
	  heapify();
  }

  // protected utilities
  protected int parent(int j) {
	  if(j % 2 == 0) {
		  return (j/2) - 1;
	  }
	  else {
		  return (j - 1)/2;
	  }
  }
  
  protected int left(int j) {
	  return ((j+1)*2) - 1;
  }
  
  protected int right(int j) {
	  return ((j+1)*2);
  }
  
  protected boolean hasLeft(int j) {
	int left = (j * 2) + 1;
	if(left <= size()-1) {
		return true;
	}
	return false;
  }
  protected boolean hasRight(int j) {
	  int right = (j * 2) + 2;
	  if( right <= size()-1) {
			return true;
		}
		return false;
  }

  /** Exchanges the entries at indices i and j of the array list. */
  protected void swap(int i, int j) {
	  Entry<K, V> temp = heap.get(i);
	  heap.set(i,  heap.get(j));
	  heap.set(j,  temp);
  }

  /** Moves the entry at index j higher, if necessary, to restore the heap property. */
  protected void upheap(int j) {
	  while(j > 0) {
		  int p = parent(j);
		  if(compare(heap.get(j), heap.get(p)) >= 0) {
			  break;
		  }
		  swap(j, p);
		  j = p;
	  }
  }
  
  /** Moves the entry at index j lower, if necessary, to restore the heap property. */
  protected void downheap(int j) {
	  while(hasLeft(j)) {
		  int leftIndex = left(j);
		  int smallChildIndex = leftIndex;
		  if(hasRight(j)) {
			  int rightIndex = right(j);
			  if(compare(heap.get(leftIndex), heap.get(rightIndex)) > 0) {
				  smallChildIndex = rightIndex;
			  }
		  }
		  
		  if(compare(heap.get(smallChildIndex), heap.get(j)) >= 0) {
			  break;
		  }
		  swap(j, smallChildIndex);
		  j = smallChildIndex;
	  }
	  
	  
  }

  /** Performs a bottom-up construction of the heap in linear time. */
  protected void heapify() {
	  int startIndex = parent(size() - 1);
	  for (int j = startIndex; j >= 0; j--) {
		  downheap(j);
	  }
  }

  // public methods

  /**
   * Returns the number of items in the priority queue.
   * @return number of items
   */
  @Override
  public int size() { return heap.size(); }

  /**
   * Returns (but does not remove) an entry with minimal key.
   * @return entry having a minimal key (or null if empty)
   */
  @Override
  public Entry<K,V> min() {
	  return heap.get(0);
  }

  /**
   * Inserts a key-value pair and return the entry created.
   * @param key     the key of the new entry
   * @param value   the associated value of the new entry
   * @return the entry storing the new key-value pair
   * @throws IllegalArgumentException if the key is unacceptable for this queue
   */
  @Override
  public Entry<K,V> insert(K key, V value) throws IllegalArgumentException {
	  checkKey(key);
	  Entry<K, V> newest = new PQEntry<>(key, value);
	  heap.add(newest);
	  upheap(heap.size() -1);
	  return newest;
  }

  /**
   * Removes and returns an entry with minimal key.
   * @return the removed entry (or null if empty)
   */
  @Override
  public Entry<K,V> removeMin() {
	  if(heap.isEmpty()) return null;
	  Entry<K, V> answer = heap.get(0);
	  swap(0, heap.size() -1);
	  heap.remove(heap.size() -1);
	  downheap(0);
	  return answer;
  }

  /** Used for debugging purposes only */
  private void sanityCheckHPQueue() {
    for (int j=0; j < heap.size(); j++) {
      int left = left(j);
      int right = right(j);
      if (left < heap.size() && compare(heap.get(left), heap.get(j)) > 0)
        System.out.println("Invalid left child relationship");
      if (right < heap.size() && compare(heap.get(right), heap.get(j)) > 0)
        System.out.println("Invalid right child relationship");
    }
  }
  
  
  @Override
	// to print out the whole array list
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("[");
	
		for(int i = 0; i < size(); i++) {
			if(i == size() - 1) {
				s.append(heap.get(i).getKey());
			}
			else {
				s.append(heap.get(i).getKey());
				s.append(", ");
			}
			
		}
		
		s.append("]");
		
		return s.toString();
	}
  
  // in-place heapsort 
  public void heapSort() {
	  // start indexes
	  int n = size() - 1;
	  int startIndex = size() - 1;
	  
	  for(int i = n; i > 0; i--) {
		  // swap root with last node
		  swap(0, n);
		  for (int j = startIndex - 1; j >= 0; j--) {
			  // upheap, starting from the previous node of the last node
			  upheap(j);
		  }
		  n--;
		  startIndex--;
	  }
  }
  
  // recurisvely sort it ascendingly
  public void PQSort() {
	ArrayList<Entry<K, V>> pq = new ArrayList<Entry<K, V>>();
	while(!heap.isEmpty()) {
		  Entry<K, V> e = heap.get(0);
		  removeMin();
		  pq.add(e);
	  }
	  
	  while(!pq.isEmpty()) {
		  Entry<K, V> e = pq.remove(0);
		  heap.add(size(), e);
	  }
  }
  
  public static void main(String[] args) {
	  int n = 100;
	  while(n < 100000) {
		  Integer[] a = new Integer[n];
		  Random randInt = new Random();
		  for (int i = 0; i < n; ++i) {
			  a[i] = randInt.nextInt(); 
		  }
		  
		  long startTime = System.nanoTime();
		  HeapPriorityQueue<Object, Object> heap = new HeapPriorityQueue<Object, Object>(a, a);
		  // sorts descendingly because our heap is a min heap
		  heap.heapSort();
		  // check if heap is in order
		  heap.sanityCheckHPQueue();
		  long endTime = System.nanoTime();
		  long elapsedTime = endTime - startTime;
		  System.out.println(n + ", " + elapsedTime);
		  n = (int) (n * 1.1);
	  }
	 
  } 
}

