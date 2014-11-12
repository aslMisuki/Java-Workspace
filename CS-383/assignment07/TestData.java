package assignment07;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class TestData{
	/*
	 * Takes in test.data and stores it as a list of Query objects
	 * n,n,y,n,y,y,y,n,y,y,y,n,y,y,n,y
	 * n,n,y,y,y,y,n,n,n,n,n,y,y,y,n,n
	 */

	//assuming we count doubles if they exists
	private List<Query> values;
	
	/* 	constructor
	 * inputs: list of queryRV, and a list of evidenceRVs 
	 * P(demo|evidence)
	 */

	public TestData(){
		values = new ArrayList<Query>();
	}

	//just like test.data, with identifier = ""
	public void parseTestFile(File inputFile)throws IOException{
		
		Scanner br = new Scanner(inputFile);
		String line = "";
		String[] temp;

		while(br.hasNext()){
			line = br.nextLine();
			temp = line.split(",");
			values.add(new Query("", temp));
		}
	}

	//getters

	public List<Query> getValues(){
		return values;
	}
	
	//used for testing
	public String toString(){
		StringBuilder st = new StringBuilder();
		st.append("Printing test data:\n");
		
		for(Query q : values){
		
		st.append(q.toString() + "\n");
		}
//			for(String s : q.getConditions()){
//				st.append(s + ",");
//			}
//			st.deleteCharAt(st.length()-1);
//			st.append("\n");
//		}
		
		return st.toString();
	}

}