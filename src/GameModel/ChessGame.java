package GameModel;

import BoardStructure.Board;
import BoardStructure.ChessVector;
import BoardStructure.Square;
import Pieces.*;

import java.util.HashSet;

/**
 * Class for ChessGame. Created by hardikparikh on 9/11/14.
 */
public class ChessGame {
    protected BlackPlayer blackPlayer;
    protected WhitePlayer whitePlayer;
    protected Board chessBoard;
    private static final int rows = 8;
    private static final int columns = 8;

    /**
     * A parametric constructor for ChessGame. To be used for the vanilla 8x8 chess game
     * @param populate if we want the players to populate the board with their pieces.
     */
    public ChessGame(boolean populate){
        chessBoard = new Board(rows,columns);
        whitePlayer = new WhitePlayer(chessBoard,populate);
        blackPlayer = new BlackPlayer(chessBoard, populate);
    }

    /**
     * A parametric constructor for ChessGame. To be used for a chess game with a different kind of board.
     * @param brd board to be used for the game.
     */
    public ChessGame(Board brd){
        chessBoard = brd;
        whitePlayer = new WhitePlayer(chessBoard,false);
        blackPlayer = new BlackPlayer(chessBoard,false);
    }

    /**
     * a function to check if the piece at position x,y is in danger from enemy pieces.
     * @param x position of the BoardStructure.Square under ambush.
     * @param y y position of the BoardStructure.Square under ambush.
     * @param oppositeColor color of the enemy pieces.
     * @return True if danger, False otherwise.
     */
    public boolean pieceInDangerAt(int x,int y, ChessPiece.ChessPieceColor oppositeColor){
        if(!chessBoard.validXY(x,y)) return false;

        if(oppositeColor == ChessPiece.ChessPieceColor.BLACK){
            return blackPlayer.isPointReachable(x,y);
        }
        else {
            return whitePlayer.isPointReachable(x,y);
        }
    }

    /**
     * a function to check if the king is under checkMate by enemy pieces
     * @param oppositeColor color of the enemy pieces.
     * @return True if checkMate, False otherwise.
     */
    public boolean checkMate(ChessPiece.ChessPieceColor oppositeColor){
        ChessPiece king = null;
        if(oppositeColor == ChessPiece.ChessPieceColor.BLACK) {
            king = whitePlayer.findPieceOfType(ChessPiece.ChessPieceType.KING);
        }
        else{
            king = blackPlayer.findPieceOfType(ChessPiece.ChessPieceType.KING);
        }

        HashSet<ChessVector> moves = king.getPossibleMoves();
        Square src = king.getSquare();
        boolean output = pieceInDangerAt(king.getX(),king.getY(),oppositeColor);

        //need to try all possibles moves to see if there is a safe move
        for(ChessVector v : moves){
            try{
                Square dest = chessBoard.getSquareFrom(src,v);
                if (!moveAndUndo(src.getX(), src.getY(), dest.getX(), dest.getY(),oppositeColor)) output = false;
            }
            catch (IllegalArgumentException e){
                continue;
            }
        }
            return true;

    }

    /**
     * getter for the chess board being used by the game.
     * @return chessboard being used by the game.
     */
    public Board getChessBoard(){
        return chessBoard;
    }

    /**
     * method to add a piece to a player's set.
     * @param p Chess Piece that we want to add.
     * @return true if the piece was added, false otherwise.
     */
    public boolean addPiece(ChessPiece p){
        if(p.getColor() == ChessPiece.ChessPieceColor.BLACK){
            return blackPlayer.addPiece(p);
        }
        else return whitePlayer.addPiece(p);
    }

    /**
     * Helper function to remove the double step moves of a pawn
     * @param color color of the player whose moves we want to remove
     */
    public void removePawnMoves(ChessPiece.ChessPieceColor color){
        if(color == ChessPiece.ChessPieceColor.BLACK){
            blackPlayer.removePawnMove();
        }
        else{
            whitePlayer.removePawnMove();
        }
    }

    /**
     * A helper function for checkMate to check if a piece is in danger if it were moved from source to destination.
     * @param xsrc x position of the piece to move.
     * @param ysrc y position of the piece to move.
     * @param xdst x position of the destination square.
     * @param ydst y position of the destination square.
     * @param oppColor color of the enemy pieces.
     * @return True if the piece is in danger OR invalid move, False otherwise.
     */
    public boolean moveAndUndo(int xsrc, int ysrc, int xdst, int ydst, ChessPiece.ChessPieceColor oppColor){
        if(!chessBoard.validateMove(xsrc,ysrc,xdst,ydst)) return true;
        Square src = chessBoard.getSquareAt(xsrc, ysrc);
        Square dst = chessBoard.getSquareAt(xdst, ydst);
        ChessPiece srcPiece = chessBoard.ChessPieceAt(xsrc, ysrc);
        ChessPiece dstPiece = chessBoard.ChessPieceAt(xdst, ydst);

        chessBoard.move(src,dst,srcPiece,dstPiece);

        //verify this position using pieceInDangerAt
        boolean output = pieceInDangerAt(xdst, ydst, oppColor);

        //undo everything
        chessBoard.undoMove(src,dst,srcPiece,dstPiece);
        return output;
    }

    /**
     * Tries to move the piece from source to destination
     * @param xsrc x position of the piece to move.
     * @param ysrc y position of the piece to move.
     * @param xdst x position of the destination square.
     * @param ydst y position of the destination square.
     * @param oppColor color of the enemy pieces.
     * @return True if the piece is in danger OR invalid move, False otherwise.
     */

    public boolean tryAndMove(int xsrc, int ysrc, int xdst, int ydst, ChessPiece.ChessPieceColor oppColor){
        if(!chessBoard.validateMove(xsrc,ysrc,xdst,ydst)) return true;

        Square src = chessBoard.getSquareAt(xsrc, ysrc);
        Square dst = chessBoard.getSquareAt(xdst, ydst);
        ChessPiece srcPiece = chessBoard.ChessPieceAt(xsrc, ysrc);
        ChessPiece dstPiece = chessBoard.ChessPieceAt(xdst, ydst);

        if(srcPiece.getColor() == oppColor) return true;

        chessBoard.move(src,dst,srcPiece,dstPiece);

        return false;
    }


}
