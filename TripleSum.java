package projectCode20280;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TripleSum {

	public static void main(String[] args) throws FileNotFoundException {
		int count = 0;
		int arrayNumber[] = new int[8];
		int x = 0;
		
		File file = new File("D:\\Stage2 - Semester2\\datastructures-choonjerald\\src\\projectCode20280\\triple_sum_ints.txt"); 
	    Scanner sc = new Scanner(file); 
	    
	    while (sc.hasNextLine()) {
	    	arrayNumber[x] = sc.nextInt();
	    	x++;
	    }
	        
	    for(int i = 0; i < 8; i++) {
	    	int a = arrayNumber[i];
	    	for(int j = i+1; j < 8; j++) {
	    		int b = arrayNumber[j];
	    		for(int k = i+2; k < 8; k++) {
	    			int c = arrayNumber[k];
	    			int difference = a + b + c;
	    			if(difference == 0) {
	    				count++;
	    			}
	    		}
	    	}
	    }
	    
	    System.out.println(count);

	}
	
}
