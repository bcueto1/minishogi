public abstract class Piece implements Cloneable {

    private int x;
    private int y;
    private String team;

    public Piece(int xPosition, int yPosition, String team) {
        this.x = xPosition;
        this.y = yPosition;
        this.team = team;
    }

    public int getX() {
    	return this.x;
    }
    
    public void setX(int x) {
    	this.x = x;
    }
    
    public int getY() {
    	return this.y;
    }
    
    public void setY(int y) {
    	this.y = y;
    }
    
    public String getTeam() {
    	return this.team;
    }
    
    public void setTeam(String newTeam) {
    	this.team = newTeam;
    }
    
    public Piece recreate() throws CloneNotSupportedException {
    	return (Piece) this.clone();
    }
    
    public boolean canMove(Position[][] board, int newX, int newY) {
    	int xPos = this.getX(), yPos = this.getY();
		if (xPos == newX && yPos == newY)
			return false;
		if (newX >= 5 || newX < 0 || newY >= 5 || newY < 0)
			return false;
		if (board[newX][newY].getPiece().getTeam().equals(this.team))
			return false;
		
		return true;
    }
    
    public void move(Position[][] board, int newX, int newY) {
    	if (this.canMove(board, newX, newY)) {
			this.setX(newX);
			this.setY(newY);
		}
    }
    
}