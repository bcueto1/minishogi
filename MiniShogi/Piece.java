import java.util.ArrayList;

public abstract class Piece implements Cloneable {

    private int x;
    private int y;
    private String type;
    private String team;
    private boolean isPromoted;
    protected ArrayList<Position> possibleMoves;

    public Piece(int xPosition, int yPosition, String team) {
        this.x = xPosition;
        this.y = yPosition;
        this.team = team;
        this.isPromoted = false;
        this.possibleMoves = new ArrayList<>();
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
    
    public ArrayList<Position> getPossibleMoves() {
    	return this.possibleMoves;
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
    
    public void demote() {
    	this.isPromoted = false;
    }
    
    public Piece recreate() throws CloneNotSupportedException {
    	return (Piece) this.clone();
    }
    
    protected void checkPossibleMove(Board board, int newX, int newY) {
    	Position[][] positions = board.getBoard();
    	if (board.hasPiece(newX, newY)) {

    		if (positions[newX][newY].getPiece().getTeam().equals(this.getTeam()))
    			return;
    	}
		if (newX >= 0 && newX < 5 && newY >= 0 && newY < 5)
			this.possibleMoves.add(positions[newX][newY]);
	}
    
    public abstract void updatePossibleMoves(Board board);
    
}