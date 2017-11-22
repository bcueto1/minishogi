
public class Bishop extends Piece {

	public Bishop(int xPosition, int yPosition, String team) {
		super(xPosition, yPosition, team);
		// TODO Auto-generated constructor stub
	}
	
	public boolean canMove(int newX, int newY) {
		
		int xPos = this.getX(), yPos = this.getY();
		if (xPos == newX && yPos == newY) {
			return false;
		}
		
		if (Math.abs(xPos - newX) > 1 || Math.abs(yPos - newY) > 1) {
			return false;
		}
		
		return true;
	}
	
	public void move(int newX, int newY) {
		if (this.canMove(newX, newY)) {
			this.setX(newX);
			this.setY(newY);
		}
	}

}
