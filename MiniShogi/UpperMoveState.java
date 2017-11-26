

public class UpperMoveState implements GameState {
	
	public UpperMoveState() { }

	@Override
	public void move(Game game, int x, int y, int newX, int newY, boolean promote) throws IllegalMoveException {
		
		Board board = game.getBoard();
		
		board.movePiece(game, x, y, newX, newY, promote);
		
		Piece piece = board.getPiece(newX, newY);
		if (board.isCheck(game, piece)) {
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

	@Override
	public void drop(Game game, String type, int x, int y) throws IllegalMoveException {
		
		Board board = game.getBoard();
		board.dropPiece(game, type, x, y);
		
		Piece piece = board.getPiece(x, y);
		if (board.isCheck(game, piece)) {
			if (board.isCheckmate(game) && piece.getType().equals("pawn"))
				throw new IllegalMoveException();
			if (board.isCheckmate(game)) {
				game.setState(game.getUpperWinState());
				return;
			}
			
			game.setState(game.getLowerCheckState());
			return;
		}
		
		game.setState(game.getLowerMoveState());
		
	}



}