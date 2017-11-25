

public class Game {

    private int moves;
    private Board board;
    private Player upperPlayer;
	private Player lowerPlayer;
	
	private LowerMoveState lowerMoveState;
	private UpperMoveState upperMoveState;
	private LowerCheckState lowerCheckState;
	private UpperCheckState upperCheckState;
	private GameState state = lowerMoveState;
	
	public Game() {
		this.moves = 0;
		this.board = new Board();
		this.upperPlayer = new Player("upper");
		this.lowerPlayer = new Player("lower");
		
		this.lowerMoveState = new LowerMoveState(this);
		this.upperMoveState = new UpperMoveState(this);
		this.lowerCheckState = new LowerCheckState(this);
		this.upperCheckState = new UpperCheckState(this);
	}
	
	public Board getBoard() {
		return this.board;
	}
	
	public GameState getState() {
		return this.state;
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
	
	public void setState(GameState state) {
		this.state = state;
	}
	
	public void move(int x, int y) {
		try {
			this.state.move(x, y);
		} catch (IllegalMoveException e) {
			e.printStackTrace();
		}
	}
	
	

    



}