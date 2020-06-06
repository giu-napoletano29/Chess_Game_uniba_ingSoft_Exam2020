package it.uniba.test.java.it.uniba.testCases.PieceTest;
import it.uniba.piece.Piece;
import it.uniba.piece.bishop.Bishop;
import it.uniba.piece.pawn.Pawn;
import it.uniba.piece.rook.Rook;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static it.uniba.chessboard.Chessboard.MAX_ALT;
import static it.uniba.chessboard.Chessboard.MAX_LUNG;
import static org.junit.jupiter.api.Assertions.*;
class PieceTest {
    Piece pTest;
    Piece chess[][];
    @Test
    void getIconTest() {
        pTest = new Piece();
        String test = "   ";
        assertEquals(test, pTest.getIcon());
    }
    @Test
    void getNameTest() {
        pTest = new Piece();
        String test = "";
        assertEquals(test, pTest.getName());
    }
    @Test
    void getPositionTest() {
        pTest = new Piece();
        int[] test = {0, 0};
        assertArrayEquals(test, pTest.getPosition());
    }
    @Test
    void setPositionTest() {
        pTest = new Piece();
        int[] test = {3, 5};
        pTest.setPosition(test);
        assertArrayEquals(test, pTest.getPosition());
    }
    @Test
    void getTurnLastMoveTest() {
        pTest = new Piece();
        assertEquals(0, pTest.getTurnLastMove());
    }
    @Test
    void setTurnLastMoveTest() {
        pTest = new Piece();
        short turn = 4;
        pTest.setTurnLastMove(turn);
        assertEquals(turn, pTest.getTurnLastMove());
    }
    @Test
    void getColorTest() {
        pTest = new Piece();
        assertEquals("invalid", pTest.getColor());
    }
    @Test
    void setColorTest() {
        pTest = new Piece();
        pTest.setColor("white");
        assertEquals("white", pTest.getColor());
    }
    @Test
    void getMovesCountTest() {
        pTest = new Piece();
        assertEquals(0, pTest.getMovesCount());
    }
    @Test
    void setMovesCountTest() {
        pTest = new Piece();
        pTest.setMovesCount();
        assertEquals(1, pTest.getMovesCount());
    }
    @Test
    void getValuePointTest() {
        pTest = new Piece();
        assertEquals(0, pTest.getValuePoint());
    }
    @Test
    void captureTest() {
        pTest = new Rook();
        pTest.setColor("white");
        int[] test = {3, 4};
        Piece capt = new Rook();
        capt.setColor("black");
        List<int[]> espected = new ArrayList<int[]>();
        espected.add(new int[] {3, 4});
        short turn = 1;
        chess= new Piece[MAX_ALT][MAX_LUNG];
        for(int i = 0; i <MAX_ALT; i++) {
            for (int j = 0; j < MAX_LUNG; j++) {
                chess[i][j] = new Piece();
            }
        }
        chess[3][5] = pTest;
        pTest.setPosition(new int[] {3, 5});
        chess[3][4] = capt;
        capt.setPosition(new int[] {3, 4});
        pTest.move(new int[] {3, 5}, pTest.getColor(), chess, turn);
        pTest.capture(test, chess[3][4], "white");
        for(int i=0; i<pTest.getCapturesList().size(); i++)
        {
            assertArrayEquals(espected.get(i), pTest.getCapturesList().get(i));
        }
    }
    @Test
    void getEnCapturesTest() {
        pTest = new Piece();
        int[] mov = {3,4};
        pTest.getEnCapturesList().add(new int[] {3, 4});
        chess= new Piece[MAX_ALT][MAX_LUNG];
        for(int i = 0; i <MAX_ALT; i++) {
            for (int j = 0; j < MAX_LUNG; j++) {
                chess[i][j] = new Piece();
            }
        }
        chess[2][4] = pTest;
        assertTrue(pTest.getEnCaptures(mov));
    }
    @Test
    void getValidCapturesTest() {
        pTest = new Piece();
        int[] mov = {3,4};
        pTest.getCapturesList().add(new int[] {3, 4});
        chess= new Piece[MAX_ALT][MAX_LUNG];
        for(int i = 0; i <MAX_ALT; i++) {
            for (int j = 0; j < MAX_LUNG; j++) {
                chess[i][j] = new Piece();
            }
        }
        chess[2][4] = pTest;
        assertTrue(pTest.getValidCaptures(mov));
    }
    @Test
    void moveTest() {
        pTest = new Piece();
        //List<int[]> test = new ArrayList<int[]>();
        //assertArrayEquals(test, pTest.move(new int[] {}));
    }
    @Test
    void getCapturesListTest() {
    }
    @Test
    void getEnCapturesListTest() {
    }
}