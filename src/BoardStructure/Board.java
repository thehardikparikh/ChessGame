package BoardStructure;

import Pieces.ChessPiece;

/**
 * Board class to represent the chess board. Created by hardikparikh on 9/10/14.
 */
public class Board {
    private final int rows;
    private final int columns;
    private Square[][] board;

    /**
     * Constructor for the board class.Throws IllegalArgumentException if x and/or y are not non-negative.
     * @param r number of rows
     * @param c number of columns
     */
    public Board(int r, int c) {
        if((r<=0)||(c<=0)) throw new IllegalArgumentException("Non-negative integers only");

        rows = r;
        columns = c;
        board = new Square[rows][columns];

        //alternatively switching between black and white to produce the chessboard pattern
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                Square.SquareColor color = ((i + j) % 2 == 0) ? Square.SquareColor.WHITE : Square.SquareColor.BLACK;
                board[i][j] = new Square(i+1, j+1, color);
            }
        }
    }

    /**
     * getter for the number of rows.
     * @return the number of rows.
     */
    public int getRows(){return rows;}

    /**
     * getter for the number of rows.
     * @return the number of columns.
     */
    public int getColumns(){return columns;}

    /**
     * A getter for retrieving the BoardStructure.Square at position (x,y) on the board.
     * Throws IllegalArgumentException if x and/or y are not within bounds.
     * @param x x position of the Square containing the piece.
     * @param y y position of the Square containing the piece.
     * @return Square at position (x,y) on the board.
     */
    public Square getSquareAt(int x, int y){
        if(!validXY(x,y)) throw new IllegalArgumentException("x and y out of bounds");
        return board[x-1][y-1];
    }

    /**
     * A getter for retrieving the Pieces.ChessPiece at position (x,y) on the board.
     * Throws IllegalArgumentException if x and/or y are not within bounds.
     * @param x x position of the BoardStructure.Square containing the piece
     * @param y y position of the BoardStructure.Square containing the piece
     * @return ChessPiece at position (x,y) on the board
     */
    public ChessPiece ChessPieceAt(int x, int y){
        if(!validXY(x,y)) throw new IllegalArgumentException("x and/or y not within bounds");
        return getSquareAt(x,y).getPiece();
    }

    public void setChessPieceAt(int x, int y, ChessPiece p){
        if(!validXY(x,y)) throw new IllegalArgumentException("x and/or y not within bounds");
        getSquareAt(x,y).setPiece(p);
    }

    /**
     * A helper function to check if a position (x,y) on the board is empty.
     * Throws IllegalArgumentException if x and/or y are not within bounds.
     * @param x x position of the BoardStructure.Square containing the piece
     * @param y y position of the BoardStructure.Square containing the piece
     * @return True if the position is empty, False otherwise.
     */
    public boolean isEmpty(int x, int y){
        return ChessPieceAt(x,y) == null;
    }

    /**
     * Getter for the BoardStructure.Square at Vector v units from source (x,y).
     * Throws IllegalArgumentException if x and/or y are not within bounds OR if the destination square
     * happens to be out of bounds of the board
     * @param sq source square
     * @param v vector to move from the source square
     * @return Destination BoardStructure.Square on the board at a BoardStructure.ChessVector v from the source (x,y).
     */
    public Square getSquareFrom(Square sq, ChessVector v){
        if(!validXY(sq.getX(),sq.getY())) throw new IllegalArgumentException("x and/or y not within bounds");


        int xdst = sq.getX() + v.getxMove();
        int ydst = sq.getY() + v.getyMove();

        if(!validXY(xdst,ydst)) throw new IllegalArgumentException("Vector too large. BoardStructure.Square From Vector Addition out of bounds");

        return getSquareAt(xdst,ydst);
    }

    /**
     * A helper method to check if the x and y positions input are within the bounds of the board.
     * @param x x position to check.
     * @param y y position to check.
     * @return True if the positions are within bounds and False otherwise.
     */
    public boolean validXY(int x, int y){
        return (x>=1)&&(x<=rows)&&(y>=1)&&(y<=columns);
    }

    /**
     * A method to validate if movement of piece (if there exists) from source to destination.
     * @param xsrc x position of the piece to move.
     * @param ysrc y position of the piece to move.
     * @param xdst x position of the destination square.
     * @param ydst y position of the destination square.
     * @return True if the piece can move from the source square to the destination square.
     */
    public boolean validateMove(int xsrc, int ysrc, int xdst, int ydst){
        if(!validXY(xsrc,ysrc)) {
            System.out.println("Source x and y invalid");
            return false;
        }
        if(!validXY(xdst,ydst)){
            System.out.println("destination x and y invalid");
            return false;
        }
        if(isEmpty(xsrc,ysrc)){
            System.out.println("source square empty");
            return false;
        }

        Square dst = getSquareAt(xdst,ydst);

        return ChessPieceAt(xsrc,ysrc).canPieceMove(dst);
    }

    /**
     * A method to move a piece from its source to destination.
     * if the move is valid, the following will happen:-
     *the piece attribute of source and destination square will change
     *the square attribute of source Piece will change
     *the ChessPieceStatus of the destination piece will change (if there was one)
     * @param xsrc x position of the piece to move.
     * @param ysrc y position of the piece to move.
     * @param xdst x position of the destination square.
     * @param ydst y position of the destination square.
     * @return True if the try was successful, False otherwise.
     */
    public boolean tryToMove(int xsrc, int ysrc, int xdst, int ydst){
        if(validateMove(xsrc,ysrc,xdst,ydst)){
            Square src = getSquareAt(xsrc,ysrc);
            Square dst = getSquareAt(xdst,ydst);
            ChessPiece srcPiece = ChessPieceAt(xsrc,ysrc);
            ChessPiece dstPiece = ChessPieceAt(xdst,ydst);

            move(src,dst,srcPiece,dstPiece);
            return true;
        }
        return false;
    }

    /**
     * A helper method for tryToMove and moveAndUndo (in ChessGame).
     * the piece attribute of source and destination square will change
     *the square attribute of source Piece will change
     *the ChessPieceStatus of the destination piece will change (if there was one)
     * @param src source BoardStructure.Square
     * @param dst destination BoardStructure.Square
     * @param srcPiece piece at the source BoardStructure.Square
     * @param dstPiece piece at the destination BoardStructure.Square
     */
    public void move(Square src, Square dst, ChessPiece srcPiece, ChessPiece dstPiece){
        src.setPiece(null);
        srcPiece.setSquare(dst);
        if(dstPiece!=null) dstPiece.setStatus(ChessPiece.ChessPieceStatus.DEAD);
        dst.setPiece(srcPiece);
    }

    /**
     * A helper method for tryToMove and moveAndUndo (in GameModel.ChessGame). Should be
     * used in conjunction with move. No practical purpose otherwise. the piece attribute of source and destination square will change
     * the square attribute of source dstPiece and srcPiece will change
     * the ChessPieceStatus of the dstPiece will change (if there was one).
     * @param src source Square.
     * @param dst destination Square.
     * @param srcPiece destination Square.
     * @param dstPiece destination Square.
     */
    public void undoMove(Square src, Square dst, ChessPiece srcPiece, ChessPiece dstPiece){
        src.setPiece(srcPiece);
        dst.setPiece(dstPiece);
        srcPiece.setSquare(src);
        if(dstPiece != null){
            dstPiece.setSquare(dst);
            dstPiece.setStatus(ChessPiece.ChessPieceStatus.SAFE);
        }
    }

}
