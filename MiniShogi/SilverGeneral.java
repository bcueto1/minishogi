
public class SilverGeneral extends Piece {

	public SilverGeneral(int xPosition, int yPosition, String team) {
		super(xPosition, yPosition, team);
		this.setType("silvergeneral");
	}

	@Override
	public boolean canMove(Position[][] board, int newX, int newY) {
		
		if (!super.canMove(board, newX, newY))
			return false;
		
		int xPos = this.getX(), yPos = this.getY();
		
		if (this.isPromoted()) {
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
		} else {
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
		}
		
		
		
		return false;
	}
	
	

}
