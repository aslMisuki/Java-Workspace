package assignment05;

import java.util.*;

public class Query{
	
	private ArrayList<String> query;

	public Query(){ // constructor
		query = new ArrayList<String>();
	}
	
	//arguments are separated by white spaces
	public void addLine(String line){
		query.add(line);
	}
	
	
	//==== get methods ====
	
	/*
	 * Format: 
	 * 1st	= is probability of each attrib
	 * rest	= given attrib1 element of... etc 
	 *
	 */
	public String getProbOf(){ 	// probability of... 
		return query.get(0);
	}
	
	public String getGiven1(){	// given...
		return query.get(1);
	}
	
	public String getGiven2(){ 	// and...
		return query.get(2);
	}
	
	public String getGiven3(){	// and...
		return query.get(3);
	}
	
	public String getGiven4(){	// and...
		return query.get(4);
	}
	
	public String getGiven5(){	// and...
		return query.get(5);
	}
	
	public String getGiven6(){
		return query.get(6);
	}
	
	public int numLines(){
		return query.size();
	}
	
	public void printQuery(){
		for(String s: query){
			System.out.println(s);
		}
		
	}
	
}