import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Course: EECS 114 Fall 2015
 *
 * First Name: Stefan
 * Last Name: Cao
 * Lab Section: 1A
 * email address: stefanc1@uci.edu
 *
 *
 * Assignment: lab5
 * Filename : heapsort.java
 *
 * I hereby certify that the contents of this file represent
 * my own original individual work. Nowhere herein is there 
 * code from any outside resources such as another individual,
 * a website, or publishings unless specifically designated as
 * permissible by the instructor or TA.
 */ 

public class heapsort {

	private static int N;
	
	public static void main(String[] args) {
	
		int lcIndex = 0;
		int heapSize = 0;
		
		
		if (args.length != 1) {
			System.out.println("No input file provided. Expected Usage: java <executable> input.txt");
			System.exit(1);
		} 
		else {
			
			// reading to get the heapsize
			File intfile = new File(args[0]);
			try {
				Scanner inNums = new Scanner(intfile);
				while(inNums.hasNextInt()) {
					if(lcIndex == 0){
						heapSize = inNums.nextInt();
						break;
					}
					
				}
				inNums.close();
			} catch (FileNotFoundException e) {
				System.out.println(e.getMessage());
			}
		
			int[] A = new int[heapSize+1];
			
			
			// reading to put values in an array
			intfile = new File(args[0]);
			try {
				Scanner inNums = new Scanner(intfile);
				while(inNums.hasNextInt()) {
					if( lcIndex == 0){
						heapSize = inNums.nextInt();
						lcIndex++;
					}	
					else{
						A[lcIndex] = inNums.nextInt();
						lcIndex++;
					}
				}
				inNums.close();
			} catch (FileNotFoundException e) {
				System.out.println(e.getMessage());
			}
			
			
			for (int i = 1; i < A.length; i++)
	            System.out.print(A[i]+" ");            
	        System.out.println(); 
			
			Heapsort(A, A.length);
			
		}	// end if else
		
	}	// end of main
	
	// Method for sorting the heap
	public static void Heapsort(int A[], int n){
		
		// call MaxHeapify which will build a max heap
		BuildMaxHeap(A, n);
		
		// starting from last index and then going down to first
		for(int i = n-1; i > 1; i--){
			
			//exchange A[1] with A[i]
			int temp = A[1];
			A[1] = A[i];
			A[i] = temp;
			
			// call buildMaxHeap where the parent is the first element
			MaxHeapify(A, 1, i-1);
			
			// printing out the heap
			for (int j = 1; j < n; j++)
				System.out.print(A[j] + " ");            
	        System.out.println();  
			
		}
	}
	
	// method for building a max heap
	public static void MaxHeapify(int A[], int i, int j){
		
		//getting index of leftChild and right child depending on 'i' (which is parent)
		int leftChild = 2*i;
		int rightChild = 2*i + 1;
		int parent = i;
		
		// if leftChild is less than or equal to N AND the key of leftChild is greater than parent, then leftChild is new parent
		if (leftChild <= j && A[leftChild] > A[i]){
            parent = leftChild;
		}
		
		// if right Child is less than or equal to N AND the key of rightChild is greater than parent, then rightCHild is new parent
        if (rightChild <= j && A[rightChild] > A[parent]){ 
            parent = rightChild;
        }
        
        // if parent has been changes from previous validations
        if(parent != i){
        	
        	//exchange  parent and 'i'
			int temp = A[parent];
			A[parent] = A[i];
			A[i] = temp;
			
			// call buildMaxHeap on parent (trickle down)
			MaxHeapify(A, parent, j);
        }
	}
	
	// method MaxHeapify
	public static void BuildMaxHeap(int A[], int n){
		for(int i = n/2; i >= 1; i--){
			MaxHeapify(A, i, n-1);
		}
	}

}