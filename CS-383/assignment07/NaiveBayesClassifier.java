package assignment07;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NaiveBayesClassifier{
	
	//max(P(democrat|evidence), P(republican|evidence)
	/*
	 * constructor
	 * classifies the query
	 */
	
	private double[] republicanCount;
	private double[] demorcratCount;
	
	private List<Query> trainingData;
	
	public NaiveBayesClassifier(){
		republicanCount = new double[17]; //17 includes id count
		demorcratCount = new double[17];
		smoothCount();
		trainingData = new ArrayList<Query>();
	}
	
	//TODO: finish
	/*
	 * parses the training data into
	 * 
	 */
	public void parseTrainingData(File inputFile) throws FileNotFoundException{
		
		Scanner br = new Scanner(inputFile);
		String line = "";
		String[] temp;
		String id;
		while(br.hasNext()){
			line = br.nextLine();
			if(line.contains("republican")){
				id = "republican";
				line.replace("republican,", "");
			}else if(line.contains("democrat")){
				id = "democrat";
				line.replace("democrat,", "");
			}else{id="";}
			temp = line.split(",");
			trainingData.add(new Query(id,temp));
		}
	}
	
	//initiates all counters to 1
	private void smoothCount(){
		int index=0;
		for(double d: republicanCount){
			republicanCount[index] = 1;
			demorcratCount[index] = 1;
			index++;
		}
	}
	
	public String trainingToString(){
		StringBuilder st = new StringBuilder();
		st.append("Printing test data:\n");
		
		for(Query q : trainingData){
			st.append(q.toString() + "\n");
		}
		return st.toString();
	}
	
	/*
	 * Print format:
	 * ID,max(<P(ID|votes)> , <P(ID|votes)>)   *no spaces
	 */
	public String toString(){
		StringBuilder sb = new StringBuilder();
		
		return sb.toString();
	}
	
	public static void main(String args[]) throws IOException{
		
		TestData testData;
		NaiveBayesClassifier naiveBC = null;
		File testFile = null;
		File trainingFile = null;
		
		String mode = "IDE"; // "IDE" or "Shell"
		
		switch(mode){
		case "IDE" :
			trainingFile = new File("C:/Users/Nam Phan/Desktop/Repo/Java-Workspace/CS-383/assignment07/Resources/training.data");
			testFile = new File("C:/Users/Nam Phan/Desktop/Repo/Java-Workspace/CS-383/assignment07/Resources/test.data");
			break;
		case "Shell":
			trainingFile = new File(args[0]);
			testFile = new File(args[1]);
			break;
		default:
			System.out.println("Bad mode!");
			break;
		}
		
		if(trainingFile.canRead() && testFile.canRead()){
			//System.out.println("file can be read");
			naiveBC = new NaiveBayesClassifier();
			naiveBC.parseTrainingData(trainingFile);
			testData = new TestData();
			testData.parseTestFile(testFile);
			File currDir = new File(".");
			System.out.println(naiveBC.trainingToString());
		}
		else{
			System.out.println("Bad File or Directory");
		}
	}
}