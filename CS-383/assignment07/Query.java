package assignment07;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Query{
	/*
	 * Stores the Query components
	 * republican,n,n,n,y,y,y,n,n,n,n,n,y,y,y,n,n
	 * democrat,n,y,y,n,y,y,y,n,y,y,y,n,y,y,n,y
	 * republican,n,n,n,y,y,y,n,n,n,y,n,?,y,y,n,?
	 */
	private String party; //republican or democrat 
	
    private String[] conditions; //{y,n,? = null;} party is included in the array... too lazy to clone another array
    /* 	constructor
     * inputs: list of queryRV, and a list of evidenceRVs 
     * P(demo|evidence)
     */

	public Query(String party, String[] conditions){
		this.party = party; 
		this.conditions = conditions;
	}
	
	//setter
	public void setParty(String s){
		party = s;
	}
	
	//getters
	public String[] getConditions(){
		return conditions;
	}
	
	public String getParty(){
		return party;
	}
	
	//used for testing
	public String toString(){
		StringBuilder st = new StringBuilder();
		st.append("Printing Query data:\n");
		
		for(String s : conditions){
			st.append(s + ",");
		}
		
		st.deleteCharAt(st.length()-1);
		return st.toString();
	}
	
}