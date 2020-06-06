package it.uniba.test.java.it.uniba.testCases.PieceTest;

import it.uniba.piece.bishop.Bishop;
import it.uniba.piece.king.King;
import it.uniba.piece.knight.Knight;
import it.uniba.piece.pawn.Pawn;
import it.uniba.piece.queen.Queen;
import it.uniba.piece.rook.Rook;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;

import static it.uniba.chessboard.Chessboard.*;
import static org.junit.jupiter.api.Assertions.*;
import it.uniba.piece.Piece;
import it.uniba.chessboard.Chessboard;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class BishopTest {
    Bishop bTest;
    Piece chess[][];

    @Test
    void getIconWhiteTest() {
        bTest = new Bishop();
        bTest.setColor("white");

        chess= new Piece[MAX_ALT][MAX_LUNG];
        chess[0][6] = bTest;

        assertEquals(" \u2657 ", bTest.getIcon());
    }

    @Test
    void getIconBlackTest() {
        bTest = new Bishop();
        bTest.setColor("black");

        chess= new Piece[MAX_ALT][MAX_LUNG];
        chess[0][6] = bTest;

        assertEquals(" \u265d ", bTest.getIcon());
    }

    @Test
    void getValuePointTest() {
        bTest = new Bishop();
        int valueP;
        valueP = bTest.getValuePoint();
        assertEquals(3, valueP);
    }

    @Test
    void moveWhiteTest() {
        bTest = new Bishop();
        bTest.setColor("white");


        chess= new Piece[MAX_ALT][MAX_LUNG];
        for(int i = 0; i <MAX_ALT; i++) {
            for (int j = 0; j < MAX_LUNG; j++) {
                chess[i][j] = new Piece();
            }
        }
        chess[2][7] = bTest;


        List<int[]> result = new ArrayList<int[]>();
        List<int[]> espected = new ArrayList<int[]>();
        espected.add(new int[]{6, 3});
        espected.add(new int[]{5, 4});
        espected.add(new int[]{4, 5});
        espected.add(new int[]{3, 6});
        espected.add(new int[]{2, 7});
        espected.add(new int[]{6, 1});
        espected.add(new int[]{5, 0});


        int[] posizioneIniziale = new int[]{7, 2};
        short turn=0;

        result= bTest.move(posizioneIniziale, "white", chess, turn);

        for(int i=0; i<7;i++)
        {
            assertArrayEquals(espected.get(i), result.get(i));
        }
    }

    @Test
    void moveWhiteCaptureTest() {
        bTest = new Bishop();
        bTest.setColor("white");

        Rook black = new Rook();
        black.setColor("black");


        chess= new Piece[MAX_ALT][MAX_LUNG];
        for(int i = 0; i <MAX_ALT; i++) {
            for (int j = 0; j < MAX_LUNG; j++) {
                chess[i][j] = new Piece();
            }
        }
        chess[2][7] = bTest;
        chess[3][6] = black;


        List<int[]> result = new ArrayList<int[]>();
        List<int[]> espected = new ArrayList<int[]>();
        espected.add(new int[]{6, 3});
        espected.add(new int[]{6, 1});
        espected.add(new int[]{5, 0});


        int[] posizioneIniziale = new int[]{7, 2};
        short turn=0;

        result= bTest.move(posizioneIniziale, "white", chess, turn);

        for(int i=0; i<espected.size();i++)
        {
            assertArrayEquals(espected.get(i), result.get(i));
        }
    }

    @Test
    void moveBlackTest() {
        bTest = new Bishop();
        bTest.setColor("black");


        chess= new Piece[MAX_ALT][MAX_LUNG];
        for(int i = 0; i <MAX_ALT; i++) {
            for (int j = 0; j < MAX_LUNG; j++) {
                chess[i][j] = new Piece();
            }
        }
        chess[2][0] = bTest;


        List<int[]> result = new ArrayList<int[]>();
        List<int[]> espected = new ArrayList<int[]>();
        espected.add(new int[]{1, 3});
        espected.add(new int[]{2, 4});
        espected.add(new int[]{3, 5});
        espected.add(new int[]{4, 6});
        espected.add(new int[]{5, 7});
        espected.add(new int[]{1, 1});
        espected.add(new int[]{2, 0});


        int[] posizioneIniziale = new int[]{0, 2};
        short turn=1;

        result= bTest.move(posizioneIniziale, "black", chess, turn);

        for(int i=0; i<7;i++)
        {
            assertArrayEquals(espected.get(i), result.get(i));
        }
    }

    @Test
    void moveBlackCaptureTest() {
        bTest = new Bishop();
        bTest.setColor("black");

        Rook white = new Rook();
        white.setColor("white");

        chess= new Piece[MAX_ALT][MAX_LUNG];
        for(int i = 0; i <MAX_ALT; i++) {
            for (int j = 0; j < MAX_LUNG; j++) {
                chess[i][j] = new Piece();
            }
        }
        chess[2][0] = bTest;
        chess[3][1] = white;


        List<int[]> result = new ArrayList<int[]>();
        List<int[]> espected = new ArrayList<int[]>();
        espected.add(new int[]{1, 3});
        espected.add(new int[]{1, 1});
        espected.add(new int[]{2, 0});


        int[] posizioneIniziale = new int[]{0, 2};
        short turn=1;

        result= bTest.move(posizioneIniziale, "black", chess, turn);

        for(int i=0; i<espected.size();i++)
        {
            assertArrayEquals(espected.get(i), result.get(i));
        }
    }
}