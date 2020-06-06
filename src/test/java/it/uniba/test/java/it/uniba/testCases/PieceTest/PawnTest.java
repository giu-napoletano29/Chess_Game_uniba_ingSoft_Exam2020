package it.uniba.test.java.it.uniba.testCases.PieceTest;

import it.uniba.chessboard.Chessboard;
import it.uniba.piece.Piece;
import it.uniba.piece.bishop.Bishop;
import it.uniba.piece.knight.Knight;
import it.uniba.piece.pawn.Pawn;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static it.uniba.chessboard.Chessboard.MAX_ALT;
import static it.uniba.chessboard.Chessboard.MAX_LUNG;
import static org.junit.jupiter.api.Assertions.*;

class PawnTest {
    Pawn pTest;
    Piece chess[][];

    @Test
    void getIconWhiteTest() {
        pTest = new Pawn();
        pTest.setColor("white");

        chess= new Piece[MAX_ALT][MAX_LUNG];
        chess[0][6] = pTest;

        assertEquals(" \u2659 ", pTest.getIcon());
    }

    @Test
    void getIconBlackTest() {
        pTest = new Pawn();
        pTest.setColor("black");

        chess= new Piece[MAX_ALT][MAX_LUNG];
        chess[0][6] = pTest;

        assertEquals(" \u265f ", pTest.getIcon());
    }

    @Test
    void moveWhiteTest() {
        pTest = new Pawn();
        pTest.setColor("white");


        chess= new Piece[MAX_ALT][MAX_LUNG];
        for(int i = 0; i <MAX_ALT; i++) {
            for (int j = 0; j < MAX_LUNG; j++) {
                chess[i][j] = new Piece();
            }
        }
        chess[0][6] = pTest;


        List<int[]> result = new ArrayList<int[]>();
        List<int[]> espected = new ArrayList<int[]>();
        espected.add(new int[]{0, 5});
        espected.add(new int[]{0, 4});


        int[] posizioneIniziale = new int[]{0, 6};
        short turn=0;

        result= pTest.move(posizioneIniziale, "white", chess, turn);

        for(int i=0; i<2;i++)
        {
            assertArrayEquals(espected.get(i), result.get(i));
        }
    }

    @Test
    void moveWhiteDiagonalTest() {
        pTest = new Pawn();
        pTest.setColor("white");

        Pawn black = new Pawn();
        black.setColor("black");

        Piece[][] chess = new Piece[MAX_ALT][MAX_LUNG];

        chess= new Piece[MAX_ALT][MAX_LUNG];
        for(int i = 0; i <MAX_ALT; i++) {
            for (int j = 0; j < MAX_LUNG; j++) {
                chess[i][j] = new Piece();
            }
        }

        chess[0][6] = pTest;
        chess[5][1] = new Bishop();
        chess[5][1].setColor("black");
        chess[5][1].setPosition(new int[] {1,5});


        List<int[]> result = new ArrayList<int[]>();
        List<int[]> espected = new ArrayList<int[]>();
        espected.add(new int[]{0, 5});
        espected.add(new int[]{0, 4});
        espected.add(new int[]{1, 5});


        int[] posizioneIniziale = new int[]{0, 6};
        short turn=0;

        result= pTest.move(posizioneIniziale, "white", chess, turn);

        for(int i=0; i<result.size();i++)
        {
            assertArrayEquals(espected.get(i), result.get(i));
        }
    }

    @Test
    void moveBlackTest() {
        pTest = new Pawn();
        pTest.setColor("black");


        chess= new Piece[MAX_ALT][MAX_LUNG];
        for(int i = 0; i <MAX_ALT; i++) {
            for (int j = 0; j < MAX_LUNG; j++) {
                chess[i][j] = new Piece();
            }
        }
        chess[7][1] = pTest;


        List<int[]> result = new ArrayList<int[]>();
        List<int[]> espected = new ArrayList<int[]>();
        espected.add(new int[]{7, 2});
        espected.add(new int[]{7, 3});


        int[] posizioneIniziale = new int[]{7, 1};
        short turn=0;

        result= pTest.move(posizioneIniziale, "black", chess, turn);

        for(int i=0; i<2;i++)
        {
            assertArrayEquals(espected.get(i), result.get(i));
        }
    }

    @Test
    void captureTest() {
        pTest = new Pawn();

        int[] pos = new int[]{4, 1};

        List<int[]> expected = new ArrayList<int[]>();
        List<int[]> result;
        expected.add(pos);
        pTest.capture(pos);
        result = pTest.getCapturesList();

        assertEquals(expected.get(0), result.get(0));
    }

    @Test
    void enCaptureTest() {
        pTest = new Pawn();

        int[] pos = new int[]{5, 4};

        List<int[]> expected = new ArrayList<int[]>();
        List<int[]> result;
        expected.add(pos);
        pTest.enCapture(pos);
        result = pTest.getEnCapturesList();

        assertEquals(expected.get(0), result.get(0));


    }
}