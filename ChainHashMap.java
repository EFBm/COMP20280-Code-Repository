package projectCode20280;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/*
 * Map implementation using hash table with separate chaining.
 */

public class ChainHashMap<K, V> extends AbstractHashMap<K, V> {
	// a fixed capacity array of UnsortedTableMap that serve as buckets
	private UnsortedTableMap<K, V>[] table; // initialized within createTable

	/** Creates a hash table with capacity 11 and prime factor 109345121. */
	public ChainHashMap() {
		super();
	}

	/** Creates a hash table with given capacity and prime factor 109345121. */
	public ChainHashMap(int cap) {
		super(cap);
	}

	/** Creates a hash table with the given capacity and prime factor. */
	public ChainHashMap(int cap, int p) {
		super(cap, p);
	}

	/** Creates an empty table having length equal to current capacity. */
	@Override
	@SuppressWarnings({ "unchecked" })
	protected void createTable() {
		table = (UnsortedTableMap<K,V>[]) new UnsortedTableMap[capacity];		
	}

	/**
	 * Returns value associated with key k in bucket with hash value h. If no such
	 * entry exists, returns null.
	 * 
	 * @param h the hash value of the relevant bucket
	 * @param k the key of interest
	 * @return associate value (or null, if no such entry)
	 */
	@Override
	protected V bucketGet(int h, K k) {
		UnsortedTableMap<K,V> bucket = table[h];
		if(bucket == null) {
			return null;
		}
		return bucket.get(k);
	}

	/**
	 * Associates key k with value v in bucket with hash value h, returning the
	 * previously associated value, if any.
	 * 
	 * @param h the hash value of the relevant bucket
	 * @param k the key of interest
	 * @param v the value to be associated
	 * @return previous value associated with k (or null, if no such entry)
	 */
	@Override
	protected V bucketPut(int h, K k, V v) {
		UnsortedTableMap<K,V> bucket = table[h];
		if(bucket == null) {
			bucket = new UnsortedTableMap<K,V>();
			table[h] = bucket;
		}
		int prev_size = bucket.size();
		V old = bucket.put(k, v);
		n += (bucket.size() - prev_size);
		//System.out.println("bucketPut " + h + ", " + k + ", " + v + ", " + bucket);
		return old;
	} 

	/**
	 * Removes entry having key k from bucket with hash value h, returning the
	 * previously associated value, if found.
	 * 
	 * @param h the hash value of the relevant bucket
	 * @param k the key of interest
	 * @return previous value associated with k (or null, if no such entry)
	 */
	@Override
	protected V bucketRemove(int h, K k) {
		UnsortedTableMap<K,V> bucket = table[h];
		if(bucket == null) {
			return null;
		}
		int prev_size = bucket.size();
		V old = bucket.remove(k);
		n -= (prev_size - bucket.size());
		return old;
	}

	/**
	 * Returns an iterable collection of all key-value entries of the map.
	 *
	 * @return iterable collection of the map's entries
	 */
	@Override
	public Iterable<Entry<K, V>> entrySet() {
		ArrayList<Entry<K,V>> res = new ArrayList<Entry<K,V>>();
		
		for(int i = 0; i < capacity; i++) {
			UnsortedTableMap<K,V> bucket = table[i];
			if(bucket != null) {
				for(Entry<K,V> entry : bucket.entrySet()) {
					res.add(entry);
				}
			}
		}
		
		return res;
	}
	
	public String toString() {
		return entrySet().toString();
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		//HashMap<Integer, String> m = new HashMap<Integer, String>();
		ChainHashMap<Integer, String> m = new ChainHashMap<Integer, String>();
//		System.out.println("size before: " + m.size());
//		String n = m.put(1, "One");
//		m.put(2, "Two");
//		System.out.println("size after: " + m.size() + " -> " + n);
//		System.out.println(m);
//		System.out.println("m.get(1) " + m.get(1));
//		m.remove(1);
//		System.out.println("size after: " + m.size());
//		m.put(1, "One");
//		m.put(10, "Ten");
//		m.put(11, "Eleven");
//		m.put(20, "Twenty");
//		
//		System.out.println("m: " + m);
//		
//		m.remove(11);
//		System.out.println("m: " + m);
	
		// count the frequency of words
		ChainHashMap<String, Integer> counter = new ChainHashMap<String, Integer>();
		Scanner sc = new Scanner(new File("D:\\Stage2 - Semester2\\datastructures-choonjerald\\src\\projectCode20280\\sample_text.txt"));
		while(sc.hasNextLine()) {
			Scanner scWord = new Scanner(sc.nextLine());
			while(scWord.hasNext()) {
				String s = scWord.next();
				if(counter.get(s) == null) {
					counter.put(s, 1);
				}
				else {
					int old_counter = counter.get(s);
					counter.put(s, old_counter+1);
				}
			}
		}
		
		System.out.println(counter);
		
		// to print top 10 most frequent words
		ArrayList<Entry<String, Integer>> arr = (ArrayList<Entry<String, Integer>>) counter.entrySet();
		
		// sort descendingly
		Entry<String, Integer> temp;
		int max_index;
		for(int i = 0; i < arr.size() - 1; i++) {
			max_index = i;
			for(int j = i + 1; j < arr.size() - 1; j++) {
				if(arr.get(max_index).getValue() < arr.get(j).getValue()) {
					max_index = j;	
					}
			}
			temp = arr.get(i);
			arr.set(i, arr.get(max_index));
			arr.set(max_index, temp);
		}

		for(int i = 0; i < 10; i++) {
			System.out.println("#" + (i+1) + " " + arr.get(i));
		}
		
		// count number of collisions
		ChainHashMap<String, Integer> words = new ChainHashMap<String, Integer>();
		sc = new Scanner(new File("D:\\Stage2 - Semester2\\datastructures-choonjerald\\src\\projectCode20280\\words.txt"));
		while(sc.hasNextLine()) {
			Scanner scWord = new Scanner(sc.nextLine());
			while(scWord.hasNext()) {
				String s = scWord.next();
				if(words.get(s) == null) {
					words.put(s, 1);
				}
			}
		}
		
		
	}
}
