public class UpperCheckState implements GameState {
	

	@Override
	public void move(Game game, int x, int y, int newX, int newY, boolean promote) {
		
		Board board = game.getBoard();
		
		King upperKing = game.getUpperPlayer().getKing();
		
		try {
			board.movePiece(game, x, y, newX, newY, promote);
		} catch (IllegalMoveException e) {
			game.getLowerWinState().setType("illegal");
			game.setState(game.getLowerWinState());
			game.setOver();
			return;
		}
		
		Position thisPosition = board.getPosition(upperKing.getX(), upperKing.getY());
		for (int i = 0; i < board.getBoard().length; i++) {
			for (int j = 0; j < board.getBoard()[0].length; j++) {
				Piece piece = board.getBoard()[i][j].getPiece();
				if (piece.getTeam().equals("lower")) {
					piece.updatePossibleMoves(board);
					for (Position danger: piece.getPossibleMoves()) {
						if (thisPosition.equals(danger)) {
							game.getLowerWinState().setType("illegal");
							game.setState(game.getLowerWinState());
							game.setOver();
							return;
						}
					}
				}
				
			}
		}
		
		Piece thisPiece = board.getPosition(newX, newY).getPiece();
		if (board.isCheck(game, thisPiece)) {
			if (board.isCheckmate(game)) {
				game.getUpperWinState().setType("checkmate");
				game.setState(game.getUpperWinState());
				game.setOver();
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
	public void drop(Game game, String type, int x, int y) {
		
		Board board = game.getBoard();
		
		King king = game.getUpperPlayer().getKing();
		
		try {
			board.dropPiece(game, type, x, y);
		} catch (IllegalMoveException e) {
			game.getLowerWinState().setType("illegal");
			game.setState(game.getLowerWinState());
			game.setOver();
			return;
		}
		
		Position thisPosition = board.getPosition(king.getX(), king.getY());
		for (int i = 0; i < board.getBoard().length; i++) {
			for (int j = 0; j < board.getBoard()[0].length; j++) {
				if (!board.hasPiece(i, j))
					continue;
				Piece temp = board.getPiece(i, j);
				if (temp.getTeam().equals("lower")) {
					temp.updatePossibleMoves(board);
					for (Position danger: temp.getPossibleMoves()) {
						if (thisPosition.equals(danger)) {
							game.getLowerWinState().setType("illegal");
							game.setState(game.getLowerWinState());
							game.setOver();
							return;
						}
					}
				}
				
			}
		}
		
		Piece piece = board.getPiece(x, y);
		if (board.isCheck(game, piece)) {
			if (board.isCheckmate(game) && piece.getType().equals("pawn")) {
				game.getLowerWinState().setType("illegal");
				game.setState(game.getLowerWinState());
				game.setOver();
				return;
			}
			if (board.isCheckmate(game)) {
				game.getUpperWinState().setType("checkmate");
				game.setState(game.getUpperWinState());
				game.setOver();
				return;
			}
			
			game.setState(game.getLowerCheckState());
			return;
		}
		
		game.setState(game.getLowerMoveState());
		
	}
	
	@Override
	public String getEndMessage() {
		return "";
	}

	

}
