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
	private double[] democratCount;
	
//	private List<Query> trainingData;

	private List<Query> dEvidence;
	private List<Query> rEvidence;
	
	
	public NaiveBayesClassifier(){
		republicanCount = new double[17]; //17 includes id count
		republicanCount[0] = 1;
		democratCount = new double[17];
		democratCount[0] = 1;
		dEvidence = new ArrayList<Query>();
		rEvidence = new ArrayList<Query>();
		smoothCount();
;
	//	trainingData = new ArrayList<Query>();
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
		String id="";
		while(br.hasNext()){
			line = br.nextLine();
			//System.out.println("new line: " + line);
			
			if(line.contains("republican")){
				id = "republican";
				line = line.replace("republican,", "");
				republicanCount[0]++;
			}
			else if(line.contains("democrat")){
				id = "democrat";
				line = line.replace("democrat,", "");
				democratCount[0]++;
			}
			else{
				id="";
				System.out.println("false");
			}
			//System.out.println("mod Line: " + line + "\n");
			temp = line.split(",");
			switch(id){
			case "republican":
				rEvidence.add(new Query(id,temp));
				break;
			case "democrat":
				dEvidence.add(new Query(id,temp));
				break;
			default:
				break;
			}
		}
	}
	
	//TODO: needs to increment index correctly
	//takes query from testdata 
	private double calcQuery(Query q){
		boolean same = false;
		int index = 1;
		smoothCount(); // resets data except for demo and rep count
	
		// goes through all queries in both givens
		for(Query rq : )
			
		for(String s : q.getConditions()){
			for(Query tq : trainingData){
				for(String tqs : tq.getConditions()){
					if(s.equals(tqs)){
						same = true;
					}
				}
			}
		}
		
		return 0;
	}
	
	
	//in itiates all counters to 1
	private void smoothCount(){
		int index=0;
		double r = republicanCount[0];
		double de = democratCount[0];
		
		for(double d: republicanCount){
			republicanCount[index] = 1;
			democratCount[index] = 1;
			index++;
		}
		republicanCount[0] = r;
		democratCount[0] = de;
	}	
	
	public String trainingToString(){
		StringBuilder st = new StringBuilder();
		st.append("Printing test data:\n");
		
		for(Query q : trainingData){
			st.append(q.toString() + "\n");
		}
		st.append("Total Demorcrat: " + democratCount[0] + "\n");
		st.append("Totl Republicans: " + republicanCount[0]);
		return st.toString();
	}
	
	/*
	 * Print format:
	 * ID,max(<P(ID|votes)> , <P(ID|votes)>)   *no spaces
	 *TODO: finish this
	 */
	public String toString(){
		StringBuilder sb = new StringBuilder();
		
		return sb.toString();
	}
	
	
	//getters
		
	public List<Query> getDEvidence(){
		return dEvidence;
	}
	
	public List<Query> getREvidence(){
		return rEvidence;
	}

	public static void main(String args[]) throws IOException{
		
		TestData testData;
		NaiveBayesClassifier naiveBC = null;
		File testFile = null;
		File trainingFile = null;
		
		String mode = "IDE"; // "IDE" or "Shell"
		
		switch(mode){
		case "IDE" :
//			trainingFile = new File("C:/Users/Nam Phan/Desktop/Repo/Java-Workspace/CS-383/assignment07/Resources/training.data");
//			testFile = new File("C:/Users/Nam Phan/Desktop/Repo/Java-Workspace/CS-383/assignment07/Resources/test.data");
			//Laptop
			trainingFile = new File("C:/Users/Nam/Desktop/Repo/Java-Workspace/CS-383/assignment07/Resources/training.data");
			testFile = new File("C:/Users/Nam/Desktop/Repo/Java-Workspace/CS-383/assignment07/Resources/test.data");
			
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
		
			System.out.println(naiveBC.trainingToString());
			
			//testData = new TestData();
			//testData.parseTestFile(testFile);
	
			//System.out.println(naiveBC.trainingToString());
		}
		else{
			System.out.println("Bad File or Directory");
		}
	}
}