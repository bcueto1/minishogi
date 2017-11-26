

interface GameState {

	public void move(Game game, int x, int y, int newX, int newY, boolean promote) throws IllegalMoveException;
	
	public Player getCurrentPlayer(Game game);
	public Player getOtherPlayer(Game game);
	

}