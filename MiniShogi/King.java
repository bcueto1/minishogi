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
	protected void checkPossibleMove(Board board, int newX, int newY) {
		Position[][] positions = board.getBoard();
		if (board.hasPiece(newX, newY)) {
    		if (positions[newX][newY].getPiece().getType().equals("king"))
    			return;
    		if (positions[newX][newY].getPiece().getTeam().equals(this.getTeam()))
    			return;
    	}
		
		if (newX < 0 || newX >= 5 || newY < 0 || newY >= 5)
			return;
		
		Position thisPosition = board.getPosition(newX, newY);
		for (int i = 0; i < positions.length; i++) {
			for (int j = 0; j < positions[0].length; j++) {
				Piece piece = positions[i][j].getPiece();
				if (!piece.getTeam().equals(this.getTeam())) {
					for (Position danger: piece.getPossibleMoves()) {
						if (thisPosition.equals(danger))
							return;
					}
				}
				
			}
		}
		
		
		this.possibleMoves.add(positions[newX][newY]);
		
		
	}

}
