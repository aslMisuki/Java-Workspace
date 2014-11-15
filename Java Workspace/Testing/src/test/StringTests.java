package test;

//string based tests
public class StringTests {
	
	
	public StringTests(){
	}
	
	public void splitters(){
		// TODO Auto-generated method stub
		
		String str = "hello: hi";
		str = str.replace(":", "").toUpperCase();
		
		String[] strA = str.split(" ");
		
		for(String s: strA){
			System.out.println(s);
		}
	}
}
