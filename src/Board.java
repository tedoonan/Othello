import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

public class Board{
	
	
	public JFrame frame = new JFrame();
    public JPanel backBoard = new JPanel();
    public static int boardSize = 8;
    private int boardWidth = 800;
    private int squareWidth = boardWidth / boardSize;
    GridLayout experimentLayout = new GridLayout(boardSize,boardSize);
    
    
    
	Board() {

		backBoard.setLayout(experimentLayout);
		frame.setSize(boardWidth,boardWidth);
		backBoard.setSize(boardWidth,boardWidth);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		backBoard.setVisible(true);
		
		BoardSquare myBoardSquare;
		SquareColour colour = SquareColour.BLANK;
		
		
		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				if (i == 3 && j == 3 || i == 4 && j == 4) {
					colour = SquareColour.WHITE;
				}
				else if (i == 3 && j == 4 || i == 4 && j == 3) {
					colour = SquareColour.BLACK;
				}
				
				myBoardSquare = new BoardSquare(i, j, colour);
				myBoardSquare.addMouseListener(new MouseAdapter() { 
			          public void mousePressed(MouseEvent evt) { 
			        	  BoardSquare c = (BoardSquare) evt.getComponent();
			        	  
			        	 
			        	  
			        	  GameController.clicked(c);
			        	  
			          } 
			        });
				
				GameController.squares[i][j] = myBoardSquare;
				backBoard.add(myBoardSquare);
				colour = SquareColour.BLANK;
			}
		}
		
		
		backBoard.repaint();
		frame.add(backBoard);
		frame.repaint();
	}



	


}
