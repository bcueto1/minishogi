
public class Position {

	private Piece piece;
	private int x;
	private int y;
	
	public Position(Piece piece, int x, int y) {
		this.piece = piece;
		this.x = x;
		this.y = y;
	}
	
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
		this.piece = null;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public Piece getPiece() {
		return this.piece;
	}
	
	public void setPiece(Piece piece) {
		this.piece = piece;
	}
	
	public void removePiece() {
		this.piece = null;
	}
	
	public boolean hasPiece() {
		if (this.piece == null)
			return false;
		return true;

	}
	
	
}
