package Pieces.PiecesTests

import BoardStructure.Board
import BoardStructure.ChessVector
import Pieces.ChessPiece
import Pieces.King
import Pieces.Pawn

/**
 * Test cases for the King class. Created by hardikparikh on 9/12/14.
 */
class KingTest extends GroovyTestCase {

    /**
     * Creates an empty chess board and checks if the king can move in all 8 directions.
     */
    void testCanMoveEmptySpaces() {
        Board brd = new Board(8,8);
        King k = new King(ChessPiece.ChessPieceColor.BLACK,brd,4,4);
        System.out.println("testCanMoveEmptySpaces");
        assertEquals(true,k.canPieceMove(brd.getSquareAt(4,5)));
        assertEquals(true,k.canPieceMove(brd.getSquareAt(5,4)));
        assertEquals(true,k.canPieceMove(brd.getSquareAt(3,4)));
        assertEquals(true,k.canPieceMove(brd.getSquareAt(4,3)));
        assertEquals(true,k.canPieceMove(brd.getSquareAt(3,5)));
        assertEquals(true,k.canPieceMove(brd.getSquareAt(5,3)));
        assertEquals(true,k.canPieceMove(brd.getSquareAt(5,5)));
        assertEquals(true,k.canPieceMove(brd.getSquareAt(3,3)));
    }

    /**
     * Passes a wrong move and checks if the containsMove function returns false.
     */
    void testContainsMove() {
        Board brd = new Board(8,8);
        King k = new King(ChessPiece.ChessPieceColor.BLACK,brd,4,4);
        System.out.println("testContainsMove");
        assertEquals(true,k.containsMove(new ChessVector(1,1,false)));
    }

    /**
     * Places a white piece and a black piece in the reach of the king and checks if the canPieceMove function
     * returns the appropriate output.
     */
    void testCanMoveWithPieces() {
        System.out.println("testCanMoveWithPieces");
        Board brd = new Board(8,8);
        King k = new King(ChessPiece.ChessPieceColor.BLACK,brd,4,4);
        Pawn pw = new Pawn(ChessPiece.ChessPieceColor.WHITE, brd,5,5);
        Pawn pb = new Pawn(ChessPiece.ChessPieceColor.BLACK,brd,4,5);
        assertEquals(false, k.canPieceMove(brd.getSquareAt(4,5)));
        assertEquals(true,k.canPieceMove(brd.getSquareAt(5,5)));
    }
}
