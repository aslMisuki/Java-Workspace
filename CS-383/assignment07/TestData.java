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
	 * Takes in training.data and stores it
	 */
	//assuming we don't count doubles
	//final Set<String> conditions;

	//assuming we count doubles if they exists
	private List<String[]> values;

	/* 	constructor
	 * inputs: list of queryRV, and a list of evidenceRVs 
	 * P(demo|evidence)
	 */

	public TestData(){
		values = new ArrayList<String[]>();
	}

	//just like training.data, with no identifier
	public void parseTestFile(File inputFile)throws IOException{
		
		Scanner br = new Scanner(inputFile);
		String line = "";
		String[] temp;

		while(br.hasNext()){
			line = br.nextLine();
			values.add(line.split(","));
		}
	}

	//getters

	public List<String[]> getValues(){
		return values;
	}

}