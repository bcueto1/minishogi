public class Board {

	private Position[][] positions;
	
	public Board(Position[][] board) {
		this.positions = board;
	}
	
	public Board(Game game) {
		this.setUpBoard(game);
	}
	
	public void setUpBoard(Game game) {
		this.positions = new Position[5][5];
		for (int i = 0; i < positions.length; i++) {
			for (int j = 0; j < positions[0].length; j++) {
				Position pos = new Position(i,j);
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
		game.getUpperPlayer().addActivePiece(upperRook);
		game.getUpperPlayer().addActivePiece(upperBishop);
		game.getUpperPlayer().addActivePiece(upperSG);
		game.getUpperPlayer().addActivePiece(upperGG);
		game.getUpperPlayer().addActivePiece(upperPawn);
		game.getUpperPlayer().addActivePiece(upperKing);
		game.getUpperPlayer().setKing(upperKing);

		Rook lowerRook = new Rook(4,4,"lower");
		Bishop lowerBishop = new Bishop(4,3,"lower");
		SilverGeneral lowerSG = new SilverGeneral(4,2,"lower");
		GoldGeneral lowerGG = new GoldGeneral(4,1,"lower");
		Pawn lowerPawn = new Pawn(3,0, "lower");
		King lowerKing = new King(4,0, "lower");
		positions[4][4].setPiece(new Rook(4,4,"lower"));
		positions[4][3].setPiece(new Bishop(4,3,"lower"));
		positions[4][2].setPiece(new SilverGeneral(4,2,"lower"));
		positions[4][1].setPiece(new GoldGeneral(4,1,"lower"));
		positions[1][4].setPiece(new Pawn(3,0,"lower"));
		positions[4][0].setPiece(new King(4,0,"lower"));
		game.getLowerPlayer().addActivePiece(lowerRook);
		game.getLowerPlayer().addActivePiece(lowerBishop);
		game.getLowerPlayer().addActivePiece(lowerSG);
		game.getLowerPlayer().addActivePiece(lowerGG);
		game.getLowerPlayer().addActivePiece(lowerPawn);
		game.getLowerPlayer().addActivePiece(lowerKing);
		game.getLowerPlayer().setKing(lowerKing);

	}
	
	public Position[][] getBoard() {
		return this.positions;
	}
	
	public Position getPosition(int x, int y) {
		return this.positions[x][y];
	}
	
	public boolean hasPiece(int x, int y) {
		return this.positions[x][y].hasPiece();
	}

}