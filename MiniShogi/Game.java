

public class Game {

    private int moves;
    private GameState state;
    private Board board;
    private Player upperPlayer;
	private Player lowerPlayer;
	
	public Game() {
		this.moves = 0;
		this.board = new Board();
		this.upperPlayer = new Player("upper");
		this.lowerPlayer = new Player("lower");
	}

    



}