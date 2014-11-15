package assignment06;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

public class PriorSample { 
	Map<String, Node> nodeMap = new HashMap<String,Node>();

	public PriorSample(Map<String,Node> bn){
		nodeMap = bn;
	}

	//take  random sample from the baye's net
	public Map<String, Event> runSampling() {

		// x <- an event with n elements
		
		ArrayList<Event> x = new ArrayList<Event>();
		// foreach variable X<sub>i</sub> in X<sub>1</sub>,...,X<sub>n</sub> do
		Random r = new Random();
		Event n;
		boolean[] probs;
		
		// generates events randomly
		for(Map.Entry Xi : nodeMap.entrySet()){
			probs = new boolean[((Node) Xi.getValue()).getProbs().length];
			System.out.println("Size of values " + probs.length );
			System.out.println("adding: " + Xi.getKey().toString());
			System.out.println("filling probs with ");
			for(int i=0; i<probs.length; i++){
				probs[i] = r.nextBoolean();
				System.out.print(probs[i] + " ");
			}
			System.out.println();
			System.out.println();
			
			// creating random event with given string
			//lets start with assume only 1 variable, no need to post parents
			n = new Event(Xi.getKey().toString(), null, probs);
			x.add(n); // needs to be a random node
		}
		
		System.out.println("hash is filled, now to iterate through ");
		
		for(Event e: x){ //goes through hashmap of (String, Entries)+
			System.out.println("Names: ");
			for(String s : e.getVariables()){
				System.out.print(s + ",");
			}
			System.out.println();
			System.out.println("parents: " + null);
			System.out.print("values: ");
			for(boolean ev : e.getValues()){
				System.out.print(e.getValues()[1] + "," + e.getValues()[0]);
			}
			System.out.println();
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
		return null;
	}
	
	public String toString(){
		
		return "";
	}

}
