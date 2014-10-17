package test;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BoardTest {
    @org.junit.Test
    public void testString() throws Exception {
        Board b = Board.fromString(".........");
        assertEquals(".........", b.toString());

        Board b2 = Board.fromString("....XOasdhfjlaksdflsadfj...");
        assertEquals("....XO...", b2.toString());
    }

    @org.junit.Test
    public void testAllEqual() throws Exception {
        Board b = Board.fromString("XXXOXOOOO");
        assertTrue(b.allEqual('X', new int[]{0, 1, 2}));
        assertTrue(b.allEqual('O', new int[]{3, 5, 6, 7, 8}));
        assertFalse(b.allEqual('X', new int[]{2, 3, 4}));
    }

    @org.junit.Test
    public void testVictoryAndDraw() throws Exception {
        Board x = Board.fromString("XO..XO..X");
        assertTrue(x.isVictorious('X'));
        assertFalse(x.isVictorious('O'));
        assertFalse(x.isDraw());

        Board o = Board.fromString("OX.OX.O.X");
        assertTrue(o.isVictorious('O'));
        assertFalse(o.isVictorious('X'));
        assertFalse(o.isDraw());

        Board tie = Board.fromString("XXOOOXXOX");
        assertFalse(tie.isVictorious('O'));
        assertFalse(tie.isVictorious('X'));
        assertTrue(tie.isDraw());
    }

    @org.junit.Test
    public void testCount() throws Exception {
        // assertEquals(1, Collections.frequency(Arrays.asList(new char[]{'a', 'b', 'c'}), 'a'));
        // -> returns 0!

        // works as expected
        assertEquals(1, Collections.frequency(Arrays.asList(new Character[]{'a', 'b', 'c'}), new Character('a')));
    }
}