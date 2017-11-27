import java.util.ArrayList;

public class King extends Piece {
	
	private ArrayList<Position> availableInCheck;

	public King(int xPosition, int yPosition, String team) {
		super(xPosition, yPosition, team);
		this.setType("king");
		this.availableInCheck = new ArrayList<>();
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
	
	public void updateMovesInCheck(Board board) {
		
		int xPos = this.getX(), yPos = this.getY();
		this.possibleMoves.clear();
		
		this.checkMoveInCheck(board, xPos + 1, yPos);
		this.checkMoveInCheck(board, xPos + 1, yPos + 1);
		this.checkMoveInCheck(board, xPos + 1, yPos - 1);
		this.checkMoveInCheck(board, xPos - 1, yPos);
		this.checkMoveInCheck(board, xPos - 1, yPos + 1);
		this.checkMoveInCheck(board, xPos - 1, yPos - 1);
		this.checkMoveInCheck(board, xPos, yPos + 1);
		this.checkMoveInCheck(board, xPos, yPos - 1);
		
	}
	
	private void checkMoveInCheck(Board board, int newX, int newY) {
    	if (newX < 0 || newX >= 5 || newY < 0 || newY >= 5)
    		return;
    	Position[][] positions = board.getPositions();
    	for (int i = 0; i < positions.length; i++) {
			for (int j = 0; j < positions[0].length; j++) {
				if (!board.hasPiece(i, j))
					continue;
				Piece piece = board.getPiece(i, j);
				if (piece.getTeam().equals(this.getTeam()))
					continue;
				piece.updatePossibleMoves(board);
				for (Position position: piece.getPossibleMoves()) {
					int tempX = position.getX();
					int tempY = position.getY();
					if (tempX == newX && tempY == newY)
						return;
				}
			}
		}
    	if (!this.availableInCheck.contains(positions[newX][newY]))
    		this.availableInCheck.add(positions[newX][newY]);
	}
	
	public ArrayList<Position> getMovesInCheck() {
		return this.availableInCheck;
	}

}
