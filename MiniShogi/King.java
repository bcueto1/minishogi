public class King extends Piece {

	public King(int xPosition, int yPosition, String team) {
		super(xPosition, yPosition, team);
		this.setType("king");
	}
	

	@Override
	public boolean canMove(Position[][] board, int newX, int newY) {
		if (!super.canMove(board, newX, newY))
			return false;
		int xPos = this.getX(), yPos = this.getY();
		
		if (Math.abs(xPos - newX) > 1 || Math.abs(yPos - newY) > 1) {
			return false;
		}
		
		return true;
	}

}
