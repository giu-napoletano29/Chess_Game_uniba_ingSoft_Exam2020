package it.uniba.piece.bishop;

import it.uniba.piece.Piece;

import java.util.List;

/**
 * <b>This is the class of Bishop</b>
 * <br><b>Responsibility</b>
 * <br>This class can:
 * <br>- define Bishop's characteristics
 * <br>- define Bishop's feature
 * <br>- define Bishop's movements and capture rules
 * <br>This class know:
 * <br>- what are the characteristics of a piece
 * <br>
 * <br><b>This class is <i>" entity "</i></b>.
 */
public class Bishop extends Piece {

    /**
     * Black icon of Bishop.
     */
    private static final String icoBlack = " \u265d ";

    /**
     * White icon of Bishop.
     */
    private static final String icoWhite = " \u2657 ";

    /**
     * Bishop constructor.
     */
    public Bishop(){
        this.icon = " A ";
        this.name = "A";
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
     * Return Bishop's value.
     * @return  valuePoint
     */
    public int getValuePoint(){
        return this.valuePoint;
    }

    /**
     * Defines a list of legal moves for Bishop.
     * @param arg Start position for Bishop
     * @param color Color of Bishop
     * @param chessboard Game's cheesboard
     * @param turn Player turn
     * @return legalMoves (List of legal moves for Bishop)
     */
    @Override
    public List<int[]> move(int[] arg, String color, Piece[][] chessboard, short turn) {
        int[] temp = arg.clone();
        this.legalMoves.clear();
        this.capturesList.clear();

        //this if controls if the actual Bishop is white or black, if white executes the 4 movements for white
        //otherwise it will execute the 4 black movements
        //there are 4 loops for white and 4 loops for black (one for up, one for down, one for left, one for right)
        if (chessboard[temp[1]][temp[0]].getColor().compareTo("white") == 0) {

            //diagonal movements for white Bishop
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
                //diagonal movements for black Bishop
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
}