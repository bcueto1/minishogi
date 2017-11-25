
public class Bishop extends Piece {

	public Bishop(int xPosition, int yPosition, String team) {
		super(xPosition, yPosition, team);
		this.setType("bishop");
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void updatePossibleMoves(Board board) {
		
		int xPos = this.getX(), yPos = this.getY();
		this.possibleMoves.clear();
		
		if (this.isPromoted()) {
			checkPossibleMove(board, xPos + 1, yPos);
			checkPossibleMove(board, xPos + 1, yPos + 1);
			checkPossibleMove(board, xPos + 1, yPos - 1);
			checkPossibleMove(board, xPos - 1, yPos);
			checkPossibleMove(board, xPos - 1, yPos + 1);
			checkPossibleMove(board, xPos - 1, yPos - 1);
			checkPossibleMove(board, xPos, yPos + 1);
			checkPossibleMove(board, xPos, yPos - 1);
		}
		
		xPos = xPos + 1;
		yPos = yPos + 1;
		while(xPos < 5 && yPos < 5) {
			if (board.hasPiece(xPos, yPos)) {
				checkPossibleMove(board, xPos, yPos);
				break;
			}
			checkPossibleMove(board, xPos, yPos);
			xPos = xPos + 1;
			yPos = yPos + 1;
		}
		
		xPos = this.getX() + 1;
		yPos = this.getY() - 1;
		while(xPos < 5 && yPos >= 0) {
			// If the position actually has a piece
			if (board.hasPiece(xPos, yPos)) {
				checkPossibleMove(board, xPos, yPos);
				break;
			} 
			
			checkPossibleMove(board, xPos, yPos);
			xPos = xPos + 1;
			yPos = yPos - 1;
		}
		
		xPos = this.getX() - 1;
		yPos = this.getY() + 1;
		while(xPos >= 0 && yPos < 5) {
			// If the position actually has a piece
			if (board.hasPiece(xPos, yPos)) {
				checkPossibleMove(board, xPos, yPos);
				break;
			}
			
			checkPossibleMove(board, xPos, yPos);
			xPos = xPos - 1;
			yPos = yPos + 1;
		}
		
		xPos = this.getX() - 1;
		yPos = this.getY() - 1;
		while(xPos >= 0 && yPos >= 0) {
			// If the position actually has a piece
			if (board.hasPiece(xPos, yPos)) {
				checkPossibleMove(board, xPos, yPos);
				break;
			}
			
			checkPossibleMove(board, xPos, yPos);
			xPos = xPos - 1;
			yPos = yPos - 1;
		}
	}


	

}
