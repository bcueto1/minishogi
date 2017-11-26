import java.util.ArrayList;

public class Player {

	private String team;
	private King king;
	private ArrayList<Piece> active;
	private ArrayList<Piece> captured;
	
	public Player(String team) {
		this.team = team;
		this.captured = new ArrayList<>();
		this.active = new ArrayList<>();
	}
	
	public String getTeam() {
		return this.team;
	}
	
	public void setTeam(String team) {
		this.team = team;
	}
	
	public ArrayList<Piece> getActive() {
		return this.active;
	}
	
	public void addActivePiece(Piece piece) {
		this.active.add(piece);
	}
	
	public void removeActivePiece(Piece piece) {
		this.active.remove(piece);
	}
	
	public void addCapturedPiece(Piece piece) {
		this.captured.add(piece);
	}
	
	public void removeCapturedPiece(Piece piece) {
		this.captured.remove(piece);
	}
	
	public ArrayList<Piece> getCaptured() {
		return this.captured;
	}
	
	public King getKing() {
		return this.king;
	}
	
	public void setKing(King king) {
		this.king = king;
	}
	
}
