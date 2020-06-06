package it.uniba.piece.pawn;
import it.uniba.piece.Piece;
import java.util.List;


/**
 * <b>This is the class of Pawn</b>
 * <br><b>Responsibility</b>
 * <br>This class can:
 * <br>- define Pawn's characteristics
 * <br>- define Pawn's feature
 * <br>- define Pawn's movements and capture rules
 * <br>This class know:
 * <br>- what are the characteristics of a piece
 * <br>
 * <br><b>This class is <i>" entity "</i></b>.
 */
public class Pawn extends Piece {

    /**
     * Black icon of Pawn.
     */
    protected static final String icoBlack = " \u265f ";

    /**
     * White icon of Pawn.
     */
    protected static final String icoWhite = " \u2659 ";

    //Variables for adding catches to the list
    /**
     * Pawn constructor.
     */
    public Pawn(){
        this.icon = " P ";
        this.name = "P";
        this.valuePoint = 1;
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
     * Defines a list of legal moves for Pawn.
     * @param arg Start position for Pawn
     * @param color Color of Pawn
     * @param chessboard Game's cheesboard
     * @param turn Player turn
     * @return legalMoves (List of legal moves for Pawn)
     */
    @Override
    public List<int[]> move(int[] arg, String color, Piece[][] chessboard, short turn) {
        int[] temp = arg.clone();
        this.legalMoves.clear();
        this.enCapturesList.clear();
        this.capturesList.clear();

        //Adding controls here
        //Only for Pawn needed to specify which color to test
        if(color.compareTo("white") == 0){
            temp[1] = temp[1] - 1;
            if(chessboard[temp[1]][temp[0]].getColor().compareTo("invalid") == 0){
                this.legalMoves.add(temp);
                temp = arg.clone();
                if(this.movesCount == 0){
                    temp[1] = temp[1] - 2;
                    this.legalMoves.add(temp);
                }
            }

            temp = arg.clone();
            //pawn simple Capture
            try{    //Test right oblique if a black piece is present
                temp[1] = temp[1] - 1;
                temp[0] = temp[0] + 1;
                if(chessboard[temp[1]][temp[0]].getColor().compareTo("black") == 0){
                    this.legalMoves.add(temp);
                    capture(temp);
                }
            }
            catch (Exception ArrayIndexOutOfBoundsException){
            }
            temp = arg.clone();

            try{    //test left oblique
                temp[1] = temp[1] - 1;
                temp[0] = temp[0] - 1;
                if(chessboard[temp[1]][temp[0]].getColor().compareTo("black") == 0){
                    this.legalMoves.add(temp);
                    capture(temp);
                }
            }
            catch (Exception ArrayIndexOutOfBoundsException){
            }
            //End Capture
        }
        //End White Moves

        //Begin Black Moves
        else{
            temp[1] = temp[1] + 1;
            if(chessboard[temp[1]][temp[0]].getColor().compareTo("invalid") == 0){
                this.legalMoves.add(temp);
                temp = arg.clone();
                if(this.movesCount == 0){
                    temp[1] = temp[1] + 2;
                    this.legalMoves.add(temp);
                }
            }

            temp = arg.clone();
            //Pawn simple capture
            try{    //Test right oblique if a white piece is present
                temp[1] = temp[1] + 1;
                temp[0] = temp[0] + 1;
                if(chessboard[temp[1]][temp[0]].getColor().compareTo("white") == 0){
                    this.legalMoves.add(temp);
                    capture(temp);
                }
            }
            catch (Exception ArrayIndexOutOfBoundsException){
            }
            temp = arg.clone();
            try {   //test left oblique
                temp[1] = temp[1] + 1;
                temp[0] = temp[0] - 1;
                if(chessboard[temp[1]][temp[0]].getColor().compareTo("white") == 0){
                    this.legalMoves.add(temp);
                    capture(temp);
                }
            }catch (Exception ArrayIndexOutOfBoundsException){
            }
            //End Capture
        }
        //End Black Moves

        temp = arg.clone();
        //Begin En-Passant
        //Left Oblique
        try{
            temp[0] = temp[0] - 1;
            checkEnpassant(chessboard, temp, turn);
        }catch (Exception ArrayIndexOutOfBoundsException){
        }
        temp = arg.clone();
        //Right Oblique
        try{
            temp[0] = temp[0] + 1;
            checkEnpassant(chessboard, temp, turn);
        }catch (Exception ArrayIndexOutOfBoundsException){
        }
        //End En-Passant

        return this.legalMoves;
    }

    /**
     * Check if actual move is an en-passant capture.
     * @param chessboard Game's cheesboard
     * @param temp Variable for Pawn movements
     * @param turn Player turn
     */
    private void checkEnpassant(Piece[][] chessboard, int[] temp, short turn) {
        if(chessboard[temp[1]][temp[0]].getColor().compareTo("white") == 0 && temp[1] == 4 && chessboard[temp[1]][temp[0]].getMovesCount() == 1 && chessboard[temp[1]][temp[0]].getTurnLastMove() == turn-1){
            enCapture(temp);
            temp[1] = temp[1] + 1;
            this.legalMoves.add(temp);
            capture(temp);
        }
        else if (chessboard[temp[1]][temp[0]].getColor().compareTo("black") == 0 && temp[1] == 3 && chessboard[temp[1]][temp[0]].getMovesCount() == 1 && chessboard[temp[1]][temp[0]].getTurnLastMove() == turn-1){
            enCapture(temp);
            temp[1] = temp[1] - 1;
            this.legalMoves.add(temp);
            capture(temp);
        }
    }
    /**
     * If move/capture is valid, add destination piece to captured list.
     * @param pos Final position of move/capture
     */
    public void capture(int[] pos) {
        this.capturesList.add(pos);
    }

    /**
     * If capture is en-passant, add destination piece to captured list.
     * @param pos Final position of en-passant capture
     */
    public void enCapture(int[] pos){
        this.enCapturesList.add(pos);
    }


}



