

public class Game {

    private int moves;
    private Board board;
    private Player upperPlayer;
	private Player lowerPlayer;
	private boolean inCheck;
	private boolean isOver;
	
	private LowerMoveState lowerMoveState;
	private UpperMoveState upperMoveState;
	private LowerCheckState lowerCheckState;
	private UpperCheckState upperCheckState;
	private LowerWinState lowerWinState;
	private UpperWinState upperWinState;
	private TieGameState tieGameState;
	private GameState state;
	
	public Game(boolean interactive) {
		this.moves = 0;
		this.upperPlayer = new Player("upper");
		this.lowerPlayer = new Player("lower");
		if (interactive)
			this.board = new Board(this);
		else
			this.board = new Board();
		this.inCheck = false;
		this.isOver = false;
		
		this.lowerMoveState = new LowerMoveState();
		this.upperMoveState = new UpperMoveState();
		this.lowerCheckState = new LowerCheckState();
		this.upperCheckState = new UpperCheckState();
		this.lowerWinState = new LowerWinState();
		this.upperWinState = new UpperWinState();
		this.tieGameState = new TieGameState();
		this.state = this.lowerMoveState;
	}
	
	public void setState(GameState state) {
		this.state = state;
	}
	
	public void move(int x, int y, int newX, int newY, boolean promote) {
		this.state.move(this, x, y, newX, newY, promote);
			
	}
	
	public void drop(String type, int x, int y) {
		this.state.drop(this, type, x, y);
			
	}
	
	public String endMessage() {
		return this.state.getEndMessage();
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
	
	public boolean isOver() {
		return this.isOver;
	}
	
	public void setOver() {
		this.inCheck = false;
		this.isOver = true;
	}
	
	public boolean inCheck() {
		return this.inCheck;
	}
	
	public void setCheck(boolean is) {
		this.inCheck = is;
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