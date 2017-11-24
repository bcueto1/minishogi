import java.util.*;

public class Board {

	private Position[][] board;
	
	public Board() {
		this.setUpBoard();
		
	}
	
	private void setUpBoard() {
		this.board = new Position[5][5];
		for (int i = 0; i < this.board.length; i++) {
			for (int j = 0; j < this.board[0].length; j++) {
				Position pos = new Position(i,j);
				
				
				this.board[i][j] = pos;
			}
		}
		
		this.board[0][0].setPiece(new Rook(0,0,"upper"));
		this.board[0][1].setPiece(new Bishop(0,1,"upper"));
		this.board[0][2].setPiece(new SilverGeneral(0,2,"upper"));
		this.board[0][3].setPiece(new GoldGeneral(0,3,"upper"));
		this.board[0][4].setPiece(new King(0,4,"upper"));
		this.board[1][4].setPiece(new Pawn(1,4,"upper"));
		this.board[4][4].setPiece(new Rook(4,4,"lower"));
		this.board[4][3].setPiece(new Bishop(4,3,"lower"));
		this.board[4][2].setPiece(new SilverGeneral(4,2,"lower"));
		this.board[4][1].setPiece(new GoldGeneral(4,1,"lower"));
		this.board[4][0].setPiece(new King(4,0,"lower"));
		this.board[1][4].setPiece(new Pawn(3,0,"lower"));
	}
	
	public Board(Position[][] board) {
		this.board = board;
	}
	
	public Position[][] getBoard() {
		return this.board;
	}
	
	public void movePiece(int x, int y, int newX, int newY) {
		Piece piece = null;
		if (this.board[x][y].hasPiece()) {
			try {
				piece = this.board[x][y].getPiece().recreate();
				piece.move(this.board, newX, newY);
				this.board[newX][newY].setPiece(piece);
				this.board[x][y].removePiece();
			} catch (CloneNotSupportedException e) {
				
			}
		} else {
			// Illegal Move there's no piece
		}
			
		
		
	}

	public int convertXPosition(String position) {
	    int xPosition = 0;
	    String xString = position.substring(0,1);
	    
	    switch (xString) {
		    case "a": xPosition = 0;
		    		  break;
		    case "b": xPosition = 1;
		     		  break;
		    case "c": xPosition = 2;
		    		  break;
		    case "d": xPosition = 3;
		    		  break;
		    case "e": xPosition = 4;
		    		  break;
		    default:  
		    	break;
	    }
	    
	    return xPosition;
    }

    private int convertYPosition(String position) {
    	int yPosition = 0;
    	int yInput = Integer.parseInt(position.substring(1,2));
		
    	switch (yInput) {
	    	case 1: yPosition = 0;
	    			break;
	    	case 2: yPosition = 1;
	    			break;
	    	case 3: yPosition = 2;
	    			break;
	    	case 4: yPosition = 3;
	    			break;
	    	case 5: yPosition = 4;
	    			break;
	    	default:
	    		break;
    	}
    	
    	return yPosition;

    }

}