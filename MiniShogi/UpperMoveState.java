

public class UpperMoveState implements GameState {
	
	public UpperMoveState() { }

	@Override
	public void move(Game game, int x, int y, int newX, int newY, boolean promote) throws IllegalMoveException {
		
		Board board = game.getBoard();
		
		board.movePiece(game, x, y, newX, newY, promote);
		
		Piece thisPiece = board.getPosition(newX, newY).getPiece();
		King lowerKing = game.getLowerPlayer().getKing();
		if (!thisPiece.getType().equals("king")) {
			for (Position position: thisPiece.getPossibleMoves()) {
				int tempX = position.getX();
				int tempY = position.getY();
				
				
				if (lowerKing.getX() == tempX && lowerKing.getY() == tempY) {
					game.setState(game.getLowerCheckState());
					return;
				}
			}
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