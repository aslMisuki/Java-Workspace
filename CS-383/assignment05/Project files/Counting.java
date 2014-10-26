package assignment05;

import java.util.*;


public class Counting{
	
//	   buying       v-high, high, med, low
//	   maint        v-high, high, med, low
//	   doors        2, 3, 4, 5-more
//	   persons      2, 4, more
//	   lug_boot     small, med, big
//	   safety       low, med, high
//	   car			unacc, acc, good, vgood	
	
	// element of
	private final String[] EBUYING = {"vhigh","high","med","low"}; 	// vhigh, high, med,low
	private final String[] EBAINT = {"vhigh","high","med","low"}; 	// vhigh","high","med","low"
	private final String[] EDOORS = {"2","3","4","5more"};		// 2","3","4","5more"
	private final String[] EPERSONS = {"2","4","more"};		// 2","4","more"
	private final String[] ELUGBOOT = {"small","med","big"};		// small","med","big"
	private final String[] ESAFETY = {"low","med","high"};		// low","med","high"
	private final String[] ECAR = {"unacc","acc","good","vgood"};		// unacc","acc","good","vgood"

	// counting
	private double[] buying = {0,0,0,0}; 	// vhigh, high, med, low
	private double[] maint = {0,0,0,0}; 	// vhigh, high, med, low
	private double[] doors = {0,0,0,0};		// 2, 3, 4, 5more
	private double[] persons = {0,0,0};		// 2, 4, more
	private double[] lugBoot = {0,0,0};		// small, med, big
	private double[] safety = {0,0,0};		// low, med, high
	private double[] car = {0,0,0,0};		// unacc, acc, good, vgood	
	
	//distributions
	private double[] dBuying = {0,0,0,0};	// v-high, high, med, low
	private double[] dMaint = {0,0,0,0};	// v-high, high, med, low
	private double[] dDoors = {0,0,0,0};	// 2, 3, 4, 5more
	private double[] dPersons = {0,0,0};	// 2, 4, more
	private double[] dLugboot = {0,0,0};	// small, med, big
	private double[] dSafety = {0,0,0};		// low, med, high
	private double[] dcar = {0,0,0,0};		// unacc, acc, good, vgood0
	
	
	private int numProb;
	private String[] prob;
	private String[][] table;
	
	// takes in number of desired probability attributes as argument
	public Counting(Query q, ArrayList<Car> c){ // constructor
		numProb = q.getProbOf().length; 
		prob = q.getProbOf();
		table = createTable(q);
		//counters
	}
	// just sets the attributes into the table
	private void popTable(){ 
		
	}
	
	private String[][] createTable(Query q){
		int comb =1; // should be at least 1 by a check in FJDQuery.run();
		for(String s : prob){
			switch(s){
				case "buying":
					comb *=4;
					break;
				case "maint":
					comb *=4;
					break;
				case "doors":
					comb *=4;
					break;
				case "persons":
					comb *=3;
					break;
				case "lug_boot":
					comb *=3;
					break;
				case "safety":
					comb *=3;
					break;
				case "car":
					comb *=4;
					break;
				default:
					break;
			}
		}
		return new String[comb][numProb+1]; // +1 for the distribution
	}
	
	//==== Getters ====
	public String[] getEBUYING(){
		return EBUYING;
	}
	public String[] getEBAINT(){
		return EBAINT;
	}
	public String[] getEDOORS(){
		return EDOORS;
	}
	public String[] getEPERSONS(){
		return EPERSONS;
	}
	public String[] getELUGBOOT(){
		return ELUGBOOT;
	}
	public String[] getESAFETY(){
		return ESAFETY;
	}
	public String[] getECAR(){
		return ECAR;
	}

	
}