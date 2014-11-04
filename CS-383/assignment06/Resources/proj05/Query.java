package assignment05;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;


public class Query{

	private ArrayList<String> query;
	private String[][]given;

	private String s;

	public Query(){ // constructor
		query = new ArrayList<String>();
		given = new String[6][0];
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
	
	
	
	public String[] getProbOf(){ 	// probability of... 
		s = query.get(0);
		return s.split(" ");
	}

	public String[] getGiven1(){	// given...
		s = query.get(1);
		return s.split(" ");
	}

	public String[] getGiven2(){ 	// and...
		s = query.get(2);
		return s.split(" ");
	}

	public String[] getGiven3(){	// and...
		s = query.get(3);
		return s.split(" ");
	}

	public String[] getGiven4(){	// and...
		s = query.get(4);
		return s.split(" ");
	}

	public String[] getGiven5(){	// and...
		s = query.get(5);
		return s.split(" ");
	}

	public String[] getGiven6(){
		s = query.get(6);
		return s.split(" ");
	}

	public int numLines(){
		return query.size();
	}

	public String[][] getAllEleLines(){

		int count = 0;
		while(count < numLines()-1){
			switch(count){
			case 0: 
				given[0] = getGiven1();
				count++;
				break;
			case 1:
				given[1] = getGiven2();
				count++;
				break;
			case 2:
				given[2] = getGiven3();
				count++;
				break;
			case 3:
				given[3] = getGiven4();
				count++;
				break;
			case 4:
				given[4] = getGiven5();
				count++;
				break;
			case 5:
				given[5] = getGiven6();
				count++;
				break;
			default:
				break;
			}
		}

		return given;
	}

	public String[] getAllEleStr(){

		StringBuilder sb = new StringBuilder();
		String line;
		String[] s;
		int spaceSub;

		for(int i=1; i<query.size(); i++){
			line = query.get(i);
			spaceSub = line.indexOf(" ");

			if (spaceSub != -1)
				line = line.substring(spaceSub+1);

			sb.append(" ");
			sb.append(line);
		}
		return s = sb.toString().split(" ");
	}

	public int getNumElements(){
		int i = 0;
		String[] e;

		for(String s : query){
			e = s.split(" ");
			i += e.length;
		}

		e = query.get(0).split(" ");
		i -= e.length;
		i -= query.size()-1;

		return i;
	}

	public int getNumAttrib(){
		s = query.get(0);
		String[] a = s.split(" ");
		return a.length;

	}

	public void printQuery(){
		for(String s: query){
			System.out.println(s);
		}

	}
	
	public boolean isValid(){ // does not check for duplicates
		if(getProbOf().length < 1){
			return false;
		}
		if(query.size() > 7){
			return false;
		}
		else 
			return true;
			
	}
}