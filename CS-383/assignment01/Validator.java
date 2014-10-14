package assignment01;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
// validator validates whether the input is solvable
public class Validator {
	
	private ArrayList<String[]> board;
	private boolean valid;
	private Solver sol;
	
	public Validator(File fileLoc) throws IOException{ //constructor
		valid = false;
		
		sol = new Solver(fileLoc);
	}
	
	public void run(){ //driver returns "valid" or "invalid"
		valid = sol.validRun();
		if(valid == true){
			System.out.println("valid");
		}
		else{
			System.out.println("invalid");
		}
	}
	
	public static void main(String args[]) throws IOException{
	
		//String fileDir = args[0];
		//File file = new File(fileDir);
		
		File file = new File("C:/Users/Nam Phan/Dropbox/Programming stuff/workspace/AI/src/assignment01/testFail.txt");
		/*
		if(file.canRead()){
			System.out.println("file can be read");
		}
		else{
			System.out.println("nope");
		}
		*/
		
		Validator vDate = new Validator(file);
		vDate.run();
		
	}

}
