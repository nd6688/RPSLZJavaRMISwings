/*
 * GameModel.java
 *
 * Version:1, 12/09/2013
 */
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
/**
 * Description: Model for the game, acts as a server.
 * 
 * @author Bhargav Solanki
 * @author Neel Desai
 */
public class GameModel extends UnicastRemoteObject implements
		GameModelInterface {

	private static int player1Choice;
	private static int player2Choice;
	private static int player1Played;
	public static Object o1;

	private int checkScore[][] = { { 0, 0, 1, 1, 0 }, { 1, 0, 0, 0, 1 },
			{ 0, 1, 0, 1, 0 }, { 0, 1, 0, 0, 1 }, { 1, 0, 1, 0, 0 } };
/*
 * Default constructor
 */
	public GameModel() throws RemoteException {
		player1Choice = -1;
		player2Choice = -1;
		o1 = "Hello";
		player1Played=0;
	}
	/*
	 * Description: sets the player's choice and 
	 * 				returns the winner
	 * 
	 * Returns: int[]   integer array containing the score 
	 * 			and option of second player
	 * 
	 * Arguments: choice      option selected by the player
	 * 			  player	  player name
	 */
	public int[] myChoice(int choice, String player) throws RemoteException {
		synchronized (o1) {
			if (player.equals("P1")) {
				player1Choice = choice;
				player1Played = 1;
			} else if (player.equals("P2"))
				player2Choice = choice;
			if (player.equals("P1"))
				try {
					o1.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			else if(player.equals("P2") && player1Played==1){
				o1.notify();
				player1Played=0;
			}
			else if(player.equals("P2") && player1Played!=1){
				int winner[] = { -1 };
				return winner;
			}
			if ((player1Choice != (-1)) && (player2Choice != (-1))) {
				System.out.println("player=" + player);
				System.out.println("player1Choice=" + player1Choice);
				System.out.println("player2Choice=" + player2Choice);
				int player1Score = checkScore[player1Choice][player2Choice];
				int player2Score = checkScore[player2Choice][player1Choice];
				if (player1Score == 1) {
					System.out.println("player 1 won");
					int winner[] = { 1, player1Choice };
					return winner;
				} else if (player2Score == 1) {
					System.out.println("player 2 won");
					int winner[] = { 2, player2Choice };
					return winner;
				} else {
					int winner[] = { 3 };
					return winner;
				}
			}
		}
		int winner[] = { 4 };
		return winner;
	}
/* Description: Main function
 * 
 * Arguments: args[]   command line arguments
 * 
 */
	public static void main(String args[]) {
		try {
			GameModel obj = new GameModel();
			Naming.rebind("//localhost:2002/HelloServer", obj);
			System.out.println("HelloServer bound in registry");
		} catch (Exception e) {
			System.out.println("HelloImpl err: " + e.getMessage());
			e.printStackTrace();
		}
	}
}