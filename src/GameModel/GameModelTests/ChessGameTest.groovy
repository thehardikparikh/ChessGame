package GameModel.GameModelTests

import GameModel.ChessGame
import Pieces.ChessPiece
import Pieces.King
import Pieces.Knight
import Pieces.Pawn
import Pieces.Queen
import Pieces.Rook

/**
 * Test cases for the ChessGame. Created by hardikparikh on 9/12/14.
 */
class ChessGameTest extends GroovyTestCase {

    /**
     * Places a Knight and a pawn of opposite colors diagonally so that the knight is in danger of attack from the pawn.
     * Then we check that the position of the Knight is under danger using the pieceInDangerAt function.
     */
    void testPieceInDangerAt() {
        ChessGame game = new ChessGame(false);
        Knight knight = new Knight(ChessPiece.ChessPieceColor.BLACK, game.getChessBoard(), 4, 4);
        Pawn pawn = new Pawn(ChessPiece.ChessPieceColor.WHITE, game.getChessBoard(), 5, 5);
        game.addPiece(pawn);
        game.addPiece(knight);
        assertTrue(game.pieceInDangerAt(4,4,ChessPiece.ChessPieceColor.WHITE));
        System.out.println("testPieceInDangerAt");
    }

    /**
     * Simulates a checkmate by placing a Pieces.Queen and two rooks of white color besides a black king so that the king
     * is under check via all possible moves. In this case the checkMate function should output true.
     */
    void testCheckMate() {
        ChessGame game = new ChessGame(false);
        King king = new King(ChessPiece.ChessPieceColor.BLACK, game.getChessBoard(), 4, 4);
        game.addPiece(king);
        Queen queen = new Queen(ChessPiece.ChessPieceColor.WHITE, game.getChessBoard(),4,6);
        Rook rook1 = new Rook(ChessPiece.ChessPieceColor.WHITE, game.getChessBoard(),3,6);
        Rook rook2 = new Rook(ChessPiece.ChessPieceColor.WHITE, game.getChessBoard(),5,6);
        game.addPiece(queen);
        game.addPiece(rook1);
        game.addPiece(rook2);
        assertTrue(game.checkMate(ChessPiece.ChessPieceColor.WHITE));
        System.out.println("testCheckMate");
    }
}
