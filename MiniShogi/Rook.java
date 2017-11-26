
public class Rook extends Piece {

	public Rook(int xPosition, int yPosition, String team) {
		super(xPosition, yPosition, team);
		this.setType("rook");
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
		
		xPos = xPos - 1;
		while (xPos >= 0) {
			if (board.hasPiece(xPos, yPos)) {
				checkPossibleMove(board, xPos, yPos);
				break;
			}
			checkPossibleMove(board, xPos, yPos);
			xPos = xPos - 1;
		}
		
		xPos = this.getX() + 1;
		while (xPos < 5) {
			if (board.hasPiece(xPos, yPos)) {
				checkPossibleMove(board, xPos, yPos);
				break;
			}
			checkPossibleMove(board, xPos, yPos);
			xPos = xPos + 1;
		}
		
		xPos = this.getX();
		yPos = yPos - 1;
		while (yPos >= 0) {
			if (board.hasPiece(xPos, yPos)) {
				checkPossibleMove(board, xPos, yPos);
				break;
			}
			checkPossibleMove(board, xPos, yPos);
			yPos = yPos - 1;
		}
		
		yPos = this.getY() + 1;
		while (yPos < 5) {
			if (board.hasPiece(xPos, yPos)) {
				checkPossibleMove(board, xPos, yPos);
				break;
			}
			checkPossibleMove(board, xPos, yPos);
			yPos = yPos + 1;
		}
		
		
	}
	
	@Override
	public String toString() {
		String rep = "";
		if (this.isPromoted())
			rep += "+";
		if (this.getTeam().equals("upper"))
			rep += "R";
		else
			rep += "r";
		return rep;
	}


	
	

}
