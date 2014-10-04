package Pieces;

import BoardStructure.*;

/**
 * Fairy Piece Ferz based on the ChessPiece class. It moves one unit diagonally in all four directions.
 * Created by hardikparikh on 9/18/14.
 */
public class Ferz extends ChessPiece {

    /**
     * A constructor for a Ferz object.
     * @param clr color of the Ferz object.
     * @param b board on which the Ferz is resting.
     * @param x x position of the Ferz on the board.
     * @param y y position of the Ferz on the board.
     */
    public Ferz(ChessPieceColor clr, Board b, int x, int y){
        super(clr, b, x, y);
        type = ChessPieceType.FERZ;

        addMove(new ChessVector(1,1,false)); //north-east
        addMove(new ChessVector(-1,-1,false)); //south-west
        addMove(new ChessVector(-1,1,false)); //north-west
        addMove(new ChessVector(1,-1,false)); //south-east

        if(clr == ChessPieceColor.BLACK){
            label = ChessPieceLabel.BLACK_FERZ;
        }
        else{
            label = ChessPieceLabel.WHITE_FERZ;
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
