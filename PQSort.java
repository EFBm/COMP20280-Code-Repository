package projectCode20280;

import java.util.Random;


public class PQSort<K, V> extends HeapPriorityQueue<K,V>{  
	
	public PQSort(K[] keys, V[] values) {
		super();
		for(int j = 0; j < Math.min(keys.length, values.length); j++) {
			  heap.add(new PQEntry<>(keys[j], values[j]));
		  }
		heapify();
	}

	public void sanityCheckPQSort() {
	    for (int j=0; j < heap.size(); j++) {
	      int left = left(j);
	      int right = right(j);
	      if (left < heap.size() && compare(heap.get(left), heap.get(j)) < 0)
	        System.out.println("Invalid left child relationship");
	      if (right < heap.size() && compare(heap.get(right), heap.get(j)) < 0)
	        System.out.println("Invalid right child relationship");
	    }
	  }
	  
	public static void main(String[] args) { 
		  int n = 100;
		  while(n < 1000000) {
			  Integer[] a = new Integer[n];
			  Random randInt = new Random();
			  for (int i = 0; i < n; ++i) {
				  a[i] = randInt.nextInt(); 
			  }
			  
			  long startTime = System.nanoTime();
			  PQSort<Object, Object> heap = new PQSort<Object, Object>(a, a);
			  // sorts ascendingly
			  heap.PQSort();
			  // check if list is in order
			  heap.sanityCheckPQSort();
			  long endTime = System.nanoTime();
			  long elapsedTime = endTime - startTime;
			  System.out.println(n + ", " + elapsedTime);
			  n = (int) (n * 1.1);
		  }
		  
	}

	

}
