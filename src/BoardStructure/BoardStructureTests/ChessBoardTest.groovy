package BoardStructure.BoardStructureTests

import BoardStructure.Board
import Pieces.ChessPiece
import Pieces.King
import Pieces.Pawn

/**
 * Tests for the Chess Board class. Created by hardikparikh on 9/12/14.
 */
class ChessBoardTest extends GroovyTestCase {

    /**
     * Testing if the validateMove function outputs true in the correct case.
     */
    void testValidateMovePass() {
        Board brd = new Board(8,8);
        King king = new King(ChessPiece.ChessPieceColor.BLACK, brd, 2,2);
        Pawn whitePawn = new Pawn(ChessPiece.ChessPieceColor.WHITE,brd,3,3);

        boolean output = brd.validateMove(2,2,3,3);
        assertEquals(true,output);
        System.out.println("testValidateMovePass");
    }

    /**
     * Testing if the validateMove function outputs false in the incorrect case.
     */
    void testValidateMoveFail(){
        Board brd = new Board(8,8);
        King king = new King(ChessPiece.ChessPieceColor.BLACK, brd, 2,2);
        Pawn whitePawn = new Pawn(ChessPiece.ChessPieceColor.BLACK,brd,3,3);
        boolean output = brd.validateMove(2,2,3,3);
        assertEquals(false,output);
        System.out.println("testValidateMoveFail");
    }

    /**
     * Testing if the TryToMove function function outputs true for the correct case.
     */
    void testTryToMovePass(){

        Board brd = new Board(8,8);
        King king = new King(ChessPiece.ChessPieceColor.BLACK, brd, 2,2);
        Pawn whitePawn = new Pawn(ChessPiece.ChessPieceColor.WHITE,brd,3,3);

        boolean output = brd.tryToMove(2,2,3,3);
        assertEquals(true,output);

        output = whitePawn.getStatus() == ChessPiece.ChessPieceStatus.DEAD;
        assertEquals(true,output);

        output = (king.getX() == 3)&&(king.getY() == 3);
        assertEquals(true,output);

        output = brd.isEmpty(2,2);
        assertEquals(true,output);
        System.out.println("testTryToMove");
    }

    /**
     * Testing if the TryToMove function fails to move in the incorrect case.
     */
    void testTryToMoveFail(){

        Board brd = new Board(8,8);
        King king = new King(ChessPiece.ChessPieceColor.BLACK, brd, 2,2);
        Pawn pawn = new Pawn(ChessPiece.ChessPieceColor.BLACK,brd,3,3);

        boolean output = brd.tryToMove(2,2,3,3);
        assertEquals(false,output);

        output = pawn.getStatus() == ChessPiece.ChessPieceStatus.DEAD;
        assertEquals(false,output);

        output = (king.getX() == 3)&&(king.getY() == 3);
        assertEquals(false,output);

        output = brd.isEmpty(2,2);
        assertEquals(false,output);
        System.out.println("testTryToMove");
    }
}
