package A3redo;

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

		miniMax(board);

		//printBoard(); //TODO: for debugging purposes remove after
		System.out.println(Utility(board));
	}

	// returns an action to take
	private Move miniMax(Board b){
		int x =0;
		int o =0;
		// need to figure out who goes next
		if(b.isSingleLine()){
			for(String[]a : b.getBoard()){
				for(String c : a){
					if(c.equals("X")){
						x++;
					}
					if(c.equals("O")){
						o++;
					}
				}
			}
		}
		else{ // not single line
			for(String[]a : b.getBoard()){
				for(String c : a){
					if(c.equals("X")){
						x++;
					}
					else if(c.equals("O")){
						o++;
					}
				}
			}
		}

		if(x > 0){
			maxPlayerTurn = false;
			minMoves = makeMoves(b);
		}
		else{
			maxPlayerTurn = true;
			maxMoves = makeMoves(b);
		}


		Move ret = null;
		int maxValue = 0;
		int value = 0;
		for(Move a : maxMoves){ // needs to return the max action
			if(ret == null){
				ret = a;
				value = ret.getValue();
				maxValue = value;
			}
			else if(a.compareTo(ret) > 0){
				ret = a;
				value = minValue(b.doMove(ret));
				maxValue = Integer.max(value, maxValue);
			}
		}
		return ret;

	}

	// returns a utility value
	private int maxValue(Board b){
		if(terminalTest(b)){
			return Utility(b);
		}

		maxPlayerTurn = true;
		maxMoves = makeMoves(b);

		int value = Integer.MIN_VALUE;
		for(Move a : maxMoves){
			value = Integer.max(value, minValue(b.doMove(a)));
		}
		return value;
	}

	//returns a utility value
	private int minValue(Board b){

		if(terminalTest(b)){
			return Utility(b);
		}

		maxPlayerTurn = false;
		minMoves = makeMoves(b);

		int value = Integer.MAX_VALUE;
		for(Move a : minMoves){
			value = Integer.min(value, maxValue(b.doMove(a)));
		}
		return value;
	}

	private boolean terminalTest(Board b){ // determines if the state is a terminal one.
		int k = b.getK(); //winning if k*X or K*Y in a row, or all filled 

		if(b.checkAllFilled()){
			return true;
		}
		else if(Utility(b) == -1 || (Utility(b) == 0 || (Utility(b) == 1 ))){
			return true;
		}
		else{
			return false;
		}
	}

	private int Utility(Board b){ // returns the win/lose/tie state of the board that has been decided terminal -1, 0 or 1

		int[] maxM = new int[b.getK()*b.getK()];
		int[] minM = new int[b.getK()*b.getK()];
		int minC = 0;
		int maxC = 0;
		int ret = 0;
		if(b.getK() == 3 && b.getIsEmpty()){
			return 0; // 3x3 Maxplayer cannot lose but won't win against a Miniplayer that also plays optimally
		}
		else if(b.getK() == 3){ // focusing only on 3x3// conditions for victory, loss or tie
			if(b.isSingleLine()){

			}
			else{ // not single line format
				for(int i=0; i<b.getK(); i++){ // rowIndex
					for (int c=0; c<b.getK(); c++){ // colIndex
						if(b.getBoard().get(i)[c].equals("X")){
							maxM[maxC] = Integer.parseInt(""+ i + c);
							maxC++;
						}
						else if (b.getBoard().get(i)[c].equals("O")){
							minM[minC] = Integer.parseInt(""+ i + c);
							minC++;
						}
					}
				}
				
				if(maxM.length > minM.length){ //we know the winner is maxM
					ret = 1;
				}
				else if(maxM.length == minM.length){
					ret = 0;
				}
				else{
					ret = -1;
				}
			}
		}
		else{
			int range = (2) + 1;
			ret = (int)(Math.random()* range) - 1;
		}
		return ret;
	}

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
