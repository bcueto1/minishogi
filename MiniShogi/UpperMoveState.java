

public class UpperMoveState implements GameState {
	
	Game game;
	
	public UpperMoveState(Game game) {
		this.game = game;
	}

	@Override
	public void move(int x, int y) throws IllegalMoveException {
		
		Board board = this.game.getBoard();
		
		if (board.getBoard()[x][y].hasPiece()) {
			if (!board.getBoard()[x][y].getPiece().getTeam().equals("lower")) {
				throw new IllegalMoveException();
			}
		}
		
		this.game.setState(this.game.getLowerMoveState());
		
	}



}