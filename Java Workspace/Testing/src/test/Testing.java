package test;

import java.io.File;

public class Testing {
	
	int count;
	
	public Testing(){
		count = 0;
	}
	
	public void adding(int c){
		c++;
	}
	
	public static void main(String[] args) {
		
		int add = 0;
		Testing test = new Testing();
		System.out.println(add + "\n");
		test.adding(add);
		System.out.println(add + "\n");
		

	}
	
	

}
