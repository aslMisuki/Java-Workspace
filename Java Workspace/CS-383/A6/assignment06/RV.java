package assignment06;

//An RV class that contains RV's name, each RV's Domain, and size of Domain[]

public class RV {
	String name;
	
	String[] domain;
	
	
	public RV(String name, String[] domain){
		
	}
	
	public String[] getDomain(){
		return domain;
	}
	
	public int getDomainSize(){
		return domain.length;
	}
	
	public String getName(){
		return name;
	}

}
