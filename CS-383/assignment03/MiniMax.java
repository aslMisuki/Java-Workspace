package assignment03;

public class MiniMax{
	// shold not handle turn determination
	
	 static int maxValue(Board b) {
	        if (b.isEndGame()) {
	            return b.getUtility();
	        }

	        int value = Integer.MIN_VALUE;

	        for (Board nextBoard: b.listSuccessors()) {
	            value = Math.max(value, minValue(nextBoard));
	        }

	        return value;
	    }
	    	
	    static int minValue(Board b) {
	        if (b.isEndGame()) {
	            return b.getUtility();
	        }

	        int value = Integer.MAX_VALUE;

	        for (Board nextBoard: b.listSuccessors()) {
	            value = Math.min(value, maxValue(nextBoard));
	        }

	        return value;
	    }
	    
	    
	    public static int minimax(Board b) {
	        if (b.getCurrentPlayer() == b.MAX_PLAYER) {
	            return maxValue(b);
	        }
	        return minValue(b);
	    }
}