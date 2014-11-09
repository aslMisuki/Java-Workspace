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
	public Map<String, Event> runSampling() {

		// x <- an event with n elements
		Map<String, Event> x = new HashMap<String, Event>();
		// foreach variable X<sub>i</sub> in X<sub>1</sub>,...,X<sub>n</sub> do
		Random r = new Random();
		Event n;
		boolean[] probs;
		// generates events randomly
		for(Map.Entry Xi : nodeMap.entrySet()){
			System.out.println(Xi.getKey() + " = " + Xi.getValue());
			System.out.println(((Node) Xi.getValue()).getProbs().length);
			probs = new boolean[((Node) Xi.getValue()).getProbs().length];
			
			for(int i=0; i<probs.length; i++){
				probs[i] = r.nextBoolean();
			}
			
			System.out.println("Testing Random");
			for(boolean t : probs){
				System.out.println(t);
			}
			
//			n = new Event(Xi.getKey().toString().split(" "), null, probs);
//			x.put(Xi.getKey().toString(),n); // needs to be a random node
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
