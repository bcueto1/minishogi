

public class LowerMoveState implements GameState {
	
	public LowerMoveState() {}

	@Override
	public void move(Game game, int x, int y, int newX, int newY, boolean promote) throws IllegalMoveException {
		
		Board board = game.getBoard();
		
		board.movePiece(game, x, y, newX, newY, promote);
		Piece thisPiece = board.getPosition(newX, newY).getPiece();
		King upperKing = game.getUpperPlayer().getKing();
		if (!thisPiece.getType().equals("king")) {
			for (Position position: thisPiece.getPossibleMoves()) {
				int tempX = position.getX();
				int tempY = position.getY();
				
				
				if (upperKing.getX() == tempX && upperKing.getY() == tempY) {
					game.setState(game.getUpperCheckState());
					return;
				}
			}
		}
		
		game.setState(game.getUpperMoveState());
		
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