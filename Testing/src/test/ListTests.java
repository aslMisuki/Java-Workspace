package test;

public class ListTests {
	
	public ListTests(){
		
	}
	
	public void boolArrayNull(){
		boolean[] t = new boolean[5];
		
		t[0] = true;
		t[1] = false;
		t[2] = false;
		
		for(boolean b : t){
			System.out.print(b + ", ");
		}
		
	}

}
