package it.uniba.piece.knight;

import it.uniba.piece.Piece;
import java.util.List;


/**
 * <b>This is the class of Knight</b>
 * <br><b>Responsibility</b>
 * <br>This class can:
 * <br>- define Knight's characteristics
 * <br>- define Knight's feature
 * <br>- define Knight's movements and capture rules
 * <br>This class know:
 * <br>- what are the characteristics of a piece
 * <br>
 * <br><b>This class is <i>" entity "</i></b>.
 */
public class Knight extends Piece {

    /**
     * Black icon of Knight.
     */
    private static final String icoBlack = " \u265e ";

    /**
     * White icon of Knight.
     */
    private static final String icoWhite = " \u2658 ";

    /**
     * Knight constructor.
     */
    public Knight(){
        this.icon = " C ";
        this.name = "C";
        this.valuePoint = 3;
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
     * Defines a list of legal moves for Knight.
     * @param arg Start position for Knight
     * @param color Color of Knight
     * @param chessboard Game's cheesboard
     * @param turn Player turn
     * @return legalMoves (List of legal moves for Knight)
     */
    @Override
    public List<int[]> move(int[] arg, String color, Piece[][] chessboard, short turn) {
        int[] temp = arg.clone();
        this.legalMoves.clear();

        for(int i = 1; i<=8; i++){
            try {
                switch (i){
                    case 1: temp[1] -= 2;   //up left 3-2
                        temp[0] -= 1;
                        break;
                    case 2: temp[1] -= 1;   //up left 2-3
                        temp[0] -= 2;
                        break;
                    case 3: temp[1] -= 2;   //up right 3-2
                        temp[0] += 1;
                        break;
                    case 4: temp[1] -= 1;   //up right 2-3
                        temp[0] += 2;
                        break;
                    case 5: temp[1] += 2;   //down right 3-2
                        temp[0] += 1;
                        break;
                    case 6: temp[1] += 1;   //down right 2-3
                        temp[0] += 2;
                        break;
                    case 7: temp[1] += 2;   //down left 3-2
                        temp[0] -= 1;
                        break;
                    case 8: temp[1] += 1;   //down left 2-3
                        temp[0] -= 2;
                        break;
                    default: System.out.println("errore.");
                }
                if(color.compareTo("white") == 0) {
                    if (chessboard[temp[1]][temp[0]].getColor().compareTo("invalid") == 0 || chessboard[temp[1]][temp[0]].getColor().compareTo("black") == 0) {
                        capture(temp, chessboard[temp[1]][temp[0]], color);
                        this.legalMoves.add(temp);
                    }
                }
                else if(color.compareTo("black") == 0){
                    if (chessboard[temp[1]][temp[0]].getColor().compareTo("invalid") == 0 || chessboard[temp[1]][temp[0]].getColor().compareTo("white") == 0) {
                        capture(temp, chessboard[temp[1]][temp[0]], color);
                        this.legalMoves.add(temp);
                    }
                }
            }
            catch(RuntimeException ArrayIndexOutOfBoundsException){ }
            catch(Exception ArrayIndexOutOfBoundsException){ }
            temp = arg.clone();
        }

        return this.legalMoves;
    }

    /**
     * Return Knight's value.
     * @return  valuePoint
     */
    public int getValuePoint(){
        return this.valuePoint;
    }
}
