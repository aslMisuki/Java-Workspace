package assignment04;

public class Board {
	
    private int[] boardState;
    //X = set of variables
    //D = set of domains for each variable which are allowable values for variable Xi
    //C = set of constraints that specify allowable combinations of values
    
    int[] X; // variables
    int[] D; // Domains for Each Y
    int[] C; 	// Constraints <scope,rel> where scope is a tuple of variables that participate in the constraint
    			//rel = a relation that defines the values that those variables can take on.
    				// which can be an explicit list of all tuples of values that satisfy the constraint
    				// or as an abstract relation that supports two operations: 
    				//1. testing if a tuple is a member of the relation and 2. enumerating the members of the relation
    
	public Board(int[] boardState) { // constructor
    	
        
    }
    

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int c : boardState) {
            sb.append(c);
        }
        return sb.toString();
    }
}
