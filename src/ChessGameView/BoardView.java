package ChessGameView;

import BoardStructure.*;
import GameModel.ChessGame;
import GameModel.CustomChessGame;
import Pieces.ChessPiece;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane.*;
import Pieces.*;
/**
 * Created by hardikparikh on 9/18/14.
 */
public class BoardView {

    private int squareWidth = 100; //width of each square
    private int squareHeight = 100; //height of each square
    private static Color [] squareColors = {Color.orange, Color.yellow}; //array for the two colors possible for each square
    private JFrame frame; //the main window
    private JPanel majorPanel; //the panel lying on top of the window
    private SquareLabel [][] squares; //the set of squares lying on top of the window. But each square wrapped by a panelBase.
    private Square src = null;//new Square(1,4, Square.SquareColor.BLACK);
    private Square dest = null;//new Square(3,4, Square.SquareColor.BLACK);
    public boolean srcSelected = false;//true;
    public boolean dstSelected = false;//true;

    private class SquareLabel extends JLabel implements MouseListener {

        private int sqX;
        private int sqY;
        public SquareLabel(String text, int x, int y){
            super(text,JLabel.CENTER);
            sqX = x;
            sqY = y;
            this.addMouseListener(this);
        }

        public void mousePressed(MouseEvent e) {
            //need to keep this otherwise class will be abstract
        }

        public void mouseReleased(MouseEvent e) {
            //need to keep this otherwise class will be abstract
        }

        public void mouseEntered(MouseEvent e) {
            //need to keep this otherwise class will be abstract
        }

        public void mouseExited(MouseEvent e) {
            //need to keep this otherwise class will be abstract
        }

        public void mouseClicked(MouseEvent e) {
            if(src == null){
                System.out.println("source is "+sqX+" "+sqY);
                src = new Square(sqX+1,sqY+1, Square.SquareColor.BLACK);
                srcSelected = true;
            }
            else if( dest == null){
                System.out.println("destination is "+sqX+" "+sqY);
                dest = new Square(sqX+1,sqY+1, Square.SquareColor.BLACK);
                dstSelected = true;
            }
        }
    }

    /**
     * Generic Parametric constructor for a board view. Initializes the window, panel and the squares.
     * @param brd the board on which we want to base our view.
     */
    public BoardView(Board brd){

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch(Exception e) {
            //silently ignore
        }

        int frameWidth = squareWidth * brd.getColumns(); //width of the window
        int frameHeight = squareHeight * brd.getRows(); //height of the window

        //setting the window frame
        initFrame("Chess Game View", frameWidth, frameHeight);

        //panel on top of the window frame
        initPanel(frameWidth, frameHeight, brd);

        //setting up each square
        initSquares(brd);

        //displaying the view
        majorPanel.setOpaque(true);
        frame.setContentPane(majorPanel);
        frame.pack();
        frame.setVisible(true);

        System.out.println(srcSelected);
        System.out.println(dstSelected);
    }

    /**
     * private helper function to initialize the frame window of the view.
     * @param text The title text.
     * @param frameWidth The width of the window.
     * @param frameHeight The height of the window.
     */
    private void initFrame(String text, int frameWidth, int frameHeight){
        frame = new JFrame(text); //title text of the window
        frame.setSize(frameWidth, frameHeight); //set the size of the window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //operations to be executed on pressing the close button
    }

    /**
     * private helper function to initialize the major Panel lying on top of the window.
     * @param width width of the panel which is set equal to the width of the window.
     * @param height height of the panel which is set equal to the height of the window.
     * @param brd the board on which we want to base our view.
     */
    private void initPanel(int width, int height, Board brd) {
        majorPanel = new JPanel();
        majorPanel.setPreferredSize(new Dimension(width, height));
        majorPanel.setLayout(new GridLayout(brd.getRows(), brd.getColumns()));
    }

    /**
     * private helper function to initialize all the square of the board view.
     * @param brd the board on which we want to base our view.
     */
    private void initSquares(Board brd){
        squares = new SquareLabel[brd.getRows()][brd.getColumns()];

        for( int x = 0; x < brd.getRows(); x++ ){
            for( int y = 0; y < brd.getColumns(); y++ ){
                JPanel panelBase = new JPanel(new BorderLayout()); //the square lies underneath the basePanel
                panelBase.setBackground(squareColors[(x+y)%2]); // alternate color for each square
                squares[x][y] = initSingleSquare(x,y,brd); //set the features of the square at (x,y)
                //first add square to the panelBase and then add the panelBase to the majorPanel
                panelBase.add(squares[x][y]);
                majorPanel.add(panelBase);
            }
        }
    }

    /**
     * helper function to initialize a single square in the board view.
     * @param x x position of the square on the board view.
     * @param y y position of the square on the board view.
     * @param brd the board on which we want to base our view.
     * @return the square initialized with the text (chess piece), alignment, font and color.
     */
    private SquareLabel initSingleSquare(int x, int y, Board brd){
        SquareLabel square = new SquareLabel(getTextFor(x,y,brd),x, y);
        square.setPreferredSize(new Dimension(squareWidth,squareHeight));
        square.setFont(new Font("Serif", Font.PLAIN, 48));
        square.setBackground(squareColors[(x+y)%2]);
        return square;
    }

    /**
     * private helper function to retrieve the text (chess piece) corresponding to a particular position on the board.
     * @param squareX the x position of query.
     * @param squareY the y position of query.
     * @param board the board on which we want to base our view.
     * @return empty string if the square is empty, unicode string of the chess piece otherwise.
     */
    private String getTextFor(int squareX, int squareY, Board board)
    {
        Square square = board.getSquareAt(squareX+1,squareY+1);
        if(square.isEmpty()) return "";

        ChessPiece piece = square.getPiece();
        return piece.getLabel();
    }

    public void setAlertMessage(String text){
        JOptionPane.showMessageDialog(null,text);
    }

    /**
     * method to retrieve the source square
     * @return the source square
     */
    public Square getSource(){
        System.out.println("what is happening in source");
        return src;
    }

    /**
     * method to retrieve the destination square
     * @return the destination square
     */
    public Square getDestination(){
        //System.out.println(" what is happening");
        System.out.println("what is happening in destination");
        return dest;
    }

    /**
     * method to set the source square
     * @param square the source square that we want to set
     */
    public void setSource(Square square){

        src = square;
    }

    /**
     * method to set the destination square
     * @param square the destination square that we want to set
     */
    public void setDestination(Square square){

        dest = square;
    }

    public void setLabelAt(int x, int y, String text){
        squares[x-1][y-1].setText(text);
        frame.validate();
    }


    /**
     * main function to display the view.
     * @param args
     */
    /*
    public static void main(String[] args) {

        CustomChessGame game = new CustomChessGame();
        BoardView view = new BoardView(game.getChessBoard());
    }
    */
}
