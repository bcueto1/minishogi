import java.util.Scanner;

public class MiniShogi {

	private static int convertXPosition(String position) {
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
		    default:  break;
	    }
	    
	    return xPosition;
    }

    private static int convertYPosition(String position) {
    	int yPosition = 0;
    	int yInput = Integer.parseInt(position.substring(1,2));
		
    	switch (yInput) {
	    	case 1:  yPosition = 0;
	    			 break;
	    	case 2:  yPosition = 1;
	    			 break;
	    	case 3:  yPosition = 2;
	    			 break;
	    	case 4:  yPosition = 3;
	    			 break;
	    	case 5:  yPosition = 4;
	    			 break;
	    	default: break;
    	}
    	
    	return yPosition;

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
	
	
	public static void main(String[] args) {
		
		Game game = new Game();
		Scanner interactive = new Scanner(System.in);
		boolean isOver = false;
		
		while (!game.isOver()) {
			String userInput = interactive.next();
			
			String[] parts = userInput.split("\\s");
			if (parts[0].equals("move")) {
				String begPos = parts[1];
				String endPos = parts[2];
				int startX = convertXPosition(begPos.substring(1, 2));
				int startY = convertYPosition(begPos.substring(0,1));
				int endX = convertXPosition(endPos.substring(1, 2));
				int endY = convertYPosition(endPos.substring(0,1));
				
				if (parts.length == 4) {
					if (parts[3].equals("promote"))
						game.move(startX, startY, endX, endY, true);
				} else {
					game.move(startX, startY, endX, endY, false);
				}
			} else if (parts[0].equals("drop")) {
				String type = convertToType(parts[1]);
				String inputPos = parts[2];
				int dropY = convertYPosition(inputPos.substring(0,1));
				int dropX = convertXPosition(inputPos.substring(1, 2));
				game.drop(type, dropX, dropY);
			}
			
			
			
			
		}
		

	}

}
