/*
 * GameController.java
 *
 * Version:1, 12/09/2013
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import javax.swing.JOptionPane;
/**
 * Description: Controller class for the game.
 * 
 * @author Bhargav Solanki
 * @author Neel Desai
 */
public class GameController implements ActionListener {
	public GameModel gameModel;
	private GameView gameView;
	public int player1Option;
	public int player2Option;
	public int player1Score = 0;
	public int player2Score = 0;
	public int count;
	public int gameComplete;
	private int winner[];
/*
 * Description: Parameterized constructor
 */
	public GameController(GameView gameView, GameModel gameModel) {
		this.gameView = gameView;
		this.gameModel = gameModel;
		player1Option = -1;
		player2Option = -1;
		count = 0;

	}
/*
 * Description: Sets the option of the player and calls the 
 * 				server method.
 * 
 * Arguments: option	option selected by the player.
 * 			  player	name of the player.
 */
	public void setOption(int option, String player) throws RemoteException {
		winner = gameView.obj.myChoice(option, player);
		if (winner[0] == 1) {
			player1Score += 1;
			gameView.imageLabel.setIcon(gameView.images[winner[1]]);
		} else if (winner[0] == 2) {
			player2Score += 1;
			gameView.imageLabel.setIcon(gameView.images[winner[1]]);
		} else if(winner[0] == -1){
			JOptionPane.showMessageDialog(gameView, "Please wait for player 1 to select an option");
		} else {
			gameView.imageLabel.setIcon(gameView.defaultImage);
		}
		gameView.player1ScoreLabel.setText("Player 1's score: " + player1Score);
		gameView.player2ScoreLabel.setText("Player 2's score: " + player2Score);
		if (player1Score == 5 || player2Score == 5)
			gameOver(player1Score, player2Score);
	}
/*
 * Description: Decides whether the game is over or not.
 * 
 * Arguments:	player1Score	score of player 1
 * 				player2Score	score of player 2
 */
	public void gameOver(int player1Score, int player2Score) {
		if (player1Score > player2Score)
			JOptionPane.showMessageDialog(gameView, "Player 1 won");
		else if (player2Score > player1Score)
			JOptionPane.showMessageDialog(gameView, "Player 2 won");
		else
			JOptionPane.showMessageDialog(gameView, "It's a tie");
		System.exit(0);
	}
/*
 * Description: sets the option for the player
 * 				on click of a button
 * 
 * Arguments:	e	object of ActionEvent
 */
	public void actionPerformed(ActionEvent e) {
		if ((e.getActionCommand()).equals("ROCK"))
			try {
				setOption(0, "P1");
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
		if (e.getActionCommand().equals("PAPER"))
			try {
				setOption(1, "P1");
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
		if (e.getActionCommand().equals("SCISSOR"))
			try {
				setOption(2, "P1");
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
		if (e.getActionCommand().equals("LIZARD"))
			try {
				setOption(3, "P1");
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
		if (e.getActionCommand().equals("SPOCK"))
			try {
				setOption(4, "P1");
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
		if (e.getActionCommand().equals("ROCK1"))
			try {
				setOption(0, "P2");
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
		if (e.getActionCommand().equals("PAPER1"))
			try {
				setOption(1, "P2");
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
		if (e.getActionCommand().equals("SCISSOR1"))
			try {
				setOption(2, "P2");
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
		if (e.getActionCommand().equals("LIZARD1"))
			try {
				setOption(3, "P2");
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
		if (e.getActionCommand().equals("SPOCK1"))
			try {
				setOption(4, "P2");
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
	}
}