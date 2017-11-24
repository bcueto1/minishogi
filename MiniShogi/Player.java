import java.util.ArrayList;

public class Player {

	private String team;
	private ArrayList<Piece> captured;
	
	public Player(String team) {
		this.team = team;
		this.captured = new ArrayList<>();
	}
	
	public String getTeam() {
		return this.team;
	}
	
	public void setTeam(String team) {
		this.team = team;
	}
	
	public void capturePiece(Piece piece) {
		this.captured.add(piece);
	}
	
	public void dropPiece(Piece piece) {
		
	}
	
}
