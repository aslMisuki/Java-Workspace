package assignment05;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;


public class FJDQuery{

	private ArrayList<Car>carList; // contains car objects. Size should be 1728
	private Query query;
	private ArrayList<Car>givenList;

	// constructor
	public FJDQuery(File file) throws FileNotFoundException{
		carList = new ArrayList<Car>();
		givenList = new ArrayList<Car>();

		//TODO: change to "./car.data" for submission
		File cardata = new File("C:/Users/Nam Phan/Desktop/Repo/Java-Workspace/CS-383/assignment05/Resources/car.data");
		parseFile(cardata, "data"); // fills carList with cars from file
		parseFile(file, "query"); // parses the query file;
		//System.out.println(query.getNumElements());
		//System.out.println(query.getNumAttrib());
	}

	//==== specific methods ====

	//fills the given list to start off
	private void fillGivenList(){
		int attCount = 0; // increases every time we are done with 1 element defaults to 1 to skip over att
		int attrib = query.getNumAttrib(); // # of attributes
		int elements = query.getNumElements(); //# of elements
		String[] allEle = query.getAllEleStr(); // all elements
		String[][] allEleLine = query.getAllEleLines();
		String att,ele;
		int counter=0; // counts the cars as we interate through them
		boolean sameCount=false;


		//		for(String[] a: allEleLine){
		//			for(String b: a){
		//				System.out.print(b + " ");
		//			}
		//			System.out.println();
		//		}

		// go through list and grab all cars that have 1 element to be filtered later

		for(Car c : carList){ // goes through each car in carList
			att = allEleLine[0][0]; // starting with just the first att
			for(int i=0; i<allEleLine[0].length-1;i++){ // length of first arg
				if(c.getThis(att).equals(allEleLine[0][i+1])){
					givenList.add(c);
				}
			}
		}
		attCount++;
		System.out.println(query.getNumAttrib());
		for(int a = attCount; a<query.getNumAttrib(); a++){
			for(Car c : carList){ // goes through each car in carList
				att = allEleLine[a][0]; 
				
				for(int i=1; i<=allEleLine[a].length-2;i++){ // through all elements

					if(c.getThis(att).equals(allEleLine[a][i]) == false){
						System.out.println("Comparing "+c.getThis(att) + " and " + allEleLine[a][i]);
//						System.out.print("removing: ");
//						givenList.get(counter).printCarShort();
						sameCount=false;
						//System.out.println(givenList.size());
					}
					else{
						sameCount=true;
					}

				}
				if(sameCount == false){
					givenList.remove(counter);
					counter--;
				}
				counter++; // counts the index for givenList;
			}
		}


			int countp =0;
			for(Car a: givenList){
				System.out.print(countp+ " ");
				a.printCarShort();
				countp++;
			}
	}

	//==== standard methods ====

	/* "car" only outputs
	 *  unacc 0.70023
	 *	acc 0.22222
	 *	good 0.03993
	 *  vgood 0.03762
	 */
	private void run(){ // runs the program
		//TODO: Testing
		query.printQuery();
		fillGivenList();


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
				carList.add(car);
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

		String mode = "local3Lines"; //"localSingle" or "local3Lines" or "edlab"
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