import java.util.ArrayList;
import java.util.Scanner;

public class MiniShogi {
	
	
    
    private static void interactiveMode(Game game) {
    	
    	Scanner interactive = new Scanner(System.in);
    	boolean lowerMove = true;
    	
    	System.out.println(Utils.stringifyBoard(game.getBoard().getBoardString()));
		System.out.println("Captures UPPER: " + game.getUpperPlayer().capturedString());
		System.out.println("Captures lower: " + game.getLowerPlayer().capturedString());
		System.out.println();
		
		while (!game.isOver()) {
			if (lowerMove)
				System.out.print("lower> ");
			else
				System.out.print("UPPER> ");
			String userInput = interactive.nextLine();
			
			System.out.println();
			
			String[] parts = userInput.split(" ");
			if (parts[0].equals("move")) {
				Utils.readMove(game, parts);
			} else if (parts[0].equals("drop")) {
				Utils.readDrop(game, parts);
			}
			
			if (lowerMove)
				System.out.println("lower player action: " + userInput);
			else
				System.out.println("UPPER player action: " + userInput );
			
			lowerMove = !lowerMove;
			System.out.println(Utils.stringifyBoard(game.getBoard().getBoardString()));
			System.out.println("Captures UPPER: " + game.getUpperPlayer().capturedString());
			System.out.println("Captures lower: " + game.getLowerPlayer().capturedString());
			if (game.inCheck()) {
				if (lowerMove) {
					System.out.println("lower player is in check!");
				} else {
					System.out.println("UPPER player is in check!");
				}
				System.out.println("Available moves:");
				ArrayList<String> moves = game.getBoard().getAvailableMoves();
				java.util.Collections.sort(moves);
				for (String move: moves) {
					System.out.println(move);
				}
			} else {
				System.out.println();
			}
			
		}
		
		System.out.println(game.endMessage());
    }
    
    private static void fileMode(Game game, String file) {
    	String lastMove = "";
    	boolean lowerMove = true;
    	try {
			Utils.TestCase parsedInfo = Utils.parseTestCase(file);
			for (Utils.InitialPosition initialPiece: parsedInfo.initialPieces) {
				String type = initialPiece.piece;
				int x = Utils.convertXPosition(initialPiece.position);
				int y = Utils.convertYPosition(initialPiece.position);
				if (type.equals(type.toUpperCase())) {
					Piece piece = Utils.createPiece(Utils.convertToType(type.toLowerCase()), "upper", x, y);
					game.getBoard().getPosition(x, y).setPiece(piece);
					if (piece.getType().equals("king"))
						game.getUpperPlayer().setKing((King) piece);
					
				} else {
					Piece piece = Utils.createPiece(Utils.convertToType(type.toLowerCase()), "lower", x, y);
					game.getBoard().getPosition(x, y).setPiece(piece);
					if (piece.getType().equals("king"))
						game.getLowerPlayer().setKing((King) piece);
				}	
			}
			for (int i = 0; i < game.getBoard().getPositions().length; i++) {
				for (int j = 0; j < game.getBoard().getPositions().length; j++) {
					if (game.getBoard().getPositions()[i][j].hasPiece())
						game.getBoard().getPositions()[i][j].getPiece().updatePossibleMoves(game.getBoard());
				}
			}
			for (String upper: parsedInfo.upperCaptures) {
				if (upper.equals(""))
					break;
				String type = Utils.convertToType(upper.toLowerCase());
				Piece piece = Utils.createPiece(type, "upper", -1, -1);
				game.getUpperPlayer().addCapturedPiece(piece);
			}
			for (String lower: parsedInfo.lowerCaptures) {
				if (lower.equals(""))
					break;
				String type = Utils.convertToType(lower.toLowerCase());
				Piece piece = Utils.createPiece(type, "lower", -1, -1);
				game.getLowerPlayer().addCapturedPiece(piece);
			}
			for (String move: parsedInfo.moves) {
				String[] parts = move.split(" ");
				lastMove = move;
				if (parts[0].equals("move"))
					Utils.readMove(game, parts);
				else if (parts[0].equals("drop"))
					Utils.readDrop(game, parts);
				lowerMove = !lowerMove;
				if (game.isOver())
					break;
			}
			
			if (!lowerMove)
				System.out.println("lower player action: " + lastMove);
			else
				System.out.println("UPPER player action: " + lastMove);
			System.out.println(Utils.stringifyBoard(game.getBoard().getBoardString()));
			System.out.println("Captures UPPER: " + game.getUpperPlayer().capturedString());
			System.out.println("Captures lower: " + game.getLowerPlayer().capturedString());
			if (game.inCheck()) {
				if (lowerMove) {
					System.out.println("lower player is in check!");
				} else {
					System.out.println("UPPER player is in check!");
				}
				System.out.println("Available moves:");
				ArrayList<String> moves = game.getBoard().getAvailableMoves();
				java.util.Collections.sort(moves);
				for (String move: moves) {
					System.out.println(move);
				}
			} else {
				System.out.println();
			}
			if (game.isOver())
				System.out.println(game.endMessage());
			else {
				if (lowerMove)
					System.out.println("lower> ");
				else
					System.out.println("UPPER> ");
			}
		} catch (Exception e1) {
			System.exit(0);
		}

    }
	
	public static void main(String[] args) {
		
		Game game;
		
		if (0 < args.length) {
			game = new Game(false);
			String filename = args[0];
			fileMode(game, filename);
		} else {
			game = new Game(true);
			interactiveMode(game);
		}

	}

}
