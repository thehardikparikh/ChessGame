package Pieces;
import BoardStructure.*;
import Pieces.ChessPiece;

/**
 * Knight class based on the ChessPiece class. Created by hardikparikh on 9/10/14.
 */
public class Knight extends ChessPiece implements ChessPieceLabel {
    /**
     * Constructor for a Knight object.
     * @param clr color of the knight.
     * @param b board on which the knight is resting
     * @param x x position of the knight.
     * @param y y position of the knight.
     */
    public Knight(ChessPiece.ChessPieceColor clr, Board b, int x, int y){
        super(clr, b, x, y);
        type = ChessPieceType.KNIGHT;
        addMove(new ChessVector(2,1,false));
        addMove(new ChessVector(-2,1,false));
        addMove(new ChessVector(2,-1,false));
        addMove(new ChessVector(-2,-1,false));
        addMove(new ChessVector(1,2,false));
        addMove(new ChessVector(-1,2,false));
        addMove(new ChessVector(1,-2,false));
        addMove(new ChessVector(-1,-2,false));

        if(clr == ChessPieceColor.BLACK){
            label = ChessPieceLabel.BLACK_KNIGHT;
        }
        else{
            label = ChessPieceLabel.WHITE_KNIGHT;
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
        //Check if the vector is in the ArrayList of possible moves
        return containsMove(v);
    }
}
