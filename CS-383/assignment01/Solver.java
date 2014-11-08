package assignment01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;
// solves the 
import java.util.regex.Pattern;

public class Solver {
		

		private Solver sol;
		private String line;
		private ArrayList<String[]> board;
		private ArrayList<String[]> moves;
		private int n; // number of rows
		private int m; // number of columns
		private int thisRow;
		private int thisCol;
		private String place;
		private boolean valid;
		
		
		
		public Solver(File fileLoc) throws IOException{ // constructor
			board = new ArrayList<String[]>();
			moves = new ArrayList<String[]>();
			valid = false;
			n=0;
			m=0;
			parseBoard(fileLoc);
			place = "";
		}
		
		private void solveRun(){ // comes up with a solution for the input board and returns the moves required
			//int curCol = 0;
			//int curRow = 0;
			thisRow = 0;
			thisCol = 0;
			int tarRow = 0;
			String move = "";
			//ArrayList<String> moveList= new ArrayList<String>();
			while(rowValid(thisRow) == false){
				for(int i=0; i<n; i++){
					if(Integer.parseInt(board.get(thisRow)[thisCol]) > thisRow ){
						move = "u";
					}
					else if(Integer.parseInt(board.get(thisRow)[thisCol]) < thisRow){
						move = "d";
					}
					tarRow = Integer.parseInt(board.get(thisRow)[thisCol]);
					dfsAlg(moves, move, thisRow, thisCol);
					thisCol++;
				}
				thisRow++;
			}
			
			String[] str = new String[moves.size()];
			for(int i=0; i<moves.size();i++){
				System.out.println(i);
			}
			
		/*	
			while(isValid() == false){
				solveAlg(row,moves);
			}
		*/
		
		}
		
		private ArrayList<String[]> dfsAlg(ArrayList<String[]> moves, String move, int curRow, int curCol){
			
			String stMove = "";
			String[] moveArray = new String[1];
			
			while(curRow != Integer.parseInt(board.get(curRow)[curCol])){
				if(move.equals("d")){
					if(curRow == n-1 || Integer.parseInt(board.get(curRow+1)[curCol]) == curRow){
						goR(curRow);
						stMove = "r " + curRow;
					}
					goD(curCol);
					stMove += "d " + curCol;
					
					if(curRow != n-1){
						if(curCol != 0){
							dfsAlg(moves, move, curRow+1, curCol+1);
						}
						else{
							dfsAlg(moves, move, curRow+1, m);
						}
				
					}
					else{
						if(curCol != 0){
							dfsAlg(moves, move, 0, curCol+1);
						}
						else{
							dfsAlg(moves, move, 0, m);
						}
					}
					moveArray[0] = stMove;
					moves.add(moveArray);
				}
				else if(move.equals("u")){
					if(curRow == 0 || Integer.parseInt(board.get(curRow-1)[curCol]) == curRow){
						goR(curRow);
						stMove = "r " + curRow;
					}
					goU(curCol);
					stMove += "u " + curCol;
					
					if(curRow != n-1){
						if(curCol != m-1){
							dfsAlg(moves, move, curRow-1, curCol-1);
						}
						else{
							dfsAlg(moves, move, curRow-1, 0);
						}
				
					}
					else{
						if(curCol != m-1){
							dfsAlg(moves, move, 0, curCol-1);
						}
						else{
							dfsAlg(moves, move, 0, 0);
						}
					}
					moveArray[0] = stMove;
					moves.add(moveArray);
				}
			}
			return moves;			
		}
		
		
		private boolean rowValid(int row){ // just validates a single row
			for(int i=0; i<m; i++){
				if(Integer.parseInt(board.get(row)[i]) != row){
					return false;
				}
			}
			
			return true;
		}
		
		public boolean validRun(){ // runs the moves for validity
			int num;
			String move;
			
			if(moves.isEmpty()){
				return isValid();
			}
			else{
				for(int i=0; i<moves.size(); i+=2){
					num = Integer.parseInt(moves.get(i)[i+1]);
					move = moves.get(i)[0];
					
					if(move.equals("d")){
						goD(num);
					}
					else if(move.equals("u")){
						goU(num);
					}
					else if(move.equals("r")){
						goR(num);
					}
					else if(move.equals("l")){
						goL(num);
					}
				}
				
				return isValid();
			}
			
		}
		
		private boolean isValid(){ // checks the current state to see if it is valid or not; returns a boolean
			for(int i=0; i<n; i++){
				for(int b=0; b<m; b++){
					if(Integer.parseInt(board.get(i)[b]) != i){
						return false;
					}
				}
			}
			
			return true;
		}
		
		private void parseBoard(File fileLoc) throws IOException{ // takes in the directory string, and puts them into the data structure; sets dimensions
			Scanner br = new Scanner(fileLoc);
			String line = "";
			String[] temp;
			
			while(br.hasNext()){
				line = br.nextLine();
		        temp = tokenizer(line);
		        
		        if(Pattern.matches("[a-zA-Z)]+", temp[0])){ // won't work if dimension is mx1, fix later for now it will work for everything else
		        	moves.add(temp);
		        }
		        else{
		        	board.add(temp);
		        }
	        }
			m = (board.get(0)).length; // gets the column dimension
			n = board.size(); // gets the row dimension
		}
		
		public static boolean isNumeric(String inputData) {
			  return inputData.matches("[-+]?\\d+(\\.\\d+)?");
			}
		
		private String[] tokenizer(String str){ //This method needs to tokenize each line and returns it as a String array
			String s = str;
			ArrayList<String> strList = new ArrayList<String>(); 
			StringTokenizer token = new StringTokenizer(s);
			
			while (token.hasMoreTokens()){
				strList.add(token.nextToken());
			}
			String arrayRet[] = new String[strList.size()];
			return strList.toArray(arrayRet);
		}
		
		public void goL(int row){ // rotates left; takes in row #
			place = board.get(row)[0];
			for(int i=0; i<m - 1;i++){
				board.get(row)[i] = board.get(row)[i+1];
			}
			board.get(row)[m - 1] = place;
		}
		
		public void goR(int row){ // rotates right takes in row #
			place = board.get(row)[m - 1];
			for(int i=m-1; i>0;i--){
				board.get(row)[i] = board.get(row)[i-1];
			}
			board.get(row)[0] = place;
		}
		
		public void goU(int col){ // rotates up takes in col #
			place = board.get(0)[col];
			for(int i=0; i<n - 1;i++){
				board.get(i)[col] = board.get(i+1)[col];
			}
			board.get(n-1)[col] = place;
		}
		
		public void goD(int col){ // rotates down takes in col #
			place = board.get(n-1)[col];
			for(int i=n-1; i>0;i--){
				board.get(i)[col] = board.get(i-1)[col];
			}
			board.get(0)[col] = place;
		}
		
		public void printBoard(){ // prints the current state of the board
			//System.out.println("prints the board current");
			for(int i=0; i<n; i++){
				for(int b=0; b<m; b++){
					System.out.print(board.get(i)[b] + " ");
				}
				System.out.println();
			}
		}
		
		public void printMoves(){ // prints the moves for this board
			//System.out.println("prints the moves");
			for(int i=0; i<moves.size(); i++){
				System.out.println(moves.get(i)[0]);
			}
		}
		
		public static void main(String args[]) throws IOException{
			
			//String fileDir = args[0];
			//File file = new File(fileDir);
			
			File file = new File("C:/Users/Nam Phan/Dropbox/Programming stuff/workspace/AI/src/assignment01/testSolver.txt");
			/*
			if(file.canRead()){
				System.out.println("file can be read");
			}
			else{
				System.out.println("nope");
			}
			*/
			
			Solver sol = new Solver(file);
			sol.printBoard();
			sol.solveRun();
			sol.printMoves();
		}
}
