package it.uniba.piece.king;

import it.uniba.checks.Checks;
import it.uniba.piece.Piece;
import java.util.List;


/**
 * <b>This is the class of King</b>
 * <br><b>Responsibility</b>
 * <br>This class can:
 * <br>- define King's characteristics
 * <br>- define King's feature
 * <br>- define King's movements and capture rules
 * <br>This class know:
 * <br>- what are the characteristics of a piece
 * <br>
 * <br><b>This class is <i>" entity "</i></b>.
 */
public class King extends Piece {

    /**
     * Black icon of King.
     */
    private static final String icoBlack = " \u265a ";

    /**
     * White icon of King.
     */
    private static final String icoWhite = " \u2654 ";

    /**
     * King constructor.
     */
    public King(){
        this.icon = " R ";
        this.name = "R";
        this.valuePoint = 99;
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
     * Defines a list of legal moves for King.
     * @param arg Start position for King
     * @param color Color of King
     * @param chessboard Game's cheesboard
     * @param turn Player turn
     * @return legalMoves (List of legal moves for King)
     */
    @Override
    public List<int[]> move(int[] arg, String color, Piece[][] chessboard, short turn) {
        int[] temp = arg.clone();
        int[] pos = chessboard[temp[1]][temp[0]].getPosition();
        this.legalMoves.clear();

        for(int i = 1; i<=8; i++){
            try {
                Checks.kingPosAround(temp, i);
                if(color.compareTo("white") == 0) {
                    if (chessboard[temp[1]][temp[0]].getColor().compareTo("invalid") == 0 || chessboard[temp[1]][temp[0]].getColor().compareTo("black") == 0) {
                        capture(temp, chessboard[temp[1]][temp[0]], color);
                        if(!Checks.isCheck(temp, chessboard, color, turn, pos)){
                            this.legalMoves.add(temp);
                        }
                    }
                }
                else if(color.compareTo("black") == 0){
                    if (chessboard[temp[1]][temp[0]].getColor().compareTo("invalid") == 0 || chessboard[temp[1]][temp[0]].getColor().compareTo("white") == 0) {
                        capture(temp, chessboard[temp[1]][temp[0]], color);
                        if(!Checks.isCheck(temp, chessboard, color, turn, pos)){
                            this.legalMoves.add(temp);
                        }
                    }
                }
            }catch(Exception ArrayIndexOutOfBoundsException){ }
            temp = arg.clone();
        }

        //Short castling
        temp[0] += 2;
        try{
            if(chessboard[temp[1]][temp[0]+1].getName().equals("T") && chessboard[temp[1]][temp[0]+1].getMovesCount() == 0 && this.getMovesCount() == 0){
                if(chessboard[temp[1]][temp[0]].getColor().equals("invalid") && chessboard[temp[1]][temp[0]-1].getColor().equals("invalid")){
                    if(!Checks.isCheck(temp, chessboard, color, turn, pos)) {
                        temp[0] -= 1;
                        if(!Checks.isCheck(temp, chessboard, color, turn, pos)) {
                            temp[0] += 1;
                            this.legalMoves.add(temp);
                        }
                    }
                }
            }
        }catch (Exception ArrayIndexOutOfBoundsException){
        }
        //End Short Castling
        temp = arg.clone();
        //Long castling
        temp[0] -= 2;
        try{
            if(chessboard[temp[1]][temp[0]-2].getName().equals("T") && chessboard[temp[1]][temp[0]-2].getMovesCount() == 0 && this.getMovesCount() == 0){
                if(chessboard[temp[1]][temp[0]].getColor().equals("invalid") && chessboard[temp[1]][temp[0]+1].getColor().equals("invalid")){
                    if(!Checks.isCheck(temp, chessboard, color, turn, pos)) {
                        temp[0] += 1;
                        if(!Checks.isCheck(temp, chessboard, color, turn, pos)) {
                            temp[0] -= 1;
                            this.legalMoves.add(temp);
                        }
                    }
                }
            }
        }catch (Exception ArrayIndexOutOfBoundsException){
        }
        //End long Castling

        return this.legalMoves;
    }

    /**
     * Return King's value.
     * @return  valuePoint
     */
    public int getValuePoint(){
        return this.valuePoint;
    }
}
