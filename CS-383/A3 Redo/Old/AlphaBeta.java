package assignment03;

/**
 * Created by liberato on 10/8/14.
 */
	//minimax alg
public class AlphaBeta {
	
    static int maxValue(Board b, int alpha, int beta) {
        if (b.isEndGame()) {
            return b.getUtility();
        }

        int value = Integer.MIN_VALUE;

        for (Board nextBoard: b.listSuccessors()) {
            value = Math.max(value, minValue(nextBoard, alpha, beta));
            if(value >= beta){
            	return value;
            }
            alpha = Math.max(alpha, value);
        }
        return value;
    }
    	
    static int minValue(Board b, int alpha, int beta) {
        if (b.isEndGame()) {
            return b.getUtility();
        }

        int value = Integer.MAX_VALUE;

        for (Board nextBoard: b.listSuccessors()) {
            value = Math.min(value, maxValue(nextBoard, alpha, beta));
            if(value <= alpha){
            	return value;
            }
            beta = Math.min(beta, value);
        }
        return value;
    }
    
    
    public static int alphaBeta(Board b) {
        if (b.getCurrentPlayer() == b.MAX_PLAYER) {
            return maxValue(b, Integer.MIN_VALUE, Integer.MAX_VALUE);
        }
        return minValue(b, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
}
