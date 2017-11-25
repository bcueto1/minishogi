
public class GoldGeneral extends Piece {

	public GoldGeneral(int xPosition, int yPosition, String team) {
		super(xPosition, yPosition, team);
		this.setType("goldgeneral");
	}
	
	@Override
	public void updatePossibleMoves(Board board) {
		
		int xPos = this.getX(), yPos = this.getY();
		this.possibleMoves.clear();
		
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
		
	}


	

}
