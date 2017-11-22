
public class Bishop extends Piece {

	public Bishop(int xPosition, int yPosition, String team) {
		super(xPosition, yPosition, team);
		// TODO Auto-generated constructor stub
	}
	
	public boolean canMove(Position[][] board, int newX, int newY) {
		
		int xPos = this.getX(), yPos = this.getY();
		if (xPos == newX && yPos == newY) {
			return false;
		}
		
		xPos = xPos + 1;
		yPos = yPos + 1;
		while(xPos < 5 && yPos < 5) {
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
			yPos = yPos + 1;
		}
		
		xPos = this.getX() + 1;
		yPos = this.getY() - 1;
		while(xPos < 5 && yPos >= 0) {
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
			yPos = yPos - 1;
		}
		
		xPos = this.getX() - 1;
		yPos = this.getY() + 1;
		while(xPos >= 0 && yPos < 5) {
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
			
			xPos = xPos - 1;
			yPos = yPos + 1;
		}
		
		xPos = this.getX() - 1;
		yPos = this.getY() - 1;
		while(xPos >= 0 && yPos >= 0) {
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
			
			xPos = xPos - 1;
			yPos = yPos - 1;
		}
		
		return false;
	}
	
	public void move(Position[][] board, int newX, int newY) {
		if (this.canMove(board, newX, newY)) {
			this.setX(newX);
			this.setY(newY);
		}
	}

}
