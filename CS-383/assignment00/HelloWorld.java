package assignment00;

public class HelloWorld {
	int argSize;
	String[] args;
	
	private HelloWorld(String[] args){ // constructor
		argSize = args.length;
		this.args = args;
	}
	
	private void run(){ // driver
		if(argSize == 0){
			System.out.println("Hello World");
		}
		else{
			System.out.print("Hello");
			for(int i=0; i<argSize;i++){
				System.out.print(" " + args[i]);
			}
		}
	}
	
	
	public static void main(String[] args) { // main

		HelloWorld obj = new HelloWorld(args);
		obj.run();
		//kewl

//		String[] argTest = new String[]{"Nam", "Nate", "Niki", "Liz"};
//		HelloWorld objTest = new HelloWorld(argTest);
//		objTest.run();
		
	}

}
