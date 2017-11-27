import java.util.ArrayList;

public class Player {

	private String team;
	private King king;
	private ArrayList<Piece> captured;
	
	public Player(String team) {
		this.team = team;
		this.captured = new ArrayList<>();
	}
	
	public String getTeam() {
		return this.team;
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
		this.king = null;
		this.king = king;
	}
	
	
	public String capturedString() {
		String cap = "";
		for (Piece piece: this.captured) {
			cap += piece.toString() + " ";
		}
		return cap;
	}
	
}
