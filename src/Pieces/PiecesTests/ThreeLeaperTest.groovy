package Pieces.PiecesTests

import BoardStructure.*
import Pieces.ChessPiece
import Pieces.*;
/**
 * Created by hardikparikh on 9/18/14.
 */
class ThreeLeaperTest extends GroovyTestCase {

    /**
     * Tests the ThreeLeaper object can leap three steps back and forth in the x direction.
     */
    void testValidMovesThreeLeaper(){
        Board brd = new Board(8,8);
        ThreeLeaper blackThreeLeaper = new ThreeLeaper(ChessPiece.ChessPieceColor.BLACK, brd,4,4);
        assertTrue(blackThreeLeaper.canPieceMove(brd.getSquareAt(1,4)));
        assertTrue(blackThreeLeaper.canPieceMove(brd.getSquareAt(7,4)));
        System.out.println("Test Valid Moves Three Leaper");
    }

    /**
     * Tests whether the ThreeLeaper refuses to move if the move required is not in the list of possible moves.
     */
    void testInvalidMovesThreeLeaper(){
        Board brd = new Board(8,8);
        ThreeLeaper blackThreeLeaper = new ThreeLeaper(ChessPiece.ChessPieceColor.WHITE,brd,4,4);
        assertFalse(blackThreeLeaper.canPieceMove(brd.getSquareAt(5,4)));
        System.out.println("Test Invalid Moves Three Leaper");
    }

    /**
     * Test to check if the ThreeLeaper refuses to move if the destination square has a piece of the same color.
     */
    void testSameColorMoveThreeLeaper(){
        Board brd = new Board(8,8);
        ThreeLeaper blackThreeLeaper = new ThreeLeaper(ChessPiece.ChessPieceColor.BLACK,brd,4,4);
        Pawn blackPawn1 = new Pawn(ChessPiece.ChessPieceColor.BLACK,brd,7,4);
        Pawn blackPawn2 = new Pawn(ChessPiece.ChessPieceColor.BLACK,brd,1,4);
        assertFalse(blackThreeLeaper.canPieceMove(brd.getSquareAt(7,4)));
        assertFalse(blackThreeLeaper.canPieceMove(brd.getSquareAt(1,4)));
        System.out.println("Test Same Color Moves Three Leaper");
    }

    /**
     * Test to check if the ThreeLeaper agrees to move if the destination square has a piece of the opposite color.
     */
    void testOppColorMoveThreeLeaper(){
        Board brd = new Board(8,8);
        ThreeLeaper blackThreeLeaper = new ThreeLeaper(ChessPiece.ChessPieceColor.BLACK,brd,4,4);
        Pawn whitePawn1 = new Pawn(ChessPiece.ChessPieceColor.WHITE,brd,7,4);
        Pawn whitePawn2 = new Pawn(ChessPiece.ChessPieceColor.WHITE,brd,1,4);
        assertTrue(blackThreeLeaper.canPieceMove(brd.getSquareAt(7,4)));
        assertTrue(blackThreeLeaper.canPieceMove(brd.getSquareAt(1,4)));
        System.out.println("Test Opposite Color Moves Three Leaper");
    }
}
