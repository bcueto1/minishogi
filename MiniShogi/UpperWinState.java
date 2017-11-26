
public class UpperWinState implements GameState {
	
	private String type;
	
	public UpperWinState() { 
		type = "";
	}

	@Override
	public void move(Game game, int x, int y, int newX, int newY, boolean promote) {
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
	public void drop(Game game, String type, int x, int y) {
		return;
		
	}
	
	public void setType(String type) {
		if (type.equals("checkmate"))
			this.type = "Checkmate.";
		else if (type.equals("illegal"))
			this.type = "Illegal move.";
	}
	
	public String getType() {
		return this.type;
	}

	@Override
	public String getEndMessage() {
		return "UPPER player wins. " + this.type;
	}

}
