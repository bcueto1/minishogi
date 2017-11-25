public class UpperCheckState implements GameState {
	
	Game game;
	
	public UpperCheckState(Game game) {
		this.game = game;
	}

	@Override
	public void move(int x, int y, int newX, int newY, boolean promote) throws IllegalMoveException {
		
		Board board = this.game.getBoard();
		
		King upperKing = this.game.getUpperPlayer().getKing();
		upperKing.updatePossibleMoves(board);
		if (upperKing.getPossibleMoves().isEmpty()) {
			this.game.setState(this.game.getLowerWinState());
		}
		
		this.game.getUpperPlayer().movePiece(board, x, y, newX, newY, promote, this.game.getLowerPlayer());
		
		Position thisPosition = board.getPosition(upperKing.getX(), upperKing.getY());
		for (int i = 0; i < board.getBoard().length; i++) {
			for (int j = 0; j < board.getBoard()[0].length; j++) {
				Piece piece = board.getBoard()[i][j].getPiece();
				if (!piece.getTeam().equals("upper")) {
					for (Position danger: piece.getPossibleMoves()) {
						if (thisPosition.equals(danger))
							throw new IllegalMoveException();
					}
				}
				
			}
		}
		
		
	}

	

}
