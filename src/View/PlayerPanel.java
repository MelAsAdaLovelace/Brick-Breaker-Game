package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;

import Model.Player;
import Model.Controller.InputController;
import Utils.Constants;

public class PlayerPanel extends JPanel {

	private Player player;
	private Dimension playerPanelArea;
	private MainPanel mainPanel;

	String paused = new String("");

	public PlayerPanel() {
		setPlayerPanelArea(new Dimension(Constants.GAMEBOARD_WIDTH, Constants.PLAYER_PANEL_HEIGHT));
		setPreferredSize(getPlayerPanelArea());	
			}
		
		
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.pink);
		g.fillRect(0, 0, Constants.GAMEBOARD_WIDTH, Constants.PLAYER_PANEL_HEIGHT);
		g.setColor(Color.black);
		g.drawRect(0, 0, Constants.GAMEBOARD_WIDTH - 1, Constants.PLAYER_PANEL_HEIGHT);

		String live = new String("Lives: " + getPlayer().getLive());
		String score = new String("Score: " + getPlayer().getScore());
	
		setFont(new Font("Bold", 10, 10));
		
		g.drawString(live, 10, 25);
		g.drawString(score, Constants.GAMEBOARD_WIDTH - 150, 25);
		g.drawString(getPlayer().getTimeAsString(), 220, 25);
		g.drawString(paused, 220, 25);

	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;

	}

	public MainPanel getMainPanel() {
		return mainPanel;
	}

	public void setMainPanel(MainPanel mainPanel) {
		this.mainPanel = mainPanel;
	}

	public Dimension getPlayerPanelArea() {
		return playerPanelArea;
	}

	public void setPlayerPanelArea(Dimension playerArea) {
		this.playerPanelArea = playerArea;
	}
	/**
	 * @return the paused
	 */
	public String getPaused() {
		return paused;
	}


	/**
	 * @param paused the paused to set
	 */
	public void setPaused(String paused) {
		this.paused = paused;
	}

}

