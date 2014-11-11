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
	 */
	private String party; //republican or democrat 
	
    private String[] conditions; //{y,n,? = null;}
    /* 	constructor
     * inputs: list of queryRV, and a list of evidenceRVs 
     * P(demo|evidence)
     */

	public Query(String[] conditions){
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
	
}