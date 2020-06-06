package it.uniba.piece.queen;

import it.uniba.piece.Piece;
import java.util.List;

/**
 * <b>This is the class of Queen</b>
 * <br><b>Responsibility</b>
 * <br>This class can:
 * <br>- define Queen's characteristics
 * <br>- define Queen's feature
 * <br>- define Queen's movements and capture rules
 * <br>This class know:
 * <br>- what are the characteristics of a piece
 * <br>
 * <br><b>This class is <i>" entity "</i></b>.
 */
public class Queen extends Piece {

    /**
     * Black icon of Queen.
     */
    private static final String icoBlack = " \u265b ";

    /**
     * White icon of Queen.
     */
    private static final String icoWhite = " \u2655 ";

    /**
     * Queen's value for counting points.
     */
    public int valuePoint;

    /**
     * Queen constructor.
     */
    public Queen(){
        this.icon = " D ";
        this.name = "D";
        this.valuePoint = 9;
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
     * Defines a list of legal moves for Queen.
     * @param arg Start position for Queen
     * @param color Color of Queen
     * @param chessboard Game's cheesboard
     * @param turn Player turn
     * @return legalMoves (List of legal moves for Queen)
     */
    @Override
    public List<int[]> move(int[] arg, String color, Piece[][] chessboard, short turn) {
        int[] temp = arg.clone();
        this.legalMoves.clear();
        this.capturesList.clear();

        //this if controls if the actual Queen is white or black, if white executes the 4 movements for white
        //otherwise it will execute the 4 black movements
        //there are 4 loops for white and 4 loops for black (one for up, one for down, one for left, one for right)
        if(chessboard[temp[1]][temp[0]].getColor().compareTo("white") == 0){

            //vertical movements for white Queen
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

            //orizzontal movements for Queen
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
                //vertical movements for white Queen
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

                //orizzontal movements for Queen
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

        temp = arg.clone();

        if (chessboard[temp[1]][temp[0]].getColor().compareTo("white") == 0) {

            //diagonal movements for white Queen
            //from the lower left corner to the upper right corner (from a1 to h8)
            try {
                int s = temp[1];
                for (int i = 1; i <= s; i++) {
                    temp[1] -= i;
                    temp[0] +=i;
                    if (chessboard[temp[1]][temp[0]].getColor().compareTo("black") == 0) {
                        this.legalMoves.add(temp);
                        capture(temp, chessboard[temp[1]][temp[0]], color);
                        break;
                    }
                    if (chessboard[temp[1]][temp[0]].getColor().compareTo("invalid") == 0) {
                        this.legalMoves.add(temp);
                        temp = arg.clone();
                    } else {
                        break;
                    }
                }
            } catch (Exception ArrayIndexOutOfBoundsException) {
            }

            //from the upper right corner to the lower left corner (from h8 to a1)
            try {
                temp = arg.clone();
                int s = temp[1];
                int j = temp[0]-1;
                for (int i = s + 1; i <= 7; i++) {
                    temp[1] = i;
                    temp[0]= j;
                    if (chessboard[temp[1]][temp[0]].getColor().compareTo("black") == 0) {
                        this.legalMoves.add(temp);
                        capture(temp, chessboard[temp[1]][temp[0]], color);
                        break;
                    }
                    if (chessboard[temp[1]][temp[0]].getColor().compareTo("invalid") == 0) {
                        this.legalMoves.add(temp);
                        temp = arg.clone();
                    } else {
                        break;
                    }
                    j--;
                }
            } catch (Exception ArrayIndexOutOfBoundsException) {
            }

            //from upper left corner to lower right corner (from a8 to h1)
            try {
                temp = arg.clone();
                int s = temp[0];
                int j = temp[1]+1;
                for (int i = s + 1; i <= 7; i++) {
                    temp[0] = i;
                    temp[1] = j;
                    if (chessboard[temp[1]][temp[0]].getColor().compareTo("black") == 0) {
                        this.legalMoves.add(temp);
                        capture(temp, chessboard[temp[1]][temp[0]], color);
                        break;
                    }
                    if (chessboard[temp[1]][temp[0]].getColor().compareTo("invalid") == 0) {
                        this.legalMoves.add(temp);
                        temp = arg.clone();
                    } else {
                        break;
                    }
                    j++;
                }
            } catch (Exception ArrayIndexOutOfBoundsException) {
            }

            //from the lower right corner to the upper left corner (from h1 to a8)
            try {
                temp = arg.clone();
                int s = temp[0];
                int j =temp[1]-1;
                for (int i = s - 1; i >= 0; i--) {
                    temp[0] = i;
                    temp[1] = j;
                    if (chessboard[temp[1]][temp[0]].getColor().compareTo("black") == 0) {
                        this.legalMoves.add(temp);
                        capture(temp, chessboard[temp[1]][temp[0]], color);
                        break;
                    }
                    if (chessboard[temp[1]][temp[0]].getColor().compareTo("invalid") == 0) {
                        this.legalMoves.add(temp);
                        temp = arg.clone();
                    } else {
                        break;
                    }
                    j--;
                }
            } catch (Exception ArrayIndexOutOfBoundsException) {
            }

        } else { //if not white check if black
            if (chessboard[temp[1]][temp[0]].getColor().compareTo("black") == 0) {
                //diagonal movements for black Queen
                //from upper left corner to lower right corner (from a8 to h1)
                try {
                    int s = temp[1];
                    int j = temp[0]+1;
                    for (int i = s + 1; i <= 7; i++) {
                        temp[1] = i;
                        temp[0] = j;
                        if (chessboard[temp[1]][temp[0]].getColor().compareTo("white") == 0) {
                            this.legalMoves.add(temp);
                            capture(temp, chessboard[temp[1]][temp[0]], color);
                            break;
                        }
                        if (chessboard[temp[1]][temp[0]].getColor().compareTo("invalid") == 0) {
                            this.legalMoves.add(temp);
                            temp = arg.clone();
                        } else {
                            break;
                        }
                        j++;
                    }
                } catch (Exception ArrayIndexOutOfBoundsException) {
                }

                //from the lower right corner to the upper left corner (from h1 to a8)
                try {
                    temp = arg.clone();
                    int s = temp[1];
                    int j =temp[0]-1;
                    for (int i = s - 1; i >= 0; i--) {
                        temp[1] = i;
                        temp[0] = j;
                        if (chessboard[temp[1]][temp[0]].getColor().compareTo("white") == 0) {
                            this.legalMoves.add(temp);
                            capture(temp, chessboard[temp[1]][temp[0]], color);
                            break;
                        }
                        if (chessboard[temp[1]][temp[0]].getColor().compareTo("invalid") == 0) {
                            this.legalMoves.add(temp);
                            temp = arg.clone();
                        } else {
                            break;
                        }
                        j--;
                    }
                } catch (Exception ArrayIndexOutOfBoundsException) {
                }

                //from the lower left corner to the upper right corner (from a1 to h8)
                try {
                    temp = arg.clone();
                    int s = temp[0];
                    int j = temp[1]-1;
                    for (int i = s + 1; i <= 7; i++) {
                        temp[0] = i;
                        temp[1] = j;
                        if (chessboard[temp[1]][temp[0]].getColor().compareTo("white") == 0) {
                            this.legalMoves.add(temp);
                            capture(temp, chessboard[temp[1]][temp[0]], color);
                            break;
                        }
                        if (chessboard[temp[1]][temp[0]].getColor().compareTo("invalid") == 0) {
                            this.legalMoves.add(temp);
                            temp = arg.clone();
                        } else {
                            break;
                        }
                        j--;
                    }
                } catch (Exception ArrayIndexOutOfBoundsException) {
                }

                //from the upper right corner to the lower left corner (from h8 to a1)
                try {
                    temp = arg.clone();
                    int s = temp[0];
                    int j =temp[1]+1;
                    for (int i = s - 1; i >= 0; i--) {
                        temp[0] = i;
                        temp[1] = j;
                        if (chessboard[temp[1]][temp[0]].getColor().compareTo("white") == 0) {
                            this.legalMoves.add(temp);
                            capture(temp, chessboard[temp[1]][temp[0]], color);
                            break;
                        }
                        if (chessboard[temp[1]][temp[0]].getColor().compareTo("invalid") == 0) {
                            this.legalMoves.add(temp);
                            temp = arg.clone();
                        } else {
                            break;
                        }
                        j++;
                    }
                } catch (Exception ArrayIndexOutOfBoundsException) {
                }
            }
        }

        return this.legalMoves;
    }

    /**
     * Return Queen's value.
     * @return  valuePoint
     */
    public int getValuePoint(){
        return this.valuePoint;
    }
}
