/*
 * GameDemo.java
 *
 * Version:1, 12/09/2013
 */
import java.io.IOException;
import java.rmi.NotBoundException;
/**
 * Description: Demo class for the clients, i.e. players.
 *              Sets up the connection and opens the view.
 * 
 * @author Bhargav Solanki
 * @author Neel Desai
 */
public class GameDemo {
	/* Description: Main function
	 * 
	 * Arguments: args[]   command line arguments
	 * 
	 */
	public static void main(String args[]) throws IOException,
			NotBoundException {
		GameView gameView = new GameView("RPSLS - Using RMI",
				"//localhost:2002/HelloServer", args[0]);
		GameModel gameModel = new GameModel();
		GameController gameController = new GameController(gameView, gameModel);
		gameView.linkController(gameController);
	}
}
