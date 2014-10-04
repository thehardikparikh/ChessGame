package Pieces.PiecesTests

import BoardStructure.Board
import Pieces.ChessPiece
import Pieces.Pawn

/**
 * Test cases for the pawn class. Created by hardikparikh on 9/12/14.
 */
class PawnTest extends GroovyTestCase {

    /**
     * This test checks for three invalid moves for pawns.
     * Moving along a vector that is not specified in the possibleMoves set.
     * Moving diagonally when there is no enemy piece.
     * Moving straight when there is an enemy piece.
     */
    void testInvalidMovePawn(){
        Board brd = new Board(8,8);
        Pawn pawn = new Pawn(ChessPiece.ChessPieceColor.BLACK,brd,4,4);
        Pawn whitePawn = new Pawn(ChessPiece.ChessPieceColor.WHITE,brd,4,5);
        assertFalse(pawn.canPieceMove(brd.getSquareAt(5,5)));
        assertFalse(pawn.canPieceMove(brd.getSquareAt(4,5)));
        assertFalse(pawn.canPieceMove(brd.getSquareAt(2,4)));
        System.out.println("Test Invalid Move");
    }

    /**
     * This test checks for two types of valid moves for pawns.
     * Moving along diagonal when there is an enemy piece.
     * Moving straight when it is empty.
     */
    void testValidMovePawn(){
        Board brd = new Board(8,8);
        Pawn pawn = new Pawn(ChessPiece.ChessPieceColor.BLACK,brd,4,4);
        Pawn whitePawn = new Pawn(ChessPiece.ChessPieceColor.WHITE,brd,5,5);
        assertTrue(pawn.canPieceMove(brd.getSquareAt(5,5)));
        assertTrue(whitePawn.canPieceMove(brd.getSquareAt(4,4)));
        System.out.println("Test Valid Move");
    }
}
