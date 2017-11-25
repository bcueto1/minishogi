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
	
	public void movePiece(Board board, int x, int y, int newX, int newY, boolean promote, Player otherPlayer) throws IllegalMoveException {
		Position[][] positions = board.getBoard();
		if (board.hasPiece(x, y)) {
			Piece piece = positions[x][y].getPiece();
			if (!piece.getTeam().equals(this.team))
				throw new IllegalMoveException();
			try { piece = positions[x][y].getPiece().recreate(); } catch (CloneNotSupportedException e) { e.printStackTrace(); }
			piece.move(board, newX, newY);
			if (this.isCapture(positions[newX][newY]))
				this.capturePiece(positions[newX][newY].getPiece(), otherPlayer);
			
			positions[newX][newY].setPiece(piece);
			if (this.isPawnPromoted(piece))
				piece.promote();
			if (promote)
				this.promotePiece(piece);
			
			
			piece.updatePossibleMoves(board);
			positions[x][y].removePiece();
		} else {
			throw new IllegalMoveException();
		}
	}
	
	public void dropPiece(Position[][] board, String type, int x, int y) throws IllegalMoveException {
		if (board[x][y].hasPiece())
			throw new IllegalMoveException();
		if (type.equals("pawn")) {
			if (this.getTeam().equals("upper")) {
				if (x == 4)
					throw new IllegalMoveException();
				for (int i = 0; i < 5; i++) {
					if (board[i][y].getPiece().getType().equals("pawn") && board[i][y].getPiece().getTeam().equals("upper")) {
						throw new IllegalMoveException();
					}
				}
			}
			if (this.getTeam().equals("lower")) {
				if (x == 0)
					throw new IllegalMoveException();
				for (int i = 0; i < 5; i++) {
					if (board[i][y].getPiece().getType().equals("pawn") && board[i][y].getPiece().getTeam().equals("lower")) {
						throw new IllegalMoveException();
					}
				}
			}
		}
		
		Piece piece = null;
		
		for (Piece caps: this.captured) {
			if (caps.getType().equals(type)) {
				this.active.add(caps);
				this.captured.remove(caps);
				piece = caps;
				break;
			}
		}
		
		if (piece == null)
			throw new IllegalMoveException();
		
		piece.setX(x);
		piece.setY(y);
		board[x][y].setPiece(piece);
	}
	
	private void capturePiece(Piece cap, Player player) {
		
		/*
		for (Piece piece: player.getActive()) {
			int tempX = piece.getX();
			int tempY = piece.getY();
			
			if (tempX == cap.getX() && tempY == cap.getY())
				player.removeActivePiece(piece);
		} */
		
		if (cap.getTeam().equals("upper"))
			cap.setTeam("lower");
		else if (cap.getTeam().equals("lower"))
			cap.setTeam("upper");
		cap.demote();
		cap.setX(-1);
		cap.setY(-1);
		
		player.removeActivePiece(cap);
		this.captured.add(cap);
	}
	
	private boolean isCapture(Position position) {
		if (position.hasPiece())
			return true;
		return false;
	}
	
	private boolean isPawnPromoted(Piece piece) {
		if (!piece.getType().equals("pawn"))
			return false;
		
		if (piece.getTeam().equals("upper") && piece.getX() == 4)
			return true;
		
		if (piece.getTeam().equals("lower") && piece.getX() == 0)
			return true;
			
		return false;
	}
	
	private void promotePiece(Piece piece) throws IllegalMoveException {
		if (piece.getType().equals("king"))
			throw new IllegalMoveException();
		if (piece.getTeam().equals("upper")) {
			if (piece.getX() == 4)
				piece.promote();
			else
				throw new IllegalMoveException();
		} else if (piece.getTeam().equals("lower")) {
			if (piece.getX() == 0)
				piece.promote();
			else
				throw new IllegalMoveException();
		}
	}
	
	public void addActivePiece(Piece piece) {
		this.active.add(piece);
	}
	
	private void removeActivePiece(Piece piece) {
		this.active.remove(piece);
	}
	
	public King getKing() {
		return this.king;
	}
	
	public void setKing(King king) {
		this.king = king;
	}
	
}
