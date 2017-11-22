import java.util.ArrayList;

public class King extends Piece {

	public King(int xPosition, int yPosition, String team) {
		super(xPosition, yPosition, team);
		
	}
	

	@Override
	public boolean canMove(Position[][] board, int newX, int newY) {
		
		int xPos = this.getX(), yPos = this.getY();
		if (xPos == newX && yPos == newY) {
			return false;
		}
		
		if (Math.abs(xPos - newX) > 1 || Math.abs(yPos - newY) > 1) {
			return false;
		}
		
		return true;
	}

}
