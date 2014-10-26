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
	private String[] eBuying = {"vhigh","high","med","low"}; 	// vhigh, high, med,low
	private String[] eBaint = {"vhigh","high","med","low"}; 	// vhigh","high","med","low"
	private String[] eDoors = {"2","3","4","5more"};		// 2","3","4","5more"
	private String[] ePersons = {"2","4","more"};		// 2","4","more"
	private String[] eLugBoot = {"small","med","big"};		// small","med","big"
	private String[] eSafety = {"low","med","high"};		// low","med","high"
	private String[] eCar = {"unacc","acc","good","vgood	"};		// unacc","acc","good","vgood"

	// counting
	private double[] buying = {0,0,0,0}; 	// vhigh, high, med, low
	private double[] maint = {0,0,0,0,}; 	// vhigh, high, med, low
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
	
	
	// takes in number of desired probability attributes as argument
	public Counting(int num){ // constructor
		

		//counters
	}
	
	
	//==== Getters ====
	
	
	
}