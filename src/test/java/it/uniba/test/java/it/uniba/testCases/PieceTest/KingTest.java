package it.uniba.test.java.it.uniba.testCases.PieceTest;

import it.uniba.piece.Piece;
import it.uniba.piece.bishop.Bishop;
import it.uniba.piece.king.King;
import it.uniba.piece.rook.Rook;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static it.uniba.chessboard.Chessboard.MAX_ALT;
import static it.uniba.chessboard.Chessboard.MAX_LUNG;
import static org.junit.jupiter.api.Assertions.*;

class KingTest {
    King kTest;
    Piece chess[][];

    @Test
    void getIconWhiteTest() {
        kTest = new King();
        kTest.setColor("white");

        chess= new Piece[MAX_ALT][MAX_LUNG];
        chess[0][6] = kTest;

        assertEquals(" \u2654 ", kTest.getIcon());
    }

    @Test
    void getIconBlackTest() {
        kTest = new King();
        kTest.setColor("black");

        chess= new Piece[MAX_ALT][MAX_LUNG];
        chess[0][6] = kTest;

        assertEquals(" \u265a ", kTest.getIcon());
    }

    @Test
    void getValuePointTest() {
        kTest = new King();
        int valueP;
        valueP = kTest.getValuePoint();
        assertEquals(99, valueP);
    }

    @Test
    void moveWhiteTest() {
        kTest = new King();
        kTest.setColor("white");

        chess= new Piece[MAX_ALT][MAX_LUNG];
        for(int i = 0; i <MAX_ALT; i++) {
            for (int j = 0; j < MAX_LUNG; j++) {
                chess[i][j] = new Piece();
            }
        }
        chess[4][7] = kTest;

        List<int[]> result = new ArrayList<int[]>();
        List<int[]> espected = new ArrayList<int[]>();
        espected.add(new int[]{7, 3});
        espected.add(new int[]{7, 5});
        espected.add(new int[]{6, 4});
        espected.add(new int[]{6, 3});
        espected.add(new int[]{6, 5});

        int[] posizioneIniziale = new int[]{7, 4};
        short turn=0;

        result= kTest.move(posizioneIniziale, "white", chess, turn);

        for(int i=0; i<5;i++)
        {
            assertArrayEquals(espected.get(i), result.get(i));
        }
    }

    @Test
    void moveBlackTest() {
        kTest = new King();
        kTest.setColor("black");

        chess= new Piece[MAX_ALT][MAX_LUNG];
        for(int i = 0; i <MAX_ALT; i++) {
            for (int j = 0; j < MAX_LUNG; j++) {
                chess[i][j] = new Piece();
            }
        }
        chess[4][4] = kTest;
        //chess[4][1] = new Piece();
        //chess[4][2] = new Piece();
        chess[4][0] = new Rook();
        chess[4][0].setColor("black");
        chess[4][7] = new Rook();
        chess[4][7].setColor("black");

        List<int[]> result = new ArrayList<int[]>();
        List<int[]> espected = new ArrayList<int[]>();
        espected.add(new int[]{4, 3});
        espected.add(new int[]{4, 5});
        espected.add(new int[]{3, 4});
        espected.add(new int[]{5, 4});
        espected.add(new int[]{3, 3});
        espected.add(new int[]{5, 5});
        espected.add(new int[]{5, 3});
        espected.add(new int[]{3, 5});
        espected.add(new int[]{6, 4});

        int[] posizioneIniziale = new int[]{4, 4};
        short turn=1;

        result= kTest.move(posizioneIniziale, "black", chess, turn);

        for(int i=0; i<5;i++)
        {
            assertArrayEquals(espected.get(i), result.get(i));
        }


    }


}