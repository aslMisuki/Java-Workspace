package assignment03;
import java.io.File;

public class TickTacToe2 {



	public int maxValue(Board b){
		if (b.isEndGame()){
			return b.getUtility();
		}

		int value = Integer.MIN_VALUE;

		for(Board nextBoard: b.listSuccessors()){
			value = Integer.max(value, minValue(nextBoard));

		}
		return value;
	}

	public int minValue(Board b){
		if(b.isEndGame()){
			return b.getUtility();
		}

		int value = Integer.MAX_VALUE;

		for(Board nextBoard: b.listSuccessors()){
			value = Integer.min(value,maxValue(nextBoard));
		}
		return value;
	}
	
}
