public class UpperCheckState implements GameState {
	

	@Override
	public void move(Game game, int x, int y, int newX, int newY, boolean promote) throws IllegalMoveException {
		
		Board board = game.getBoard();
		
		King upperKing = game.getUpperPlayer().getKing();
		
		board.movePiece(game, x, y, newX, newY, promote);
		
		Position thisPosition = board.getPosition(upperKing.getX(), upperKing.getY());
		for (int i = 0; i < board.getBoard().length; i++) {
			for (int j = 0; j < board.getBoard()[0].length; j++) {
				Piece piece = board.getBoard()[i][j].getPiece();
				if (piece.getTeam().equals("lower")) {
					piece.updatePossibleMoves(board);
					for (Position danger: piece.getPossibleMoves()) {
						if (thisPosition.equals(danger))
							throw new IllegalMoveException();
					}
				}
				
			}
		}
		
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

	@Override
	public void drop(Game game, String type, int x, int y) throws IllegalMoveException {
		
		Board board = game.getBoard();
		
		King king = game.getUpperPlayer().getKing();
		
		board.dropPiece(game, type, x, y);
		
		Position thisPosition = board.getPosition(king.getX(), king.getY());
		for (int i = 0; i < board.getBoard().length; i++) {
			for (int j = 0; j < board.getBoard()[0].length; j++) {
				if (!board.hasPiece(i, j))
					continue;
				Piece temp = board.getPiece(i, j);
				if (temp.getTeam().equals("lower")) {
					temp.updatePossibleMoves(board);
					for (Position danger: temp.getPossibleMoves()) {
						if (thisPosition.equals(danger))
							throw new IllegalMoveException();
					}
				}
				
			}
		}
		
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
