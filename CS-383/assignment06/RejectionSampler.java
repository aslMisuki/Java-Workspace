package assignment06;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by liberato on 10/25/14.
 */
public class RejectionSampler {

	public RejectionSampler(Query Query, boolean[] values, Map<String, Node> bn, int N ){ //constructor
		int[] count;
		Node x;
		for(int j =1; j<N; j++){
			x = priorSampler(bn);
		}

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

	public Node priorSampler(Map<String, Node> bn){

		Random randomizer = null;
		

		Map<String, Node> x = new LinkedHashMap<String, Node>();
		// foreach variable X<sub>i</sub> in X<sub>1</sub>,...,X<sub>n</sub> do
		for (String Xi : bn) {
			// x[i] <- a random sample from
			// <b>P</b>(X<sub>i</sub> | parents(X<sub>i</sub>))
			
			Xi.getCPD().getSample(r.nextDouble(), getEventValuesForParents(Xi, event));
			
			x.put(Xi, ProbUtil.randomSample(bn.getNode(Xi), x, randomizer));
		}
		// return x
		return x;


		//		Node x;
		//		for()
		//		//if(x[i]) = randomsample fropm P(Xi|p(Xi)
		//		}
		//		return x;
		//		
		//		return null;
		//	}
	}

	public static void main(String[] args) {
		String mode = "IDE"; // "shell" or "IDE"
		String fileContents = "";
		Map<String, Node> nodeMap;
		Query q;
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
				fileContents = "[" + readEntireFile(new File("C:/Users/Nam Phan/Desktop/Repo/Java-Workspace/CS-383/assignment06/TestQ.json")) + "]";
				break;
			case "Q+E" :
				System.out.println("Testing Query given Evidence");
				fileContents = "[" + readEntireFile(new File("C:/Users/Nam Phan/Desktop/Repo/Java-Workspace/CS-383/assignment06/TestQnE.json")) + "]";
				break;
			case "Q+E+V" :
				System.out.println("Testing Query given Evidence with set Values");
				fileContents =  "[" + readEntireFile(new File("C:/Users/Nam Phan/Desktop/Repo/Java-Workspace/CS-383/assignment06/TestQnEnV.json")) +  "]";
				break;
			case "grass" :
				testGrass = true;
				System.out.println("Testing regular Bayes net imput");
				fileContents = readEntireFile(new File("C:/Users/Nam Phan/Desktop/Repo/Java-Workspace/CS-383/assignment06/wetgrass.json"));
				nodeMap = Node.nodesFromString(fileContents);
				System.out.println("Bayes Net:");
				System.out.println(nodeMap);
				break;
			case "cGrass" :
				testGrass = true;
				System.out.println("Testing regular Bayes net imput");
				fileContents = readEntireFile(new File("C:/Users/Nam Phan/Desktop/Repo/Java-Workspace/CS-383/assignment06/cloudyGrass.json"));
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
			q = new Query(fileContents);
			System.out.println(q.toString());
		}


	}
}
