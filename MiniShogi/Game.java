

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
		this.board = new Board(this);
		
		this.lowerMoveState = new LowerMoveState();
		this.upperMoveState = new UpperMoveState();
		this.lowerCheckState = new LowerCheckState();
		this.upperCheckState = new UpperCheckState();
		this.lowerWinState = new LowerWinState();
		this.upperWinState = new UpperWinState();
		this.tieGameState = new TieGameState();
	}
	
	public void setState(GameState state) {
		this.state = state;
	}
	
	public void move(int x, int y, int newX, int newY, boolean promote) {
		try {
			this.state.move(this, x, y, newX, newY, promote);
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