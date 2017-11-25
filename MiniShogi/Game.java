

public class Game {

    private int moves;
    private Board board;
    private Player upperPlayer;
	private Player lowerPlayer;
	
	private LowerMoveState lowerMoveState;
	private UpperMoveState upperMoveState;
	private LowerCheckState lowerCheckState;
	private UpperCheckState upperCheckState;
	private LowerWinState lowerWinState;
	private UpperWinState upperWinState;
	private TieGameState tieGameState;
	private GameState state = lowerMoveState;
	
	public Game() {
		this.moves = 0;
		this.upperPlayer = new Player("upper");
		this.lowerPlayer = new Player("lower");
		this.setUpBoard();
		
		this.lowerMoveState = new LowerMoveState(this);
		this.upperMoveState = new UpperMoveState(this);
		this.lowerCheckState = new LowerCheckState(this);
		this.upperCheckState = new UpperCheckState(this);
		this.lowerWinState = new LowerWinState(this);
		this.upperWinState = new UpperWinState(this);
		this.tieGameState = new TieGameState(this);
	}
	
	private void setUpBoard() {
		Position[][] setup = new Position[5][5];
		for (int i = 0; i < setup.length; i++) {
			for (int j = 0; j < setup[0].length; j++) {
				Position pos = new Position(i,j);
				setup[i][j] = pos;
			}
		}
		
		Rook upperRook = new Rook(0,0,"upper");
		Bishop upperBishop = new Bishop(0,1,"upper");
		SilverGeneral upperSG = new SilverGeneral(0,2,"upper");
		GoldGeneral upperGG = new GoldGeneral(0,3,"upper");
		Pawn upperPawn = new Pawn(1,4, "upper");
		King upperKing = new King(0,4, "upper");
		setup[0][0].setPiece(upperRook);
		setup[0][1].setPiece(upperBishop);
		setup[0][2].setPiece(upperSG);
		setup[0][3].setPiece(upperGG);
		setup[1][4].setPiece(upperPawn);
		setup[0][4].setPiece(upperKing);
		this.upperPlayer.addActivePiece(upperRook);
		this.upperPlayer.addActivePiece(upperBishop);
		this.upperPlayer.addActivePiece(upperSG);
		this.upperPlayer.addActivePiece(upperGG);
		this.upperPlayer.addActivePiece(upperPawn);
		this.upperPlayer.addActivePiece(upperKing);
		this.upperPlayer.setKing(upperKing);

		Rook lowerRook = new Rook(4,4,"lower");
		Bishop lowerBishop = new Bishop(4,3,"lower");
		SilverGeneral lowerSG = new SilverGeneral(4,2,"lower");
		GoldGeneral lowerGG = new GoldGeneral(4,1,"lower");
		Pawn lowerPawn = new Pawn(3,0, "lower");
		King lowerKing = new King(4,0, "lower");
		setup[4][4].setPiece(new Rook(4,4,"lower"));
		setup[4][3].setPiece(new Bishop(4,3,"lower"));
		setup[4][2].setPiece(new SilverGeneral(4,2,"lower"));
		setup[4][1].setPiece(new GoldGeneral(4,1,"lower"));
		setup[1][4].setPiece(new Pawn(3,0,"lower"));
		setup[4][0].setPiece(new King(4,0,"lower"));
		this.lowerPlayer.addActivePiece(lowerRook);
		this.lowerPlayer.addActivePiece(lowerBishop);
		this.lowerPlayer.addActivePiece(lowerSG);
		this.lowerPlayer.addActivePiece(lowerGG);
		this.lowerPlayer.addActivePiece(lowerPawn);
		this.lowerPlayer.addActivePiece(lowerKing);
		this.lowerPlayer.setKing(lowerKing);
		
		this.board = new Board(setup);
	}
	
	public void setState(GameState state) {
		this.state = state;
	}
	
	public void move(int x, int y, int newX, int newY, boolean promote) {
		try {
			this.state.move(x, y, newX, newY, promote);
		} catch (IllegalMoveException e) {
			e.printStackTrace();
		}
		this.moves++;
	}
	
	public Board getBoard() {
		return this.board;
	}
	
	public GameState getState() {
		return this.state;
	}
	
	public Player getLowerPlayer() {
		return this.lowerPlayer;
	}
	
	public Player getUpperPlayer() {
		return this.upperPlayer;
	}
	
	public LowerMoveState getLowerMoveState() {
		return this.lowerMoveState;
	}
	
	public UpperMoveState getUpperMoveState() {
		return this.upperMoveState;
	}
	
	public LowerCheckState getLowerCheckState() {
		return this.lowerCheckState;
	}
	
	public UpperCheckState getUpperCheckState() {
		return this.upperCheckState;
	}
	
	public LowerWinState getLowerWinState() {
		return this.lowerWinState;
	}
	
	public UpperWinState getUpperWinState() {
		return this.upperWinState;
	}
	
	public TieGameState getTieGameState() {
		return this.tieGameState;
	}
	
	

    



}