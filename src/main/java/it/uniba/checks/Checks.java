package it.uniba.checks;

import it.uniba.command.Command;
import it.uniba.piece.Piece;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


/**
 * <b>This is the class of Checks</b>
 * <br><b>Responsibility</b>
 * <br>This class can:
 * <br>- check the correspondence between chessboard letters and matrix indices
 * <br>- translate matrix indices to chessboard numbers
 * <br>- control if moves are valid
 * <br>- control if moves respects italian short algebric notation
 * <br>- control turn of moves
 * <br>- control if a new game is requested
 * <br>- control if starting position for a piece is valid
 * <br>- check if King is surrounded by enemy pieces
 * <br>This class know:
 * <br>- "Command" class methods
 * <br>- "Piece" class methods
 * <br>
 * <br><b>This class is <i>" control "</i></b>.
 */
public class Checks {

    /**
     * Translates input letter into corresponding matrix index.
     * @param arg Starting letter position for pieces
     * @return x (Translated matrix index)
     */
    public static int checkCoordinates(String arg){
        short x = -1;
        switch (arg)
        {
            case "a":
                x = 0;
                break;
            case "b":
                x = 1;
                break;
            case "c":
                x = 2;
                break;
            case "d":
                x = 3;
                break;
            case "e":
                x = 4;
                break;
            case "f":
                x = 5;
                break;
            case "g":
                x = 6;
                break;
            case "h":
                x = 7;
                break;
            default:
                System.out.println ( "Errore di traduzione coordinate! ");
        }
        return x;
    }

    /**
     * Translates input number into corresponding matrix index.
     * @param arg Starting number position for pieces
     * @return (Translated matrix index)
     */
    public static int numTranslation(int arg){
        short num = 0;
        switch (arg)
        {
            case 0:
                num = 8;       //Fast fix for reverse translation
                break;
            case 1:
                num = 7;
                break;
            case 2:
                num = 6;
                break;
            case 3:
                num = 5;
                break;
            case 5:
                num = 3;
                break;
            case 4:
                num = 4;
                break;
            case 6:
                num = 2;
                break;
            case 7:
                num = 1;
                break;
            case 8:
                num = 0;
                break;
            default:
                System.out.println ( "Errore di traduzione! ");
        }
        return num;
    }

    /**
     * Translates inserted move into matrix coordinates.
     * @param mossa Inserted move
     * @return numposition (Translated moves)
     */
    public static CmdString insAlgebricPos(String mossa){      //insert of Algebric Position. 2 Char only allowed
        CmdString numposition = new CmdString();
        String[] split = mossa.split("");
        numposition.coords[0] = checkCoordinates(split[0]);    //int coordinates translation
        numposition.coords[1] = numTranslation(Integer.parseInt(split[1]));    //letter translation
        numposition.coords[2] = checkCoordinates(split[3]);
        numposition.coords[3] = numTranslation(Integer.parseInt(split[4]));
        return numposition;
    }

    /**
     * Translates inserted move in Short Algebric Notation into matrix coordinates.
     * @param mossa Inserted move
     * @param chessboard Game's Chessboard
     * @param turn Player's turn
     * @return numposition (Translated moves)
     */
    public static CmdString insShortAlgebricPos(String mossa, Piece[][] chessboard, short turn){      //insert of Algebric Position. 2 Char only allowed
        CmdString numposition = new CmdString();
        String split[] = mossa.split("");
        if(mossa.matches("((T|A|C|D|R|)([a-h]|)|)[a-h][1-8]")){
            if(split[0].matches("(T|A|C|D|R)")){
                if(mossa.matches("(T|A|C|D|R|)[a-h][a-h][1-8]")){
                    numposition.coords[0] = checkCoordinates(split[1]);
                    numposition.coords[2] = checkCoordinates(split[2]);
                    numposition.coords[3] = numTranslation(Integer.parseInt(split[3]));
                }
                else{
                    numposition.coords[2] = checkCoordinates(split[1]);
                    numposition.coords[3] = numTranslation(Integer.parseInt(split[2]));
                }
                //Finding Piece on chessboard
                for(int i = 0; i<=7; i++){
                    for(int j = 0; j<=7; j++){
                        if(chessboard[i][j].getName().equals(split[0])){
                            if(numposition.coords[0] == -1 || numposition.coords[0] == chessboard[i][j].getPosition()[0]){
                                numposition.possibleStartPos.add(chessboard[i][j].getPosition());
                            }
                        }
                    }
                }
                //end
            }else if(split[1].matches("[1-8]")){  //Simple move Pawn
                numposition.coords[2] = checkCoordinates(split[0]);
                numposition.coords[3] = numTranslation(Integer.parseInt(split[1]));
                //Adding Starting position of every pawns on the chessboard
                for(int i = 0; i<=7; i++){
                    for(int j = 0; j<=7; j++){
                        if(chessboard[i][j].getName().equals("P")){
                            numposition.possibleStartPos.add(chessboard[i][j].getPosition());
                        }
                    }
                }
                //end
            }
        }else if(mossa.matches("([a-h]|((T|A|C|D|R|)([a-h]|)|))[x][a-h][1-8]( ep|)")){
            if(split[0].matches("(T|A|C|D|R)")){
                numposition.piece = split[0];
                numposition.isCapture = true;
                if(mossa.matches("(T|A|C|D|R|)[a-h].*")){
                    numposition.coords[0] = checkCoordinates(split[1]);
                    numposition.coords[2] = checkCoordinates(split[3]);
                    numposition.coords[3] = numTranslation(Integer.parseInt(split[4]));
                }
                else{
                    numposition.coords[2] = checkCoordinates(split[2]);
                    numposition.coords[3] = numTranslation(Integer.parseInt(split[3]));
                }
                //Finding Piece on chessboard
                for(int i = 0; i<=7; i++){
                    for(int j = 0; j<=7; j++){
                        if(chessboard[i][j].getName().equals(numposition.piece)){
                            if(numposition.coords[0] == -1 || numposition.coords[0] == chessboard[i][j].getPosition()[0]) {
                                numposition.possibleStartPos.add(chessboard[i][j].getPosition());
                            }
                        }
                    }
                }
                //end
            }else{
                numposition.pawnPos = checkCoordinates(split[0]);
                numposition.isCapture = true;
                numposition.coords[2] = checkCoordinates(split[2]);
                numposition.coords[3] = numTranslation(Integer.parseInt(split[3]));
                if(mossa.contains("ep")){
                    numposition.isEnpassant = true;        //En-passant check
                }
                //Adding Starting position of every pawns on the chessboard
                for(int i = 0; i<=7; i++){
                    for(int j = 0; j<=7; j++){
                        if(chessboard[i][j].getName().equals("P")){
                            numposition.possibleStartPos.add(chessboard[i][j].getPosition());
                        }
                    }
                }
                //end
            }
        }
        else if(mossa.equals("0-0")){
            for(int i = 0; i<=7; i++){
                for(int j = 0; j<=7; j++){
                    if(chessboard[i][j].getName().equals("R")){
                        int[]temp = new int[2];
                        temp[1] = i;
                        temp[0] = j;
                        chessboard[i][j].setPosition(temp);
                        numposition.possibleStartPos.add(chessboard[i][j].getPosition());
                        numposition.coords[2] = 6;//col fixed
                        if(turn %2 != 0){
                            numposition.coords[3] = 7; //Eighth row
                            numposition.castW[1] = 7;
                            numposition.castW[0] = numposition.coords[2]-1;
                        }else{
                            numposition.coords[3] = 0; //First row
                            numposition.castB[1] = 0;
                            numposition.castB[0] = numposition.coords[2]-1;
                        }
                        break;
                    }
                }
            }
        }
        else if(mossa.equals("0-0-0")){
            for(int i = 0; i<=7; i++){
                for(int j = 0; j<=7; j++){
                    if(chessboard[i][j].getName().equals("R")){
                        int[]temp = new int[2];
                        temp[1] = i;
                        temp[0] = j;
                        chessboard[i][j].setPosition(temp);
                        numposition.possibleStartPos.add(chessboard[i][j].getPosition());
                        numposition.coords[2] = 2; //col fixed
                        if(turn %2 != 0){
                            numposition.coords[3] = 7; //Eighth row
                            numposition.castW[1] = 7;
                            numposition.castW[0] = numposition.coords[2]+1;
                        }else{
                            numposition.coords[3] = 0; //First row
                            numposition.castB[1] = 0;
                            numposition.castB[0] = numposition.coords[2]+1;
                        }
                        break;
                    }
                }
            }
        }
        return numposition;
    }

    /**
     * Removes non valid starting positions for pieces.
     * @param numposition Translated moves
     * @param turn Player's turn
     * @param chessboard Game's Chessboard
     * @return numposition (Filtered set of starting positions)
     */
    public static CmdString filterStartPos(CmdString numposition, short turn, Piece[][] chessboard){
        int[] move;
        short count = 0;
        for(int j = -1; j<count; j++){
            int size = numposition.possibleStartPos.size();
            for(int i = 0; i < size; i++){
                move = numposition.possibleStartPos.get(i);
                if((chessboard[move[1]][move[0]].getColor().compareTo("white") == 0) && (turn % 2 == 0) ){
                    count++;
                    numposition.possibleStartPos.remove(i);
                    break;
                }
                else if((chessboard[move[1]][move[0]].getColor().compareTo("black") == 0) && (turn % 2 != 0)) {
                    count++;
                    numposition.possibleStartPos.remove(i);
                    break;
                }
            }
        }
        return numposition;
    }

    /**
     * Asks player for confirmatino if he wants start a new match.
     * @param mossa Inserted move
     */
    public static void isPlay(String mossa){
        Scanner input = new Scanner ( System.in, "UTF-8");
        String command;
        if(mossa.equals("play")){
            System.out.println("Partita in corso! Iniziando una nuova partita perderai\nquella attuale. Sei sicuro? [s/n]");
            command = input.nextLine().toLowerCase();
            while(!command.equals("s") && !command.equals("n")){
                System.out.println("Errore! Digita 's' per confermare, 'n' se vuoi continuare la partita");
                command = input.nextLine().toLowerCase();
            }
            if(command.equals("s")){
                Command.commandList(mossa);
            }else{
                if(command.equals("n")){
                    System.out.println("Continuerai la partita corrente");
                }
            }
        }
        else{
            Command.commandList(mossa);
        }
    }

    /**
     * Tells the player that is not his turn if he try to move different pieces from his owns.
     * @param pos Start position for pieces
     * @param chessboard Game's Chessboard
     * @param turn Player's turn
     * @return error
     */
    public static boolean checkTurn(int[] pos, Piece[][] chessboard, int turn){
        boolean error = false;

        if((chessboard[pos[1]][pos[0]].getColor().compareTo("white") == 0) && (turn % 2 == 0)){
            System.out.println("Il Bianco non può muovere adesso! È il turno del Nero.");
            error = false;
        }
        else if((chessboard[pos[1]][pos[0]].getColor().compareTo("black") == 0) && (turn % 2 != 0)) {
            System.out.println("Il Nero non può muovere adesso! È il turno del Bianco.");
            error = false;
        }
        else if(chessboard[pos[1]][pos[0]].getColor().compareTo("invalid") == 0){
            System.out.println("Non è un pezzo!");
            error = false;
        }
        else{
            error = true;
        }
        return error;
    }

    /**
     * Tells the player that move is invalid if he try to move pieces using illegal moves for that piece.
     * @param mov Start positon for pieces
     * @param legalMoves List of legal moves for pieces
     * @return error
     */
    public static boolean checkLegalMoves(int[] mov, List<int[]> legalMoves){
        boolean error = false;
        for(int i = 0; i < legalMoves.size(); i++){
            if(Arrays.equals(mov, legalMoves.get(i))){
                error = true;
                break;
            }
            else {
                error = false;
            }
        }
        return error;
    }

    /**
     * Translates matrix coordinates into corresponding Chessboard letters.
     * @param coord Matrix coordinates for Chessboard
     * @return letter
     */
    public static char reverseCheckCoordinates(int coord){
        char letter=' ';
        switch (coord)
        {
            case 0:
                letter = 'a';
                break;
            case 1:
                letter = 'b';
                break;
            case 2:
                letter = 'c';
                break;
            case 3:
                letter = 'd';
                break;
            case 4:
                letter = 'e';
                break;
            case 5:
                letter = 'f';
                break;
            case 6:
                letter = 'g';
                break;
            case 7:
                letter = 'h';
                break;

            default:
                break;
        }
        return letter;
    }

    /**
     * Controls if final position for King puts him in check.
     * @param kingPos Actual position for King
     * @param chessboard Game's Chessboard
     * @param color King's color
     * @param turn Player turn
     * @param pos Final position for King
     * @return check
     */
    public static boolean isCheck(int[] kingPos, Piece[][] chessboard, String color, short turn, int[] pos){
        boolean check = false;
        Piece tempPiece;
        List<int[]> legalMoves = new ArrayList<>();
        for (int i = 0; i <= 7; i++) {
            for (int j = 0; j <= 7; j++) {
                if ((chessboard[i][j].getColor().equals("black") && color.equals("white")) || (chessboard[i][j].getColor().equals("white") && color.equals("black"))) {
                    tempPiece = chessboard[kingPos[1]][kingPos[0]];
                    chessboard[kingPos[1]][kingPos[0]] = chessboard[pos[1]][pos[0]];
                    chessboard[pos[1]][pos[0]] = new Piece();
                    if(chessboard[i][j].getName().compareTo("R") != 0){
                        legalMoves = chessboard[i][j].move(chessboard[i][j].getPosition(), chessboard[i][j].getColor(), chessboard, turn);
                        check = Checks.checkLegalMoves(kingPos, legalMoves);
                    }
                    else{
                        int[] temp = kingPos.clone();
                        for(int y = 1; y<=8; y++){
                            try {
                                kingPosAround(temp, y);
                                if(chessboard[temp[1]][temp[0]].getName().equals("R")){
                                    check = true;
                                }
                            }catch(Exception ArrayIndexOutOfBoundsException){ }
                            temp = kingPos.clone();
                        }
                    }
                    chessboard[pos[1]][pos[0]] = chessboard[kingPos[1]][kingPos[0]];
                    chessboard[kingPos[1]][kingPos[0]] = tempPiece;
                    if(check){
                        break;
                    }
                }
                legalMoves.clear();
            }
            if(check){
                break;
            }
        }
        return check;
    }

    /**
     * Chose the set of movement for King based on its starting position.
     * @param temp List of possible moves for King
     * @param y Select corresponding move in the switch
     */
    public static void kingPosAround(int[] temp, int y) {
        switch (y){
            case 1: temp[1] -= 1;   //up
                break;
            case 2: temp[1] += 1;   //down
                break;
            case 3: temp[0] -= 1;   //left
                break;
            case 4: temp[0] += 1;   //right
                break;
            case 5: temp[1] -= 1;   //up left
                temp[0] -= 1;
                break;
            case 6: temp[1] += 1;   //down right
                temp[0] += 1;
                break;
            case 7: temp[1] -= 1;   //up right
                temp[0] += 1;
                break;
            case 8: temp[1] += 1;   //down left
                temp[0] -= 1;
                break;
            default:
                System.out.println("errore.");
                break;
        }
    }
}
