package Pieces.PiecesTests

import BoardStructure.Board
import Pieces.ChessPiece
import Pieces.Pawn
import Pieces.Rook

/**
 * Test cases for the rook class. Created by hardikparikh on 9/12/14.
 */
class RookTest extends GroovyTestCase {

    /**
     * This test checks that the rook does not travel along a vector if there are pieces along the way.
     * Basically, we do not want the rook to leap.
     */
    void testWithIntermediatePiecesRook(){
        Board brd = new Board(8,8);
        Rook rook = new Rook(ChessPiece.ChessPieceColor.BLACK, brd,1,1);
        Rook whiteRook = new Rook(ChessPiece.ChessPieceColor.BLACK,brd, 1,8);
        Pawn whitePawn= new Pawn(ChessPiece.ChessPieceColor.BLACK, brd,1,4);
        assertFalse(rook.canPieceMove(brd.getSquareAt(1,8)));
        assertFalse(whiteRook.canPieceMove(brd.getSquareAt(1,1)));
        System.out.println("test With Intermediate Pieces Pieces.Rook");
    }

    /**
     * This test checks that the rook does not move in a way that is not specified within its list of possible moves.
     * We check that the rook fails to move diagonally in this test.
     */
    void testInvalidMovesRook(){
        Board brd = new Board(8,8);
        Rook rook = new Rook(ChessPiece.ChessPieceColor.BLACK, brd,1,1);
        assertFalse(rook.canPieceMove(brd.getSquareAt(5,5)));
        System.out.println("test Invalid Moves Pieces.Rook");
    }

    /**
     * This test checks if the rook can move along its four specified directions.
     */
    void testValidMovesRook(){
        Board brd = new Board(8,8);
        Rook rook = new Rook(ChessPiece.ChessPieceColor.BLACK, brd,4,4);
        assertTrue(rook.canPieceMove(brd.getSquareAt(4,2)));
        assertTrue(rook.canPieceMove(brd.getSquareAt(2,4)));
        assertTrue(rook.canPieceMove(brd.getSquareAt(4,6)));
        assertTrue(rook.canPieceMove(brd.getSquareAt(6,4)));
        System.out.println("test Valid Moves Pieces.Rook");
    }

}
