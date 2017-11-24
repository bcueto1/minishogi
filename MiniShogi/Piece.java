public abstract class Piece implements Cloneable {

    private int x;
    private int y;
    private String type;
    private String team;
    private boolean isPromoted;

    public Piece(int xPosition, int yPosition, String team) {
        this.x = xPosition;
        this.y = yPosition;
        this.team = team;
        this.isPromoted = false;
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
    
    public String getType() {
    	return this.type;
    }
    
    public void setType(String type) {
    	this.type = type;
    }
    
    public String getTeam() {
    	return this.team;
    }
    
    public void setTeam(String newTeam) {
    	this.team = newTeam;
    }
    
    public boolean isPromoted() {
    	return this.isPromoted;
    }
    
    public void promote() {
    	this.isPromoted = true;
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
		if (board[newX][newY].getPiece().getType().equals("king"))
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