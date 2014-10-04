package BoardStructure;

/**
 * ChessVector class representing a move on a chess board. Created by hardikparikh on 9/10/14.
 */
public class ChessVector {
    private int xMove;
    private int yMove;
    private boolean scalable;

    /**
     * A generic parametric constructor for a ChessVector.
     * @param x movement in x direction.
     * @param y movement in y direction.
     * @param canScale to specify whether the vector is scalable or not.
     */
    public ChessVector(int x, int y, boolean canScale){
        xMove = x;
        yMove = y;
        scalable = canScale;
    }

    /*
    GETTERS, SETTERS AND HELPERS
     */

    /**
     * getter for the xMove.
     * @return returns the movement in x direction.
     */
    public int getxMove(){
        return xMove;
    }

    /**
     * getter for the yMove.
     * @return returns the movement in y direction.
     */
    public int getyMove(){
        return yMove;
    }

    /**
     * method to check if the vector can be scaled or not.
     * @return True if the vector can be scaled, False otherwise.
     */
    public boolean canScale(){
        return scalable;
    }

    /**
     * Method to override the equals method of the Object class.
     * @param other the Object to compare with
     * @return True if the x and y movements of both vectors are same, false otherwise.
     */
    @Override
    public boolean equals(Object other){
        if (other instanceof ChessVector) {
             return (((ChessVector) other).getxMove() == xMove) && (((ChessVector) other).getyMove() == yMove);
        }
        return false;
    }

    /**
     * Method to scale up the vector.
     * if the vector is scalable it returns a vector magnified by size parameter, throws IllegalArgumentException if the vector is not scalable
     * @param size the size by which we want to scale the vector.
     * @return scaled up vector if the instance is scalable.
     */
    public ChessVector scaleUp(int size) {
        if(!canScale()) throw new IllegalArgumentException("Vector cannot be scaled");

        if(size <= 0) throw new IllegalArgumentException("Method accepts non-negative integers only");

        return new ChessVector(getxMove()*size, getyMove()*size,true);
    }

    /**
     * Finds the unit vector of a given vector if it is diagonal, horizontal or vertical.
     * @return unit vector form of the vector if it is diagonal, horizontal or vertical, returns the vector instance otherwise.
     */
    public ChessVector unitVector() {
        if(!canScale()) return this;

        int absY = Math.abs(yMove);
        int absX = Math.abs(xMove);

        if((absY!=0)&&(absX!=0)&&(absX!=absY)){
            return this;
        }
        // for instance if the vector is <0,-5> then scaleDown is <0,-1>
        int scaleDown = Math.max(absX,absY);

        return new ChessVector(getxMove()/scaleDown, getyMove()/scaleDown,canScale());
    }
}
