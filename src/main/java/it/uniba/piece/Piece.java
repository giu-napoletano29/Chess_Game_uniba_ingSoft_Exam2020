package it.uniba.piece;

import java.lang.*;
import java.util.*;

/**
 * <b>This is the class of piece</b>
 * <br><b>Responsibility</b>
 * <br>This class can:
 * <br>- define pieces characteristics
 * <br>- define pieces feature
 * <br>This class know:
 * <br>- nothing
 * <br>
 * <br><b>This class is <i>" entity "</i></b>.
 */
public class Piece {

    /**
     * Icon of Piece.
     */
    protected String icon;

    /**
     * Color of Piece.
     */
    protected String color;

    /**
     * Position of Piece.
     */
    protected int[] position;

    /**
     * Count of movements for Piece.
     */
    protected int movesCount;

    /**
     * List of legal moves.
     */
    protected List<int[]> legalMoves;

    /**
     * List of en-passant captures.
     */
    protected List<int[]> enCapturesList;

    /**
     * List of captures.
     */
    protected List<int[]> capturesList;

    /**
     * Piece's value for counting points.
     */
    protected int valuePoint;

    /**
     * Piece's initial letter.
     */
    protected String name;

    /**
     * Piece's turn that moved last.
     */
    protected short turnLastMove;

    /**
     * Piece constructor.
     */
    public Piece(){
        this.icon = "   ";
        this.name = "";
        this.color = "invalid";
        this.position = new int[2];
        this.movesCount = 0;
        this.valuePoint = 0;
        this.turnLastMove = 0;
        this.legalMoves = new ArrayList<>();
        this.enCapturesList = new ArrayList<>();
        this.capturesList = new ArrayList<>();
    }

    /**
     * Return color of icon (invalid for empty boxes).
     * @return icon
     */
    public String getIcon(){
        return this.icon;
    }

    /**
     * Return name of pieces (no name for empty boxes).
     * @return name
     */
    public String getName(){
        return this.name;
    }

    /**
     * Getter method for pieces position.
     * @return position
     */
    public int[] getPosition(){
        return (int[])position.clone();
    }

    /**
     * Setter method for Piece position.
     * @param args Final position of pieces
     */
    public void setPosition(int[] args) {
        this.position = args.clone();
    }

    /**
     * Getter method for last move turn.
     * @return turnLastMove
     */
    public short getTurnLastMove() {
        return this.turnLastMove;
    }

    /**
     * Setter method for last move turn.
     * @param turn Last move turn
     */
    public void setTurnLastMove(short turn){
        this.turnLastMove += turn;
    }

    /**
     * Getter method for pieces color.
     * @return color
     */
    public String getColor(){
        return this.color;
    }

    /**
     * Setter method for pieces color.
     * @param colo Piece color
     */
    public void setColor(String colo){
        this.color = colo;
    }

    /**
     * Getter method for moves count.
     * @return movesCount
     */
    public int getMovesCount(){
        return this.movesCount;
    }

    /**
     * Setter method for moves count.
     */
    public void setMovesCount(){
        this.movesCount++;
    }

    /**
     * Return piece's value.
     * @return  valuePoint
     */
    public int getValuePoint() {
        return this.valuePoint;
    }

    /**
     * Add piece to capture list.
     * @param pos Final position for Piece
     * @param chessboard Game's cheesboard
     * @param color Color of Piece
     */
    public void capture(int[] pos, Piece chessboard, String color) {
        if(color.compareTo("white") == 0) {
            if(chessboard.getColor().compareTo("black") == 0){
                this.capturesList.add(pos);
            }
        }else if(color.compareTo("black") == 0){
            if(chessboard.getColor().compareTo("white") == 0){
                this.capturesList.add(pos);
            }
        }
    }

    /**
     * Return true/false if final position for piece is valid for en-passant.
     * @param mov List of movements
     * @return cap (true/false)
     */
    public boolean getEnCaptures(int[] mov){
        boolean cap = false;
        for(int i = 0; i < this.enCapturesList.size(); i++){
            if(Arrays.equals(mov, this.enCapturesList.get(i))){
                cap = true;
                break;
            }
        }
        return cap;
    }

    /**
     * Return true/false if capture is valid.
     * @param mov List of movements
     * @return cap (true/false)
     */
    public boolean getValidCaptures(int[] mov){
        boolean cap = false;
        try {
            for (int i = 0; i < this.capturesList.size(); i++) {
                if (Arrays.equals(mov, this.capturesList.get(i))) {
                    cap = true;
                    break;
                }
            }
        }catch (Exception NullPointerException){
        }
        return cap;
    }

    /**
     * Define an interface for pieces legal movements.
     * @param arg Start position for pieces
     * @param color Color of pieces
     * @param chessboard Game's chessboard
     * @param turn Player turn
     * @return legalMoves (List of legal moves for pieces)
     */
    //arg must be start position
    public List<int[]> move(int[] arg, String color, Piece[][] chessboard, short turn) {
        return this.legalMoves;
    }

    public List<int[]> getCapturesList(){ return this.capturesList;}

    public List<int[]> getEnCapturesList() { return this.enCapturesList;}



}
