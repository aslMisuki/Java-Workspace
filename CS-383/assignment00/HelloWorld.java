package assignment00;

import java.util.ArrayList;

public class HelloWorld {
	int argSize;
	String[] args;
	
	private HelloWorld(String[] args){ // constructor
		argSize = args.length;
		this.args = args;
	}
	
	private void run(){ // driver
		if(argSize == 0){
			System.out.println("Hello World");
		}
		else{
			System.out.print("Hello");
			for(int i=0; i<argSize;i++){
				System.out.print(" " + args[i]);
			}
		}
	}
	
	
	public static void main(String[] args) { // main

		HelloWorld obj = new HelloWorld(args);
		obj.run();
		System.out.println("testing stage starts now");
		
		
		// 5x5
    	int count = 0;
    	int k = 5;
    	int offset = 0;
    	int numWinStates = (int)((2*(double)k) + 2); // number of winWtates
    	int[] singleArray = new int[k];
    	int index =0;
    	int[][] ret = new int[numWinStates][k];
    	// going to fill in horizontal first, then vertical, then diagonal
    	
    	//horizontal
    	for(int i=0; i<k; i++){
    		for(int b=0; b<k; b++){
    			singleArray[b] = count;
    			count++;
    		}
    		
    		index = i;
    		ret[index] = singleArray.clone();
    	}
    	
    	index++;
    	
    	//System.out.println("index: " +index);
    	count=0;
    	//vertical
    	for(int i=0; i<k; i++){ // offset
    		for(int b=0; b<k; b++){
    			singleArray[b] = i+count; //starts at 0
    			count+=k;
    		}
    		count=0;
    		ret[index] = singleArray.clone();
    		index++;
    	}
    	//diagonal
    	for(int i=0; i<k; i++){
    		singleArray[i] = i*(k+1);
    	}
    	ret[index] = singleArray.clone();
     	index++;
    	
    	for(int i=1; i<=k; i++){
    		singleArray[i-1] = i*(k-1);
    	}
    	
    	ret[index] = singleArray.clone();
    	
    	
    	for(int[] a : ret){
    		for(int b : a){
    			System.out.print(b + " ");
    		}
    		System.out.println();
    	}
		//kewl

//		String[] argTest = new String[]{"Nam", "Nate", "Niki", "Liz"};
//		HelloWorld objTest = new HelloWorld(argTest);
//		objTest.run();
		
	}

}
