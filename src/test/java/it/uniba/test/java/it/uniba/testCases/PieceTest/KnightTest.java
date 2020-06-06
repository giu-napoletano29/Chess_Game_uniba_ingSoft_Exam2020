package it.uniba.test.java.it.uniba.testCases.PieceTest;

import it.uniba.piece.Piece;
import org.junit.jupiter.api.Test;
import it.uniba.piece.knight.Knight;

import java.util.ArrayList;
import java.util.List;

import static it.uniba.chessboard.Chessboard.MAX_ALT;
import static it.uniba.chessboard.Chessboard.MAX_LUNG;
import static org.junit.jupiter.api.Assertions.*;

class KnightTest {
    Knight knTest;
    Piece chess[][];

    @Test
    void getIconWhiteTest() {
        knTest = new Knight();
        knTest.setColor("white");

        chess= new Piece[MAX_ALT][MAX_LUNG];
        chess[0][6] = knTest;

        assertEquals(" \u2658 ", knTest.getIcon());
    }

    @Test
    void getIconBlackTest() {
        knTest = new Knight();
        knTest.setColor("black");

        chess= new Piece[MAX_ALT][MAX_LUNG];
        chess[0][6] = knTest;

        assertEquals(" \u265e ", knTest.getIcon());
    }

    @Test
    void getValuePointTest() {
        knTest = new Knight();
        int valueP;
        valueP = knTest.getValuePoint();
        assertEquals(3, valueP);
    }

    @Test
    void moveWhiteTest() {
        knTest = new Knight();
        knTest.setColor("white");

        chess= new Piece[MAX_ALT][MAX_LUNG];
        for(int i = 0; i <MAX_ALT; i++) {
            for (int j = 0; j < MAX_LUNG; j++) {
                chess[i][j] = new Piece();
            }
        }
        chess[1][7] = knTest;

        List<int[]> result = new ArrayList<int[]>();
        List<int[]> espected = new ArrayList<int[]>();
        espected.add(new int[]{0, 5});
        espected.add(new int[]{2, 5});
        espected.add(new int[]{3, 6});

        int[] posizioneIniziale = new int[]{1, 7};
        short turn=0;

        result= knTest.move(posizioneIniziale, "white", chess, turn);

        for(int i=0; i<3;i++)
        {
            assertArrayEquals(espected.get(i), result.get(i));
        }
    }

    @Test
    void moveBlackTest() {
        knTest = new Knight();
        knTest.setColor("black");

        chess= new Piece[MAX_ALT][MAX_LUNG];
        for(int i = 0; i <MAX_ALT; i++) {
            for (int j = 0; j < MAX_LUNG; j++) {
                chess[i][j] = new Piece();
            }
        }
        chess[1][0] = knTest;

        List<int[]> result = new ArrayList<int[]>();
        List<int[]> espected = new ArrayList<int[]>();
        espected.add(new int[]{2, 0});
        espected.add(new int[]{1, 3});
        espected.add(new int[]{2, 2});

        int[] posizioneIniziale = new int[]{0, 1};
        short turn=1;

        result= knTest.move(posizioneIniziale, "black", chess, turn);

        for(int i=0; i<3;i++)
        {
            assertArrayEquals(espected.get(i), result.get(i));
        }
    }

}