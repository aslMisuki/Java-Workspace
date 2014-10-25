package assignment05;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;


public class FJDQuery{

	private ArrayList<Car>carlist; // contains car objects

	public FJDQuery(File file) throws FileNotFoundException{ // constructor
		carlist = new ArrayList<Car>();
		File cardata = new File("C:/Users/Nam Phan/Desktop/Repo/Java-Workspace/CS-383/assignment05/Resources/car.data");
		parseFile(cardata, "data"); // fills carList with cars from file
		parseFile(file, "query"); // parses the query file;
		
	}


	//==== standard methods ============

	private void run(){ // runs the program

	}

	// reads line by line and add them to an arrayList to be returned
	//takes in a File and either "query" or "data" as arguments
	private void parseFile(File file, String mode) throws FileNotFoundException{ 

		Scanner br = new Scanner(file);
		String line = "";
		String[] temp;
		Car car;
		Query query = new Query();

		
		while(br.hasNext()){
			line = br.nextLine();
			
			
			switch(mode){
				case "query":
					query.addLine(line);
					break;
					
				case "data":
					temp = tokenizer(line);
					car = new Car(temp[0],temp[1],temp[2], temp[3], temp[4], temp[5], temp[6]);
					carlist.add(car);
					break;
					
				default:
					System.out.println("bad mode passed into parseFile()");
					break;
			}

		}
	}
	//car.data format: (buying, maint, doors, persons, lug_boot, safety, car)

	private String[] tokenizer(String str){ //This method needs to tokenize each line and returns it as a String array
		String s = str;
		s = s.replace(" ", ",");
		String[] tokens = s.split(",");

		//for(String string : tokens){System.out.print(string);} //test print
		return tokens;
	}




	public static void main(String args[]) throws IOException{

		String mode = "Local"; //Local Edlab
		File file = null;

		switch(mode){
		case "Local" : 
			file = new File("C:/Users/Nam Phan/Desktop/Repo/Java-Workspace/CS-383/assignment05/Project files/test.txt");
			break;

		case "Edlab" :
			String fileDir = args[0];
			file = new File(fileDir);
			break;
		default: 
			System.out.println("bad mode");
			break;
		}

		if(file.canRead()){
			//System.out.println("file can be read");
			FJDQuery Cars = new FJDQuery(file);
			Cars.run();
		}
		else{
			System.out.println("Bad File or Directory");
		}





	}
}