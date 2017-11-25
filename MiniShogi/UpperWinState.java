
public class UpperWinState implements GameState {
	
	Game game;
	
	public UpperWinState(Game game) {
		this.game = game;
	}

	@Override
	public void move(int x, int y, int newX, int newY, boolean promote) throws IllegalMoveException {
		return;
		
	}

}
