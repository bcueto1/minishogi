
public class LowerCheckState implements GameState {


	@Override
	public void move(Game game, int x, int y, int newX, int newY, boolean promote) throws IllegalMoveException {
		
		Board board = game.getBoard();
		
		King lowerKing = game.getLowerPlayer().getKing();
		lowerKing.updatePossibleMoves(board);
		if (lowerKing.getPossibleMoves().isEmpty()) {
			game.setState(game.getUpperWinState());
		}
		
		board.movePiece(game, x, y, newX, newY, promote);
		
		Position thisPosition = board.getPosition(lowerKing.getX(), lowerKing.getY());
		for (int i = 0; i < board.getBoard().length; i++) {
			for (int j = 0; j < board.getBoard()[0].length; j++) {
				Piece piece = board.getBoard()[i][j].getPiece();
				if (piece.getTeam().equals("upper")) {
					for (Position danger: piece.getPossibleMoves()) {
						if (thisPosition.equals(danger))
							throw new IllegalMoveException();
					}
				}
				
			}
		}
		
	}

	@Override
	public Player getCurrentPlayer(Game game) {
		return game.getLowerPlayer();
	}

	@Override
	public Player getOtherPlayer(Game game) {
		return game.getUpperPlayer();
	}


}
