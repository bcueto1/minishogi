
public class SilverGeneral extends Piece {

	public SilverGeneral(int xPosition, int yPosition, String team) {
		super(xPosition, yPosition, team);
		
	}

	@Override
	public boolean canMove(Position[][] board, int newX, int newY) {
		
		int xPos = this.getX(), yPos = this.getY();
		if (xPos == newX && yPos == newY) {
			return false;
		}
		
		if (this.getTeam() == "upper") {
			if (newX == xPos - 1) {
				if (newY == yPos + 1 || newY == yPos - 1)
					return true;
			}
			if (newX == xPos + 1) {
				if (newY == yPos || newY == yPos + 1 || newY == yPos -1)
					return true;
			}
		}
		
		if (this.getTeam() == "lower") {
			if (newX == xPos - 1) {
				if (newY == yPos || newY == yPos + 1 || newY == yPos - 1)
					return true;
			}
			if (newX == xPos + 1) {
				if (newY == yPos + 1 || newY == yPos -1)
					return true;
			}
		}
		
		
		return false;
	}
	
	

}
