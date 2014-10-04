package ChessGameController;

import BoardStructure.Board;
import BoardStructure.Square;
import GameModel.CustomChessGame;
import ChessGameView.BoardView;
import Pieces.ChessPiece;

/**
 * Created by hardikparikh on 9/26/14.
 */
public class ChessGameLoop {

    public static void main(String [] args){
        ChessPiece.ChessPieceColor currColor = ChessPiece.ChessPieceColor.WHITE;
        ChessPiece.ChessPieceColor oppColor = ChessPiece.ChessPieceColor.BLACK;

        CustomChessGame game = new CustomChessGame();
        BoardView view = new BoardView(game.getChessBoard());

        boolean blackFirstMove = false;
        boolean whiteFirstMove = false;

        while(true) {
            if(view.srcSelected){
                System.out.println("source selected");
            }
            if(view.dstSelected){
                System.out.println("destination selected");
            }
            if(view.srcSelected && view.dstSelected) {
                System.out.println("Current Color"+currColor);
                Square src = view.getSource();
                String srcText = "";
                String dstText = "";
                Square dst = view.getDestination();

                if (game.tryAndMove(src.getX(), src.getY(), dst.getX(), dst.getY(), oppColor)) {
                    System.out.println("Illegal move try again");
                    view.setAlertMessage("Illegal move try again");

                } else {
                    if(game.pieceInDangerAt(src.getX(), src.getY(), oppColor)){
                        view.setAlertMessage("Piece is in danger");
                    }

                    game.removePawnMoves(currColor);
                    //exchanging the symbols at source and destination
                    ChessPiece srcPiece = game.getChessBoard().ChessPieceAt(src.getX(), src.getY());
                    ChessPiece dstPiece = game.getChessBoard().ChessPieceAt(dst.getX(), dst.getY());
                    if(srcPiece != null) srcText = srcPiece.getLabel();
                    if(dstPiece != null) dstText = dstPiece.getLabel();
                    view.setLabelAt(src.getX(),src.getY(),srcText);
                    view.setLabelAt(dst.getX(),dst.getY(),dstText);

                    ChessPiece.ChessPieceColor tempColor = currColor;
                    currColor = oppColor;
                    oppColor = tempColor;
                }
                view.setSource(null);
                view.setDestination(null);
                view.srcSelected = false;
                view.dstSelected = false;
            }
        }
    }

}
