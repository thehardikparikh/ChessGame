package Pieces;
import BoardStructure.*;

/**
 * Bishop class based on the ChessPiece class. Created by hardikparikh on 9/10/14.
 */
public class Bishop extends ChessPiece implements ChessPieceLabel {
    /**
     * A constructor for a Bishop object.
     * @param clr color of the Bishop object.
     * @param b board on which the bishop is resting.
     * @param x x position of the bishop on the board.
     * @param y y position of the bishop on the board.
     */
    public Bishop(ChessPieceColor clr, Board b, int x, int y){
        super(clr, b, x, y);
        type = ChessPieceType.BISHOP;
        addMove(new ChessVector(1,1,true)); //north-east
        addMove(new ChessVector(-1,-1,true)); //south-west
        addMove(new ChessVector(-1,1,true)); //north-west
        addMove(new ChessVector(1,-1,true)); //south-east

        if(clr == ChessPieceColor.BLACK){
            label = ChessPieceLabel.BLACK_BISHOP;
        }
        else{
            label = ChessPieceLabel.WHITE_BISHOP;
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
        ChessVector u = v.unitVector();

        //Check if the unit vector is in the ArrayList of possible moves
        if(!containsMove(u)) return false;

        //Check if there are pieces along the way
        //For instance maxMovement of <0,-5> would be 5
        int maxMovement = Math.abs(v.getxMove());
        int i = 1;
        ChessVector scale = null;
        while(i<maxMovement){
            scale = u.scaleUp(i);
            if(!b.getSquareFrom(src,scale).isEmpty()) return false;
            i++;
        }

        return true;
    }
}
