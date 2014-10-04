package Pieces;
import BoardStructure.*;

/**
 * Queen class based on the ChessPiece class. Created by hardikparikh on 9/10/14.
 */
public class Queen extends ChessPiece implements ChessPieceLabel {

    /**
     * Cosntructor for the Queen class.
     * @param clr color of the Queen.
     * @param b board on which the queen is resting.
     * @param x x position of the queen on the board.
     * @param y y position of the queen on the board.
     */
    public Queen(ChessPieceColor clr, Board b, int x, int y){
        super(clr,b,x,y);
        type = ChessPieceType.QUEEN;
        addMove(new ChessVector(1,0,true)); //right
        addMove(new ChessVector(0,1,true)); //up
        addMove(new ChessVector(0,-1,true)); //down
        addMove(new ChessVector(-1,0,true)); //left
        addMove(new ChessVector(1,1,true)); //north-east
        addMove(new ChessVector(-1,-1,true)); //south-west
        addMove(new ChessVector(-1,1,true)); //north-west
        addMove(new ChessVector(1,-1,true)); //south-east

        if(clr == ChessPieceColor.BLACK){
            label = ChessPieceLabel.BLACK_QUEEN;
        }
        else{
            label = ChessPieceLabel.WHITE_QUEEN;
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
        int maxMovement = Math.max(Math.abs(v.getxMove()),Math.abs(v.getyMove()));
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
