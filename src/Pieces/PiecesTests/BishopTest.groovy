package Pieces.PiecesTests

import BoardStructure.Board
import Pieces.Bishop
import Pieces.ChessPiece
import Pieces.Pawn

/**
 * Test cases for the Bishop class. Created by hardikparikh on 9/12/14.
 */
class BishopTest extends GroovyTestCase {

    /**
     * This test checks that the bishop fails to move if there are intermediate pieces along the vector.
     * In this test we can see that neither the black piece nor the white one can move along the vector because of the
     * pawn in between.
     */
    void testWithIntermediatePiecesBishop(){
        Board brd = new Board(8,8);
        Bishop bishop = new Bishop(ChessPiece.ChessPieceColor.BLACK, brd,1,1);
        Bishop whiteBishop = new Bishop(ChessPiece.ChessPieceColor.WHITE,brd, 8,8);
        Pawn whitePawn= new Pawn(ChessPiece.ChessPieceColor.BLACK, brd,4,4);
        assertFalse(bishop.canPieceMove(brd.getSquareAt(8,8)));
        assertFalse(whiteBishop.canPieceMove(brd.getSquareAt(1,1)));
        System.out.println("test With Intermediate Pieces Pieces.Bishop");
    }

    /**
     * This test checks that the bishop fails to make any moves that are not in its set of possibleMoves.
     * Namely, it verifies that the bishop does not move in a straight line.
     */
    void testInvalidMovesBishop(){
        Board brd = new Board(8,8);
        Bishop bishop = new Bishop(ChessPiece.ChessPieceColor.BLACK, brd,1,1);
        assertFalse(bishop.canPieceMove(brd.getSquareAt(1,8)));
        System.out.println("test Invalid Moves Pieces.Bishop");
    }

    /**
     * Tests the valid moves the the bishop can make in all 4 diagonal directions.
     */
    void testValidMovesBishop(){
        Board brd = new Board(8,8);
        Bishop bishop = new Bishop(ChessPiece.ChessPieceColor.BLACK, brd,4,4);
        assertTrue(bishop.canPieceMove(brd.getSquareAt(1,1)));
        assertTrue(bishop.canPieceMove(brd.getSquareAt(8,8)));
        assertTrue(bishop.canPieceMove(brd.getSquareAt(7,1)));
        assertTrue(bishop.canPieceMove(brd.getSquareAt(1,7)));
        System.out.println("test Valid Moves Pieces.Bishop");
    }
}
