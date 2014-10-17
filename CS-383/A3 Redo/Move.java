package A3redo;
public class Move implements Comparable {

	private final int xIndex;
	private final int yIndex;
	private final int value;
	private final Board b;
	private final boolean maxPlayer; // true for x, false for o

	public Move(int xIndex, int yIndex, Board b, boolean maxPlayer){
		this.b = b;
		this.xIndex  = xIndex;
		this.yIndex = yIndex;
		this.value = calcValue();
		this.maxPlayer = maxPlayer;
	}

	// The index of this move
	// will use a 2 step check for value, +1 if adjacent to another same kind, then +1 if adjacent to another, -1 if O is blocking victory or walled, 0 if next is empty
	// 
	private int calcValue(){ // calculates the value for this move for the heuristic
		String thisPlayer = "";
		String thatPlayer = "";
		int step1V = 0; // value for first step
		int step2V = 0; // vlaue for 2nd step probably not needed
		int placement = -1; // -1 for null, 0 = corner, 1 = edge, 2 = non edge/non corner
		// identifying player
		if(maxPlayer == true){
			thisPlayer = "X";
			thatPlayer = "O";
		}
		else{
			thisPlayer = "O";
			thatPlayer = "X";
		}


		//check to see what type of placement it is
		if(		(xIndex == 0 && yIndex == 0) 			|| //top-left
				(xIndex == b.getK()-1 && yIndex == 0) 	||//top-right
				(xIndex == 0 && yIndex == b.getK()-1) 	|| //bottom-left
				(xIndex == b.getK()-1 && yIndex == b.getK()-1 )){ // bottom-righ 
			placement = 0;
		}
		else if	((xIndex == b.getK()-1)	|| //bottom edge
				( yIndex == b.getK()-1) ||//right edge
				( xIndex == 0) 			|| //left edge
				( yIndex == 0)){ //top edge
			placement = 1;
		}
		else{ // this is a non corner, non-edge piece
			placement = 2;
		}

		// the first step:
		// now calculate the value of this move for first step 
		// order of checking: clockwise starting at l
		if(placement == 2){ // a piece that is not corner or edge: 8 for first step
			if(b.isSingleLine()){
				//l
				if(b.getBoard().get(0)[(b.getK()*yIndex) + xIndex-1].equals(thisPlayer)){ step1V += 1;}
				else if(b.getBoard().get(0)[(b.getK()*yIndex) + xIndex-1].equals(thatPlayer)){ step1V -= 1;}
				// tl
				if(b.getBoard().get(0)[(b.getK()*yIndex-1) + xIndex-1].equals(thisPlayer)){ step1V += 1;}
				else if(b.getBoard().get(0)[(b.getK()*yIndex-1) + xIndex-1].equals(thatPlayer)){ step1V -= 1;}
				//t
				if(b.getBoard().get(0)[(b.getK()*yIndex-1) + xIndex].equals(thisPlayer)){ step1V += 1;}
				else if(b.getBoard().get(0)[(b.getK()*yIndex-1) + xIndex].equals(thatPlayer)){ step1V -= 1;}
				//tr
				if(b.getBoard().get(0)[(b.getK()*yIndex-1) + xIndex+1].equals(thisPlayer)){ step1V += 1;}
				else if(b.getBoard().get(0)[(b.getK()*yIndex-1) + xIndex+1].equals(thatPlayer)){ step1V -= 1;}
				//r
				if(b.getBoard().get(0)[(b.getK()*yIndex) + xIndex+1].equals(thisPlayer)){ step1V += 1;}
				else if(b.getBoard().get(0)[(b.getK()*yIndex) + xIndex+1].equals(thatPlayer)){ step1V -= 1;}
				//br
				if(b.getBoard().get(0)[(b.getK()*yIndex+1) + xIndex+1].equals(thisPlayer)){ step1V += 1;}
				else if(b.getBoard().get(0)[(b.getK()*yIndex+1) + xIndex+1].equals(thatPlayer)){ step1V -= 1;}
				//b
				if(b.getBoard().get(0)[(b.getK()*yIndex+1) + xIndex].equals(thisPlayer)){ step1V += 1;}
				else if(b.getBoard().get(0)[(b.getK()*yIndex+1) + xIndex].equals(thatPlayer)){ step1V -= 1;}
				//bl
				if(b.getBoard().get(0)[(b.getK()*yIndex+1) + xIndex-1].equals(thisPlayer)){ step1V += 1;}
				else if(b.getBoard().get(0)[(b.getK()*yIndex+1) + xIndex-1].equals(thatPlayer)){ step1V -= 1;}

			}
			else{ // board is not single line format
				if(b.getBoard().get(yIndex)[xIndex-1].equals(thisPlayer)){ step1V += 1;} 
				else if(b.getBoard().get(yIndex)[xIndex-1].equals(thatPlayer)){ step1V -= 1;}
				// tl
				if(b.getBoard().get(yIndex-1)[xIndex-1].equals(thisPlayer)){ step1V += 1;}
				else if(b.getBoard().get(yIndex-1)[xIndex-1].equals(thatPlayer)){ step1V -= 1;} 
				//t
				if(b.getBoard().get(yIndex-1)[xIndex].equals(thisPlayer)){ step1V += 1;}
				else if(b.getBoard().get(yIndex-1)[xIndex].equals(thatPlayer)){ step1V -= 1;} 
				//tr
				if(b.getBoard().get(yIndex-1)[xIndex+1].equals(thisPlayer)){ step1V += 1;}
				else if(b.getBoard().get(yIndex-1)[xIndex+1].equals(thatPlayer)){ step1V -= 1;} 
				//r
				if(b.getBoard().get(yIndex)[xIndex+1].equals(thisPlayer)){ step1V += 1;}
				else if(b.getBoard().get(yIndex)[xIndex+1].equals(thatPlayer)){ step1V -= 1;} 
				//br
				if(b.getBoard().get(yIndex+1)[xIndex+1].equals(thisPlayer)){ step1V += 1;}
				else if(b.getBoard().get(yIndex+1)[xIndex+1].equals(thatPlayer)){ step1V -= 1;} 
				//b
				if(b.getBoard().get(yIndex+1)[xIndex].equals(thisPlayer)){ step1V += 1;}
				else if(b.getBoard().get(yIndex+1)[xIndex].equals(thatPlayer)){ step1V -= 1;} 
				//bl
				if(b.getBoard().get(yIndex+1)[xIndex-1].equals(thisPlayer)){ step1V += 1;}
				else if(b.getBoard().get(yIndex+1)[xIndex-1].equals(thatPlayer)){ step1V -= 1;} 
			}
		}
		else if(placement == 1){// the edge piece: total moves: 5 for first step
			if(b.isSingleLine()){
				//l
				if(xIndex != 0){ // not on left side
					if(b.getBoard().get(0)[(b.getK()*yIndex) + xIndex-1].equals(thisPlayer)){ step1V += 1;}
					else if(b.getBoard().get(0)[(b.getK()*yIndex) + xIndex-1].equals(thatPlayer)){ step1V -= 1;}
					// tl
					if(b.getBoard().get(0)[(b.getK()*yIndex-1) + xIndex-1].equals(thisPlayer)){ step1V += 1;}
					else if(b.getBoard().get(0)[(b.getK()*yIndex-1) + xIndex-1].equals(thatPlayer)){ step1V -= 1;}
					//bl
					if(b.getBoard().get(0)[(b.getK()*yIndex+1) + xIndex-1].equals(thisPlayer)){ step1V += 1;}
					else if(b.getBoard().get(0)[(b.getK()*yIndex+1) + xIndex-1].equals(thatPlayer)){ step1V -= 1;}
				}
				if(xIndex != b.getK()-1){ // not on right side
					//tr
					if(b.getBoard().get(0)[(b.getK()*yIndex-1) + xIndex+1].equals(thisPlayer)){ step1V += 1;}
					else if(b.getBoard().get(0)[(b.getK()*yIndex-1) + xIndex+1].equals(thatPlayer)){ step1V -= 1;}
					//r
					if(b.getBoard().get(0)[(b.getK()*yIndex) + xIndex+1].equals(thisPlayer)){ step1V += 1;}
					else if(b.getBoard().get(0)[(b.getK()*yIndex) + xIndex+1].equals(thatPlayer)){ step1V -= 1;}
					//br
					if(b.getBoard().get(0)[(b.getK()*yIndex+1) + xIndex+1].equals(thisPlayer)){ step1V += 1;}
					else if(b.getBoard().get(0)[(b.getK()*yIndex+1) + xIndex+1].equals(thatPlayer)){ step1V -= 1;}
				}
				
				if(yIndex != 0){ // not on top side
					//t
					if(b.getBoard().get(0)[(b.getK()*yIndex-1) + xIndex].equals(thisPlayer)){ step1V += 1;}
					else if(b.getBoard().get(0)[(b.getK()*yIndex-1) + xIndex].equals(thatPlayer)){ step1V -= 1;}
				}
				
				if(yIndex != b.getK()-1){ // not on bottom side
					//b
					if(b.getBoard().get(0)[(b.getK()*yIndex+1) + xIndex].equals(thisPlayer)){ step1V += 1;}
					else if(b.getBoard().get(0)[(b.getK()*yIndex+1) + xIndex].equals(thatPlayer)){ step1V -= 1;}
				}

			}
			else{ // board is not single line format
				if(xIndex != 0){ // not on left side
					if(b.getBoard().get(yIndex)[xIndex-1].equals(thisPlayer)){ step1V += 1;} 
					else if(b.getBoard().get(yIndex)[xIndex-1].equals(thatPlayer)){ step1V -= 1;}
					// tl
					if(b.getBoard().get(yIndex-1)[xIndex-1].equals(thisPlayer)){ step1V += 1;}
					else if(b.getBoard().get(yIndex-1)[xIndex-1].equals(thatPlayer)){ step1V -= 1;} 
					//bl
					if(b.getBoard().get(yIndex+1)[xIndex-1].equals(thisPlayer)){ step1V += 1;}
					else if(b.getBoard().get(yIndex+1)[xIndex-1].equals(thatPlayer)){ step1V -= 1;} 
				}
				if(xIndex != b.getK()-1){ // not on right side
					//tr
					if(b.getBoard().get(yIndex-1)[xIndex+1].equals(thisPlayer)){ step1V += 1;}
					else if(b.getBoard().get(yIndex-1)[xIndex+1].equals(thatPlayer)){ step1V -= 1;} 
					//r
					if(b.getBoard().get(yIndex)[xIndex+1].equals(thisPlayer)){ step1V += 1;}
					else if(b.getBoard().get(yIndex)[xIndex+1].equals(thatPlayer)){ step1V -= 1;} 
					//br
					if(b.getBoard().get(yIndex+1)[xIndex+1].equals(thisPlayer)){ step1V += 1;}
					else if(b.getBoard().get(yIndex+1)[xIndex+1].equals(thatPlayer)){ step1V -= 1;} 
				}
				
				if(yIndex != 0){ // not on top side
					//t
					if(b.getBoard().get(yIndex-1)[xIndex].equals(thisPlayer)){ step1V += 1;}
					else if(b.getBoard().get(yIndex-1)[xIndex].equals(thatPlayer)){ step1V -= 1;} 
				}
				
				if(yIndex != b.getK()-1){ // not on bottom side
					//b
					if(b.getBoard().get(yIndex+1)[xIndex].equals(thisPlayer)){ step1V += 1;}
					else if(b.getBoard().get(yIndex+1)[xIndex].equals(thatPlayer)){ step1V -= 1;} 
				}
			}
		}
		else{// the corner piece: total moves: 3 for first step
			if(b.isSingleLine()){
				if(xIndex == 0 && yIndex == 0){ // on tl
					//r
					if(b.getBoard().get(0)[(b.getK()*yIndex) + xIndex+1].equals(thisPlayer)){ step1V += 1;}
					else if(b.getBoard().get(0)[(b.getK()*yIndex) + xIndex+1].equals(thatPlayer)){ step1V -= 1;}
					//br
					if(b.getBoard().get(0)[(b.getK()*yIndex+1) + xIndex+1].equals(thisPlayer)){ step1V += 1;}
					else if(b.getBoard().get(0)[(b.getK()*yIndex+1) + xIndex+1].equals(thatPlayer)){ step1V -= 1;}
					//b
					if(b.getBoard().get(0)[(b.getK()*yIndex+1) + xIndex].equals(thisPlayer)){ step1V += 1;}
					else if(b.getBoard().get(0)[(b.getK()*yIndex+1) + xIndex].equals(thatPlayer)){ step1V -= 1;}
				}
				if(xIndex == b.getK()-1 && yIndex == 0 ){ // on tr
					//l
					if(b.getBoard().get(0)[(b.getK()*yIndex) + xIndex-1].equals(thisPlayer)){ step1V += 1;}
					else if(b.getBoard().get(0)[(b.getK()*yIndex) + xIndex-1].equals(thatPlayer)){ step1V -= 1;}
					//b
					if(b.getBoard().get(0)[(b.getK()*yIndex+1) + xIndex].equals(thisPlayer)){ step1V += 1;}
					else if(b.getBoard().get(0)[(b.getK()*yIndex+1) + xIndex].equals(thatPlayer)){ step1V -= 1;}
					//bl
					if(b.getBoard().get(0)[(b.getK()*yIndex+1) + xIndex-1].equals(thisPlayer)){ step1V += 1;}
					else if(b.getBoard().get(0)[(b.getK()*yIndex+1) + xIndex-1].equals(thatPlayer)){ step1V -= 1;}
				}
				if(xIndex == 0 && yIndex == b.getK()-1){ // on bl
					//t
					if(b.getBoard().get(0)[(b.getK()*yIndex-1) + xIndex].equals(thisPlayer)){ step1V += 1;}
					else if(b.getBoard().get(0)[(b.getK()*yIndex-1) + xIndex].equals(thatPlayer)){ step1V -= 1;}
					//tr
					if(b.getBoard().get(0)[(b.getK()*yIndex-1) + xIndex+1].equals(thisPlayer)){ step1V += 1;}
					else if(b.getBoard().get(0)[(b.getK()*yIndex-1) + xIndex+1].equals(thatPlayer)){ step1V -= 1;}
					//r
					if(b.getBoard().get(0)[(b.getK()*yIndex) + xIndex+1].equals(thisPlayer)){ step1V += 1;}
					else if(b.getBoard().get(0)[(b.getK()*yIndex) + xIndex+1].equals(thatPlayer)){ step1V -= 1;}
				}
				if(xIndex == b.getK()-1 && yIndex == b.getK()-1){ // on br
					//l
					if(b.getBoard().get(0)[(b.getK()*yIndex) + xIndex-1].equals(thisPlayer)){ step1V += 1;}
					else if(b.getBoard().get(0)[(b.getK()*yIndex) + xIndex-1].equals(thatPlayer)){ step1V -= 1;}
					// tl
					if(b.getBoard().get(0)[(b.getK()*yIndex-1) + xIndex-1].equals(thisPlayer)){ step1V += 1;}
					else if(b.getBoard().get(0)[(b.getK()*yIndex-1) + xIndex-1].equals(thatPlayer)){ step1V -= 1;}
					//t
					if(b.getBoard().get(0)[(b.getK()*yIndex-1) + xIndex].equals(thisPlayer)){ step1V += 1;}
					else if(b.getBoard().get(0)[(b.getK()*yIndex-1) + xIndex].equals(thatPlayer)){ step1V -= 1;}
				}

			}
			else{ // board is not single line format
				if(xIndex == 0 && yIndex == 0){ // on tl
					//r
					if(b.getBoard().get(yIndex)[xIndex+1].equals(thisPlayer)){ step1V += 1;}
					else if(b.getBoard().get(yIndex)[xIndex+1].equals(thatPlayer)){ step1V -= 1;} 
					//br
					if(b.getBoard().get(yIndex+1)[xIndex+1].equals(thisPlayer)){ step1V += 1;}
					else if(b.getBoard().get(yIndex+1)[xIndex+1].equals(thatPlayer)){ step1V -= 1;} 
					//b
					if(b.getBoard().get(yIndex+1)[xIndex].equals(thisPlayer)){ step1V += 1;}
					else if(b.getBoard().get(yIndex+1)[xIndex].equals(thatPlayer)){ step1V -= 1;} 
				}
				if(xIndex == b.getK()-1 && yIndex == 0 ){ // on tr
					//l
					if(b.getBoard().get(yIndex)[xIndex-1].equals(thisPlayer)){ step1V += 1;} 
					else if(b.getBoard().get(yIndex)[xIndex-1].equals(thatPlayer)){ step1V -= 1;}
					//b
					if(b.getBoard().get(yIndex+1)[xIndex].equals(thisPlayer)){ step1V += 1;}
					else if(b.getBoard().get(yIndex+1)[xIndex].equals(thatPlayer)){ step1V -= 1;} 
					//bl
					if(b.getBoard().get(yIndex+1)[xIndex-1].equals(thisPlayer)){ step1V += 1;}
					else if(b.getBoard().get(yIndex+1)[xIndex-1].equals(thatPlayer)){ step1V -= 1;} 
				}
				if(xIndex == 0 && yIndex == b.getK()-1){ // on bl
					//t
					if(b.getBoard().get(yIndex-1)[xIndex].equals(thisPlayer)){ step1V += 1;}
					else if(b.getBoard().get(yIndex-1)[xIndex].equals(thatPlayer)){ step1V -= 1;} 
					//tr
					if(b.getBoard().get(yIndex-1)[xIndex+1].equals(thisPlayer)){ step1V += 1;}
					else if(b.getBoard().get(yIndex-1)[xIndex+1].equals(thatPlayer)){ step1V -= 1;} 
					//r
					if(b.getBoard().get(yIndex)[xIndex+1].equals(thisPlayer)){ step1V += 1;}
					else if(b.getBoard().get(yIndex)[xIndex+1].equals(thatPlayer)){ step1V -= 1;} 
				}
				if(xIndex == b.getK()-1 && yIndex == b.getK()-1){ // on br
					//l
					if(b.getBoard().get(yIndex)[xIndex-1].equals(thisPlayer)){ step1V += 1;} 
					else if(b.getBoard().get(yIndex)[xIndex-1].equals(thatPlayer)){ step1V -= 1;}
					// tl
					if(b.getBoard().get(yIndex-1)[xIndex-1].equals(thisPlayer)){ step1V += 1;}
					else if(b.getBoard().get(yIndex-1)[xIndex-1].equals(thatPlayer)){ step1V -= 1;} 
					//t
					if(b.getBoard().get(yIndex-1)[xIndex].equals(thisPlayer)){ step1V += 1;}
					else if(b.getBoard().get(yIndex-1)[xIndex].equals(thatPlayer)){ step1V -= 1;} 
				}
				
			}
		}
		return step1V;

	}



	public String toString(){
		return "X index: " + xIndex + "Y index: " + yIndex;
	}

	public int getValue(){ // returns the heuristic of this move
		return value;
	}

	public int getXIndex(){
		return this.xIndex;
	}

	public int getYIndex(){
		return this.yIndex;
	}

	public boolean getPlayerTurn(){
		return maxPlayer;
	}
	public int compareTo(Object move) { // returns a 1 if both sides are open, 0 if only 1 is open, -1 if none are available
		Move other = (Move)move;
		int otherValue = other.getValue();
		return Integer.compare(value, otherValue); // returns 0 if same, neg if x < y, and pos if x > y
	}
}
