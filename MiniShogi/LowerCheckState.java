
public class LowerCheckState implements GameState {

	Game game;
	
	public LowerCheckState(Game game) {
		this.game = game;
	}

	@Override
	public void move(int x, int y, int newX, int newY, boolean promote) throws IllegalMoveException {
		
		Board board = this.game.getBoard();
		
		King lowerKing = this.game.getLowerPlayer().getKing();
		lowerKing.updatePossibleMoves(board);
		if (lowerKing.getPossibleMoves().isEmpty()) {
			this.game.setState(this.game.getUpperWinState());
		}
		
		this.game.getUpperPlayer().movePiece(board, x, y, newX, newY, promote, this.game.getLowerPlayer());
		
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


}
