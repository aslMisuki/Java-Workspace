package assignment07;

public class BinaryRV{
	
	
	private String name; //demorcrat or republican
	private final boolean[] DOMAIN = {true,false};
	private boolean value;
	
	
	//constructor
	public BinaryRV(String name, boolean value){
		this.name = name;
		this.value = value;
	}
	
	//get methods
	public String getName(){
		return name;
	}
	
	public boolean getValue(){
		return value;
	}
	
	public boolean[] getPosisbleDomain(){
		return DOMAIN;
	}
	
}