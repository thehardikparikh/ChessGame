package Pieces.PiecesTests

import BoardStructure.Board
import Pieces.Bishop
import Pieces.ChessPiece
import Pieces.Ferz
import Pieces.Pawn

/**
 * Test cases for the Ferz class.Created by hardikparikh on 9/18/14.
 */
class FerzTest extends GroovyTestCase {

    /**
     * tests whether the Ferz piece fails to move if the destination square conatins a piece of the same color
     */
   void testSameColorMove(){
       Board brd = new Board(8, 8);
       Ferz blackFerz = new Ferz(ChessPiece.ChessPieceColor.BLACK,brd,4,4);
       Pawn blackPawn = new Pawn(ChessPiece.ChessPieceColor.BLACK,brd,5,5);
       assertFalse(blackFerz.canPieceMove(brd.getSquareAt(5,5)));
       System.out.println("Test Same Color Move");
   }

    /**
     * tests whether the Ferz piece agrees to move if the destination square contains a piece of the opposite color
     */
   void testOppColorMove(){
       Board brd = new Board(8, 8);
       Ferz blackFerz = new Ferz(ChessPiece.ChessPieceColor.BLACK,brd,4,4);
       Pawn whitePawn = new Pawn(ChessPiece.ChessPieceColor.WHITE,brd,5,5);
       assertTrue(blackFerz.canPieceMove(brd.getSquareAt(5,5)));
       System.out.println("Test Opposite Color Move");
   }

    /**
     * Tests whether the Ferz piece disagrees to move if the move is invalid.
     */
   void testInvalidMove(){
       Board brd = new Board(8, 8);
       Ferz blackFerz = new Ferz(ChessPiece.ChessPieceColor.BLACK,brd,4,4);
       assertFalse(blackFerz.canPieceMove(brd.getSquareAt(4,5)));
       System.out.println("Test Invalid Move");
   }

    /**
     * Tests whether the Ferz piece agrees to move on all the valid moves in the possibleMoves list.
     */
   void testValidMoves(){
       Board brd = new Board(8,8);
       Ferz blackFerz = new Ferz(ChessPiece.ChessPieceColor.BLACK,brd,4,4);
       assertTrue(blackFerz.canPieceMove(brd.getSquareAt(5,5)));
       assertTrue(blackFerz.canPieceMove(brd.getSquareAt(3,3)));
       assertTrue(blackFerz.canPieceMove(brd.getSquareAt(3,5)));
       assertTrue(blackFerz.canPieceMove(brd.getSquareAt(5,3)));
       System.out.println("Test Valid Moves");
   }
}
