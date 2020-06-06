package it.uniba.test.java.it.uniba.testCases.ChecksTest;

import it.uniba.checks.CmdString;
import it.uniba.piece.Piece;
import it.uniba.piece.king.King;
import it.uniba.piece.queen.Queen;
import it.uniba.piece.rook.Rook;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import it.uniba.checks.Checks;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import static it.uniba.checks.Checks.*;
import static it.uniba.chessboard.Chessboard.MAX_ALT;
import static it.uniba.chessboard.Chessboard.MAX_LUNG;
import static org.junit.jupiter.api.Assertions.*;

class ChecksTest {
    Piece chess[][];
    Rook rTest;

    @Test
    void checkCoordinatesTest() throws UnsupportedEncodingException, FileNotFoundException {
        String test = "a";
        int coordTranslated = checkCoordinates(test);
        assertEquals(0, coordTranslated);

        test = "b";
        coordTranslated = checkCoordinates(test);
        assertEquals(1, coordTranslated);

        test = "c";
        coordTranslated = checkCoordinates(test);
        assertEquals(2, coordTranslated);

        test = "d";
        coordTranslated = checkCoordinates(test);
        assertEquals(3, coordTranslated);

        test = "e";
        coordTranslated = checkCoordinates(test);
        assertEquals(4, coordTranslated);

        test = "f";
        coordTranslated = checkCoordinates(test);
        assertEquals(5, coordTranslated);

        test = "g";
        coordTranslated = checkCoordinates(test);
        assertEquals(6, coordTranslated);

        test = "h";
        coordTranslated = checkCoordinates(test);
        assertEquals(7, coordTranslated);

        test = "m";
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        checkCoordinates(test);
        String expected = "Errore di traduzione coordinate! " + System.getProperty("line.separator");
        try{
            assertEquals( expected, outContent.toString( "UTF-8"));
        } catch (Exception e){
            System.out.println("errore");
        }
    }

    @Test
    void numTranslationTest() {
        int test = 0;
        int numTranslated = numTranslation(test);
        assertEquals(8, numTranslated);

        test = 1;
        numTranslated = numTranslation(test);
        assertEquals(7, numTranslated);

        test = 2;
        numTranslated = numTranslation(test);
        assertEquals(6, numTranslated);

        test = 3;
        numTranslated = numTranslation(test);
        assertEquals(5, numTranslated);

        test = 5;
        numTranslated = numTranslation(test);
        assertEquals(3, numTranslated);

        test = 4;
        numTranslated = numTranslation(test);
        assertEquals(4, numTranslated);

        test = 6;
        numTranslated = numTranslation(test);
        assertEquals(2, numTranslated);

        test = 7;
        numTranslated = numTranslation(test);
        assertEquals(1, numTranslated);

        test = 8;
        numTranslated = numTranslation(test);
        assertEquals(0, numTranslated);

        test = 9;
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        numTranslation(test);
        String expected = "Errore di traduzione! " + System.getProperty("line.separator");
        try{
            assertEquals( expected, outContent.toString( "UTF-8"));
        } catch (Exception e) {
            System.out.println("errore");
        }
    }

    @Test
    void insAlgebricPosTest() {
        CmdString test;
        CmdString espected = new CmdString();

        test = insAlgebricPos("a2 a4");
        espected.coords[0] = 0;
        espected.coords[1] = 6;
        espected.coords[2] = 0;
        espected.coords[3] = 4;

        for(int i=0; i<4;i++)
        {
            assertEquals(espected.coords[i], test.coords[i]);
        }

    }

    @Test
    void insShortAlgebricPosTest() {
        CmdString espected = new CmdString();
        espected.coords[0] = -1;
        espected.coords[3] = 4;
        espected.isCapture = false;
        espected.isEnpassant = false;
        espected.possibleStartPos.add(new int[]{0, 7});

        CmdString test;


        rTest = new Rook();
        rTest.setColor("white");

        String mossa = "Ta4";
        short turn = 0;

        chess= new Piece[MAX_ALT][MAX_LUNG];
        for(int i = 0; i <MAX_ALT; i++) {
            for (int j = 0; j < MAX_LUNG; j++) {
                chess[i][j] = new Piece();
            }
        }
        chess[0][7] = rTest;
        rTest.setPosition(new int[] {0,7});

        test = insShortAlgebricPos(mossa, chess, turn);

        assertArrayEquals(espected.coords, test.coords);

        for(int i=0; i<test.possibleStartPos.size(); i++)
        {
            assertArrayEquals(espected.possibleStartPos.get(i), test.possibleStartPos.get(i));
        }

        assertEquals(espected.pawnPos, test.pawnPos);
        assertEquals(espected.piece, test.piece);
        assertEquals(espected.isCapture, test.isCapture);
        assertEquals(espected.isEnpassant, test.isEnpassant);

        mossa = "Taa4";
        test = insShortAlgebricPos(mossa, chess, turn);
        espected.coords[0] = 0;
        espected.coords[3] = 4;

        assertArrayEquals(espected.coords, test.coords);

        mossa = "c4";
        test = insShortAlgebricPos(mossa, chess, turn);
        espected.coords[0] = -1;
        espected.coords[2] = 2;
        espected.coords[3] = 4;

        assertArrayEquals(espected.coords, test.coords);

        espected = new CmdString();
        mossa = "Taxa4";
        test = insShortAlgebricPos(mossa, chess, turn);
        espected.piece = "T";
        espected.isCapture = true;
        espected.coords[0] = 0;
        espected.coords[2] = 0;
        espected.coords[3] = 4;
        assertArrayEquals(espected.coords, test.coords);

        espected = new CmdString();
        mossa = "bxa4";
        test = insShortAlgebricPos(mossa, chess, turn);
        espected.pawnPos = 1;
        espected.isCapture = true;
        espected.coords[2] = 0;
        espected.coords[3] = 4;
        assertArrayEquals(espected.coords, test.coords);


        espected = new CmdString();
        mossa = "0-0";
        chess[0][0] = new King();
        chess[0][0].setPosition(new int[] {0,0});
        turn = 0;
        test = insShortAlgebricPos(mossa, chess, turn);
        espected.coords[3] = 0;
        espected.coords[2] = 6;
        espected.castB[1] = 0;
        espected.castB[0] =  5;
        assertArrayEquals(espected.coords, test.coords);


        turn = 1;
        test = insShortAlgebricPos(mossa, chess, turn);
        espected.coords[3] = 7;
        espected.coords[2] = 6;
        espected.castW[1] = 7;
        espected.castW[0] =  5;
        assertArrayEquals(espected.coords, test.coords);

        espected = new CmdString();
        mossa = "0-0-0";
        turn = 0;
        test = insShortAlgebricPos(mossa, chess, turn);
        espected.coords[3] = 0;
        espected.coords[2] = 2;
        espected.castB[1] = 0;
        espected.castB[0] =  3;
        assertArrayEquals(espected.coords, test.coords);


        turn = 1;
        test = insShortAlgebricPos(mossa, chess, turn);
        espected.coords[3] = 7;
        espected.coords[2] = 2;
        espected.castW[1] = 7;
        espected.castW[0] =  3;
        assertArrayEquals(espected.coords, test.coords);

    }

    @Test
    void filterStartPosTest() {
        CmdString espected = new CmdString();
        CmdString test;
        rTest = new Rook();
        Rook r2Test = new Rook();

        short turn = 0;

        chess= new Piece[MAX_ALT][MAX_LUNG];
        for(int i = 0; i <MAX_ALT; i++) {
            for (int j = 0; j < MAX_LUNG; j++) {
                chess[i][j] = new Piece();
            }
        }
        chess[0][7] = rTest;
        rTest.setColor("white");
        chess[0][0] = r2Test;
        r2Test.setColor("black");
        rTest.setPosition(new int[]{0, 7});
        r2Test.setPosition(new int[]{0, 0});

        espected.possibleStartPos.add(r2Test.getPosition());
        espected.possibleStartPos.add(rTest.getPosition());

        test = insShortAlgebricPos("Ta4", chess, turn);

        test = filterStartPos(test, turn, chess);

        for(int i=0; i<test.possibleStartPos.size(); i++)
        {
            assertArrayEquals(espected.possibleStartPos.get(i), test.possibleStartPos.get(i));
        }


    }

    @Test
    void checkTurnTest() {
        rTest = new Rook();
        rTest.setColor("white");
        short turn = 1;

        chess= new Piece[MAX_ALT][MAX_LUNG];
        for(int i = 0; i <MAX_ALT; i++) {
            for (int j = 0; j < MAX_LUNG; j++) {
                chess[i][j] = new Piece();
            }
        }
        chess[0][7] = rTest;
        rTest.setPosition(new int[]{0,7});
        int[] pos = {7, 0};

        assertTrue(checkTurn(pos, chess, turn));

    }

    @Test
    void checkLegalMovesTest() {
        rTest = new Rook();
        rTest.setColor("white");
        List<int[]> legal;

        chess= new Piece[MAX_ALT][MAX_LUNG];
        for(int i = 0; i <MAX_ALT; i++) {
            for (int j = 0; j < MAX_LUNG; j++) {
                chess[i][j] = new Piece();
            }
        }
        chess[0][7] = rTest;
        rTest.setPosition(new int[]{0,7});
        int[] mossa = {4, 0};
        short turn = 1;
        legal = rTest.move(new int[]{7, 0}, rTest.getColor(), chess, turn);

        assertTrue(checkLegalMoves(mossa, legal));


    }

    @Test
    void reverseCheckCoordinatesTest() {
        int test = 0;
        char charTranslated = reverseCheckCoordinates(test);
        assertEquals('a', charTranslated);

        test = 1;
        charTranslated = reverseCheckCoordinates(test);
        assertEquals('b', charTranslated);

        test = 2;
        charTranslated = reverseCheckCoordinates(test);
        assertEquals('c', charTranslated);

        test = 3;
        charTranslated = reverseCheckCoordinates(test);
        assertEquals('d', charTranslated);

        test = 4;
        charTranslated = reverseCheckCoordinates(test);
        assertEquals('e', charTranslated);

        test = 5;
        charTranslated = reverseCheckCoordinates(test);
        assertEquals('f', charTranslated);

        test = 6;
        charTranslated = reverseCheckCoordinates(test);
        assertEquals('g', charTranslated);

        test = 7;
        charTranslated = reverseCheckCoordinates(test);
        assertEquals('h', charTranslated);

    }

    @Test
    void isCheckTest() {
        King kTest = new King();
        kTest.setColor("white");

        Queen qTest = new Queen();
        qTest.setColor("black");

        short turn = 1;

        chess= new Piece[MAX_ALT][MAX_LUNG];
        for(int i = 0; i <MAX_ALT; i++) {
            for (int j = 0; j < MAX_LUNG; j++) {
                chess[i][j] = new Piece();
            }
        }
        chess[1][7] = kTest;
        kTest.setPosition(new int[]{7, 1});
        chess[0][0] = qTest;

        int[] mossa = {0, 7};

        assertTrue(isCheck(mossa, chess, "white", turn, kTest.getPosition()));


    }
}
