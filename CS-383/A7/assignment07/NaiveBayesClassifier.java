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
	// first element is # of identifiers
	// using these arrays because this will only need 1 iteration over training.data
	private double[] republicanYCount;
	private double[] republicanNCount;
	private double[] democratYCount;
	private double[] democratNCount;

	private double democrats;
	private double republicans;

	//list split into republicans and democrats from training.data
	private List<Query> dEvidence;
	private List<Query> rEvidence;


	public NaiveBayesClassifier(){
		republicanYCount = new double[16];
		republicanNCount = new double[16]; 
		republicans = 1; //smoothed
		democratYCount = new double[16];
		democratNCount = new double[16];
		democrats = 1; //smoothed

		dEvidence = new ArrayList<Query>();
		rEvidence = new ArrayList<Query>();
		smoothCount(); //smoothes all y/n counts to account  for naiveBayes
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
			line = br.nextLine().toLowerCase(); //read with error tolerance

			if(line.contains("republican")){
				id = "republican";
				line = line.replace("republican,", "");
				republicans++;
			}
			else if(line.contains("democrat")){
				id = "democrat";
				line = line.replace("democrat,", "");
				democrats++;
			}
			else{
				id="";
			}

			temp = line.split(",");
			switch(id){
			case "republican":
				rEvidence.add(countValues(new Query(id,temp)));
				break;
			case "democrat":
				dEvidence.add(countValues(new Query(id,temp)));
				break;
			default:
				break;
			}
		}
	}

	/*
	 * increments counters in Arrays for Democrats and Republicans
	 */
	private Query countValues(Query q){
		int index=0;
		String p = q.getParty();

		switch(p){
			case "republican":
				for(String s : q.getConditions()){
					switch(s){
					case "y" :
						republicanYCount[index]+=1;
						break;
					case "n" :
						republicanNCount[index]+=1;
						break;
					default: // ?
						break;
					}
					index++;
				}
				break;
			case "democrat" :
				for(String s : q.getConditions()){
					switch(s){
					case "y" :
						democratYCount[index]+=1;
						break;
					case "n" :
						democratNCount[index]+=1;
						break;
					default:
						break;
					}
					index++;
				}
				break;
			default:
				break;
		}
		return q;
	}

	/*
	 * Takes test data and constructs P(class|evidence) using counting collected
	 */
	public void calcTestData(TestData td){
		
		List<Result> result = new ArrayList<Result>();
		
		for(Query q : td.getValues()){
			result.add(calcQuery(q));
		}
		//System.out.println("Printing calcTestData");
		for(Result r : result){
			System.out.println(r.getParty() + "," + r.getProb());
		}
	}
	
	//takes a single query from test data and returns a Result obj
	private Result calcQuery(Query q){
		String id ="";
		double r = 0;
		double d = 0;

		int index = 0;
		for(String s : q.getConditions()){
			//P(c|evidence)
			switch(s){
				case "y" :
					r += Math.log(republicanYCount[index] / republicans);
					//System.out.println("dividing by: " + republicanYCount[index]  + "/" + republicans + "@index: " + index);
					d += Math.log(democratYCount[index] / democrats);
					break;
				case "n" :
					r += Math.log(republicanNCount[index] / republicans);
					d += Math.log(democratNCount[index] / democrats);
					break;
				case "?" : //just a precaution do nothing
					break;
				default:
					break;
			}
			index++;
		}
		
		r += Math.log(republicans/(republicans + democrats)) ;
		d += Math.log(democrats/(republicans + democrats)) ;
		
		r = Math.exp(r);
		d = Math.exp(d);
		double ret = 0;
		if((d/(r+d)) > (r/(r+d))){
			id = "democrat";
			ret = d/(r+d);
		}
		else{ // default to
			id = "republican";
			ret = r/(r+d);;
		}
		
		return new Result(id, ret);
	}

	//in itiates all counters to 1 except IDs
	private void smoothCount(){
		int index=0;

		for(double d: republicanYCount){
			republicanYCount[index] = 1;
			index++;
		}
		index=0;
		for(double d: republicanNCount){
			republicanNCount[index] = 1;
			index++;
		}
		index=0;
		for(double d: democratYCount){
			democratYCount[index] = 1;
			index++;
		}
		index=0;
		for(double d: democratNCount){
			democratNCount[index] = 1;
			index++;
		}
	}


	/*
	 * Getters
	 * 
	 */

	public List<Query> getDEvidence(){
		return dEvidence;
	}

	public List<Query> getREvidence(){
		return rEvidence;
	}

	/*
	 * toStrings
	 * 
	 */

	public String trainingToString(){
		StringBuilder st = new StringBuilder();
		st.append("Printing test data:\n");
		st.append("Printing Republicans data:\n");

		for(Query q : rEvidence){
			st.append(q.toString() + "\n");
		}

		st.append("\nPrinting Demorcrats:\n");
		for(Query q : dEvidence){
			st.append(q.toString() + "\n");
		}

		st.append("Total Demorcrat: " + democrats + "\n");
		st.append("Total Republicans: " + republicans + "\n");

		return st.toString();
	}

	public String valuesToString(){
		StringBuilder sb = new StringBuilder();
		
		sb.append("Democrats: " + democrats + "\n");
		sb.append("democratYCount: ");
		for(double d: democratYCount){
			sb.append("[" + d + "],");
		}
		sb.append("\ndemocratNCount: ");
		for(double d: democratNCount){
			sb.append("[" + d + "],");
		}
		sb.append("\nRepublicans: " + republicans + "\n");
		sb.append("republicanYCount: ");
		for(double d: republicanYCount){
			sb.append("[" + d + "],");
		}
		sb.append("\nrepublicanNCount: ");
		for(double d: republicanNCount){
			sb.append("[" + d + "],");
		}
		return sb.toString();
	}

	public static void main(String args[]) throws IOException{

		TestData testData;
		NaiveBayesClassifier naiveBC = null;
		File testFile = null;
		File trainingFile = null;

		String mode = "IDE"; // "IDE" or "Shell"
		mode = mode.toUpperCase();

		switch(mode){
		case "IDE" :
			trainingFile 	= new File("C:/Users/Nam Phan/Desktop/Repo/Java-Workspace/CS-383/assignment07/Resources/training.data");
			testFile 		= new File("C:/Users/Nam Phan/Desktop/Repo/Java-Workspace/CS-383/assignment07/Resources/test.data");
			//Laptop
//			trainingFile = new File("C:/Users/Nam/Desktop/Repo/Java-Workspace/CS-383/assignment07/Resources/training.data");
//			testFile = new File("C:/Users/Nam/Desktop/Repo/Java-Workspace/CS-383/assignment07/Resources/test.data");

			break;
		case "SHELL":
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
			//System.out.println(naiveBC.valuesToString());
			//System.out.println(naiveBC.trainingToString());

			testData = new TestData();
			testData.parseTestFile(testFile);
			naiveBC.calcTestData(testData);

			//System.out.println(naiveBC.trainingToString());
		}
		else{
			System.out.println("Bad File or Directory");
		}
	}

	/*
	 * just a data structure for containing 
	 */
	public class Result{
		
		private String party;
		private double prob;
		
		public Result(String party, double prob){
			this.party = party;
			this.prob = prob;
		}
		public String getParty(){
			return party;
		}
		public double getProb(){
			return prob;
		}
	}
}
