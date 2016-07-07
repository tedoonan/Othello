import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameController {

	boolean gameDone = false;
	static Board b;
	static BoardSquare[][] squares;
	static Player currentPlayer;
	static Player playerOne;
	static Player playerTwo;
	static int numTurns;

	private static void startGame() {
		
		squares = new BoardSquare[Board.boardSize][Board.boardSize];
		b = new Board();
		
		playerOne = new Player(SquareColour.BLACK);
		playerTwo = new Player(SquareColour.WHITE);
		
		currentPlayer = playerOne;
	}
	//squares[c.getXPos()][c.getYPos()].getColour();
	private static int flipTile(BoardSquare square, int xDir, int yDir) {
		//Returns -1 if not flips made
		int newXPos = square.getXPos() + xDir;
		int newYPos = square.getYPos() + yDir;
		
		
		
		if (newXPos >= 0 && newXPos < Board.boardSize && newYPos >= 0 && newYPos < Board.boardSize) {
			
			BoardSquare newSquare = squares[newXPos][newYPos];
		
			SquareColour squareColour = newSquare.getColour();

			if (squareColour == SquareColour.BLANK) {
				//Not valid chain
				return -1;
			}
			else {
				if (squareColour == currentPlayer.getColour()) {
					//End of chain
					return 0;
				}
				else {
					//Chain continues
					int numFurtherFlips = flipTile(newSquare, xDir, yDir);
					if (numFurtherFlips >= 0) {
						newSquare.setColour(currentPlayer.getColour());
						return numFurtherFlips + 1;
					}
					else {
						return -1;
					}
					
				}
			}
		}
		else return -1; //not on board so not valid chain
	}
	
	private static boolean flipTiles(BoardSquare square) {
		//returns false if no tiles flipped, meaning not a valid move
		
		int numFlips = 0;
		int numThisFlips = 0; //num of flips for this direction
		
		for (int i = -1; i <= 1; i ++) {
			for (int j = -1; j <= 1; j++ ) {
				numThisFlips = flipTile(square, i, j);
				if (numThisFlips == -1) {
					numThisFlips = 0;
				}
				numFlips += numThisFlips;
				System.out.println(i + " " + j);
			}
		}
		return numFlips > 0;
	}
	
	private static void endGame() {
		int numPlayerOne = 0;
		int numPlayerTwo = 0;
		String winText;
		
		for (int i = 0; i < squares.length; i++){
		     for (int j = 0; j < squares[i].length; j++){
		        if (squares[i][j].getColour() == playerOne.getColour()) {
		        	numPlayerOne++;
		        }
		        else numPlayerTwo++;
		     }
		}
		
		if (numPlayerOne > numPlayerTwo) {
			winText = "Player One Wins!:   ";
		}
		else winText = "Player Two Wins!:   ";
		
		JFrame frame = new JFrame();
	    JPanel backBoard = new JPanel();
	    JLabel winLabel = new JLabel(winText + numPlayerOne + " vs " + numPlayerTwo);
	    frame.setVisible(true);
	    frame.setSize(500,500);
	    
	    backBoard.repaint();
	    backBoard.add(winLabel);
	    frame.add(backBoard);
	    
	    frame.repaint();
		
	}
	
	public static void clicked(BoardSquare square) {
		
		if (square.getColour() == SquareColour.BLANK) {
			if (flipTiles(square)) {
				square.setColour(currentPlayer.getColour());
				
				//Switching players
				numTurns += 1;
				if (numTurns == Board.boardSize * Board.boardSize - 4) {
					endGame();
				}
				
				if (currentPlayer.equals(playerOne)) {
					currentPlayer = playerTwo;
				}
				else currentPlayer = playerOne;
			}
		}
			
		b.frame.repaint();
	}
	
	
	public static void main(String[] args) {
		startGame();
		

	    
	}

}
