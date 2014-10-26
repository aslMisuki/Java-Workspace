package assignment05;

import java.util.*;

public class Car{
	private String buying, maint, doors, persons, lugBoot, safety, car;

	
//	   buying       vhigh, high, med, low
//	   maint        vhigh, high, med, low
//	   doors        2, 3, 4, 5-more
//	   persons      2, 4, more
//	   lug_boot     small, med, big
//	   safety       low, med, high
// 	   car			unacc, acc, good, vgood	
	
	public Car(String buying, String maint, String doors, String persons, String lugboot, String safety, String car ){ // constructor
		this.buying = buying;
		this.maint = maint;
		this.doors = doors;
		this.persons = persons;
		this.lugBoot = lugboot;
		this.safety = safety;
		this.car = car;
	}

	public Car(){ //creates an empty car
		this.buying = "";
		this.maint = "";
		this.doors = "";
		this.persons = "";
		this.lugBoot = "";
		this.safety = "";
		this.car = "";
	}
	
	//==== get methods ====
	
	public String getThis(String str){
		
		switch(str){
		case "buying":
			return buying;
		case "maint":
			return maint;
		case "doors":
			return doors;
		case "persons":
			return persons;
		case "lug_boot":
			return lugBoot;
		case "safety":
			return safety;
		case "car":
			return car;
		default:
			return str;
		}
		
	}
	
	
	public String getBuying(){
		return buying;
	}
	
	public String getMaint(){
		return maint;
	}
	
	public String getDoors(){
		return doors;
	}
	
	public String getPersons(){
		return persons;
	}
	
	public String getLugBoot(){
		return lugBoot;
	}
	
	public String getSafety(){
		return safety;
	}
	
	public String getCar(){
		return car;
	}
	
	
	public void printCarInfo(){
		System.out.println("Information for this car: ");
		System.out.println("Buying: " + buying);
		System.out.println("Maintenance: " + maint);
		System.out.println("Doors: " + doors);
		System.out.println("Persons: " + persons);
		System.out.println("lugboot: " + lugBoot);
		System.out.println("safety: " + safety);
		System.out.println("car: " + car);
	}
	
	public void printCarShort(){
		System.out.println(buying + " " + maint + " " + doors + " " + persons + " " + lugBoot + " " + safety + " " + car);
	}
}