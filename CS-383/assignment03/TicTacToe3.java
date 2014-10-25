package assignment03;

import java.util.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;
import java.util.regex.Pattern;
import java.util.StringTokenizer;

public class TicTacToe {

	private List<Move> maxMoves = new ArrayList<Move>();// remember that max moves = [(k^2)/2] - # already on the board 
	private List<Move> minMoves = new ArrayList<Move>();
	private List<Board> goalStates = new ArrayList<Board>();
	private ArrayList<String[]> strBoard; 
	private Board board;
	private boolean maxPlayerTurn = true; //true for X, and false for O



	private final int WIN = 1;
	private final int LOSE = -1;
	private final int DRAW = 0;

	public TicTacToe (File fileLoc) throws IOException{ //constructor

		strBoard = new ArrayList<String[]>();
		board = new Board();
		parseFile(fileLoc);
		board.setBoard(strBoard);
		if(strBoard.size() == 1){ // if there is only 1 array for the 1 string only representation
			board.setDimSingle(strBoard.get(0).length);
		}
		else{
			board.setDimReg(strBoard.size());
		}
	}

	public void run(){

		
		//		Move moveTest = new Move(0,2,1,board,maxPlayerTurn);
		//		board.doMove(moveTest);

		MiniMax miniMax(board);

		//printBoard(); //TODO: for debugging purposes remove after
		//System.out.println(Utility(board));
	}

	// returns an action to take


	// moves generater
	private List<Move> makeMoves(Board b){
		List<Move> m = new ArrayList<Move>();

		if (terminalTest(b)) {
			return m;   // return empty list
		}

		// Search for empty cells and add to the List
		for (int rowIndex = 0; rowIndex < b.getK(); ++rowIndex) {
			for (int colIndex = 0; colIndex < b.getK(); ++colIndex) {
				if(b.isSingleLine()){
					if (b.getBoard().get(0)[(b.getK()*colIndex) + rowIndex].equals(".")) {
						m.add(new Move(colIndex, rowIndex, b, maxPlayerTurn));
					}
				}
				else {
					if(b.getBoard().get(rowIndex)[colIndex].equals(".")){
						m.add(new Move(colIndex, rowIndex, b, maxPlayerTurn));
					}
				}
			}
		}
		return m;
	}


	//	private void setGoalStates(){
	//		int k = board.getK(); // assume for now 3x3 and without rotation stuff
	//		Board b = new Board();
	//		//Horizontal Goal States
	//		for(int i = k; i < k; i++){
	//			b.getBoard().get(i)[]
	//		}
	//		
	//		//Vertical Goal States
	//		
	//		//Diagonal Goal States
	//		
	//	
	//		
	//		
	//		
	//	}

	//returns an array of the line containing tokens of "X", "O", or "." only and no spaces
	private String[] tokenizer(String str){ //This method needs to tokenize each line and returns it as a String array
		String s = str;
		//System.out.println(s);
		s = s.replace("|", "");
		s = s.replace("+", "");
		s = s.replace("-", "");
		s = s.replace(" ", "");

		String[] tokens = s.split("");

		//				for(String string : tokens){
		//					System.out.print(string);
		//				}

		return tokens;
	}

	private void parseFile(File fileLoc) throws IOException{ // takes in the directory string, and puts them into the data structure; sets dimensions of the board
		Scanner br = new Scanner(fileLoc);
		String line = "";
		String[] temp;

		while(br.hasNext()){
			line = br.nextLine();
			temp = tokenizer(line);
			if(!temp[0].equals("")){
				strBoard.add(temp);
			}
		}
	}

	public void printBoard(){
		System.out.println(board.toString());
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//String fileDir = args[1]; // file dir
		//File file = new File(fileDir);
//		
//		File file = new File("C:/Users/Nam Phan/Dropbox/383 AI/Java workspace/AI/A3/assignment03/testPeriods.txt");
//
//		TicTacToe t = new TicTacToe(file); // creates a 5x5
//		t.run();

		Double a1 = new Double(2);
		Double a2 = new Double(4);
		Double a3 = new Double(6);
		System.out.println(a1 + a2 + a3);
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
