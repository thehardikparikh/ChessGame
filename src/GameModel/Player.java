package GameModel;

import BoardStructure.Board;
import BoardStructure.ChessVector;
import BoardStructure.Square;
import Pieces.*;

import java.util.ArrayList;

/**
 * Player class. Created by hardikparikh on 9/11/14.
 */
public class Player {
    protected Board b;
    protected ChessPiece.ChessPieceColor color;
    protected ArrayList<ChessPiece> pieces;

    /**
     * A generic parametric constructor for player.
     * @param brd the board that the player is using.
     * @param clr  color of the player's pieces.
     */
    public Player(Board brd, ChessPiece.ChessPieceColor clr){
        //Black pieces on the top
        b = brd;
        color = clr;
        pieces = new ArrayList<ChessPiece>();
    }

    /**
     * Method to add a piece to the set of pieces owned by the player.
     * @param p The chess piece that you want to add to the set.
     * @return True if the addition was successful, False if the color, x and/or y positions are invalid.
     */
    protected boolean addPiece(ChessPiece p){
        try {
            if(p.getColor() != color) return false;
            b.setChessPieceAt(p.getX(), p.getY(), p);
            pieces.add(p);
            return true;
        }
        catch (IllegalArgumentException e){
            return false;
        }
    }

    /**
     * A helper function that the player can use to populate the first row in vanilla chess.
     * The specified row will be filled with 2 knights, 2 bishops, 2 Rooks, 1 queen and 1 king.
     * @param row Index of the row where the player wants to set up his "non-pawn" pieces.
     */
    protected void populateFrom(int row){
        addPiece(new Rook(color,b,row,1));
        addPiece(new Knight(color,b,row,2));
        addPiece(new Bishop(color,b,row,3));
        addPiece(new Queen(color,b,row,4));
        addPiece(new King(color, b, row, 5));
        addPiece(new Bishop(color,b,row,6));
        addPiece(new Knight(color,b,row,7));
        addPiece(new Rook(color,b,row,8));
    }

    /**
     * A helper function that the player can use to populate a row with Pawns in vanilla chess.
     * The specified row will be filled 8 pawns.
     * @param row Index of the row where the player wants to set up his Pawns.
     */
    protected void fillWithPawns(int row){
        for(int i = 1; i<=b.getRows(); i++){
            addPiece(new Pawn(color,b,row,i));
        }
    }

    public void removePawnMove(){
        for(ChessPiece piece : pieces){
            if(piece.getType() == ChessPiece.ChessPieceType.PAWN){
                if(piece.getColor() == ChessPiece.ChessPieceColor.BLACK){
                    piece.deleteMove(new ChessVector(-2,0,false));
                }
                else{
                    piece.deleteMove(new ChessVector(-2,0,false));
                }
            }
        }
    }


    /**
     * A helper used to check whether any of the pieces owned by the player.
     * @param x x coordinate of the point in question.
     * @param y y coordinate of the point in question.
     * @return True if the point can be reached, False otherwise.
     */
    public boolean isPointReachable(int x, int y){
        System.out.println("Inside is PointReachable for "+x+", "+y+", wait");
        int count = 0;
        Square dst = b.getSquareAt(x,y);
        for(ChessPiece piece : pieces){
            count++;

            System.out.println(piece.getType()+" "+piece.getColor()+" "+count);
            if(piece.getStatus() != ChessPiece.ChessPieceStatus.DEAD ){
                System.out.println(piece.getX()+" "+piece.getY());
            }
            if( (piece.getStatus()!= ChessPiece.ChessPieceStatus.DEAD) && (piece.canPieceMove(dst))) {
                return true;
            }
        }
        System.out.println("Inside is PointReachable finished");
        return false;
    }

    /**
     * A helper used to find an instance of a piece of type t owned by the Player.
     * @param t The type whose instance we want to find.
     * @return ChessPiece object of type t, Null if not found.
     */
    public ChessPiece findPieceOfType(ChessPiece.ChessPieceType t){
        for(ChessPiece piece : pieces){
            if(piece.getType() == t) return piece;
        }
        return null;
    }

    /**
     * A wrapper function that can be used directly in the game loop by the player.
     * @param xsrc x position of the source square.
     * @param ysrc y position of the source square.
     * @param xdst x position of the destination square.
     * @param ydst y position of the destination square.
     * @return True if the trial was successful, False otherwise.
     */
    public boolean tryMoving(int xsrc, int ysrc, int xdst, int ydst){
        try{
            Square sq= b.getSquareAt(xsrc,ysrc);
            if(sq.isEmpty() || sq.getPiece().getColor() != color) return false;

            return b.tryToMove(xsrc,ysrc,xdst,ydst);
        }
        catch (IllegalArgumentException e){
            return false;
        }
    }
}
