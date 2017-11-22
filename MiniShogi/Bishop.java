
public class Bishop extends Piece {

	public Bishop(int xPosition, int yPosition, String team) {
		super(xPosition, yPosition, team);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean canMove(Position[][] board, int newX, int newY) {
		
		if (!super.canMove(board, newX, newY))
			return false;
		int xPos = this.getX(), yPos = this.getY();
		// Bishop move cannot result in same x or y
		if (xPos == newX || yPos == newY) {
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

}
