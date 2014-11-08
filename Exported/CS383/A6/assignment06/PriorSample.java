package assignment06;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

public class PriorSample { 
	Map<String, Node> nodeMap = new HashMap<String,Node>();


	public PriorSample(Map<String,Node> bn){
		nodeMap = bn;
	}

	//Maap<name of node, Node containing
	public Map<String, Node> runSampling() {

		// x <- an event with n elements
		Map<String, Node> x = new HashMap<String, Node>();
		// foreach variable X<sub>i</sub> in X<sub>1</sub>,...,X<sub>n</sub> do
		Random r = new Random();
		Node n;
		double[] probs;
		// hash map: string, Node
		for(Map.Entry Xi : nodeMap.entrySet()){
			System.out.println(Xi.getKey() + " = " + Xi.getValue());
			probs = ((Node) Xi.getValue()).getProbs();
			
			n = new Node((String) Xi.getKey(), null, probs);
			x.put(Xi.getKey(), ); // needs to be a random node
		}
		
		

		
		/*
		 *      // x <- an event with n elements
                Map<RandomVariable, Object> x = new LinkedHashMap<RandomVariable, Object>();
                // foreach variable X<sub>i</sub> in X<sub>1</sub>,...,X<sub>n</sub> do
                for (RandomVariable Xi : bn.getVariablesInTopologicalOrder()) {
                        // x[i] <- a random sample from
                        // <b>P</b>(X<sub>i</sub> | parents(X<sub>i</sub>))
                        x.put(Xi, ProbUtil.randomSample(bn.getNode(Xi), x, randomizer));
                }
                // return x
                return x;
		 */
		// return x
		return x;
	}
	
	public String toString(){
		
		return "";
	}

}
