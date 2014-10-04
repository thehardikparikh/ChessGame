package Pieces;

import BoardStructure.*;

/**
 * ThreeLeaper piece can only leap 3 steps in the x direction. Created by hardikparikh on 9/18/14.
 */
public class ThreeLeaper extends ChessPiece {

    /**
     * A constructor for a ThreeLeaper object.
     * @param clr color of the ThreeLeaper object.
     * @param b board on which the ThreeLeaper is resting.
     * @param x x position of the ThreeLeaper on the board.
     * @param y y position of the ThreeLeaper on the board.
     */
    public ThreeLeaper(ChessPieceColor clr, Board b, int x, int y){
        super(clr, b, x, y);
        type = ChessPieceType.THREELEAPER;
        addMove(new ChessVector(3,0,false));
        addMove(new ChessVector(-3,0,false));

        if(clr == ChessPieceColor.BLACK){
            label = ChessPieceLabel.BLACK_THREELEAPER;
        }
        else{
            label = ChessPieceLabel.WHITE_THREELEAPER;
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
