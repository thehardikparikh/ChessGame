package Pieces;
import BoardStructure.*;

/**
 * Pawn class based on the ChessPiece class. Created by hardikparikh on 9/10/14.
 */
public class Pawn extends ChessPiece implements ChessPieceLabel {

    /**
     * Constructor for a Pawn class.
     * @param clr color of the pawn.
     * @param b board on which the pawn is resting.
     * @param x x position of the pawn.
     * @param y y position of the pawn.
     */
    public Pawn(ChessPieceColor clr, Board b, int x, int y){
        super(clr, b, x, y);
        type = ChessPieceType.PAWN;
        //convention that black will always be on the top, so that black pawns always move southwards
        if(clr == ChessPieceColor.BLACK) {
            addMove(new ChessVector(-1,-1, false)); //south-east
            addMove(new ChessVector(-1, 1, false)); //south-west
            addMove(new ChessVector(-2,0,false)); //only for first move
            addMove(new ChessVector(-1,0,false)); //down
        }
        else{
            addMove(new ChessVector(1,1,false)); //north-west
            addMove(new ChessVector(1,-1,false)); //north-east
            addMove(new ChessVector(2,0,false)); //only for first move
            addMove(new ChessVector(1,0,false)); //up
        }

        if(clr == ChessPieceColor.BLACK){
            label = ChessPieceLabel.BLACK_PAWN;
        }
        else{
            label = ChessPieceLabel.WHITE_PAWN;
        }

    }

    /**
     * Method to check if the piece can move from the source square to the destination square.
     * Method overrides the canPieceMove method of the ChessPiece base class.
     * @param dst destination square.
     * @return True if it can move, False otherwise.
     */
    @Override
    public boolean canPieceMove(Square dst){
        if(!super.canPieceMove(dst)) return false;
        ChessVector v = Square.getVector(src, dst);

        if(!containsMove(v)) return false;

        if(Math.abs(v.getxMove())==Math.abs(v.getyMove())){
            //it's a diagonal move there has to be an opposite colored piece
            // We have already checked that the destination square does not contain same colored piece
            return !b.getSquareFrom(src,v).isEmpty();
        }

        //if it is not diagonal then it has to be empty
        return dst.isEmpty();
    }
}
