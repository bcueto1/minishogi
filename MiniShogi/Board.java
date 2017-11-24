public class Board {

	private Position[][] board;
	
	public Board() {
		this.setUpBoard();
		
	}
	
	private void setUpBoard() {
		this.board = new Position[5][5];
		for (int i = 0; i < this.board.length; i++) {
			for (int j = 0; j < this.board[0].length; j++) {
				Position pos = new Position(i,j);
				this.board[i][j] = pos;
			}
		}
		this.board[0][0].setPiece(new Rook(0,0,"upper"));
		this.board[0][1].setPiece(new Bishop(0,1,"upper"));
		this.board[0][2].setPiece(new SilverGeneral(0,2,"upper"));
		this.board[0][3].setPiece(new GoldGeneral(0,3,"upper"));
		this.board[0][4].setPiece(new King(0,4,"upper"));
		this.board[1][4].setPiece(new Pawn(1,4,"upper"));
		this.board[4][4].setPiece(new Rook(4,4,"lower"));
		this.board[4][3].setPiece(new Bishop(4,3,"lower"));
		this.board[4][2].setPiece(new SilverGeneral(4,2,"lower"));
		this.board[4][1].setPiece(new GoldGeneral(4,1,"lower"));
		this.board[4][0].setPiece(new King(4,0,"lower"));
		this.board[1][4].setPiece(new Pawn(3,0,"lower"));
	}
	
	public Board(Position[][] board) {
		this.board = board;
	}
	
	public Position[][] getBoard() {
		return this.board;
	}
	
	public void movePiece(int x, int y, int newX, int newY) throws IllegalMoveException {
		Piece piece = null;
		if (this.board[x][y].hasPiece()) {
			try {
				piece = this.board[x][y].getPiece().recreate();
				piece.move(this.board, newX, newY);
				this.board[newX][newY].setPiece(piece);
				this.checkPiecePromoted(piece);
				this.board[x][y].removePiece();
			} catch (CloneNotSupportedException e) {
				
			}
		} else {
			throw new IllegalMoveException();
		}	
	}
	
	public void checkPiecePromoted(Piece piece) throws IllegalMoveException {
		if (piece.getType().equals("king"))
			throw new IllegalMoveException();
		if (piece.getTeam().equals("upper")) {
			if (piece.getX() == 4) {
				piece.promote();
			}
		} else if (piece.getTeam().equals("lower")) {
			if (piece.getX() == 0) {
				piece.promote();
			}
		}
	}
	
	public void capturePiece(Piece piece, Player player, int x, int y) {
		try {
			Piece capturedPiece = piece.recreate();
			if (capturedPiece.getTeam().equals("upper"))
				capturedPiece.setTeam("lower");
			else if (capturedPiece.getTeam().equals("lower"))
				capturedPiece.setTeam("upper");
			capturedPiece.demote();
			
			
			capturedPiece.setX(-1);
			capturedPiece.setY(-1);
			player.capturePiece(capturedPiece);
			this.board[x][y].removePiece();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		
	}
	
	public void dropPieceToBoard(String type, Player player, int x, int y) throws IllegalMoveException {
		if (this.board[x][y].hasPiece()) {
			throw new IllegalMoveException();
		}
		// Add support for dropping a piece in promotion zone and not being allowed to promote it
		if (type.equals("pawn")) {
			if (player.getTeam().equals("upper")) {
				if (x == 4)
					throw new IllegalMoveException();
				for (int i = 0; i < 5; i++) {
					if (this.board[i][y].getPiece().getType().equals("pawn") && this.board[i][y].getPiece().getTeam().equals("upper")) {
						throw new IllegalMoveException();
					}
				}
			}
			if (player.getTeam().equals("lower")) {
				if (x == 0)
					throw new IllegalMoveException();
				for (int i = 0; i < 5; i++) {
					if (this.board[i][y].getPiece().getType().equals("pawn") && this.board[i][y].getPiece().getTeam().equals("lower")) {
						throw new IllegalMoveException();
					}
				}
			}
		}
		Piece piece = player.dropPiece(type);
		if (piece == null)
			throw new IllegalMoveException();
		if (type.equals("pawn"))
		piece.setX(x);
		piece.setY(y);
		this.board[x][y].setPiece(piece);
	}

	public int convertXPosition(String position) {
	    int xPosition = 0;
	    String xString = position.substring(0,1);
	    
	    switch (xString) {
		    case "a": xPosition = 0;
		    		  break;
		    case "b": xPosition = 1;
		     		  break;
		    case "c": xPosition = 2;
		    		  break;
		    case "d": xPosition = 3;
		    		  break;
		    case "e": xPosition = 4;
		    		  break;
		    default:  
		    	break;
	    }
	    
	    return xPosition;
    }

    private int convertYPosition(String position) {
    	int yPosition = 0;
    	int yInput = Integer.parseInt(position.substring(1,2));
		
    	switch (yInput) {
	    	case 1: yPosition = 0;
	    			break;
	    	case 2: yPosition = 1;
	    			break;
	    	case 3: yPosition = 2;
	    			break;
	    	case 4: yPosition = 3;
	    			break;
	    	case 5: yPosition = 4;
	    			break;
	    	default:
	    		break;
    	}
    	
    	return yPosition;

    }

}