public class Board {

	private Position[][] positions;
	
	public void resetBoard(Game game) {
		for (int i = 0; i < this.positions.length; i++) {
			for (int j = 0; j < this.positions[0].length; j++) {
				this.positions[i][j].removePiece();
			}
		}
		game.getUpperPlayer().setKing(null);
		game.getLowerPlayer().setKing(null);
		
	}
	
	public Board(Game game) {
		this.positions = new Position[5][5];
		for (int i = 0; i < positions.length; i++) {
			for (int j = 0; j < positions[0].length; j++) {
				Position pos = new Position(null, i,j);
				positions[i][j] = pos;
			}
		}
		
		Rook upperRook = new Rook(0,0,"upper");
		Bishop upperBishop = new Bishop(0,1,"upper");
		SilverGeneral upperSG = new SilverGeneral(0,2,"upper");
		GoldGeneral upperGG = new GoldGeneral(0,3,"upper");
		Pawn upperPawn = new Pawn(1,4, "upper");
		King upperKing = new King(0,4, "upper");
		positions[0][0].setPiece(upperRook);
		positions[0][1].setPiece(upperBishop);
		positions[0][2].setPiece(upperSG);
		positions[0][3].setPiece(upperGG);
		positions[1][4].setPiece(upperPawn);
		positions[0][4].setPiece(upperKing);
		game.getUpperPlayer().setKing(upperKing);

		Rook lowerRook = new Rook(4,4,"lower");
		Bishop lowerBishop = new Bishop(4,3,"lower");
		SilverGeneral lowerSG = new SilverGeneral(4,2,"lower");
		GoldGeneral lowerGG = new GoldGeneral(4,1,"lower");
		Pawn lowerPawn = new Pawn(3,0, "lower");
		King lowerKing = new King(4,0, "lower");
		positions[4][4].setPiece(lowerRook);
		positions[4][3].setPiece(lowerBishop);
		positions[4][2].setPiece(lowerSG);
		positions[4][1].setPiece(lowerGG);
		positions[3][0].setPiece(lowerPawn);
		positions[4][0].setPiece(lowerKing);
		game.getLowerPlayer().setKing(lowerKing);
		
		for (int i = 0; i < positions.length; i++) {
			for (int j = 0; j < positions[0].length; j++) {
				if (positions[i][j].hasPiece())
					positions[i][j].getPiece().updatePossibleMoves(this);
			}
		}
	}
	
	private boolean checkPosition(Piece piece, int newX, int newY) {
		for (Position position: piece.getPossibleMoves()) {
    		int tempX = position.getX();
    		int tempY = position.getY();
    		
    		if (tempX == newX && tempY == newY)
    			return true;
    	}
    	return false;
	}
	
	private void capturePiece(Piece cap, Player curr) {
		if (cap.getTeam().equals("upper"))
			cap.setTeam("lower");
		else if (cap.getTeam().equals("lower"))
			cap.setTeam("upper");
		cap.demote();
		cap.setX(-1);
		cap.setY(-1);
	
		curr.addCapturedPiece(cap);
	}
	
	private boolean isPawnPromoted(Piece piece) {
		if (!piece.getType().equals("pawn"))
			return false;
		if (piece.isPromoted())
			return false;
		if (piece.getTeam().equals("upper") && piece.getX() == 4)
			return true;
		
		if (piece.getTeam().equals("lower") && piece.getX() == 0)
			return true;
			
		return false;
	}
	
	private void promotePiece(Piece piece) throws IllegalMoveException {
		if (piece.getType().equals("king") || piece.isPromoted())
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
	
	public void movePiece(Game game, int x, int y, int newX, int newY, boolean promote) throws IllegalMoveException {
		if (!this.hasPiece(x, y))
			throw new IllegalMoveException();
		
		Player curPlayer = game.getState().getCurrentPlayer(game);
		Piece piece = null;
		try { piece = this.getPiece(x, y).recreate(); } catch (CloneNotSupportedException e) { e.printStackTrace(); }
		if (!piece.getTeam().equals(curPlayer.getTeam()) || !checkPosition(piece, newX, newY))
			throw new IllegalMoveException();
		
		piece.setX(newX);
		piece.setY(newY);
		if (this.hasPiece(newX, newY))
			this.capturePiece(this.getPiece(newX, newY), curPlayer);
		
		positions[newX][newY].setPiece(piece);
		
		if (this.isPawnPromoted(piece))
			piece.promote();
		if (promote)
			this.promotePiece(piece);
		
		
		piece.updatePossibleMoves(this);
		this.positions[x][y].removePiece();
		
		
	}
	
	public boolean isCheck(Game game, Piece piece) {
		
		King otherKing = game.getState().getOtherPlayer(game).getKing();
		if (!piece.getType().equals("king")) {
			for (Position position: piece.getPossibleMoves()) {
				int tempX = position.getX();
				int tempY = position.getY();
				
				if (otherKing.getX() == tempX && otherKing.getY() == tempY) {
					return true;
				}
						
			}
		}
		return false;
	}
	
	public boolean isCheckmate(Game game) {
		King otherKing = game.getState().getOtherPlayer(game).getKing();
		otherKing.updatePossibleMoves(this);
		if (otherKing.getPossibleMoves().isEmpty()) {
			return true;
		}
		return false;
	}
	
	public void dropPiece(Game game, String type, int x, int y) throws IllegalMoveException {
		if (this.hasPiece(x, y))
			throw new IllegalMoveException();
		
		Player player = game.getState().getCurrentPlayer(game);
		if (type.equals("pawn")) {
			if (player.getTeam().equals("upper") && x == 4)
				throw new IllegalMoveException();
			if (player.getTeam().equals("lower") && x == 0)
				throw new IllegalMoveException();
			for (int i = 0; i < 5; i++) {
				if (!this.hasPiece(i, y))
					continue;
				Piece temp = this.getPiece(i, y);
				if(temp.getType().equals("pawn") && temp.getTeam().equals(player.getTeam()))
					throw new IllegalMoveException();
			}
		}
		
		Piece piece = null;
		for (Piece caps: player.getCaptured()) {
			if (caps.getType().equals(type)) {
				piece = caps;
				player.removeCapturedPiece(piece);
				break;
			}
		}
		
		if (piece == null)
			throw new IllegalMoveException();
		
		piece.setX(x);
		piece.setY(y);
		piece.updatePossibleMoves(this);
		this.positions[x][y].setPiece(piece);
	}
	
	
	
	public Position[][] getBoard() {
		return this.positions;
	}
	
	public Position getPosition(int x, int y) {
		return this.positions[x][y];
	}
	
	public Piece getPiece(int x, int y) {
		return this.positions[x][y].getPiece();
	}
	
	public boolean hasPiece(int x, int y) {
		return this.positions[x][y].hasPiece();
	}
	
	public String[][] getBoardString() {
		
		String[][] boardString = new String[5][5];
		
		for (int i = 0; i < this.positions.length; i++) {
			for (int j = 0; j < this.positions[0].length; j++) {
				if (this.hasPiece(i, j)) {
					Piece piece = positions[i][j].getPiece();
					boardString[j][this.positions.length - i - 1] = piece.toString();
				} else {
					boardString[j][this.positions.length - i - 1] = "";
				}
			}
		}
		
		return boardString;
		
	}
	
	

}