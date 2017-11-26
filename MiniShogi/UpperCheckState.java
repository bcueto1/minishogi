public class UpperCheckState implements GameState {
	

	@Override
	public void move(Game game, int x, int y, int newX, int newY, boolean promote) throws IllegalMoveException {
		
		Board board = game.getBoard();
		
		King upperKing = game.getUpperPlayer().getKing();
		upperKing.updatePossibleMoves(board);
		if (upperKing.getPossibleMoves().isEmpty()) {
			game.setState(game.getLowerWinState());
		}
		
		board.movePiece(game, x, y, newX, newY, promote);
		
		Position thisPosition = board.getPosition(upperKing.getX(), upperKing.getY());
		for (int i = 0; i < board.getBoard().length; i++) {
			for (int j = 0; j < board.getBoard()[0].length; j++) {
				Piece piece = board.getBoard()[i][j].getPiece();
				if (piece.getTeam().equals("lower")) {
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
		return game.getUpperPlayer();
	}

	@Override
	public Player getOtherPlayer(Game game) {
		return game.getLowerPlayer();
	}

	

}
