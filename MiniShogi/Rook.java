
public class Rook extends Piece {

	public Rook(int xPosition, int yPosition, String team) {
		super(xPosition, yPosition, team);
		this.setType("rook");
	}

	@Override
	public boolean canMove(Position[][] board, int newX, int newY) {
		
		if (!super.canMove(board, newX, newY))
			return false;
		int xPos = this.getX(), yPos = this.getY();
		
		if (this.isPromoted()) {
			if (Math.abs(xPos - newX) <= 1 && Math.abs(yPos - newY) <= 1)
				return true;
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
		
		
		return false;
	}
	

}
