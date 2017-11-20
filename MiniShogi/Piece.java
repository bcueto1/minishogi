

public class Piece {

    private String position;
    private String type;
    private String team;

    public Piece(String position, String type, String team) {
        this.position = position;
        this.type = type;
        this.team = team;
    }


    public String getPosition() {
        return this.position;
    }

    public void setPosition(String newPosition) {
        this.position = newPosition;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String newType) {
        this.type = newType;
    }
    
    public String getTeam() {
    	return this.team;
    }
    
    public void setTeam(String newTeam) {
    	this.team = newTeam;
    }

}