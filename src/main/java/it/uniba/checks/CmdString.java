package it.uniba.checks;

import java.util.ArrayList;
import java.util.List;

/**
 * <b>This is the class of CmdString</b>
 * <br><b>Responsibility</b>
 * <br>This class can:
 * <br>- store information passed in algebric notation
 * <br>This class know:
 * <br>- nothing
 * <br>
 * <br><b>This class is <i>" noECB "</i></b>.
 */
public class CmdString {

    /**
     * List of coordinates for chessboard matrix.
     */
    public int[] coords;

    /**
     * List of possible starting coordinates for chessboard matrix.
     */
    public List<int[]> possibleStartPos;

    /**
     * Positions of Pawns.
     */
    public int pawnPos;

    /**
     * Name of pieces.
     */
    public String piece;

    /**
     * Tells if capture is valide or not.
     */
    public boolean isCapture;

    /**
     * Tells if en-passant capture is valide or not.
     */
    public boolean isEnpassant;

    /**
     * List of possible position for Black's castling.
     */
    public int[] castB;

    /**
     * List of possible position for White's castling.
     */
    public int[] castW;

    /**
     * CmdString constructor.
     */
    public CmdString() {
        coords = new int[4];
        coords[0] = -1;
        possibleStartPos = new ArrayList<>();
        pawnPos = -1;
        piece = "";
        isCapture = false;
        isEnpassant = false;
        castB = new int[2];
        castW = new int[2];
    }

    /**
     * Return if castling is made by Black or White pieces.
     * @param color Pieces color
     * @return cast
     */
    public int[] getCast(String color){
        int[] cast;
        if(color.equals("white")){
            cast = castW;
        }else{
            cast = castB;
        }
        return cast;
    }
}
