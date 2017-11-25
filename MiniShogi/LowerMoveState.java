

public class LowerMoveState implements GameState {
	
	Game game;
	
	public LowerMoveState(Game game) {
		this.game = game;
	}

	@Override
	public void move(int x, int y, int newX, int newY, boolean promote) throws IllegalMoveException {
		
		Board board = this.game.getBoard();
		
		if (board.getBoard()[x][y].hasPiece()) {
			if (!board.getBoard()[x][y].getPiece().getTeam().equals("upper")) {
				throw new IllegalMoveException();
			}
		}
		
		this.game.getLowerPlayer().movePiece(board, x, y, newX, newY, promote, this.game.getUpperPlayer());
		this.game.setState(this.game.getUpperMoveState());
		
	}
	
	public boolean isCheck() {
		
		
		
		
		return false;
	}



}