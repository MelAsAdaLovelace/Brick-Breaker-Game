package View;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import Model.GameObject;
import Model.Bricks.Brick;
import Model.Controller.Controller;
import Model.Controller.InputController;
import Utils.Constants;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SwingUtilities;

public class GamePanel extends JPanel{
	private InputController inputController;
	private Dimension gamePanelArea;
	private ArrayList<GameObject> gameObjectsToDisplay;
	private Image img = Toolkit.getDefaultToolkit().createImage("background.jpg");

	public GamePanel(){
		setGamePanelArea(new Dimension(Constants.GAMEBOARD_WIDTH, Constants.GAMEBOARD_HEIGHT));
		setPreferredSize(getGamePanelArea());	

	}

	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.fillRect(0, 0, getGamePanelArea().width, getGamePanelArea().height);
		g.drawImage(img, 0, 0, null);
		if (!(getGameObjectsToDisplay() == null))
			for (int i = 0; i < getGameObjectsToDisplay().size(); i++){
				GameObject go = getGameObjectsToDisplay().get(i);
				go.draw(g);
			}


		g.drawRect(0, 0, getGamePanelArea().width, getGamePanelArea().height);
	}

	public void show(ArrayList<Brick> bricks){
		this.setGameObjectsToDisplay(bricks);
		SwingUtilities.invokeLater(() -> this.repaint());
	}


	public Dimension getGamePanelArea(){
		return gamePanelArea;
	}

	public void setGamePanelArea(Dimension gameArea){
		this.gamePanelArea = gameArea;
	}

	public ArrayList<GameObject> getGameObjectsToDisplay(){
		return gameObjectsToDisplay;
	}

	public <T extends GameObject> void setGameObjectsToDisplay(ArrayList<T> gameObjectsToDisplay){
		this.gameObjectsToDisplay = (ArrayList<GameObject>) gameObjectsToDisplay;
	}

	/**
	 * @return the inputController
	 */
	public InputController getInputController() {
		return inputController;
	}

	/**
	 * @param inputController the inputController to set
	 */
	public void setInputController(InputController inputController) {
		this.inputController = inputController;
	}
	//
	//	public BrickPanel getBrickPanel() {
	//		return brickPanel;
	//	}
	//
	//	public void setBrickPanel(BrickPanel brickPanel) {
	//		this.brickPanel = brickPanel;
	//	}




}
