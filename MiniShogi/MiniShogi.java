
public class MiniShogi {
	
	public static Game game;

	private int convertXPosition(String position) {
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

    private int convertYPosition(String position) {
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
	
	
	public static void main(String[] args) {
		

	}

}
