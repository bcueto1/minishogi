import java.io.*;
import java.util.*;

public class Utils {

    static class InitialPosition {
        String piece;
        String position;

        public InitialPosition(String pc, String pos) {
            piece = pc;
            position = pos;
        }

        public String toString() {
            return piece + " " + position;
        }
    }

    static class TestCase {

        List<InitialPosition> initialPieces;
        List<String> upperCaptures;
        List<String> lowerCaptures;
        List<String> moves;

        public TestCase(List<InitialPosition> ip, List<String> uc, List<String> lc, List<String> m) {
            initialPieces = ip;
            upperCaptures = uc;
            lowerCaptures = lc;
            moves = m;
        }

        public String toString() {
            String str = "";

            str += "initialPieces: [\n";
            for (InitialPosition piece : initialPieces) {
                str += piece + "\n";
            }
            str += "]\n";

            str += "upperCaptures: [";
            for (String piece : upperCaptures) {
                str += piece + " ";
            }
            str += "]\n";

            str += "lowerCaptures: [";
            for (String piece : lowerCaptures) {
                str += piece + " ";
            }
            str += "]\n";

            str += "moves: [\n";
            for (String move : moves) {
                str += move + "\n";
            }
            str += "]";

            return str;
        }
    }

    public static String stringifyBoard(String[][] board) {

        String str = "";

        for (int row = board.length - 1; row >= 0; row--) {
            
            str += Integer.toString(row + 1) + " |";
            for (int col = 0; col < board[row].length; col++) {
                str += stringifySquare(board[col][row]);
            }
            str += System.getProperty("line.separator");
        }

        str += "    a  b  c  d  e" + System.getProperty("line.separator");

        return str;
    }

    private static String stringifySquare(String sq) {

        switch(sq.length()) {
            case 0:
                return "__|";
            case 1:
                return " " + sq + "|";
            case 2:
                return sq + "|";
        }

        throw new IllegalArgumentException("Board must be an array of strings like \"\", \"P\", or \"+P\"");
    }

    public static TestCase parseTestCase(String path) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(path));
        String line = br.readLine().trim();
        List<InitialPosition> initialPieces = new ArrayList<InitialPosition>();
        while (!line.equals("")) {
            String[] lineParts = line.split(" ");
            initialPieces.add(new InitialPosition(lineParts[0], lineParts[1]));
            line = br.readLine().trim();
        }
        line = br.readLine().trim();
        List<String> upperCaptures = Arrays.asList(line.substring(1, line.length() - 1).split(" "));
        line = br.readLine().trim();
        List<String> lowerCaptures = Arrays.asList(line.substring(1, line.length() - 1).split(" "));
        line = br.readLine().trim();
        line = br.readLine().trim();
        List<String> moves = new ArrayList<String>();
        while (line != null) {
            line = line.trim();
            moves.add(line);
            line = br.readLine();
        }

        return new TestCase(initialPieces, upperCaptures, lowerCaptures, moves);
    }
    
    public static void printBoardAndCaptures(Game game) {
		System.out.println(Utils.stringifyBoard(game.getBoard().getBoardString()));
		System.out.println("Captures UPPER: " + game.getUpperPlayer().capturedString());
		System.out.println("Captures lower: " + game.getLowerPlayer().capturedString());
	}
    
    public static void readMove(Game game, String[] parts) {
    	String begPos = parts[1];
		String endPos = parts[2];
		int startX = Utils.convertXPosition(begPos);
		int startY = Utils.convertYPosition(begPos);
		int endX = Utils.convertXPosition(endPos);
		int endY = Utils.convertYPosition(endPos);
		if (parts.length == 4) {
			if (parts[3].equals("promote"))
				game.move(startX, startY, endX, endY, true);
		} else {
			game.move(startX, startY, endX, endY, false);
		}
    }
    
    public static void readDrop(Game game, String[] parts) {
    	String type = Utils.convertToType(parts[1]);
		String inputPos = parts[2];
		int dropY = Utils.convertYPosition(inputPos);
		int dropX = Utils.convertXPosition(inputPos);
		game.drop(type, dropX, dropY);
    }
    
    public static int convertYPosition(String position) {
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
    
    public static String yPositionToString(int y) {
    	String yString = "";
    	
    	switch (y) {
	    	case 0:	yString = "a";
	    			break;
	    	case 1:	yString = "b";
					break;	
	    	case 2:	yString = "c";
					break;
	    	case 3: yString = "d";
	    			break;
	    	case 4: yString = "e";
	    			break;
	    	default: break;
    	}
    	
    	return yString;
    }
    
    public static int convertXPosition(String position) {
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
    
    public static String xPositionToString(int x) {
    	String xString = "";
    	
    	switch (x) {
	    	case 0:	xString = "5";
					break;
	    	case 1:	xString = "4";
	    			break;	
	    	case 2:	xString = "3";
					break;
	    	case 3: xString = "2";
					break;
	    	case 4: xString = "1";
					break;
	    	default: break;
    	}
    	
    	return xString;
    }
    
    public static String convertToType(String input) {
    	
    	String type = "";
    	
    	switch (input) {
	    	case "b":	type = "bishop";
	    				break;
	    	case "+b":	type = "pBishop";
	    				break;
	    	case "g":	type = "goldgeneral";
	    				break;
	    	case "k":	type = "king";
	    				break;
	    	case "p":	type = "pawn";
	    				break;
	    	case "+p":	type = "pPawn";
	    				break;
	    	case "r":	type = "rook";
	    				break;
	    	case "+r":	type = "pRook";
	    				break;
	    	case "s":	type = "silvergeneral";
	    				break;
	    	case "+s":	type = "pSilvergeneral";
	    				break;
	    	default:	break;
    	}
    	
    	return type;
    }
    
    public static Piece createPiece(String type, String team, int x, int y) {
    	Piece piece = null;
    	switch (type) {
	    	case "bishop":		
	    		piece = new Bishop(x, y, team);
	    		break;
	    	case "pBishop":		
	    		piece = new Bishop(x, y, team);
	    		piece.promote();
	    		break;
	    	case "goldgeneral": 
	    		piece = new GoldGeneral(x, y, team);
	    		break;
	    	case "king":			
	    		piece = new King(x, y, team);
	    		break;
	    	case "pawn":			
	    		piece = new Pawn(x, y, team);
	    		break;
	    	case "pPawn":		
	    		piece = new Pawn(x, y, team);
	    		piece.promote();
	    		break;
	    	case "rook":	
	    		piece = new Rook(x, y, team);
	    		break;
	    	case "pRook":	
	    		piece = new Rook(x, y, team);
	    		piece.promote();
	    		break;
	    	case "silvergeneral": 
	    		piece = new SilverGeneral(x, y, team);
	    		break;
	    	case "pSilvergeneral": 
	    		piece = new SilverGeneral(x, y, team);
	    		piece.promote();
	    		break;
	    	default:		
	    		break;
    	}
    	
    	return piece;
    }
}