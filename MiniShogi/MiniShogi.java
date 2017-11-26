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
	
	
	public static void main(String[] args) {
		
		Game game = new Game();
		Scanner interactive = new Scanner(System.in);
		boolean isOver = false;
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
			} else if (parts[0].equals("drop")) {
				String type = convertToType(parts[1]);
				String inputPos = parts[2];
				int dropY = convertYPosition(inputPos);
				int dropX = convertXPosition(inputPos);
				game.drop(type, dropX, dropY);
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

}
