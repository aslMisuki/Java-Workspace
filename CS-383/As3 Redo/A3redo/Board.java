package A3redo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liberato on 10/7/14.
 */
public class Board {
    public static final char MAX_PLAYER = 'X';
    public static final char MIN_PLAYER = 'O';
    public static final char UNPLAYED = '.';
    
    private char[] boardState;
    // for 3x3
    private int[][] victoryIndices;

    // sets this board
    public Board(char[] boardState) { // constructor
    	
        if (boardState.length == 9) {
        	victoryIndices = new int[][] {
                    {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, 	//horizontal # of horizontal wins = 
                    {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, 	// vertical
                    {0, 4, 8}, {2, 4, 6}};				// diagonal
        		this.boardState = boardState;
        }
        else if(boardState.length > 9){
        	this.boardState = boardState;
        	victoryIndices = vitoryIndiciesGenerator();
        }
        else{
            throw new RuntimeException("Board only supports 3x3 boards (input had " + boardState.length + " cells)");
        }
        
    }
    
    boolean allEqual(char targetChar, int[] indices) {
        for (int i : indices) {
            if (boardState[i] != targetChar) {
                return false;
            }
        }
        return true;
    }

    boolean isVictorious(char playerChar) {
        for (int[] vi : victoryIndices) {
            if (allEqual(playerChar, vi)) {
                return true;
            }
        }
        return false;
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

    boolean isDraw() {
        if (isVictorious(MAX_PLAYER) || isVictorious(MIN_PLAYER)) {
            return false;
        }
        int unplayedCount = countPlayed(UNPLAYED);
        if (unplayedCount == 0) {
            return true;
        }
        return false;
    }
    
    boolean isEndGame() {
        if (isVictorious(MAX_PLAYER) || isVictorious(MIN_PLAYER) || isDraw()) {
            return true;
        }
        return false;
    }
    
    //generates the winning indicies
    private int[][] vitoryIndiciesGenerator(){
    	int count = 0;
    	int k = (int)Math.sqrt(boardState.length);
    	int index=0;
    	int numWinStates = (int)((2*(double)k) + 2); // number of winWtates
    	int[] singleArray = new int[k];
    	ArrayList<Character> list = new ArrayList<>();
    	
    	int [][] ret = new int[numWinStates][k];
    	
    	//horizontal
    	for(int i=0; i<k; i++){
    		for(int b=0; b<k; b++){
    			singleArray[b] = count;
    			count++;
    		}
    		
    		index = i;
    		ret[index] = singleArray.clone();
    	}
    	
    	index++;
    	
    	//System.out.println("index: " +index);
    	count=0;
    	//vertical
    	for(int i=0; i<k; i++){ // offset
    		for(int b=0; b<k; b++){
    			singleArray[b] = i+count; //starts at 0
    			count+=k;
    		}
    		count=0;
    		ret[index] = singleArray.clone();
    		index++;
    	}
    	//diagonal
    	for(int i=0; i<k; i++){
    		singleArray[i] = i*(k+1);
    	}
    	ret[index] = singleArray.clone();
     	index++;
    	
    	for(int i=1; i<=k; i++){
    		singleArray[i-1] = i*(k-1);
    	}
    	
    	ret[index] = singleArray.clone();
    	
    	
//    	for(int[] a : ret){
//    		for(int b : a){
//    			System.out.print(b + " ");
//    		}
//    		System.out.println();
//    	}
    	
    	return ret;
    }
    // returns 1, 0, -1
    public int getUtility() {
        int value;
        if (isVictorious(MAX_PLAYER)) {
            value = 1;
        }
        else if (isVictorious(MIN_PLAYER)) {
            value =  -1;
        }
        else if (isDraw()) {
            value = 0;
        }
        else {
            throw new RuntimeException("Programmer error! getUtility() should only be called at endgame!");
        }
        return value;
    }
    
    //returns current player
    char getCurrentPlayer() {
        int maxPlayerCount = countPlayed(MAX_PLAYER);
        int minPlayerCount = countPlayed(MIN_PLAYER);
        if (minPlayerCount == maxPlayerCount) {
            return MAX_PLAYER;
        }
        else return MIN_PLAYER;
    }

    // iterates through the board state, and if a tile is unplayed
    // clone the board and have the current player pay that spot
    // then add to sucessors list
    public List<Board> listSuccessors() {
        char currentPlayer = getCurrentPlayer();
        List<Board> successors = new ArrayList<Board>();

        for (int i = 0; i < boardState.length; i++) {
            if (boardState[i] == UNPLAYED) {
                char[] newBoardState = boardState.clone();
                newBoardState[i] = currentPlayer;
                Board newBoard = new Board(newBoardState);
                successors.add(newBoard);
            }
        }
        return successors;
    }

    public static Board fromFile(File file) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }

        String fullFile = sb.toString();

        return Board.fromString(fullFile);
    }

    public static Board fromString(String inputString) {
        ArrayList<Character> charArrayList = new ArrayList<Character>();
        for (char c : inputString.toCharArray()) {
            if ((c == MAX_PLAYER) || (c == MIN_PLAYER) || (c == UNPLAYED)) {
                charArrayList.add(c);
            }
        }

        char[] chars = new char[charArrayList.size()];
        for (int i = 0; i < chars.length; i++) {
            chars[i] = charArrayList.get(i);
        }

        return new Board(chars);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (char c : boardState) {
            sb.append(c);
        }
        return sb.toString();
    }
}
