public class Piece {

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
    
}