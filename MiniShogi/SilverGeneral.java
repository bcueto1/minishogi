
public class SilverGeneral extends Piece {

	public SilverGeneral(int xPosition, int yPosition, String team) {
		super(xPosition, yPosition, team);
		this.setType("silvergeneral");
	}
	
	@Override
	public void updatePossibleMoves(Board board) {
		
		int xPos = this.getX(), yPos = this.getY();
		this.possibleMoves.clear();
		
		if (this.isPromoted()) {
			if (this.getTeam() == "upper") {
				checkPossibleMove(board, xPos - 1, yPos);
				checkPossibleMove(board, xPos + 1, yPos + 1);
				checkPossibleMove(board, xPos + 1, yPos - 1);
				checkPossibleMove(board, xPos + 1, yPos);
				checkPossibleMove(board, xPos, yPos + 1);
				checkPossibleMove(board, xPos, yPos - 1);
			}
			if (this.getTeam() == "lower") {
				checkPossibleMove(board, xPos + 1, yPos);
				checkPossibleMove(board, xPos - 1, yPos + 1);
				checkPossibleMove(board, xPos - 1, yPos - 1);
				checkPossibleMove(board, xPos - 1, yPos);
				checkPossibleMove(board, xPos, yPos + 1);
				checkPossibleMove(board, xPos, yPos - 1);
			}
		} else {
			if (this.getTeam() == "upper") {
				checkPossibleMove(board, xPos - 1, yPos + 1);
				checkPossibleMove(board, xPos - 1, yPos - 1);
				checkPossibleMove(board, xPos + 1, yPos);
				checkPossibleMove(board, xPos + 1, yPos + 1);
				checkPossibleMove(board, xPos + 1, yPos - 1);
			}
			if (this.getTeam() == "lower") {
				checkPossibleMove(board, xPos + 1, yPos + 1);
				checkPossibleMove(board, xPos + 1, yPos - 1);
				checkPossibleMove(board, xPos - 1, yPos);
				checkPossibleMove(board, xPos - 1, yPos + 1);
				checkPossibleMove(board, xPos - 1, yPos - 1);
			}
			
		}
		
	}

	@Override
	public String toString() {
		String rep = "";
		if (this.isPromoted())
			rep += "+";
		if (this.getTeam().equals("upper"))
			rep += "S";
		else
			rep += "s";
		return rep;
	}

	
	
	

}
