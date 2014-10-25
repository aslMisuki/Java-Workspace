package assignment05;

import java.util.*;

public class Query{
	
	private ArrayList<String> query;

	public Query(){ // constructor
		query = new ArrayList<String>();
	}
	
	public void addLine(String line){
		query.add(line);
	}
	
	
	//==== get methods ====
	
	public String getL1(){
		return query.get(0);
	}
	
	public String getL2(){
		return query.get(1);
	}
	
	public String getL3(){
		return query.get(2);
	}
	
	public String getL4(){
		return query.get(3);
	}
	
	public String getL5(){
		return query.get(4);
	}
	
	public String getL6(){
		return query.get(5);
	}
	
	public String getL7(){
		return query.get(6);
	}
	
	public int numLines(){
		return query.size();
	}
	
}