
public class LowerCheckState implements GameState {


	@Override
	public void move(Game game, int x, int y, int newX, int newY, boolean promote) {
		
		Board board = game.getBoard();
		
		try {
			board.movePiece(game, x, y, newX, newY, promote);
		} catch (IllegalMoveException e) {
			game.getUpperWinState().setType("illegal");
			game.setState(game.getUpperWinState());
			game.setOver();
			return;
		}
		
		this.getCurrentPlayer(game).increaseMoves();
		King king = game.getLowerPlayer().getKing();
		King otherKing = game.getUpperPlayer().getKing();
		game.setCheck(false);
		if (board.isCheck(game, king)) {
			
			game.getUpperWinState().setType("illegal");
			game.setState(game.getUpperWinState());
			game.setOver();
			return;
		}
		
		if (board.isCheck(game, otherKing)) {
			game.setCheck(true);
			game.getBoard().getPossibleMovesInCheck(game, this.getOtherPlayer(game));
			if (board.isCheckmate(game)) {
				game.getLowerWinState().setType("checkmate");
				game.setState(game.getLowerWinState());
				game.setOver();
				return;
			}
			
			if (this.getCurrentPlayer(game).getMoves() == 200 && this.getOtherPlayer(game).getMoves() == 200) {
				game.setState(game.getTieGameState());
				game.setOver();
				return;
			}
			game.setState(game.getUpperCheckState());
			return;
		}
		
		if (this.getCurrentPlayer(game).getMoves() == 200 && this.getOtherPlayer(game).getMoves() == 200) {
			game.setState(game.getTieGameState());
			game.setOver();
			return;
		}
		game.setCheck(false);
		game.setState(game.getUpperMoveState());
		
	}

	@Override
	public void drop(Game game, String type, int x, int y) {
		
		
		Board board = game.getBoard();
		
		try {
			board.dropPiece(game, type, x, y);
		} catch (IllegalMoveException e) {
			game.getUpperWinState().setType("illegal");
			game.setState(game.getUpperWinState());
			game.setOver();
			return;
		}
		
		this.getCurrentPlayer(game).increaseMoves();
		
		King king = game.getLowerPlayer().getKing();
		King otherKing = game.getUpperPlayer().getKing();
		if (board.isCheck(game, king)) {
			game.getUpperWinState().setType("illegal");
			game.setState(game.getUpperWinState());
			game.setOver();
			return;
		}
		
		Piece piece = board.getPiece(x, y);
		if (board.isCheck(game, otherKing)) {
			game.setCheck(true);
			game.getBoard().getPossibleMovesInCheck(game, this.getOtherPlayer(game));
			if (board.isCheckmate(game)) {
				if (piece.getType().equals("pawn")) {
					game.getUpperWinState().setType("illegal");
					game.setState(game.getUpperWinState());
					game.setOver();
					return;
				}
				game.getLowerWinState().setType("checkmate");
				game.setState(game.getLowerWinState());
				game.setOver();
				return;
			}
			
			if (this.getCurrentPlayer(game).getMoves() == 200 && this.getOtherPlayer(game).getMoves() == 200) {
				game.setState(game.getTieGameState());
				game.setOver();
				return;
			}
			game.setState(game.getUpperCheckState());
			return;
		}
		
		if (this.getCurrentPlayer(game).getMoves() == 200 && this.getOtherPlayer(game).getMoves() == 200) {
			game.setState(game.getTieGameState());
			game.setOver();
			return;
		}
		game.setCheck(false);
		game.setState(game.getUpperMoveState());
		
	}
	
	@Override
	public String getEndMessage() {
		return "";
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
