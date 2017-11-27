import java.util.Scanner;

public class MiniShogi {

	private static int convertYPosition(String position) {
	    int yPosition = 0;
	    String yString = position.substring(0,1);
	    
	    switch (yString) {
		    case "a": yPosition = 0;
		    		  break;
		    case "b": yPosition = 1;
		     		  break;
		    case "c": yPosition = 2;
		    		  break;
		    case "d": yPosition = 3;
		    		  break;
		    case "e": yPosition = 4;
		    		  break;
		    default:  break;
	    }
	    
	    return yPosition;
    }

    private static int convertXPosition(String position) {
    	int xPosition = 0;
    	int xInput = Integer.parseInt(position.substring(1,2));
		
    	switch (xInput) {
	    	case 1:  xPosition = 4;
	    			 break;
	    	case 2:  xPosition = 3;
	    			 break;
	    	case 3:  xPosition = 2;
	    			 break;
	    	case 4:  xPosition = 1;
	    			 break;
	    	case 5:  xPosition = 0;
	    			 break;
	    	default: break;
    	}
    	
    	return xPosition;

    }
    
    private static String convertToType(String input) {
    	
    	String type = "";
    	
    	switch (input) {
	    	case "b":	type = "bishop";
	    				break;
	    	case "g":	type = "goldgeneral";
	    				break;
	    	case "k":	type = "king";
	    				break;
	    	case "p":	type = "pawn";
	    				break;
	    	case "r":	type = "rook";
	    				break;
	    	case "s":	type = "silvergeneral";
	    				break;
	    	default:	break;
    	}
    	
    	return type;
    }
    
    private static Piece createPiece(String type, String team, int x, int y) {
    	Piece piece = null;
    	switch (type) {
    	case "bishop":	piece = new Bishop(x, y, team);
    					break;
    	case "goldgeneral": piece = new GoldGeneral(x, y, team);
    						break;
    	case "king":	piece = new King(x, y, team);
    					break;
    	case "pawn":	piece = new Pawn(x, y, team);
    					break;
    	case "rook":	piece = new Rook(x, y, team);
    					break;
    	case "silvergeneral": piece = new SilverGeneral(x, y, team);
    						  break;
    	default:		break;
    	}
    	
    	return piece;
    }
    
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
				System.out.print("upper> ");
			String userInput = interactive.nextLine();
			
			System.out.println();
			
			String[] parts = userInput.split(" ");
			if (parts[0].equals("move")) {
				readMove(game, parts);
			} else if (parts[0].equals("drop")) {
				readDrop(game, parts);
			}
			
			if (lowerMove)
				System.out.println("lower player action: " + userInput);
			else
				System.out.println("UPPER player action: " + userInput );
			
			System.out.println(Utils.stringifyBoard(game.getBoard().getBoardString()));
			System.out.println("Captures UPPER: " + game.getUpperPlayer().capturedString());
			System.out.println("Captures lower: " + game.getLowerPlayer().capturedString());
			System.out.println();
			lowerMove = !lowerMove;
		}
		
		System.out.println(game.endMessage());
    }
    
    private static void readMove(Game game, String[] parts) {
    	String begPos = parts[1];
		String endPos = parts[2];
		int startX = convertXPosition(begPos);
		int startY = convertYPosition(begPos);
		int endX = convertXPosition(endPos);
		int endY = convertYPosition(endPos);
		if (parts.length == 4) {
			if (parts[3].equals("promote"))
				game.move(startX, startY, endX, endY, true);
		} else {
			game.move(startX, startY, endX, endY, false);
		}
    }
    
    private static void readDrop(Game game, String[] parts) {
    	String type = convertToType(parts[1]);
		String inputPos = parts[2];
		int dropY = convertYPosition(inputPos);
		int dropX = convertXPosition(inputPos);
		game.drop(type, dropX, dropY);
    }
    
    private static void fileMode(Game game, String file) {
    	String lastMove = "";
    	boolean lowerMove = true;
    	try {
			Utils.TestCase parsedInfo = Utils.parseTestCase(file);
			for (Utils.InitialPosition initialPiece: parsedInfo.initialPieces) {
				String type = initialPiece.piece;
				int x = convertXPosition(initialPiece.position);
				int y = convertYPosition(initialPiece.position);
				if (type.equals(type.toUpperCase())) {
					Piece piece = createPiece(convertToType(type.toLowerCase()), "upper", x, y);
					game.getBoard().getPosition(x, y).setPiece(piece);
					if (piece.getType().equals("king"))
						game.getUpperPlayer().setKing((King) piece);
					
				} else {
					Piece piece = createPiece(convertToType(type.toLowerCase()), "lower", x, y);
					game.getBoard().getPosition(x, y).setPiece(piece);
					if (piece.getType().equals("king"))
						game.getLowerPlayer().setKing((King) piece);
				}	
			}
			for (int i = 0; i < game.getBoard().getBoard().length; i++) {
				for (int j = 0; j < game.getBoard().getBoard().length; j++) {
					if (game.getBoard().getBoard()[i][j].hasPiece())
						game.getBoard().getBoard()[i][j].getPiece().updatePossibleMoves(game.getBoard());
				}
			}
			for (String upper: parsedInfo.upperCaptures) {
				if (upper.equals(""))
					break;
				String type = convertToType(upper.toLowerCase());
				Piece piece = createPiece(type, "upper", -1, -1);
				game.getUpperPlayer().addCapturedPiece(piece);
			}
			for (String lower: parsedInfo.lowerCaptures) {
				if (lower.equals(""))
					break;
				String type = convertToType(lower.toLowerCase());
				Piece piece = createPiece(type, "lower", -1, -1);
				game.getLowerPlayer().addCapturedPiece(piece);
			}
			for (String move: parsedInfo.moves) {
				String[] parts = move.split(" ");
				lastMove = move;
				if (parts[0].equals("move"))
					readMove(game, parts);
				else if (parts[0].equals("drop"))
					readDrop(game, parts);
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
			System.out.println();
			if (game.isOver())
				System.out.println(game.endMessage());
			else {
				if (lowerMove)
					System.out.println("lower> ");
				else
					System.out.println("upper> ");
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
