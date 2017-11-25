

public class LowerMoveState implements GameState {
	
	Game game;
	
	public LowerMoveState(Game game) {
		this.game = game;
	}

	@Override
	public void move(int x, int y, int newX, int newY, boolean promote) throws IllegalMoveException {
		
		Board board = this.game.getBoard();
		
		this.game.getLowerPlayer().movePiece(board, x, y, newX, newY, promote, this.game.getUpperPlayer());
		Piece thisPiece = board.getPosition(newX, newY).getPiece();
		King upperKing = this.game.getUpperPlayer().getKing();
		if (!thisPiece.getType().equals("king")) {
			for (Position position: thisPiece.getPossibleMoves()) {
				int tempX = position.getX();
				int tempY = position.getY();
				
				
				if (upperKing.getX() == tempX && upperKing.getY() == tempY) {
					this.game.setState(this.game.getUpperCheckState());
					return;
				}
			}
		}
		
		this.game.setState(this.game.getUpperMoveState());
		
	}



}