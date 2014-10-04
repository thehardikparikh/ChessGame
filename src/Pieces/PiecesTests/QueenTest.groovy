package Pieces.PiecesTests

import BoardStructure.Board
import Pieces.Bishop
import Pieces.ChessPiece
import Pieces.Pawn
import Pieces.Queen

/**
 * Test cases for the queen class. Created by hardikparikh on 9/12/14.
 */
class QueenTest extends GroovyTestCase {

    /**
     * Checks to see if the Queen fails to move along a vector if there are intermediate pieces along the way.
     */
    void testWithIntermediatePiecesQueen(){
        Board brd = new Board(8,8);
        Queen queen = new Queen(ChessPiece.ChessPieceColor.BLACK, brd,1,1);
        Queen whiteQueen = new Queen(ChessPiece.ChessPieceColor.WHITE,brd, 1,8);
        Pawn whitePawn= new Pawn(ChessPiece.ChessPieceColor.WHITE, brd,1,4);
        Pawn blackPawn= new Pawn(ChessPiece.ChessPieceColor.BLACK, brd,4,4);
        Bishop whiteBishop = new Bishop(ChessPiece.ChessPieceColor.WHITE,brd, 8,8);

        assertFalse(queen.canPieceMove(brd.getSquareAt(1,8)));
        assertFalse(whiteQueen.canPieceMove(brd.getSquareAt(1,1)));
        assertFalse(queen.canPieceMove(brd.getSquareAt(8,8)));

        System.out.println("test With Intermediate Pieces Pieces.Queen");
    }

    /**
     * This test checks that the Pieces.Queen fails to make any moves that are not in its set of possibleMoves.
     * Namely, it verifies that the Queen does not move like a knight.
     */
    void testInvalidMovesQueen(){
        Board brd = new Board(8,8);
        Queen queen = new Queen(ChessPiece.ChessPieceColor.BLACK, brd,1,1);
        assertFalse(queen.canPieceMove(brd.getSquareAt(3,2)));
        System.out.println("test With Invalid Moves Pieces.Queen");
    }


    /**
     * Tests the valid moves the the Queen can make.
     */
    void testValidMoves(){
        Board brd = new Board(8,8);
        Queen queen = new Queen(ChessPiece.ChessPieceColor.BLACK, brd,4,4);
        assertTrue(queen.canPieceMove(brd.getSquareAt(6,6)));
        assertTrue(queen.canPieceMove(brd.getSquareAt(4,6)));
        assertTrue(queen.canPieceMove(brd.getSquareAt(6,4)));
        assertTrue(queen.canPieceMove(brd.getSquareAt(2,2)));
        assertTrue(queen.canPieceMove(brd.getSquareAt(2,4)));
        assertTrue(queen.canPieceMove(brd.getSquareAt(4,2)));
        System.out.println("test Valid Moves Pieces.Queen");
    }


}
