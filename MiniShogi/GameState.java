

interface GameState {

	public void move(Game game, int x, int y, int newX, int newY, boolean promote);
	public void drop(Game game, String type, int x, int y);
	
	public Player getCurrentPlayer(Game game);
	public Player getOtherPlayer(Game game);
	
	public String getEndMessage();
	

}