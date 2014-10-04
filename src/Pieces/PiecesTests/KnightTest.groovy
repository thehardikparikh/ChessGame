package Pieces.PiecesTests

import BoardStructure.Board
import BoardStructure.Square
import Pieces.ChessPiece
import Pieces.Knight
import Pieces.Pawn

/**
 * Test cases for the Knight class. Created by hardikparikh on 9/12/14.
 */
class KnightTest extends GroovyTestCase {
    /**
     * Places pawns of the same color in all 8 possible jumps of the pawn. This is to check if the pawn
     * refuses to jump if the piece on destination is of the same color.
     */
    void testSameColorMove() {
        Board brd = new Board(8,8);
        Knight knight = new Knight(ChessPiece.ChessPieceColor.BLACK,brd,4,4);
        Pawn p1 = new Pawn(ChessPiece.ChessPieceColor.BLACK,brd, 4+2,4+1);
        Pawn p2 = new Pawn(ChessPiece.ChessPieceColor.BLACK,brd,4-2,4-1);
        Pawn p3 = new Pawn(ChessPiece.ChessPieceColor.BLACK,brd,4+2,4-1);
        Pawn p4 = new Pawn(ChessPiece.ChessPieceColor.BLACK,brd,4-2,4+1);
        Pawn p5 = new Pawn(ChessPiece.ChessPieceColor.BLACK,brd,4+1,4+2);
        Pawn p6 = new Pawn(ChessPiece.ChessPieceColor.BLACK,brd,4-1,4+2);
        Pawn p7 = new Pawn(ChessPiece.ChessPieceColor.BLACK,brd,4+1,4-2);
        Pawn p8 = new Pawn(ChessPiece.ChessPieceColor.BLACK,brd,4-1,4-2);

        assertFalse(knight.canPieceMove(brd.getSquareAt(4+2,4+1)));
        assertFalse(knight.canPieceMove(brd.getSquareAt(4-2,4+1)));
        assertFalse(knight.canPieceMove(brd.getSquareAt(4+2,4-1)));
        assertFalse(knight.canPieceMove(brd.getSquareAt(4-2,4-1)));
        assertFalse(knight.canPieceMove(brd.getSquareAt(4+1,4+2)));
        assertFalse(knight.canPieceMove(brd.getSquareAt(4-1,4+2)));
        assertFalse(knight.canPieceMove(brd.getSquareAt(4+1,4-2)));
        assertFalse(knight.canPieceMove(brd.getSquareAt(4-1,4-2)));
        System.out.println("testSameColorMove");
    }

    /**
     * Passes destination squares that are out of the board to check if the knight refuses to move or not.
     */
    void testOutOfBoundMove() {
        Board brd = new Board(8,8);
        Knight knight = new Knight(ChessPiece.ChessPieceColor.BLACK,brd,1,1);
        assertFalse(knight.canPieceMove(new Square(1-1,1-2,Square.SquareColor.BLACK)));
        System.out.println("testOutOfBoundMove");
    }

    /**
     * Passes a move that is not contained within the list of possible moves and checks if the knight refuses or not.
     */
    void testInvalidMove(){
        Board brd = new Board(8,8);
        Knight knight = new Knight(ChessPiece.ChessPieceColor.BLACK,brd,1,1);
        assertFalse(knight.canPieceMove(brd.getSquareAt(4,4)));
        System.out.println("testInvalidMove");
    }

    /**
     * Passes a valid move to check if the knight agrees to move or not.
     */
    void testValidMove(){
        Board brd = new Board(8,8);
        Knight knight = new Knight(ChessPiece.ChessPieceColor.BLACK,brd,4,4);
        Pawn p1 = new Pawn(ChessPiece.ChessPieceColor.WHITE,brd, 4+2,4+1);
        assertTrue(knight.canPieceMove(brd.getSquareAt(4+2,4+1)));
        System.out.println("testValidMove");
    }
}
