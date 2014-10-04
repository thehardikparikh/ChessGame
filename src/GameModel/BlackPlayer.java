package GameModel;

import BoardStructure.Board;
import Pieces.ChessPiece;

/**
 * Class for a black player. Created by hardikparikh on 9/11/14.
 */
public class BlackPlayer extends Player {

    /**
     * Basic parametric constructor for a black player.
     * @param brd board on which the player plays.
     * @param populate whether we want the player to populate the board or not.
     */
    public BlackPlayer(Board brd, boolean populate){
        super(brd, ChessPiece.ChessPieceColor.BLACK);
        /*
        fill the pawns and non-pawn pieces only if populate is specified.
        Use addPieces to fill otherwise
        */
        if(populate) {
            int startRow = 1;
            populateFrom(startRow);
            fillWithPawns(startRow + 1);
        }
    }
}
