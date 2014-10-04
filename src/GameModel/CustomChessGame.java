package GameModel;

import BoardStructure.Board;
import Pieces.*;

/**
 * Created by hardikparikh on 9/25/14.
 */
public class CustomChessGame extends ChessGame {
    private static final int numRows = 12;
    private static final int numColumns = 12;

    /**
     * Generic default constructor for the Custom chess game
     */
    public CustomChessGame() {
        super(new Board(numRows, numColumns));

        //white starts at row 1 and fills pawns at row 2
        whitePlayer.fillWithPawns(2);
        fillPieces(1, ChessPiece.ChessPieceColor.WHITE);

        //black starts at row 12 and fills pawns at row 11
        blackPlayer.fillWithPawns(11);
        fillPieces(12, ChessPiece.ChessPieceColor.BLACK);

    }


    private void fillPieces(int row, ChessPiece.ChessPieceColor color) {
        addPiece(new Ferz(color,chessBoard,row,1));
        addPiece(new ThreeLeaper(color,chessBoard,row,2));
        addPiece(new Rook(color, chessBoard, row, 3));
        addPiece(new Knight(color, chessBoard, row, 4));
        addPiece(new Bishop(color, chessBoard, row, 5));
        addPiece(new Queen(color, chessBoard, row, 6));
        addPiece(new King(color, chessBoard, row, 7));
        addPiece(new Bishop(color, chessBoard, row, 8));
        addPiece(new Knight(color, chessBoard, row, 9));
        addPiece(new Rook(color, chessBoard, row, 10));
        addPiece(new ThreeLeaper(color,chessBoard,row,11));
        addPiece(new Ferz(color,chessBoard,row,12));
    }
}