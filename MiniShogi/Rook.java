
public class Rook extends Piece {

	public Rook(int xPosition, int yPosition, String team) {
		super(xPosition, yPosition, team);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean canMove(Position[][] board, int newX, int newY) {
		
		if (!super.canMove(board, newX, newY))
			return false;
		int xPos = this.getX(), yPos = this.getY();
		
		
		// Not on a horizontal/vertical
		if (xPos != newX && yPos != newY) {
			return false;
		}
		
		if (xPos > newX) {
			xPos = xPos - 1;
			while(xPos >= 0) {
				// If the position actually has a piece
				if (board[xPos][yPos].hasPiece()) {
					if (xPos == newX && yPos == newY) {
						return true;
					} else {
						return false;
					}
				} else {
					// If there is no piece
					if (xPos == newX && yPos == newY)
						return true;
				}
				
				xPos = xPos -1;
			}
		}
		if (xPos < newX) {
			xPos = xPos + 1;
			while(xPos < 5) {
				// If the position actually has a piece
				if (board[xPos][yPos].hasPiece()) {
					if (xPos == newX && yPos == newY) {
						return true;
					} else {
						return false;
					}
				} else {
					// If there is no piece
					if (xPos == newX && yPos == newY)
						return true;
				}
				
				xPos = xPos + 1;
			}
		}
		
		if (yPos > newY) {
			yPos = yPos - 1;
			while(yPos >= 0) {
				// If the position actually has a piece
				if (board[xPos][yPos].hasPiece()) {
					if (xPos == newX && yPos == newY) {
						return true;
					} else {
						return false;
					}
				} else {
					// If there is no piece
					if (xPos == newX && yPos == newY)
						return true;
				}
				
				yPos = yPos - 1;
			}
		}
		
		if (yPos < newY) {
			yPos = yPos + 1;
			while(yPos < 5) {
				// If the position actually has a piece
				if (board[xPos][yPos].hasPiece()) {
					if (xPos == newX && yPos == newY) {
						return true;
					} else {
						return false;
					}
				} else {
					// If there is no piece
					if (xPos == newX && yPos == newY)
						return true;
				}
				
				yPos = yPos + 1;
			}
		}
		
		/*
		int xDir = 0, yDir = 0;
		if (xPos > newX) {
			xDir = -1;
		} else if (xPos < newX) {
			xDir = 1;
		}
		
		if (yPos > newY) {
			yDir = -1;
		} else if (yPos < newY) {
			yDir = 1;
		} */
		
		
		return false;
	}
	
	/*
	private boolean checkRookPath(Position[][] board, int x, int y, int newX, int newY, int xDir, int yDir) {
		
		int tempX = x + xDir;
		int tempY = y + yDir;
		while(tempX < 5 && tempX >= 0 && tempY < 5 && tempY >= 0) {
			
		}
		
		return false;
	}*/

}
