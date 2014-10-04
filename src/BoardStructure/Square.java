package BoardStructure;

import Pieces.ChessPiece;

/**
 * A square class to represent each position on the chessboard. Created by hardikparikh on 9/10/14.
 */
public class Square {
    public enum SquareColor{BLACK,WHITE};
    private final SquareColor color;
    private final int xpos;
    private final int ypos;
    private ChessPiece piece;

    /**
     * A generic parametric constructor to construct a Square.
     * @param x x position of the square.
     * @param y y position of the square.
     * @param clr color of the square on the board to be selected from the SquareColor enum.
     */
    public Square(int x, int y, SquareColor clr){
        xpos = x;
        ypos = y;
        color = clr;
        piece = null;
    }

    /*
    SETTERS, GETTERS AND HELPERS
     */

    /**
     * method to check if a square is empty or not.
     * @return True if empty, False otherwise.
     */
    public boolean isEmpty(){
        return null == piece;
    }

    /**
     * Method to get the chess piece on the square.
     * @return Chess piece lying on the square.
     */
    public ChessPiece getPiece(){
        return piece;
    }

    /**
     * Method to set the piece on the square.
     * @param p piece that we want to place on the square.
     */
    public void setPiece(ChessPiece p){
            piece = p;
    }

    /**
     * Method to acquire the x position of the square on the board.
     * @return x position of the square.
     */
    public int getX(){
        return xpos;
    }

    /**
     * Method to acquire the y position of the square on the board.
     * @return y position of the square.
     */
    public int getY(){
        return ypos;
    }

    /**
     * Method to get the ChessVector between two squares.
     * @param src source square.
     * @param dest destination square.
     * @return ChessVector between the two squares.
     */
    public static ChessVector getVector(Square src, Square dest){
        return new ChessVector(dest.getX()-src.getX(),dest.getY()-src.getY(),true);
    }



}
