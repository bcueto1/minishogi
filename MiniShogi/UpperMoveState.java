

public class UpperMoveState implements GameState {
	
	public UpperMoveState() { }

	@Override
	public void move(Game game, int x, int y, int newX, int newY, boolean promote) {
		
		Board board = game.getBoard();
		
		
		try {
			board.movePiece(game, x, y, newX, newY, promote);
		} catch (IllegalMoveException e) {
			game.getLowerWinState().setType("illegal");
			game.setState(game.getLowerWinState());
			game.setOver();
			return;
		}

		King otherKing = game.getLowerPlayer().getKing();		
		if (board.isCheck(game, otherKing)) {
			game.setCheck(true);
			if (board.isCheckmate(game)) {
				game.getUpperWinState().setType("checkmate");
				game.setState(game.getUpperWinState());
				game.setOver();
				return;
			}
			game.setState(game.getLowerCheckState());
			return;
		}
		
		game.setCheck(false);
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
		
		
		try {
			board.dropPiece(game, type, x, y);
		} catch (IllegalMoveException e) {
			game.getLowerWinState().setType("illegal");
			game.setState(game.getLowerWinState());
			game.setOver();
			return;
		}
		King otherKing = game.getLowerPlayer().getKing();
		Piece piece = board.getPiece(x, y);
		if (board.isCheck(game, otherKing)) {
			game.setCheck(true);
			if (board.isCheckmate(game)) {
				if (piece.getType().equals("pawn")) {
					game.getLowerWinState().setType("illegal");
					game.setState(game.getLowerWinState());
					game.setOver();
					return;
				}
				game.getUpperWinState().setType("checkmate");
				game.setState(game.getUpperWinState());
				game.setOver();
				return;
			}
			
			game.setState(game.getLowerCheckState());
			return;
		}
		
		game.setCheck(false);
		game.setState(game.getLowerMoveState());
		
	}

	@Override
	public String getEndMessage() {
		return "";
	}



}