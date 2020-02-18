package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Model.GameObject;
import Model.Player;
import Model.Controller.Controller;
import Utils.Constants;


public class LayeredPanel extends JLayeredPane {
	private MainPanel mainPanel;
	private PausePanel pausePanel;
	private Dimension layeredPaneArea;
	private Controller controller;
	private static int brickType = 0;

	public LayeredPanel() {
		setMainPanel(new MainPanel());
		setPausePanel(new PausePanel());
		setLayeredPaneArea(new Dimension(Constants.GAMEBOARD_WIDTH,
				Constants.PLAYER_PANEL_HEIGHT + Constants.GAMEBOARD_HEIGHT));

		add(getMainPanel(), 0);
//		add(getPausePanel(), 1);

		setFocusable(true);
		setRequestFocusEnabled(false);

		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent ke) {
				getController().getInputController().handleInput(ke);
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				getController().getInputController().keyReleased(arg0);
			}
		});

		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me){
				getController().getInputController().handleMouseInput2(me);
				getController().getInputController().handleMouseInput3(me);
			}
		});


	}

	public void render(ArrayList<GameObject> ListOfObjects, Player player) {
		getMainPanel().setListOfObjectsToRender(ListOfObjects);
		getMainPanel().setPlayer(player);
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {

				repaint();

			}
		});

	}

	@Override
	protected void paintComponent(Graphics g) {
		g.translate((getWidth() - (int) getLayeredPaneArea().getWidth()) / 2,
				(getHeight() - (int) getLayeredPaneArea().getHeight()) / 2);

		super.paintComponent(g);

	}
	public MainPanel getMainPanel() {
		return mainPanel;
	}

	public void setMainPanel(MainPanel mainPanel) {
		this.mainPanel = mainPanel;
	}


	public Controller getController() {
		return controller;
	}


	public void setController(Controller controller) {
		this.controller = controller;
	}

	public PausePanel getPausePanel() {
		return pausePanel;
	}

	public void setPausePanel(PausePanel pausePanel) {
		this.pausePanel = pausePanel;
	}

	public Dimension getLayeredPaneArea() {
		return layeredPaneArea;
	}

	public void setLayeredPaneArea(Dimension layeredPaneArea) {
		this.layeredPaneArea = layeredPaneArea;
	}

	/**
	 * @return the brickType
	 */
	public static int getBrickType() {
		return brickType;
	}

	/**
	 * @param brickType the brickType to set
	 */
	public static void setBrickType(int brickType) {
		LayeredPanel.brickType = brickType;
	}




}
