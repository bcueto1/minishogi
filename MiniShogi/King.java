public class King extends Piece {

	public King(int xPosition, int yPosition, String team) {
		super(xPosition, yPosition, team);
		this.setType("king");
	}


	@Override
	public void updatePossibleMoves(Board board) {
		
		int xPos = this.getX(), yPos = this.getY();
		this.possibleMoves.clear();
		
		checkPossibleMove(board, xPos + 1, yPos);
		checkPossibleMove(board, xPos + 1, yPos + 1);
		checkPossibleMove(board, xPos + 1, yPos - 1);
		checkPossibleMove(board, xPos - 1, yPos);
		checkPossibleMove(board, xPos - 1, yPos + 1);
		checkPossibleMove(board, xPos - 1, yPos - 1);
		checkPossibleMove(board, xPos, yPos + 1);
		checkPossibleMove(board, xPos, yPos - 1);
		
	}

	@Override
	public String toString() {
		String rep = "";
		if (this.isPromoted())
			rep += "+";
		if (this.getTeam().equals("upper"))
			rep += "K";
		else
			rep += "k";
		return rep;
	}

}
