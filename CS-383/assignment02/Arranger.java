//package assignment02;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

public class Arranger {
	
	private String[][] wall;
	private int rowDim; // number of rows on wall
	private int colDim; // number of columns on wall
	private ArrayList<String[]> paintings;
	private ArrayList<String[]> sortedPaintings;
	private ArrayList<String[]> usedPaintings;
	
	
	public Arranger(File file, String eval) throws IOException{ // constructor
		
		paintings = new ArrayList<String[]>();
		sortedPaintings = new ArrayList<String[]>();
		usedPaintings = new ArrayList<String[]>();
		parseFile(file);
		wall = new String[rowDim][colDim]; //creates wall
		fillBoard();
	}
	
	private void fillBoard(){
		for(int i=0; i<rowDim; i++){
			for(int b=0; b<colDim; b++){
				wall[i][b] = ".";
			}
		}
	}
	
	public void run(String eval){
		
		if(eval.equals("coverage")){
			coverageMethod(paintings,0,0,0);
		}
		else if(eval.equals("bigger")){
			biggerMethod(paintings,0,0,0);
		}
		else{
			System.out.println("Wrong Input");
		}
		print();
	}
	
	private void print(){ // prints the result: paintName paintM paintN indexM indexN
		System.out.println(colDim + " " + rowDim); // dimensions of the wall
		String paintName = "";
		int paintM = 0;
		int paintN = 0;
		int mIndex = 0;
		int nIndex = 0;
		
		for(int i=0; i<usedPaintings.size();i++){
			paintName = usedPaintings.get(i)[0];
			paintM = Integer.parseInt(usedPaintings.get(i)[1]);
			paintN = Integer.parseInt(usedPaintings.get(i)[2]);
			mIndex = Integer.parseInt(usedPaintings.get(i)[3]);
			nIndex = Integer.parseInt(usedPaintings.get(i)[4]);
			System.out.print(paintName +" "+ paintM +" "+ paintN +" "+ mIndex +" "+ nIndex + "\n");
		}
	}
	
	private void printSorted(){
		for(int i=0; i<sortedPaintings.size(); i++){
			for(int b=0; b<3; b++){
				System.out.print(sortedPaintings.get(i)[b] + " ");
			}
			System.out.println();
		}
	}
	
	private void printWall(){
		for(int i=0; i<rowDim; i++){
			for(int b=0; b<colDim; b++){
				System.out.print(wall[i][b] + " ");
			}
			System.out.println();
		}
	}
	
	private ArrayList<String[]> biggerSort(ArrayList<String[]> list){ //analyzes and sorts the list of paintings by area
		//TODO
		//sorts paintings into sortedPaintings
		if(list.isEmpty()){
			return list;
		}
		String[] biggest = list.get(0);
		ArrayList<String[]> ret = new ArrayList<String[]>();
		int biggestIndx = 0;
		boolean T= true;
		int size = list.size();

		while(size>0){ // only compare to paintings list
			for(int i=1; i<list.size();i++){
				if(Math.pow((double)Math.max(Integer.parseInt(biggest[1]),Integer.parseInt(biggest[2])),(double)2) < Math.pow((double)Math.max(Integer.parseInt(list.get(i)[1]),Integer.parseInt(list.get(i)[2])),(double)2)){
					biggest = list.get(i);
					biggestIndx = i;
				}
			}

			ret.add(biggest);
			list.remove(biggestIndx);
			size--;
			if(!list.isEmpty()){
				biggest = list.get(0);	
				biggestIndx = 0;
			}
		}
		sortedPaintings = ret;
		return ret;
		
	}
	
	private ArrayList<String[]> coverSort(ArrayList<String[]> list){ //analyzes and sorts the list of paintings by area
		//sorts paintings into sortedPaintings
		if(list.isEmpty()){
			return list;
		}
		String[] biggest = list.get(0);
		ArrayList<String[]> ret = new ArrayList<String[]>();
		int biggestIndx = 0;
		boolean T= true;
		int size = list.size();

		while(size>0){ // only compare to paintings list
			for(int i=1; i<list.size();i++){
				if(Integer.parseInt(biggest[1])*Integer.parseInt(biggest[2]) < Integer.parseInt(list.get(i)[1])*Integer.parseInt(list.get(i)[2])){
					biggest = list.get(i);
					biggestIndx = i;
				}
			}

			ret.add(biggest);
			list.remove(biggestIndx);
			size--;
			if(!list.isEmpty()){
				biggest = list.get(0);	
				biggestIndx = 0;
			}
		}
		sortedPaintings = ret;
		return ret;
	}
	
	private void biggerMethod(ArrayList<String[]> list, int rowIdx, int colIdx, int count){ // f = Assign each painting a value equal to the square of the smaller of its two dimensions. use greedy
		//TODO
		ArrayList<String[]> sorted = biggerSort(list);
		sortedPaintings = sorted;
		hangAll(sorted,0, 0, 0);
	}
	
	private void coverageMethod(ArrayList<String[]> list, int rowIdx, int colIdx, int count){
	//For a given arrangement, f is equal to the number of square units of the wall that are covered by paintings.
		ArrayList<String[]> sorted = coverSort(list);
		sortedPaintings = sorted;
		hangAll(sorted,0, 0, 0);
	}
		
	private void hangAll(ArrayList<String[]> list, int rowIdx, int colIdx, int count){
		if(count == 3){ // how many times to iterate through the wall to fill in
			return;
		}
		
		ArrayList<String[]> skipped = new ArrayList<String[]>();
		ArrayList<String[]> sorted = list;
		int colUnitsLeft = colDim;
		int rowUnitsLeft = rowDim;
		int colIndex = colIdx;
		int rowIndex = rowIdx;
		boolean set = false;
		int c = count;

		int a = 0;
		for(int i=0; i<rowDim; i++){
			for(int b=0; b<colDim; b++){
				if(a < sorted.size()){
					//System.out.println("PSize: " + sortedPaintings.size());
					//System.out.println("a: " + a);
					if(placePainting(sorted.get(a), i, b) == true){ // moves to next painting if we successfully hang a painting
						a++;
					}
					if( wall[i][b].equals( "." ) && set == false){ // saves a coordinate to come back to
						colIndex = b;
						rowIndex = i;
						set = true;
					}
					
					if(i == rowDim-1 && b == colDim-1){
						for(int z=a; z<sorted.size();z++){
							skipped.add(sorted.get(z));
						}

						if(skipped.size() == sorted.size()){
							skipped.remove(0);
						}
					}
				}
			}
		}
		c++;
		hangAll(skipped, rowIndex, colIndex, c);
	}
	
	/*
	 * Takes the string[] that has paint name, and its dimensions, wallRowDim and wallN are the coordinate to insert the paint.
	 */
	private boolean placePainting(String[] painting, int wallRowIndex, int wallColIndex){ // hangs a painting into the wall
		int paintColDim = Integer.parseInt(painting[1]); // dimension of col
		int paintRowDim = Integer.parseInt(painting[2]); // dimension of row
		String[] used = new String[5];
		String paintName = painting[0];
		if(wallRowIndex+paintRowDim > rowDim || wallColIndex+paintColDim> colDim){
			return false;
		}
		
		if(wall[wallRowIndex][wallColIndex] == "." ){
			for(int i=0; i<paintRowDim; i++){ //searches the col available
				for (int b=0; b<paintColDim; b++){//searches the row available
					if(wall[i+wallRowIndex][b+wallColIndex] != "."){
						return false;
					}
				}
			}

			for(int i=0; i<paintRowDim; i++){ 
				for(int b=0; b<paintColDim; b++){
					wall[i+wallRowIndex][b+wallColIndex] = paintName;
				}
			}
			
			System.arraycopy(painting, 0, used, 0, painting.length);
			used[3] = Integer.toString(wallColIndex);
			used[4] = Integer.toString(wallRowIndex);
			usedPaintings.add(used);
			return true;
		}
		return false;
	}
	
	private String[] tokenizer(String str){ //This method needs to tokenize each line and returns it as a String array
		String s = str;
		ArrayList<String> strList = new ArrayList<String>(); 
		StringTokenizer token = new StringTokenizer(s);
		
		while (token.hasMoreTokens()){
			System.out.println(s);
			strList.add(token.nextToken());
		}
		String arrayRet[] = new String[strList.size()];
		return strList.toArray(arrayRet);
	}
	
	
	private void parseFile(File fileLoc) throws IOException{ // takes in the directory string, and puts them into the data structure; sets dimensions of the board
		Scanner br = new Scanner(fileLoc);
		String line = "";
		String[] temp;
		
		while(br.hasNext()){
			line = br.nextLine();
	        temp = tokenizer(line);
	        
	        if(Pattern.matches("[a-zA-Z)]+", temp[0])){ // if is a letter
	        	paintings.add(temp);
	        }
	        else{
	        	colDim = Integer.parseInt(temp[0]); //col
	            rowDim = Integer.parseInt(temp[1]); //row
	        }
        }
//		System.out.println("Wall Dimensions: m = " + m + " n = " + n);
//		System.out.println("# of Paintings = " + paintings.size());
	}
	
	public static void main(String[] args) throws IOException {
		//String fileDir = args[1]; // file dir
		//String method = args[0]; //"coverage" or "bigger"
		//File file = new File(fileDir);
		
		//File file = new File("C:/Users/Nam Phan/Dropbox/383 AI/Java workspace/AI/A2/assignment02/test.txt");
		File file = new File ("./test.txt");
		String method = "bigger";
		
		
		
		Arranger arge = new Arranger(file, method);
		arge.run(method);
		/*
		if(file.canRead()){
			System.out.println("file can be read");
		}
		else{
			System.out.println("nope");
		}
		*/
	}

}
