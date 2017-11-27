
public class Bishop extends Piece {

	public Bishop(int xPosition, int yPosition, String team) {
		super(xPosition, yPosition, team);
		this.setType("bishop");
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
			checkPossibleMove(board, xPos, yPos);
			if (board.hasPiece(xPos, yPos))
				break;
			xPos = xPos + 1;
			yPos = yPos + 1;
		}
		
		xPos = this.getX() + 1;
		yPos = this.getY() - 1;
		while(xPos < 5 && yPos >= 0) {
			checkPossibleMove(board, xPos, yPos);
			if (board.hasPiece(xPos, yPos))
				break;
			xPos = xPos + 1;
			yPos = yPos - 1;
		}
		
		xPos = this.getX() - 1;
		yPos = this.getY() + 1;
		while(xPos >= 0 && yPos < 5) {
			
			checkPossibleMove(board, xPos, yPos);
			if (board.hasPiece(xPos, yPos))
				break;
			xPos = xPos - 1;
			yPos = yPos + 1;
		}
		
		xPos = this.getX() - 1;
		yPos = this.getY() - 1;
		while(xPos >= 0 && yPos >= 0) {
			
			checkPossibleMove(board, xPos, yPos);
			if (board.hasPiece(xPos, yPos))
				break;
			xPos = xPos - 1;
			yPos = yPos - 1;
		}
	}
	
	@Override
	public String toString() {
		String rep = "";
		if (this.isPromoted())
			rep += "+";
		if (this.getTeam().equals("upper"))
			rep += "B";
		else
			rep += "b";
		return rep;
	}


	

}
