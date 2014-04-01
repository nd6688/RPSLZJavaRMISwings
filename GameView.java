/*
 * GameView.java
 *
 * Version:1, 12/09/2013
 */
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Description: View class.
 * 
 * @author Bhargav Solanki
 * @author Neel Desai
 */
public class GameView extends JFrame {
	GameController gameController;
	public JLabel playerInfo = null;
	public JLabel player1ScoreLabel = null;
	public JLabel player2ScoreLabel = null;
	public JButton rock;
	public JButton paper;
	public JButton scissor;
	public JButton lizard;
	public JButton spock;
	public JButton rock1;
	public JButton paper1;
	public JButton scissor1;
	public JButton lizard1;
	public JButton spock1;
	public ImageIcon images[] = {
			new ImageIcon(getClass().getResource("rock.jpg")),
			new ImageIcon(getClass().getResource("paper.jpg")),
			new ImageIcon(getClass().getResource("scissor.jpg")),
			new ImageIcon(getClass().getResource("lizard.jpg")),
			new ImageIcon(getClass().getResource("spock.jpg")) };
	public ImageIcon defaultImage = new ImageIcon(getClass().getResource(
			"initial.jpg"));
	public JLabel imageLabel;
	public String playerName;
	public String server;
	GameModelInterface obj;

	/*
	 * Description: checks if the game is over or not
	 * 
	 * Arguments: gameController object of GameController class player player1
	 * or player2
	 */
	public void linkController(GameController gameController)
			throws MalformedURLException, RemoteException, NotBoundException {
		this.gameController = gameController;
		Component contents = createComponents();
		this.getContentPane().add(contents);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		this.pack();
		if (playerName.equals("P1")) {
			rock1.setEnabled(false);
			paper1.setEnabled(false);
			scissor1.setEnabled(false);
			lizard1.setEnabled(false);
			spock1.setEnabled(false);
		} else {
			rock.setEnabled(false);
			paper.setEnabled(false);
			scissor.setEnabled(false);
			lizard.setEnabled(false);
			spock.setEnabled(false);
			rock1.setEnabled(true);
			paper1.setEnabled(true);
			scissor1.setEnabled(true);
			lizard1.setEnabled(true);
			spock1.setEnabled(true);
		}
		GameModelInterface obj = (GameModelInterface) Naming.lookup(server);
		this.obj = obj;
		this.setVisible(true);
	}

	/*
	 * Description: Parameterized constructor
	 */
	public GameView(String frameName, String server, String player)
			throws IOException {
		this.server = server;
		this.playerName = player;
		this.setName(frameName);

	}

	/*
	 * Description: creates player 1 buttons
	 * 
	 * Returns: component
	 */
	public Component createButtonsPlayer1() {
		JPanel pane = new JPanel();
		pane.setBorder(BorderFactory.createLoweredBevelBorder());
		pane.setLayout(new GridLayout(6, 1));
		{
			JButton rock = new JButton(new String("ROCK"));
			this.rock = rock;
			rock.addActionListener(gameController);
			pane.add(rock);
		}
		{
			JButton paper = new JButton(new String("PAPER"));
			this.paper = paper;
			paper.addActionListener(gameController);
			pane.add(paper);
		}
		{
			JButton scissor = new JButton(new String("SCISSOR"));
			this.scissor = scissor;
			scissor.addActionListener(gameController);
			pane.add(scissor);
		}
		{
			JButton lizard = new JButton(new String("LIZARD"));
			this.lizard = lizard;
			lizard.addActionListener(gameController);
			pane.add(lizard);
		}
		{
			JButton spock = new JButton(new String("SPOCK"));
			this.spock = spock;
			spock.addActionListener(gameController);
			pane.add(spock);
		}
		{
			final JLabel player1ScoreLabel = new JLabel("0");
			player1ScoreLabel.setText("Player 1 Score: 0");
			player1ScoreLabel.setHorizontalAlignment(JLabel.RIGHT);
			this.player1ScoreLabel = player1ScoreLabel;
			pane.add(player1ScoreLabel);
		}
		return pane;
	}

	/*
	 * Description: image area
	 * 
	 * Returns: component
	 */
	public Component imageArea() {
		JPanel pane = new JPanel();
		pane.setBorder(BorderFactory.createLoweredBevelBorder());
		pane.setLayout(new GridLayout(2, 1));
		{
			ImageIcon image = new ImageIcon(getClass().getResource(
					"initial.jpg"));
			JLabel imageLabel = new JLabel(image);
			this.imageLabel = imageLabel;
			pane.add(imageLabel);

		}
		return pane;
	}

	/*
	 * Description: creates player 2 buttons
	 * 
	 * Returns: component
	 */
	public Component createButtonsPlayer2() {
		JPanel pane = new JPanel();
		pane.setBorder(BorderFactory.createLoweredBevelBorder());
		pane.setLayout(new GridLayout(6, 1));
		{
			JButton rock = new JButton(new String("ROCK1"));
			rock.setEnabled(false);
			this.rock1 = rock;
			rock.addActionListener(gameController);
			pane.add(rock);
		}
		{
			JButton paper = new JButton(new String("PAPER1"));
			paper.setEnabled(false);
			this.paper1 = paper;
			paper.addActionListener(gameController);
			pane.add(paper);
		}
		{
			JButton scissor = new JButton(new String("SCISSOR1"));
			scissor.setEnabled(false);
			this.scissor1 = scissor;
			scissor.addActionListener(gameController);
			pane.add(scissor);
		}
		{
			JButton lizard = new JButton(new String("LIZARD1"));
			lizard.setEnabled(false);
			this.lizard1 = lizard;
			lizard.addActionListener(gameController);
			pane.add(lizard);
		}
		{
			JButton spock = new JButton(new String("SPOCK1"));
			spock.setEnabled(false);
			this.spock1 = spock;
			spock.addActionListener(gameController);
			pane.add(spock);
		}
		{
			final JLabel player2ScoreLabel = new JLabel("0");
			player2ScoreLabel.setText("Player 2 Score: 0");
			player2ScoreLabel.setHorizontalAlignment(JLabel.RIGHT);
			this.player2ScoreLabel = player2ScoreLabel;
			pane.add(player2ScoreLabel);
		}
		return pane;
	}

	/*
	 * Description: add all the components into a single component
	 * 
	 * Returns: component
	 */
	public Component createComponents() {
		JPanel pane = new JPanel();
		pane.setBorder(BorderFactory.createLoweredBevelBorder());
		pane.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 100));
		pane.add(createButtonsPlayer1());
		pane.add(imageArea());
		pane.add(createButtonsPlayer2());
		return pane;
	}
}
