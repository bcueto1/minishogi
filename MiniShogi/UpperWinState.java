
public class UpperWinState implements GameState {
	
	public UpperWinState() { }

	@Override
	public void move(Game game, int x, int y, int newX, int newY, boolean promote) throws IllegalMoveException {
		return;
		
	}

	@Override
	public Player getCurrentPlayer(Game game) {
		return null;
	}

	@Override
	public Player getOtherPlayer(Game game) {
		return null;
	}

}
