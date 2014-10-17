package test;

import junit.framework.TestCase;

public class MinimaxTest extends TestCase {

    public void testMinimax() throws Exception {
        Board b = Board.fromString(".........");
        assertEquals(0, Minimax.minimax(b));

        b = Board.fromString("X........");
        assertEquals(0, Minimax.minimax(b));

        b = Board.fromString(".O..X....");
        assertEquals(1, Minimax.minimax(b));

        b = Board.fromString(".X..O..X.");
        assertEquals(-1, Minimax.minimax(b));
    }
}