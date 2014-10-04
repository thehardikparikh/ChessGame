package Pieces;
import BoardStructure.*;
import java.util.ArrayList;
import java.util.HashSet;


/**
 * A generic superclass for chess pieces. Created by hardikparikh on 9/10/14.
 */
public class ChessPiece {
    protected ChessPieceStatus status;
    protected ChessPieceColor color;
    protected HashSet<ChessVector> possibleMoves;
    protected Square src;
    protected Board b;
    protected ChessPieceType type;
    protected String label;

    /**
     * An enum. Lists the various possible statuses for a chess piece.
     */
    public enum ChessPieceStatus{SAFE, /**< enum value SAFE*/
                                DANGER,/**< enum value DANGER*/
                                DEAD /**< enum value DEAD*/
                                };

    /**
     * An enum. Lists the various colors possible for a chess piece.
     */
    public enum ChessPieceColor{BLACK,/**< enum value BLACK*/
                                WHITE /**< enum value WHITE*/
                                };

    /**
     * An enum. Lists the various types of chess pieces.
     */
    public enum ChessPieceType{KING, /**< enum value KING*/
                               QUEEN,/**< enum value QUEEN*/
                               ROOK,/**< enum value ROOK*/
                               KNIGHT,/**< enum value KNIGHT*/
                               BISHOP,/**< enum value BISHOP*/
                               PAWN, /**< enum value PAWN*/
                               FERZ, /**< enum value FERZ*/
                               THREELEAPER /**< enum value THREELEAPER*/
                               };


    /**
     * Generic parametric constructor for a ChessPiece.
     * SIDE EFFECTS: throws IllegalArgumentException if the BoardStructure.Board object passed in is null or x and/or y
     * are out of bounds.
     * @param clr color of the Pieces.ChessPiece selected from the enum.
     * @param brd board where the piece resides.
     * @param x position of the piece on the board.
     * @param y position of the piece on the board.
     */
    public ChessPiece(ChessPieceColor clr, Board brd, int x, int y){
        if(null == brd) throw new IllegalArgumentException("BoardStructure.Board parameter is null");
        if(!brd.validXY(x,y)) throw new IllegalArgumentException("x and y are out of bounds");

        b = brd;
        src = b.getSquareAt(x,y);
        b.setChessPieceAt(x,y,this);
        status = ChessPieceStatus.SAFE;
        color = clr;
        possibleMoves = new HashSet<ChessVector>();

    }

    /**
     * canPieceMove performs two basic checks to see if the piece can move to destination square.
     * Each subclass piece needs a more advanced and specific implementation of this method.
     * SIDE EFFECTS: One should not rely solely on this method to verify if the piece can move. Need an advanced override
     * implementation in the subclass.
     * @param dst destination square.
     * @return True if the destination square is within bounds of the board and the color of piece at the square is
     * not the same (that is, if the square has a piece).
     */
    public boolean canPieceMove(Square dst){
        if(!b.validXY(dst.getX(),dst.getY())) return false; //cannot move if destination is out of bounds

        //if the destination contains piece of same color then return false
        if((!b.isEmpty(dst.getX(),dst.getY())) && (dst.getPiece().getColor() == getColor())) return false;

        return true;
    }

    /*
    GETTERS AND SETTERS AND HELPERS
     */

    /**
     * getter for color.
     * @return color of the chess piece instance.
     */
    public ChessPieceColor getColor(){
        return color;
    }

    /**
     * getter for status.
     * @return status of the chess piece.
     */
    public ChessPieceStatus getStatus(){
        return status;
    }

    /**
     * setter for the status.
     * @param s desired status of the chess piece namely - SAFE, DANGER, DEAD.
     */
    public void setStatus(ChessPieceStatus s){
        status = s;
        if(s==ChessPieceStatus.DEAD){
            src = null;
        }
    }

    /**
     * getter for the type of chess piece.
     * @return type of the chess piece namely KING, QUEEN, ROOK, BISHOP, KNIGHT, PAWN.
     */
    public ChessPieceType getType(){
        return type;
    }

    /**
     * set of moves possible for the chess piece.
     * @return a HashSet of Chess Vectors representing the moves that the piece can make.
     */
    public HashSet<ChessVector> getPossibleMoves(){
        return possibleMoves;
    }

    /**
     * a getter for the source square of the chess piece.
     * @return the source square of the piece.
     */
    public Square getSquare(){
        return src;
    }

    /**
     * A setter to set the source square of the chess piece.
     * @param sq the square that we want to set.
     */
    public void setSquare(Square sq){
        if(!b.validXY(sq.getX(),sq.getY())) throw new IllegalArgumentException("x and y are out of bounds");
        src = sq;
    }

    /**
     * getter for the x position of the chess piece.
     * @return x position of the chess piece.
     */
    public int getX(){return src.getX();}

    /**
     * getter for the y position of the chess piece.
     * @return y position of the chess piece.
     */
    public int getY(){return src.getY();}

    /**
     * a getter for the board that the chess piece is lying on.
     * @return the board where the chess piece is located.
     */
    public Board getBoard(){return b;}

    /**
     * Method to add a move to the list of possible moves of the chess piece.
     * @param move the ChessVector that we want to add to the list of possible moves.
     */
    public void addMove(ChessVector move){
        possibleMoves.add(move);
    }

    /**
     * Method to remove a move from the list of possible moves for a chess piece.
     * @param move the move that we want to remove from the list of possible moves.
     */
    public void deleteMove(ChessVector move){

        for(ChessVector m: possibleMoves){
            if(m.equals(move)) {
                possibleMoves.remove(m);
                return;
            }
        }

    }

    /**
     * Method to check if a particular move exists in the list of possible moves.
     * @param move the move that we want to query for
     * @return True if the move exists in the list of possible moves, False otherwise.
     */
    public boolean containsMove(ChessVector move){
        //return possibleMoves.contains(move);
        for(ChessVector m: possibleMoves){
            if(m.equals(move)) return true;
        }
        return false;
    }

    /**
     * Getter for the label of the ChessPiece.
     * @return the label of the ChessPiece.
     */
    public String getLabel(){ return label; };
}
