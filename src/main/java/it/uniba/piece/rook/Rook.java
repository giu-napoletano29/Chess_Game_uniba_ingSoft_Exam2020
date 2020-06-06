package it.uniba.piece.rook;

import it.uniba.piece.Piece;
import java.util.List;


/**
 * <b>This is the class of Rook</b>
 * <br>Responsibility
 * <br>This class can:
 * <br>- define Rook's characteristics
 * <br>- define Rook's feature
 * <br>- define Rook's movements and capture rules
 * <br>This class know:
 * <br>- what are the characteristics of a piece
 * <br>
 * <br><b>This class is <i>" entity "</i></b>.
 */
public class Rook extends Piece {

    /**
     * Black icon of Rook.
     */
    protected static final String icoBlack = " \u265c ";

    /**
     * White icon of Rook.
     */
    protected static final String icoWhite = " \u2656 ";

    /**
     * Rook constructor.
     */
    public Rook(){
        this.icon = " T ";
        this.name = "T";
        this.valuePoint = 5;
    }

    /**
     * Return color of icon (white or black).
     * @return icon
     */
    public String getIcon(){

        if(this.getColor().compareTo("white") == 0){
            this.icon = icoWhite;
        }
        else if (this.getColor().compareTo("black") == 0){
            this.icon = icoBlack;
        }

        return this.icon;
    }

    /**
     * Return Rook's value.
     * @return  valuePoint
     */
    public int getValuePoint(){
        return this.valuePoint;
    }

    /**
     * Defines a list of legal moves for Rook.
     * @param arg Start position for Rook
     * @param color Color of Rook
     * @param chessboard Game's cheesboard
     * @param turn Player turn
     * @return legalMoves (List of legal moves for Rook)
     */
    @Override
    public List<int[]> move(int[] arg, String color, Piece[][] chessboard, short turn) {
        int[] temp = arg.clone();
        this.legalMoves.clear();
        this.capturesList.clear();

        //this if controls if the actual Rook is white or black, if white executes the 4 movements for white
        //otherwise it will execute the 4 black movements
        //there are 4 loops for white and 4 loops for black (one for up, one for down, one for left, one for right)
        if(chessboard[temp[1]][temp[0]].getColor().compareTo("white") == 0){

            //vertical movements for white Rook
            //from white to black (from 1 to 8)
            try {
                int s = temp[1];
                for (int i = 1; i <= s; i++) {
                    temp[1] -= i;
                    if(chessboard[temp[1]][temp[0]].getColor().compareTo("black") == 0){
                        this.legalMoves.add(temp);
                        capture(temp, chessboard[temp[1]][temp[0]], color);
                        break;
                    }
                    if (chessboard[temp[1]][temp[0]].getColor().compareTo("invalid") == 0) {
                        this.legalMoves.add(temp);
                        temp = arg.clone();
                    }else {
                        break;
                    }
                }
            }catch (Exception ArrayIndexOutOfBoundsException){
            }

            //from black back to white (from 8 to 1)
            try {
                temp = arg.clone();
                int s = temp[1];
                for (int i = s+1; i <= 7; i++) {
                    temp[1] = i;
                    if(chessboard[temp[1]][temp[0]].getColor().compareTo("black") == 0){
                        this.legalMoves.add(temp);
                        capture(temp, chessboard[temp[1]][temp[0]], color);
                        break;
                    }
                    if (chessboard[temp[1]][temp[0]].getColor().compareTo("invalid") == 0) {
                        this.legalMoves.add(temp);
                        temp = arg.clone();
                    }else {
                        break;
                    }
                }
            }catch (Exception ArrayIndexOutOfBoundsException){
            }

            //orizzontal movements for Rook
            //from left to right (from a to h)
            try {
                temp = arg.clone();
                int s = temp[0];
                for (int i = s+1; i <=7; i++) {
                    temp[0] = i;
                    if(chessboard[temp[1]][temp[0]].getColor().compareTo("black") == 0){
                        this.legalMoves.add(temp);
                        capture(temp, chessboard[temp[1]][temp[0]], color);
                        break;
                    }
                    if (chessboard[temp[1]][temp[0]].getColor().compareTo("invalid") == 0) {
                        this.legalMoves.add(temp);
                        temp = arg.clone();
                    }else {
                        break;
                    }
                }
            }catch (Exception ArrayIndexOutOfBoundsException){
            }

            //from right to left (from h to a)
            try {
                temp = arg.clone();
                int s = temp[0];
                for (int i = s-1; i >=0; i--) {
                    temp[0] = i;
                    if(chessboard[temp[1]][temp[0]].getColor().compareTo("black") == 0){
                        this.legalMoves.add(temp);
                        capture(temp, chessboard[temp[1]][temp[0]], color);
                        break;
                    }
                    if (chessboard[temp[1]][temp[0]].getColor().compareTo("invalid") == 0) {
                        this.legalMoves.add(temp);
                        temp = arg.clone();
                    }else {
                        break;
                    }
                }
            }catch (Exception ArrayIndexOutOfBoundsException){
            }

        }else{ //if not white check if black
            if(chessboard[temp[1]][temp[0]].getColor().compareTo("black") == 0){
                //vertical movements for white Rook
                //from black to white (from 8 to 1)
                try {
                    int s = temp[1];
                    for (int i = s+1; i <=7; i++) {
                        temp[1] = i;
                        if(chessboard[temp[1]][temp[0]].getColor().compareTo("white") == 0){
                            this.legalMoves.add(temp);
                            capture(temp, chessboard[temp[1]][temp[0]], color);
                            break;
                        }
                        if (chessboard[temp[1]][temp[0]].getColor().compareTo("invalid") == 0) {
                            this.legalMoves.add(temp);
                            temp = arg.clone();
                        }else {
                            break;
                        }
                    }
                }catch (Exception ArrayIndexOutOfBoundsException){
                }

                //from white to black (from 1 to 8)
                try {
                    temp = arg.clone();
                    int s = temp[1];
                    for (int i = s-1; i >=0; i--) {
                        temp[1] = i;
                        if(chessboard[temp[1]][temp[0]].getColor().compareTo("white") == 0){
                            this.legalMoves.add(temp);
                            capture(temp, chessboard[temp[1]][temp[0]], color);
                            break;
                        }
                        if (chessboard[temp[1]][temp[0]].getColor().compareTo("invalid") == 0) {
                            this.legalMoves.add(temp);
                            temp = arg.clone();
                        }else {
                            break;
                        }
                    }
                }catch (Exception ArrayIndexOutOfBoundsException){
                }

                //orizzontal movements for Rook
                //from left to right (from a to h)
                try {
                    temp = arg.clone();
                    int s = temp[0];
                    for (int i = s+1; i <= 7; i++) {
                        temp[0] = i;
                        if(chessboard[temp[1]][temp[0]].getColor().compareTo("white") == 0){
                            this.legalMoves.add(temp);
                            capture(temp, chessboard[temp[1]][temp[0]], color);
                            break;
                        }
                        if (chessboard[temp[1]][temp[0]].getColor().compareTo("invalid") == 0) {
                            this.legalMoves.add(temp);
                            temp = arg.clone();
                        }else {
                            break;
                        }
                    }
                }catch (Exception ArrayIndexOutOfBoundsException){
                }

                //from right to left (from h to a)
                try {
                    temp = arg.clone();
                    int s = temp[0];
                    for (int i = s-1; i >=0; i--) {
                        temp[0] = i;
                        if(chessboard[temp[1]][temp[0]].getColor().compareTo("white") == 0){
                            this.legalMoves.add(temp);
                            capture(temp, chessboard[temp[1]][temp[0]], color);
                            break;
                        }
                        if (chessboard[temp[1]][temp[0]].getColor().compareTo("invalid") == 0) {
                            this.legalMoves.add(temp);
                            temp = arg.clone();
                        }else {
                            break;
                        }
                    }
                }catch (Exception ArrayIndexOutOfBoundsException){
                }
            }
        }
        return this.legalMoves;
    }
}
