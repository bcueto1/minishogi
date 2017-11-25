

public class UpperMoveState implements GameState {
	
	Game game;
	
	public UpperMoveState(Game game) {
		this.game = game;
	}

	@Override
	public void move(int x, int y, int newX, int newY, boolean promote) throws IllegalMoveException {
		
		Board board = this.game.getBoard();
		
		this.game.getUpperPlayer().movePiece(board, x, y, newX, newY, promote, this.game.getLowerPlayer());
		
		Piece thisPiece = board.getPosition(newX, newY).getPiece();
		King lowerKing = this.game.getLowerPlayer().getKing();
		if (!thisPiece.getType().equals("king")) {
			for (Position position: thisPiece.getPossibleMoves()) {
				int tempX = position.getX();
				int tempY = position.getY();
				
				
				if (lowerKing.getX() == tempX && lowerKing.getY() == tempY) {
					this.game.setState(this.game.getLowerCheckState());
					return;
				}
			}
		}
		
		this.game.setState(this.game.getLowerMoveState());
		
	}



}