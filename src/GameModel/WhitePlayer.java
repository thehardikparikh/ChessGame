package GameModel;

import BoardStructure.Board;
import GameModel.Player;
import Pieces.ChessPiece;

/**
 * Class for the white player. Created by hardikparikh on 9/11/14.
 */
public class WhitePlayer extends Player {
    /**
     * Basic parametric constructor for a white player.
     * @param brd board on which the player plays.
     * @param populate whether we want the player to populate the board or not.
     */
    public WhitePlayer(Board brd, boolean populate){
        super(brd, ChessPiece.ChessPieceColor.WHITE);
        /*
        fill the pawns and non-pawn pieces only if populate is specified.
        Use addPieces to fill otherwise
        */
        if(populate) {
            int startRow = 8;
            populateFrom(startRow);
            fillWithPawns(startRow - 1);
        }
    }
}
