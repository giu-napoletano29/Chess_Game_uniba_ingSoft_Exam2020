package it.uniba.test.java.it.uniba.testCases.PieceTest;

import it.uniba.piece.Piece;
import it.uniba.piece.bishop.Bishop;
import it.uniba.piece.rook.Rook;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static it.uniba.chessboard.Chessboard.MAX_ALT;
import static it.uniba.chessboard.Chessboard.MAX_LUNG;
import static org.junit.jupiter.api.Assertions.*;

class RookTest {
    Rook rTest;
    Piece chess[][];

    @Test
    void getIconWhiteTest() {
        rTest = new Rook();
        rTest.setColor("white");

        chess= new Piece[MAX_ALT][MAX_LUNG];
        chess[0][7] = rTest;

        assertEquals(" \u2656 ", rTest.getIcon());
    }

    @Test
    void getIconBlackTest() {
        rTest = new Rook();
        rTest.setColor("black");

        chess= new Piece[MAX_ALT][MAX_LUNG];
        chess[0][0] = rTest;

        assertEquals(" \u265c ", rTest.getIcon());
    }

    @Test
    void getValuePointTest() {
        rTest = new Rook();
        int valueP;
        valueP = rTest.getValuePoint();
        assertEquals(5, valueP);
    }

    @Test
    void moveWhiteTest() {
        rTest = new Rook();
        rTest.setColor("white");


        chess= new Piece[MAX_ALT][MAX_LUNG];
        for(int i = 0; i <MAX_ALT; i++) {
            for (int j = 0; j < MAX_LUNG; j++) {
                chess[i][j] = new Piece();
            }
        }
        chess[2][7] = rTest;
        chess[1][7] = new Piece();
        chess[0][7] = new Bishop();
        chess[0][7].setColor("black");


        List<int[]> result = new ArrayList<int[]>();
        List<int[]> espected = new ArrayList<int[]>();
        espected.add(new int[]{7, 1});
        espected.add(new int[]{7, 0});
        espected.add(new int[]{7, 3});
        espected.add(new int[]{7, 4});
        espected.add(new int[]{7, 5});
        espected.add(new int[]{7, 6});
        espected.add(new int[]{7, 7});
        espected.add(new int[]{6, 2});
        espected.add(new int[]{5, 2});
        espected.add(new int[]{4, 2});
        espected.add(new int[]{3, 2});
        espected.add(new int[]{2, 2});
        espected.add(new int[]{1, 2});
        espected.add(new int[]{0, 2});



        int[] posizioneIniziale = new int[]{7, 2};
        short turn=0;

        result= rTest.move(posizioneIniziale, "white", chess, turn);
        for(int i=0; i<14;i++)
        {
            assertArrayEquals(espected.get(i), result.get(i));
        }
    }

    @Test
    void moveBlackTest() {
        rTest = new Rook();
        rTest.setColor("black");


        chess= new Piece[MAX_ALT][MAX_LUNG];
        for(int i = 0; i <MAX_ALT; i++) {
            for (int j = 0; j < MAX_LUNG; j++) {
                chess[i][j] = new Piece();
            }
        }
        chess[0][0] = rTest;


        List<int[]> result = new ArrayList<int[]>();
        List<int[]> espected = new ArrayList<int[]>();
        espected.add(new int[]{0, 1});
        espected.add(new int[]{0, 2});
        espected.add(new int[]{0, 3});
        espected.add(new int[]{0, 4});
        espected.add(new int[]{0, 5});
        espected.add(new int[]{0, 6});
        espected.add(new int[]{0, 7});
        espected.add(new int[]{1, 0});
        espected.add(new int[]{2, 0});
        espected.add(new int[]{3, 0});
        espected.add(new int[]{4, 0});
        espected.add(new int[]{5, 0});
        espected.add(new int[]{6, 0});
        espected.add(new int[]{7, 0});



        int[] posizioneIniziale = new int[]{0, 0};
        short turn=1;

        result= rTest.move(posizioneIniziale, "black", chess, turn);

        for(int i=0; i<14;i++)
        {
            assertArrayEquals(espected.get(i), result.get(i));
        }
    }
}