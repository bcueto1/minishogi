
public class LowerCheckState implements GameState {


	@Override
	public void move(Game game, int x, int y, int newX, int newY, boolean promote) {
		
		Board board = game.getBoard();
		
		King lowerKing = game.getLowerPlayer().getKing();
		
		try {
			board.movePiece(game, x, y, newX, newY, promote);
		} catch (IllegalMoveException e) {
			game.getUpperWinState().setType("illegal");
			game.setState(game.getUpperWinState());
			game.setOver();
			return;
		}
		
		Position thisPosition = board.getPosition(lowerKing.getX(), lowerKing.getY());
		for (int i = 0; i < board.getBoard().length; i++) {
			for (int j = 0; j < board.getBoard()[0].length; j++) {
				Piece piece = board.getBoard()[i][j].getPiece();
				if (piece.getTeam().equals("upper")) {
					piece.updatePossibleMoves(board);
					for (Position danger: piece.getPossibleMoves()) {
						if (thisPosition.equals(danger)) {
							game.getUpperWinState().setType("illegal");
							game.setState(game.getUpperWinState());
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
				game.getLowerWinState().setType("checkmate");
				game.setState(game.getLowerWinState());
				game.setOver();
				return;
			}
				
			game.setState(game.getUpperCheckState());
			return;
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

	@Override
	public void drop(Game game, String type, int x, int y) {
		
		
		Board board = game.getBoard();
		
		King king = game.getLowerPlayer().getKing();
		
		try {
			board.dropPiece(game, type, x, y);
		} catch (IllegalMoveException e) {
			game.getUpperWinState().setType("illegal");
			game.setState(game.getUpperWinState());
			game.setOver();
			return;
		}
		
		Position thisPosition = board.getPosition(king.getX(), king.getY());
		for (int i = 0; i < board.getBoard().length; i++) {
			for (int j = 0; j < board.getBoard()[0].length; j++) {
				if (!board.hasPiece(i, j))
					continue;
				Piece temp = board.getPiece(i, j);
				if (temp.getTeam().equals("upper")) {
					temp.updatePossibleMoves(board);
					for (Position danger: temp.getPossibleMoves()) {
						if (thisPosition.equals(danger)) {
							game.getUpperWinState().setType("illegal");
							game.setState(game.getUpperWinState());
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
				game.getUpperWinState().setType("illegal");
				game.setState(game.getUpperWinState());
				game.setOver();
				return;
			}
			if (board.isCheckmate(game)) {
				game.getLowerWinState().setType("checkmate");
				game.setState(game.getLowerWinState());
				game.setOver();
				return;
			}
			
			game.setState(game.getUpperCheckState());
			return;
		}
		
		game.setState(game.getUpperMoveState());
		
	}
	
	@Override
	public String getEndMessage() {
		return "";
	}


}
