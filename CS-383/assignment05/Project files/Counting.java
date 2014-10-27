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
	private final String[] EMAINT = {"vhigh","high","med","low"}; 	// vhigh","high","med","low"
	private final String[] EDOORS = {"2","3","4","5more"};		// 2","3","4","5more"
	private final String[] EPERSONS = {"2","4","more"};		// 2","4","more"
	private final String[] ELUGBOOT = {"small","med","big"};		// small","med","big"
	private final String[] ESAFETY = {"low","med","high"};		// low","med","high"
	private final String[] ECAR = {"unacc","acc","good","vgood"};		// unacc","acc","good","vgood"

	private int numProb;
	private String[] prob;
	private ArrayList<String>table;
	private double givenSize;

	// takes in number of desired probability attributes as argument
	public Counting(Query q, ArrayList<Car> cl){ // constructor
		numProb = q.getProbOf().length; 
		prob = q.getProbOf();
		table = createTable(q);
		count(cl);
		//counters
	}
	
	private void count(ArrayList<Car> cl){
		double[] countTable = new double[table.size()];
		boolean match = false;
		
		for(int i=0; i<countTable.length; i++){
			countTable[i] = 0;
		}
		int index =0;
		
		double size = cl.size();
		String[] eleArray;
		for(Car c: cl){// all cars
			for(String s: table){ // all joint in table
				eleArray = s.split(" ");
				for(String e: eleArray){ // elements
					for(String p: prob){ // probability of..
						if(c.getThis(p).equals(e)){
							match = true;
						}
					}
				}
				if(match == true){
					countTable[index] += 1;
				}
				match = false;
				index++;
			}
		}
		
		for(int i=0; i<table.size(); i++){
			System.out.println(table.get(i) + " " + countTable[i]);
			
		}
		
		
	}
	
	// just sets the attributes into the table
	// no calculating
	private ArrayList<String> createTable(Query q){
		int comb =1; // should be at least 1 by a check in FJDQuery.run();
		ArrayList<String[]> elements = new ArrayList<String[]>();
		ArrayList<String> temp = new ArrayList<String>();
		String emptyStr= "";
		
		for(String s : prob){
			switch(s){
			case "buying":
				comb *=4;
				elements.add(EBUYING);
				break;
			case "maint":
				comb *=4;
				elements.add(EMAINT);
				break;
			case "doors":
				comb *=4;
				elements.add(EDOORS);
				break;
			case "persons":
				comb *=3;
				elements.add(EPERSONS);
				break;
			case "lug_boot":
				comb *=3;
				elements.add(ELUGBOOT);
				break;
			case "safety":
				comb *=3;
				elements.add(ESAFETY);
				break;
			case "car":
				comb *=4;
				elements.add(ECAR);
				break;
			default:
				break;
			}
		}

		permutations(elements, temp, 0, emptyStr);

		String holder = "";
	
		for(int i=0; i<temp.size(); i++){
			holder = temp.get(0);
			temp.add(holder.substring(1));
			temp.remove(0) ;
		}
		
		for(String str : temp){
			System.out.println(str); // each str in temp has both elements
		}
		return temp;
	}

	void permutations(ArrayList<String[]> elementList, ArrayList<String> dest, int index, String current)
	{
		if(index == elementList.size()){ // this is where everything gets added to dest
			dest.add(current);
			return;
		}

		for(int i=0; i<elementList.get(index).length; ++i){
			permutations(elementList, dest, index+1, current +" "+ elementList.get(index)[i]);
		}
	}


	//==== Getters ====
	public String[] getEBUYING(){
		return EBUYING;
	}
	public String[] getEMAINT(){
		return EMAINT;
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