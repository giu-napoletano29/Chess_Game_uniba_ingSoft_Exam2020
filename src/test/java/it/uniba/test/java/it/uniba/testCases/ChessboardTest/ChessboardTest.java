package it.uniba.test.java.it.uniba.testCases.ChessboardTest;

import it.uniba.chessboard.Chessboard;
import it.uniba.piece.Piece;
import it.uniba.piece.bishop.Bishop;
import it.uniba.piece.king.King;
import it.uniba.piece.knight.Knight;
import it.uniba.piece.pawn.Pawn;
import it.uniba.piece.queen.Queen;
import it.uniba.piece.rook.Rook;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import static it.uniba.chessboard.Chessboard.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ChessboardTest {

    @Test
    void displayTest() throws UnsupportedEncodingException, FileNotFoundException {
        Chessboard c = new Chessboard();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String s = "";
        s = s +System.getProperty("line.separator");
        s = s + "    a  b  c  d  e  f  g  h" + System.getProperty("line.separator");
        s = s + " ";
        s = s + (MAX_ALT-0) + " ";
        s = s + WHITE;

        String expected = s;

        c.display();

        try{
            assertTrue(outContent.toString("UTF-8").contains(expected));
        } catch (Exception e){
            System.out.println("errore");
        }
    }

    @Ignore
    void updatetest() {

    }

}
