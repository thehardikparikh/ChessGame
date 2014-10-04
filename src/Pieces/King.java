package Pieces;
import BoardStructure.*;
import Pieces.ChessPiece;

/**
 * A King class based on the ChessPiece class. Created by hardikparikh on 9/10/14.
 */
public class King extends ChessPiece implements ChessPieceLabel {

    /**
     * Constructor for a King object.
     * @param clr color of the king
     * @param b board on which the king is resting
     * @param x x position of the king on the board
     * @param y y position of the king on the board
     */
    public King(ChessPieceColor clr, Board b, int x, int y){
        super(clr, b, x, y);
        type = ChessPieceType.KING;
        addMove(new ChessVector(1,0,false)); //right
        addMove(new ChessVector(0,1,false)); //up
        addMove(new ChessVector(0,-1,false)); //down
        addMove(new ChessVector(-1,0,false)); //left
        addMove(new ChessVector(1,1,false)); //north-east
        addMove(new ChessVector(-1,-1,false)); //south-west
        addMove(new ChessVector(-1,1,false)); //north-west
        addMove(new ChessVector(1,-1,false)); //south-east

        if(clr == ChessPieceColor.BLACK){
            label = ChessPieceLabel.BLACK_KING;
        }
        else{
            label = ChessPieceLabel.WHITE_KING;
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
        return containsMove(v);
    }
}
