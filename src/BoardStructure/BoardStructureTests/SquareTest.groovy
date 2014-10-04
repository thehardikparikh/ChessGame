package BoardStructure.BoardStructureTests

import BoardStructure.Board
import BoardStructure.ChessVector
import BoardStructure.Square
import Pieces.ChessPiece
import Pieces.King

/**
 * Tests for the Square class. Created by hardikparikh on 9/12/14.
 */
class SquareTest extends GroovyTestCase {

    /**
     *A test to check if the isEmpty function of the BoardStructure.Square class works. Simply creates and new square
     *whose Piece attribute is set to null by default. Then it calls the isEmpty function on the square
     */
    void testIsEmpty() {
        Square sq = new Square(1,1,Square.SquareColor.BLACK);
        //System.out.println(sq.isEmpty());
        assertEquals(true,sq.isEmpty());
    }

    /**
     * A sanity test to check if the GetPiece function of BoardStructure.Square class works as expected.
     * Simply calling the generic equals function to check if the ChessPiece created and passed on to various objects are the same.
     */
    void testGetPiece() {
        Board brd = new Board(8,8);
        King k = new King(ChessPiece.ChessPieceColor.BLACK,brd,1,1);
        Square sq = brd.getSquareAt(1,1);

        assertEquals(true,sq.equals(k.getSquare()));
        assertEquals(true,(sq.getPiece()).equals(k));
    }

    /**
     * This test creates two Squares and checks if the vector created matches our expectations.
     */
    void testGetVector() {
        Board brd = new Board(8,8);
        Square sq1 = brd.getSquareAt(1,1);
        Square sq2 = brd.getSquareAt(3,5);
        ChessVector output = Square.getVector(sq1,sq2);
        assertEquals(2,output.getxMove());
        assertEquals(4,output.getyMove());
    }
}
