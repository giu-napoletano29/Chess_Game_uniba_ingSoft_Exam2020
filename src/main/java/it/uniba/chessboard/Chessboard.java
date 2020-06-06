package it.uniba.chessboard;


import it.uniba.checks.Checks;
import it.uniba.checks.CmdString;
import it.uniba.command.Command;
import it.uniba.command.history.History;
import it.uniba.piece.Piece;
import it.uniba.piece.bishop.Bishop;
import it.uniba.piece.king.King;
import it.uniba.piece.knight.Knight;
import it.uniba.piece.pawn.Pawn;
import it.uniba.piece.queen.Queen;
import it.uniba.piece.rook.Rook;

import java.util.ArrayList;
import java.util.List;

/**
 * <b>This is the class of Chessboard</b>
 * <br><b>Responsibility</b>
 * <br>This class can:
 * <br>- create the chessboard
 * <br>- show the chessboard
 * <br>- update the chessboard when a piece is moved
 * <br>This class know:
 * <br>- all pieces class methods
 * <br>- "History" class methods
 * <br>- "Check" class methods
 * <br>
 * <br><b>This class is <i>" control "</i></b>.
 */
public class Chessboard{

    /**
     * Number of chessboard boxes.
     */
    public static final short MAX_LUNG = 8;

    /**
     * Number of chessboard boxes.
     */
    public static final short MAX_ALT = 8;

    /**
     * ANSI Code for resetting boxes color when a piece is moved/captured.
     */
    public static final String ANSI_RESET = "\u001B[0m";

    /**
     * Color for black boxes.
     */
    public static final String BLACK = "\u001B[48;5;52m";

    /**
     * Color for white boxes.
     */
    public static final String WHITE = "\u001B[48;5;221m";

    /**
     * Space between boxes.
     */
    public static final String SPACE = "\u2003";

    /**
     * Turn of play.
     */
    public short turn = 1;

    /**
     * Create new game history.
     */
    static History h = new History();

    /**
     * Initial position.
     */
    public int[] startPos = new int[2];

    /**
     * Chessboard instantiation.
     */
    public static Piece[][] chessboard = new Piece[MAX_ALT][MAX_LUNG];

    /**
     * Chessboard constructor.
     */
    public Chessboard() {

        short i = 0, j = 0;

        //ciclo sulle colonne innestato nel ciclo sulle righe
        for(i = 0; i <MAX_ALT; i++) {
            for(j = 0; j< MAX_LUNG; j++) {
                startPos[0] = j;
                startPos[1] = i;
                //controlli per l'assegnazione del valore per ciascun elemento della matrice
                if(i == 1) {
                    chessboard[i][j] = new Pawn();
                    chessboard[i][j].setColor("black");   //setting Pawn color
                    chessboard[i][j].setPosition(startPos);
                }
                else if(i == 6) {
                    chessboard[i][j] = new Pawn();
                    chessboard[i][j].setColor("white");   //setting Pawn color
                    chessboard[i][j].setPosition(startPos);
                }
                else if(((i == 0) && (j == 0 || j == 7))){
                    chessboard[i][j] = new Rook();
                    chessboard[i][j].setColor("black");   //setting Rook color
                    chessboard[i][j].setPosition(startPos);
                }
                else if(i == 7 && (j == 0 || j == 7)){
                    chessboard[i][j] = new Rook();
                    chessboard[i][j].setColor("white");
                    chessboard[i][j].setPosition(startPos);
                }
                else if((i == 0) && (j == 1 || j == 6)){
                    chessboard[i][j] = new Knight();
                    chessboard[i][j].setColor("black");   //setting Knight color
                    chessboard[i][j].setPosition(startPos);
                }
                else if((i == 7) && (j == 1 || j == 6)){
                    chessboard[i][j] = new Knight();
                    chessboard[i][j].setColor("white");
                    chessboard[i][j].setPosition(startPos);
                }
                else if((i == 0) && (j == 2 || j == 5)){
                    chessboard[i][j] = new Bishop();
                    chessboard[i][j].setColor("black");   //setting Bishop color
                    chessboard[i][j].setPosition(startPos);
                }
                else if((i == 7) && (j == 2 || j == 5)){
                    chessboard[i][j] = new Bishop();
                    chessboard[i][j].setColor("white");
                    chessboard[i][j].setPosition(startPos);
                }
                else if((i == 0) && (j == 3)){
                    chessboard[i][j] = new Queen();
                    chessboard[i][j].setColor("black");   //setting Queen color
                    chessboard[i][j].setPosition(startPos);
                }
                else if((i == 7) && (j == 3)){
                    chessboard[i][j] = new Queen();
                    chessboard[i][j].setColor("white");
                    chessboard[i][j].setPosition(startPos);
                }
                else if((i == 0) && (j == 4)){
                    chessboard[i][j] = new King();
                    chessboard[i][j].setColor("black");   //setting King color
                    chessboard[i][j].setPosition(startPos);
                }
                else if((i == 7) && (j == 4)){
                    chessboard[i][j] = new King();
                    chessboard[i][j].setColor("white");
                    chessboard[i][j].setPosition(startPos);
                }
                else chessboard[i][j] = new Piece();

            }
        }

    }

    /**
     * Show the Chessboard.
     */
    public static void display(){
        //Chessboard print
        System.out.println();
        System.out.println("    a  b  c  d  e  f  g  h");
        for(int i = 0; i < MAX_ALT; i++){
            System.out.print(" ");
            System.out.print(MAX_ALT-i+" ");
            for(int j = 0; j < MAX_LUNG; j++){
                if((j%2 == 0 && i%2 == 0) || (j%2 != 0 && i%2 != 0)){
                    System.out.print(WHITE);
                }
                else{
                    System.out.print(BLACK);
                }
                if(chessboard[i][j].getColor().equals("invalid")){ // empty space
                    System.out.print(SPACE + "\u0020" + "\u0020");
                } else { // piece
                    System.out.print(chessboard[i][j].getIcon());
                }
                System.out.print(ANSI_RESET);
            }
            System.out.print(" ");
            System.out.print(MAX_ALT-i);
            if(i < MAX_ALT) { // border between rows
                System.out.println();
            }


        }
        // stampa delle lettere a-h
        System.out.println("    a  b  c  d  e  f  g  h");
        System.out.println();

        //cmdMove();    <----- Call disabled in Sprint 1
    }

    /**
     * Updates Chessboard when pieces are captured/moved.
     */
    public void update(){
        List<int[]> legalMoves;
        List<int[]> selPieces = new ArrayList<>();
        int[] mov = new int[2];
        int[] pos = new int[2];
        CmdString movePos = new CmdString();
        boolean error = false;
        String symbol = " ";
        String ep = "";
        String mossa = "";
        String regEx = "[a-h][1-8][ ][a-h][1-8]";
        String regExCap = "((((T|A|C|D|R|)([a-h]|)|)[a-h][1-8]|)(([a-h]|((T|A|C|D|R|)([a-h]|)|))[x][a-h][1-8]( ep|)|)|0-0|0-0-0)";

        while(!error){
            while(!error){
                mossa = Command.enterCommand();
                selPieces.clear();
                if(mossa.matches("[a-h][a-h][1-8].*")){
                    mossa = "error";
                }
                if(mossa.matches(regEx)){
                    error = true;
                    movePos = Checks.insAlgebricPos(mossa);
                }
                else if(mossa.matches(regExCap)){
                    movePos = Checks.insShortAlgebricPos(mossa, chessboard, turn);
                    movePos = Checks.filterStartPos(movePos, turn, chessboard);
                    for(int i = 0; i < movePos.possibleStartPos.size(); i++){
                        pos = movePos.possibleStartPos.get(i);
                        legalMoves = chessboard[pos[1]][pos[0]].move(pos, chessboard[pos[1]][pos[0]].getColor(), chessboard, turn);
                        mov[0] = movePos.coords[2];
                        mov[1] = movePos.coords[3];
                        error = Checks.checkLegalMoves(mov, legalMoves);
                        if(error){
                            selPieces.add(pos);
                        }
                    }

                    if(selPieces.size() == 1){
                        pos = selPieces.get(0);
                        movePos.coords[0] = pos[0];
                        movePos.coords[1] = pos[1];

                        error = true;
                    }
                    else if(selPieces.size() > 1){
                        System.out.println("C'è ambiguità nella mossa. Specifica il pezzo da muovere.");
                        error = false;
                    }
                    else{
                        System.out.println("Mossa Illegale!");
                        error = false;
                    }
                }
                else{
                    error = false;
                    Checks.isPlay(mossa);
                }
            }

            pos[0] = movePos.coords[0];    //split every int in a variable
            pos[1] = movePos.coords[1];    //pos is for starting position

            chessboard[pos[1]][pos[0]].setPosition(pos);
            error = Checks.checkTurn(pos, chessboard, turn);

            if(error){
                legalMoves = chessboard[pos[1]][pos[0]].move(pos, chessboard[pos[1]][pos[0]].getColor(), chessboard, turn);    //check if final position is a valid move

                mov[0] = movePos.coords[2];    //mov is for final position
                mov[1] = movePos.coords[3];
                error = Checks.checkLegalMoves(mov, legalMoves);
                if(!error){
                    System.out.println("Mossa illegale!");
                }

                if(mossa.equals("0-0")){    //Short castling rook movement
                    int[] temp = movePos.getCast(chessboard[pos[1]][pos[0]].getColor());
                    chessboard[temp[1]][temp[0]] = chessboard[temp[1]][7];
                    chessboard[temp[1]][7] = new Piece();
                    chessboard[temp[1]][temp[0]].setPosition(temp);
                    symbol = "0-0";
                }
                else if(mossa.equals("0-0-0")){ //Long castling rook movement
                    int[] temp = movePos.getCast(chessboard[pos[1]][pos[0]].getColor());
                    chessboard[temp[1]][temp[0]] = chessboard[temp[1]][0];
                    chessboard[temp[1]][0] = new Piece();
                    chessboard[temp[1]][temp[0]].setPosition(temp);
                    symbol = "0-0-0";
                }else{
                    if(chessboard[pos[1]][pos[0]].getValuePoint() != 1){    //check if not pawn than print piece name in moves history
                        symbol = chessboard[pos[1]][pos[0]].getName();
                    }
                }

                boolean capture = chessboard[pos[1]][pos[0]].getValidCaptures(mov); //check if current move is an actual capture
                if(capture){
                    if(mossa.contains("x") || mossa.matches(regEx)){
                        if(chessboard[pos[1]][pos[0]].getValuePoint() == 1){
                            symbol = symbol + Checks.reverseCheckCoordinates(pos[0]) + "x";
                        }
                        else{
                            symbol = symbol + "x";
                        }
                        //Last minutes changes are very bad
                        if(movePos.pawnPos == pos[0] || movePos.pawnPos == -1){       //check if first letter of "algebric" capture is the same of the pawn position or if it's just a simple capture
                            mossa = Checks.reverseCheckCoordinates(mov[0]) + String.valueOf(Checks.numTranslation(mov[1])) + " (" + chessboard[mov[1]][mov[0]].getName() + ")";
                            h.addCatch(mossa);
                        }
                        else{
                            System.out.println("La sintassi della cattura è sbagliata!");
                            error = false;
                        }
                        //end of very bad changes
                    }
                    else{
                        System.out.println("Questa è una cattura! Usare la sintassi appropriata.");
                        error = false;
                    }

                }
                else{
                    if(movePos.isCapture == true){        //magic number to know if the input is an "algebric" capture
                        System.out.println("Non è una cattura!");
                        error = false;
                    }
                }

                boolean enCapture = chessboard[pos[1]][pos[0]].getEnCaptures(mov); //<-------check for possible capture (En-passant only for now)
                if(enCapture && chessboard[pos[1]][pos[0]].getColor().compareTo("white") == 0){
                    chessboard[mov[1] + 1][mov[0]] = new Piece();
                    ep = " ep";
                }
                else if(enCapture && chessboard[pos[1]][pos[0]].getColor().compareTo("black") == 0){
                    chessboard[mov[1] - 1][mov[0]] = new Piece();
                    ep = " ep";
                } //<--------end check for possible capture

                else if(movePos.isEnpassant == true){
                    System.out.println("Non è una cattura En-passant!");
                    error = false;
                }
            }
        }


        Piece tempPiece = chessboard[mov[1]][mov[0]];
        chessboard[mov[1]][mov[0]] = chessboard[pos[1]][pos[0]];
        chessboard[mov[1]][mov[0]].setPosition(mov);
        chessboard[pos[1]][pos[0]] = new Piece();

        int[] tempPos = new int[2];

        for(int i = 0; i<=7; i++){
            for(int j = 0; j<=7; j++){
                if(chessboard[i][j].getName().equals("R")){
                    if(turn % 2 == 0 && chessboard[i][j].getColor().equals("black")){
                        tempPos = chessboard[i][j].getPosition();
                    }
                    else if(turn % 2 != 0 && chessboard[i][j].getColor().equals("white")){
                        tempPos = chessboard[i][j].getPosition();
                    }
                }
            }
        }


        if(Checks.isCheck(tempPos, chessboard, chessboard[tempPos[1]][tempPos[0]].getColor(), turn, tempPos)){
            chessboard[pos[1]][pos[0]] = chessboard[mov[1]][mov[0]];
            chessboard[pos[1]][pos[0]].setPosition(pos);
            chessboard[mov[1]][mov[0]] = tempPiece;

            System.out.println("Il Re non può andare sotto scacco!");

        }else{
            chessboard[mov[1]][mov[0]].setMovesCount();
            chessboard[mov[1]][mov[0]].setTurnLastMove(turn);

            //Adding move history
            if(symbol.contains("0-0") || symbol.contains("0-0-0")){
                mossa = symbol;
            }else{
                mossa = symbol + Checks.reverseCheckCoordinates(mov[0]) + Checks.numTranslation(mov[1]) + ep;
            }
            h.addPrevious(mossa);

            turn++;
        }

        //display();    <------ Call disabled in Sprint 1
    }
}

