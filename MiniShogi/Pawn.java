
public class Pawn extends Piece {

	public Pawn(int xPosition, int yPosition, String team) {
		super(xPosition, yPosition, team);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean canMove(Position[][] board, int newX, int newY) {
		
		int xPos = this.getX(), yPos = this.getY();
		if (xPos == newX && yPos == newY) {
			return false;
		}
		
		if (this.getTeam() == "upper") {
			if (newX == xPos + 1 && yPos == newY) {
				return true;
			}
		}
		
		if (this.getTeam() == "lower") {
			if (newX == xPos - 1 && yPos == newY) {
				return true;
			}
		}
		
		
		return false;
	}

}
