import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;

public class BoardSquare extends JComponent{
	private int xPos = 0;
	private int yPos = 0;
	
	SquareColour colour = SquareColour.BLANK;
	
	BoardSquare(int x, int y, SquareColour type) {
		this.setBorder(new LineBorder(Color.CYAN, 2));
		xPos = x;
		yPos = y;
		colour = type;	
	}
	

	
	public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        Rectangle box = new Rectangle(0,0,100,100);
        g2.draw(box);
        
        if (colour == SquareColour.WHITE) {
        	g2.setPaint(Color.WHITE);
        	g2.fill(box);
        }
        
        else if (colour == SquareColour.BLACK) {
        	g2.setPaint(Color.BLACK);
        	g2.fill(box);
        }
    }
	
	public SquareColour getColour() {
		return colour;
	}
	
	public void setColour(SquareColour myColour) {
		colour = myColour;
	}
	
	public int getXPos() {
		return xPos;
	}
	
	public int getYPos() {
		return yPos;
	}
}
