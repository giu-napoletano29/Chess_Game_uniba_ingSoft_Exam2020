package it.uniba.command.history;
import java.util.Queue;
import java.util.LinkedList;

/**
 * <b>This is the class of History moves</b>
 * <br><b>Responsibility</b>
 * <br>This class can:
 * <br>- create list for moves history
 * <br>- create list for catch history
 * <br>- adds moves and catches to lists
 * <br>This class know:
 * <br>- nothing
 * <br>
 * <br><b>This class is <i>" control "</i></b>.
 */
public class History {
    /**
     * List that will contain the move history.
     */
    private static final Queue<String> previousMoves = new LinkedList<>();

    /**
     * List that will contain the capture history.
     */
    private static final Queue<String> catchHistory = new LinkedList<>();

    /**
     * Getter method for moves history.
     * @return previousMoves
     */
    public static Queue<String> getPreviousMoves() {
        return previousMoves;
    }

    /**
     * Getter method for catch history.
     * @return catchHistory
     */
    public static Queue<String> getCatchHistory() {
        return catchHistory;
    }

    /**
     * Print played moves.
     */
    public void printPreviousMoves(){
        short nMoves=1;
        short count = 0;
        if (!previousMoves.isEmpty()) {
            for (String move : previousMoves) {
                count++;
                if(count%2 != 0){
                    System.out.print( nMoves + ". ");
                }
                System.out.print(move);
                if(count%2 == 0){
                    System.out.print("\n");
                    nMoves++;
                }else{
                    System.out.print(", ");
                }
            }
            System.out.print("\n");
        }else
            {
               System.out.println("[]");
            }
    }

    /**
     * Print a list of captured pieces.
     */
    public void printCatchHistory() { System.out.println(catchHistory);}

    /**
     * Add just played move to moves history.
     * @param move Move just played
     */
    public void addPrevious(String move){
        previousMoves.add(move);
    }

    /**
     * Adds captured piece to captures history.
     * @param capture Piece captured
     */
    public void addCatch(String capture){
        catchHistory.add(capture);
    }

    /**
     * Delete list of previous moves.
     */
    public void clearPrevious(){ previousMoves.clear();}

    /**
     * Delete list of previous captures.
     */
    public void clearCaptured(){ catchHistory.clear();};


}
