package assignment05;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;


public class FJDQuery{

	private ArrayList<Car>carlist; // contains car objects. Size should be 1728
	private Query query;
	private ArrayList<Car>givenList;
	
	// constructor
	public FJDQuery(File file) throws FileNotFoundException{
		carlist = new ArrayList<Car>();
		givenList = new ArrayList<Car>();
		
		//TODO: change to "./car.data" for submission
		File cardata = new File("C:/Users/Nam Phan/Desktop/Repo/Java-Workspace/CS-383/assignment05/Resources/car.data");
		parseFile(cardata, "data"); // fills carList with cars from file
		parseFile(file, "query"); // parses the query file;
		
	}


	//==== standard methods ============

	/* "car" only outputs
	 *  unacc 0.70023
	 *	acc 0.22222
	 *	good 0.03993
	 *  vgood 0.03762
	 */
	private void run(){ // runs the program
		//TODO: Testing
		int count = 0;
		double[] car = {0.0,0.0,0.0,0.0}; // ordered :  unacc, acc, good, vgood
		
		for(Car c : carlist){
			switch(c.getCar()){
			case "unacc":
				car[0] += 1;
				break;
			case "acc":
				car[1] += 1;
				break;
			case "good":
				car[2] += 1;
				break;
			case "vgood":
				car[3] += 1;
				break;
			default:
				break;
			}
		}
		System.out.println("unacc: " + car[0]/1728);
		System.out.println("acc: " + car[1]/1728);
		System.out.println("good: " + car[2]/1728);
		System.out.println("vgood: " + car[3]/1728);
		
		
	}

	// reads line by line and add them to an arrayList to be returned
	//takes in a File and either "query" or "data" as arguments
	private void parseFile(File file, String mode) throws FileNotFoundException{ 

		Scanner br = new Scanner(file);
		String line = "";
		String[] temp;
		Car car;
		query = new Query();
		
		while(br.hasNext()){
			line = br.nextLine();
			switch(mode){
				case "query":
					query.addLine(line);
					break;
					
				case "data":
					temp = tokenizer(line, mode);
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

	private String[] tokenizer(String str, String mode){ //This method needs to tokenize each line and returns it as a String array
		String s = str;
		String[] tokens;
		if(mode.equals("query")){
			return tokens = s.split(" ");
			
		}
		else{
			return tokens = s.split(",");
		}
	}


	public static void main(String args[]) throws IOException{

		String mode = "localSingle"; //"localSingle" or "local3Lines" or "edlab"
		File file = null;

		switch(mode){
		case "localSingle" : 
			file = new File("C:/Users/Nam Phan/Desktop/Repo/Java-Workspace/CS-383/assignment05/Project files/testSingle.txt");
			break;
		case "local3Lines":
			file = new File("C:/Users/Nam Phan/Desktop/Repo/Java-Workspace/CS-383/assignment05/Project files/test3Lines.txt");
			break;
			
		case "edlab" :
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