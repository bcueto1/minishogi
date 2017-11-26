

public class UpperMoveState implements GameState {
	
	public UpperMoveState() { }

	@Override
	public void move(Game game, int x, int y, int newX, int newY, boolean promote) throws IllegalMoveException {
		
		Board board = game.getBoard();
		
		board.movePiece(game, x, y, newX, newY, promote);
		
		Piece thisPiece = board.getPosition(newX, newY).getPiece();
		if (board.isCheck(game, thisPiece)) {
			if (board.isCheckmate(game)) {
				game.setState(game.getUpperWinState());
				return;
			}
			game.setState(game.getLowerCheckState());
			return;
		}
		
		game.setState(game.getLowerMoveState());
		
	}

	@Override
	public Player getCurrentPlayer(Game game) {
		return game.getUpperPlayer();
	}

	@Override
	public Player getOtherPlayer(Game game) {
		return game.getLowerPlayer();
	}



}