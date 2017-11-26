public class TieGameState implements GameState {
	
	public TieGameState() { }

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

	@Override
	public void drop(Game game, String type, int x, int y) throws IllegalMoveException {
		
		return;
		
	}

}
