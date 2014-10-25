package assignment05;

import java.util.*;

public class Car{
	private String buying, maint, doors, persons, lugboot, safety, car;

	public Car(String buying, String maint, String doors, String persons, String lugboot, String safety, String car ){
		this.buying = buying;
		this.maint = maint;
		this.doors = doors;
		this.persons = persons;
		this.lugboot = lugboot;
		this.safety = safety;
		this.car = car;
	}

	public Car(){ //creates an empty car
		this.buying = "";
		this.maint = "";
		this.doors = "";
		this.persons = "";
		this.lugboot = "";
		this.safety = "";
		this.car = "";
	}
	
	//==== get methods ====
	
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
		return lugboot;
	}
	
	public String getSafety(){
		return safety;
	}
	
	public String getCar(){
		return car;
	}
	
}