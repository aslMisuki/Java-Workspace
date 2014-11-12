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
	 * Stores the Query components for Test.data and Training.data
	 * Test.data
	 */
	private String party; //republican or democrat 
	
    private String[] conditions; //{y,n,? = null;}
    /* 	constructor
     * inputs: list of queryRV, and a list of evidenceRVs 7
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
		if(party != ""){
			st.append(party + ",");
		}
		for(String s : conditions){
			st.append(s + ",");
		}
		
		st.deleteCharAt(st.lastIndexOf(",")); // deletes extra ","
		return st.toString();
	}
	
}