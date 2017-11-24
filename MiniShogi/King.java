public class King extends Piece {

	public King(int xPosition, int yPosition, String team) {
		super(xPosition, yPosition, team);
		this.setType("king");
	}


	@Override
	public void updatePossibleMoves(Position[][] board) {
		
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

}
