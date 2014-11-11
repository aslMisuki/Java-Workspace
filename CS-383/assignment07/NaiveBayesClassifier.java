package assignment07;

import java.io.File;
import java.util.Scanner;

public class NaiveBayesClassifier{
	
	//max(P(democrat|evidence), P(republican|evidence)
	/*
	 * constructor
	 * classifies the query
	 */
	
	private double[] valueCount;
	
	
	public NaiveBayesClassifier(){
		valueCount = new double[16];
		smoothCount();
	}
	
	public void parseTrainingData(File inputFile){
		
		Scanner br = new Scanner(inputFile);
		String line = "";
		String[] temp;

		while(br.hasNext()){
			line = br.nextLine();
			values.add(line.split(","));
		}
	}
	
	private void smoothCount(){
		int index=0;
		for(double d: valueCount){
			valueCount[index] = 1;
			index++;
		}
	}
	
	
	public static void main(String args[]){
		
		TestData testData = new TestData();
		File testFile = null;
		File trainingFile = null;
		
		String mode = "IDE"; // "IDE" or "Shell"
		
		switch(mode){
		case "IDE" :
			trainingFile = new File("./Resources/training.data");
			testFile = new File("./Resources/test.data");
			break;
		case "Shell":
			trainingFile = new File(args[0]);
			testFile = new File(args[1]);
			break;
		default:
			System.out.println("Bad mode!");
			break;
		}
		
		if(trainingFile.canRead()){
			//System.out.println("file can be read");
			FJDQuery Cars = new FJDQuery(file, cardata);
			Cars.run();
		}
		else{
			System.out.println("Bad File or Directory");
		}
	}
}