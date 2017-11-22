
public class GoldGeneral extends Piece {

	public GoldGeneral(int xPosition, int yPosition, String team) {
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
			if (newX == xPos - 1) {
				if (newY == yPos)
					return true;
			}
			if (newX == xPos + 1) {
				if (newY == yPos || newY == yPos + 1 || newY == yPos -1)
					return true;
			}
			if (newY == yPos + 1 || newY == yPos - 1) {
				if (newX == xPos)
					return true;
			}
		}
		
		if (this.getTeam() == "lower") {
			if (newX == xPos - 1) {
				if (newY == yPos || newY == yPos + 1 || newY == yPos - 1)
					return true;
			}
			if (newX == xPos + 1) {
				if (newY == yPos)
					return true;
			}
			if (newY == yPos + 1 || newY == yPos - 1) {
				if (newX == xPos)
					return true;
			}
		}
		
		
		return false;
	}

}
