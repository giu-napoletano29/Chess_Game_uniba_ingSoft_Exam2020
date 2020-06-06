package it.uniba.test.java.it.uniba.testCases.PieceTest;

import it.uniba.piece.Piece;
import it.uniba.piece.bishop.Bishop;
import it.uniba.piece.queen.Queen;
import it.uniba.piece.rook.Rook;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static it.uniba.chessboard.Chessboard.MAX_ALT;
import static it.uniba.chessboard.Chessboard.MAX_LUNG;
import static org.junit.jupiter.api.Assertions.*;

class QueenTest {
    Queen qTest;
    Piece chess[][];

    @Test
    void getIconWhiteTest() {
        qTest = new Queen();
        qTest.setColor("white");

        chess= new Piece[MAX_ALT][MAX_LUNG];
        chess[3][7] = qTest;

        assertEquals(" \u2655 ", qTest.getIcon());
    }

    @Test
    void getIconBlackTest() {
        qTest = new Queen();
        qTest.setColor("black");

        chess= new Piece[MAX_ALT][MAX_LUNG];
        chess[3][7] = qTest;

        assertEquals(" \u265b ", qTest.getIcon());
    }

    @Test
    void getValuePointTest() {
        qTest = new Queen();
        int valueP;
        valueP = qTest.getValuePoint();
        assertEquals(9, valueP);
    }

    @Test
    void moveWhiteTest() {
        qTest = new Queen();
        qTest.setColor("white");


        chess= new Piece[MAX_ALT][MAX_LUNG];
        for(int i = 0; i <MAX_ALT; i++) {
            for (int j = 0; j < MAX_LUNG; j++) {
                chess[i][j] = new Piece();
            }
        }
        chess[3][7] = qTest;


        List<int[]> result = new ArrayList<int[]>();
        List<int[]> espected = new ArrayList<int[]>();
        espected.add(new int[]{7, 2});
        espected.add(new int[]{7, 1});
        espected.add(new int[]{7, 0});
        espected.add(new int[]{7, 4});
        espected.add(new int[]{7, 5});
        espected.add(new int[]{7, 6});
        espected.add(new int[]{7, 7});
        espected.add(new int[]{6, 3});
        espected.add(new int[]{5, 3});
        espected.add(new int[]{4, 3});
        espected.add(new int[]{3, 3});
        espected.add(new int[]{2, 3});
        espected.add(new int[]{1, 3});
        espected.add(new int[]{0, 3});
        espected.add(new int[]{6, 4});
        espected.add(new int[]{5, 5});
        espected.add(new int[]{4, 6});
        espected.add(new int[]{3, 7});
        espected.add(new int[]{6, 2});
        espected.add(new int[]{5, 1});
        espected.add(new int[]{4, 0});



        int[] posizioneIniziale = new int[]{7, 3};
        short turn=0;

        result= qTest.move(posizioneIniziale, "white", chess, turn);

        for(int i=0; i<espected.size();i++)
        {
            assertArrayEquals(espected.get(i), result.get(i));
        }
    }

    @Test
    void moveWhiteCaptureTest() {
        qTest = new Queen();
        qTest.setColor("white");

        Rook black = new Rook();
        black.setColor("black");


        chess= new Piece[MAX_ALT][MAX_LUNG];
        for(int i = 0; i <MAX_ALT; i++) {
            for (int j = 0; j < MAX_LUNG; j++) {
                chess[i][j] = new Piece();
            }
        }
        chess[3][7] = qTest;
        chess[3][6] = black;


        List<int[]> result = new ArrayList<int[]>();
        List<int[]> espected = new ArrayList<int[]>();
        //Horizontal moves
        espected.add(new int[]{7, 2});
        espected.add(new int[]{7, 1});
        espected.add(new int[]{7, 0});
        espected.add(new int[]{7, 4});
        espected.add(new int[]{7, 5});
        espected.add(new int[]{7, 6});
        espected.add(new int[]{7, 7});
        //Vertical moves
        espected.add(new int[]{6, 3});
        espected.add(new int[]{6, 4});
        espected.add(new int[]{5, 5});
        espected.add(new int[]{4, 6});
        espected.add(new int[]{3, 7});
        espected.add(new int[]{6, 2});
        espected.add(new int[]{5, 1});
        espected.add(new int[]{4, 0});



        int[] posizioneIniziale = new int[]{7, 3};
        short turn=0;

        result= qTest.move(posizioneIniziale, "white", chess, turn);

        for(int i=0; i<espected.size();i++)
        {
            assertArrayEquals(espected.get(i), result.get(i));
        }
    }

    @Test
    void moveBlackTest() {
        qTest = new Queen();
        qTest.setColor("black");


        chess= new Piece[MAX_ALT][MAX_LUNG];
        for(int i = 0; i <MAX_ALT; i++) {
            for (int j = 0; j < MAX_LUNG; j++) {
                chess[i][j] = new Piece();
            }
        }
        chess[3][0] = qTest;


        List<int[]> result = new ArrayList<int[]>();
        List<int[]> espected = new ArrayList<int[]>();
        espected.add(new int[]{0, 4});
        espected.add(new int[]{0, 5});
        espected.add(new int[]{0, 6});
        espected.add(new int[]{0, 7});
        espected.add(new int[]{0, 2});
        espected.add(new int[]{0, 1});
        espected.add(new int[]{0, 0});
        espected.add(new int[]{1, 3});
        espected.add(new int[]{2, 3});
        espected.add(new int[]{3, 3});
        espected.add(new int[]{4, 3});
        espected.add(new int[]{5, 3});
        espected.add(new int[]{6, 3});
        espected.add(new int[]{7, 3});
        espected.add(new int[]{1, 4});
        espected.add(new int[]{2, 5});
        espected.add(new int[]{3, 6});
        espected.add(new int[]{4, 7});
        espected.add(new int[]{1, 2});
        espected.add(new int[]{2, 1});
        espected.add(new int[]{3, 0});



        int[] posizioneIniziale = new int[]{0, 3};
        short turn=1;

        result= qTest.move(posizioneIniziale, "black", chess, turn);

        for(int i=0; i<20;i++)
        {
            assertArrayEquals(espected.get(i), result.get(i));
        }
    }

    @Test
    void moveBlackCaptureTest() {
        qTest = new Queen();
        qTest.setColor("black");

        Rook white = new Rook();
        white.setColor("white");


        chess= new Piece[MAX_ALT][MAX_LUNG];
        for(int i = 0; i <MAX_ALT; i++) {
            for (int j = 0; j < MAX_LUNG; j++) {
                chess[i][j] = new Piece();
            }
        }
        chess[3][7] = qTest;
        chess[3][6] = white;


        List<int[]> result = new ArrayList<int[]>();
        List<int[]> espected = new ArrayList<int[]>();
        //Horizontal moves
        espected.add(new int[]{7, 4});
        espected.add(new int[]{7, 5});
        espected.add(new int[]{7, 6});
        espected.add(new int[]{7, 7});
        espected.add(new int[]{7, 2});
        espected.add(new int[]{7, 1});
        espected.add(new int[]{7, 0});
        //Vertical moves
        espected.add(new int[]{6, 3});
        espected.add(new int[]{6, 2});
        espected.add(new int[]{5, 1});
        espected.add(new int[]{4, 0});
        espected.add(new int[]{6, 4});
        espected.add(new int[]{5, 5});
        espected.add(new int[]{4, 6});
        espected.add(new int[]{3, 7});



        int[] posizioneIniziale = new int[]{7, 3};
        short turn=0;

        result= qTest.move(posizioneIniziale, "black", chess, turn);

        for(int i=0; i<espected.size();i++)
        {
            assertArrayEquals(espected.get(i), result.get(i));
        }
    }

}