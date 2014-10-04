package Pieces;
import BoardStructure.*;

/**
 * Rook class based on the ChessPiece class. Created by hardikparikh on 9/10/14.
 */
public class Rook extends ChessPiece implements ChessPieceLabel {

    /**
     * Constructor for the rook class.
     * @param clr color of the rook.
     * @param b board on which the rook is resting.
     * @param x x position of the rook.
     * @param y y position of the rook.
     */
    public Rook(ChessPieceColor clr, Board b, int x, int y){
        super(clr,b, x, y);
        type = ChessPieceType.ROOK;
        addMove(new ChessVector(1,0,true)); //up
        addMove(new ChessVector(0,1,true)); //right
        addMove(new ChessVector(0,-1,true)); //left
        addMove(new ChessVector(-1,0,true)); //down

        if(clr == ChessPieceColor.BLACK){
            label = ChessPieceLabel.BLACK_ROOK;
        }
        else{
            label = ChessPieceLabel.WHITE_ROOK;
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
        if(!containsMove(u)){
            System.out.println("Does not contain move");
            return false;
        }

        //Check if there are pieces along the way
        //For instance maxMovement of <0,-5> would be 5
        int maxMovement = Math.max(Math.abs(v.getxMove()),Math.abs(v.getyMove()));
        System.out.println("Max movement "+maxMovement);
        int i = 1;
        ChessVector scale = null;
        while(i<maxMovement){
            scale = u.scaleUp(i);
            //can safely call without worrying about exceptions
            if(!b.getSquareFrom(src,scale).isEmpty()) return false;
            i++;
        }

        return true;
    }
}
