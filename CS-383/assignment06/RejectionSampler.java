package assignment06;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by liberato on 10/25/14.
 */
public class RejectionSampler {

	public RejectionSampler(Event event, boolean[] values, Map<String, Node> bn, int N ){ //constructor
		
		int[] count;
		Node x;
		PriorSample ps = new PriorSample(bn);
		
//		for(int j =1; j<N; j++){
//			x = ps.runSampling();
//		}
		
		/*
		 * double[] N = new double[X.getDomain().size()];

                for (int j = 0; j < Nsamples; j++) { // # of samples
                        // <b>x</b> <- PRIOR-SAMPLE(bn)
                        Map<RandomVariable, Object> x = ps.priorSample(bn);
                        // if <b>x</b> is consistent with e then
                        if (isConsistent(x, e)) {
                                // <b>N</b>[x] <- <b>N</b>[x] + 1
                                // where x is the value of X in <b>x</b>
                                N[indexOf(X, x)] += 1.0;
                        }
                }
                // return NORMALIZE(<b>N</b>)
                return new ProbabilityTable(N, X).normalize();
		 */
	}
	
	public RejectionSampler(Event event){
		int[] count;
		Node x;
	}

	public static String readEntireFile(File f) {
		FileInputStream fin = null;
		try {
			fin = new FileInputStream(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// tomfoolery relying on '\A' meaning "separate tokens using only the
		// beginning of the input as a boundary"
		java.util.Scanner scanner = new java.util.Scanner(fin,"UTF-8").useDelimiter("\\A");
		return scanner.hasNext() ? scanner.next() : "";
	}
	
	public String toString(){
		
		return "This is the steadyState";
	}

	public static void main(String[] args) {
		String mode = "IDE"; // "shell" or "IDE"
		String fileContents = "";
		Map<String, Node> nodeMap = new HashMap<String,Node>();
		Event e;
		boolean testGrass = false;

		switch(mode){
		case "shell" :
			fileContents = readEntireFile(new File(args[0]));
			break;
		case "IDE" :
			String test = "Q"; // "Q", "Q+E", "Q+E+V", "grass", "cGrass"
			switch(test){	
			case "Q":
				System.out.println("Testing Query only");
				fileContents = "[" + readEntireFile(new File("./A6/assignment06/jResources/TestQ.json")) + "]";
				break;
			case "Q+E" :
				System.out.println("Testing Query given Evidence");
				fileContents = "[" + readEntireFile(new File("./A6/assignment06/jResources/TestQnE.json")) + "]";
				break;
			case "Q+E+V" :
				System.out.println("Testing Query given Evidence with set Values");
				fileContents =  "[" + readEntireFile(new File("./A6/assignment06/jResources/TestQnEnV.json")) +  "]";
				break;
			case "grass" :
				testGrass = true;
				System.out.println("Testing regular Bayes net imput");
				fileContents = readEntireFile(new File("./A6/assignment06/jResources/wetgrass.json"));
				nodeMap = Node.nodesFromString(fileContents);
				System.out.println("Bayes Net:");
				System.out.println(nodeMap);
				break;
			case "cGrass" :
				testGrass = true;
				System.out.println("Testing regular Bayes net imput");
				fileContents = readEntireFile(new File("./A6/assignment06/jResources/cloudyGrass.json"));
				nodeMap = Node.nodesFromString(fileContents);
				System.out.println("Bayes Net:");	
				System.out.println(nodeMap);
				break;
			default:
				System.out.println("bad test!");
				break;
			}
			break;
		default:
			System.out.println("bad mode!");
			break;
		}
		if(!testGrass){
			// take in query input
			e = new Event(fileContents); // the prob request
			System.out.println("Request: " + e.toString());
			String bnLoc = readEntireFile(new File("./A6/assignment06/jResources/cloudyGrass.json"));
			nodeMap = Node.nodesFromString(bnLoc);
			System.out.println("Bayes's Net: " + nodeMap.toString());
			System.out.println("Testing Priory Sample: " + "\nNodeMapSize: " + nodeMap.size());
			
			PriorSample prior = new PriorSample(nodeMap);
			prior.runSampling();
			
			//RejectionSampler rejFinal = new RejectionSampler(q, q.getValues(), nodeMap, 1000 );
			//System.out.println(rejFinal.toString());
			
			//print out query output
		}


	}
}
