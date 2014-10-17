package assignment03;


import java.util.*;
/*
 * 
 */
public class Board { // creates and fills the board

	private int colDim;
	private int rowDim;
	private int k;
	private boolean isEmpty;
	
    private final int[][] victoryIndices = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, 	//horizontal # of horizontal wins = 
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, 	// vertical
            {0, 4, 8}, {2, 4, 6}};				// diagonal
    
    public static final char MAX_PLAYER = 'X';
    public static final char MIN_PLAYER = 'O';
    public static final char UNPLAYED = '.';
    
	private char[] boardState;

	private ArrayList<String[]> board;

	public Board(){
		colDim = 0;
		rowDim = 0;
		k = 0;
		board = new ArrayList<String[]>();
		isEmpty = true;

	}

	//performs the move located in the input argument: m
	public Board doMove(Move m){
		String p = "";
		if(m.getPlayerTurn() == true){p = "X";}
		else{p = "O";}

		if(!isSingleLine){ // if not single string representation
			board.get(m.getYIndex())[m.getXIndex()] = p; 
		}
		else{
			board.get(0)[(k*m.getYIndex()) + m.getXIndex()] = p;
		}
		return this;
	}

	public void checkEmpty(){ //TODO need to implement this
		if(board.size() == 0){
			isEmpty = true;
		}

		for(String[] strA : board){
			for(String str : strA ){
				if(!str.equals(".")){
					isEmpty = false;
				}
			}
		}
	}

	public boolean checkAllFilled(){
		if(board.size() == 0){
			return true;
		}

		for(String[] strA : board){
			for(String str : strA ){
				if(!str.equals("X") || !str.equals("Y")){
					return false; // still moves left
				}
			}
		}
		return true;
	}

	private void createEmptyBoard(){
		for(int i=0; i<rowDim; i++){
			for(int b=0; b<colDim; b++){
				board.get(i)[b] = ".";
			}
		}
		isEmpty = true;
	}

	public int getK(){
		return k;
	}

	public void setBoard(ArrayList<String[]>b){ // sets the board to this board
		board = b;
		checkEmpty();
	}

	public void setDimReg(int k){
		colDim = k;
		rowDim = k;
		this.k = k;
		checkEmpty();
	}

	public void setDimSingle(int k){
		rowDim = 1;
		colDim = k;
		this.k = (int)Math.sqrt((double)k); // this is the board dimensions
		checkEmpty();
	}

	public ArrayList<String[]> getBoard(){
		return board;
	}

	public boolean getIsEmpty(){
		return isEmpty;
	}

	
	public String toString(){ // prints the board
		String print = "";

		for(int i=0; i<rowDim; i++){
			for(int b=0; b<colDim; b++){
				print += board.get(i)[b];
			}
			print += "\n";
		}
		print+= "the k = " + k;
		return print;
	}
	
    int countPlayed(char target) {
        int count = 0;
        for (char c: boardState) {
            if (c == target) {
                count++;
            }
        }
        return count;
    }
}
