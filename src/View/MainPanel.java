package View;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;
import javax.swing.LayoutStyle.ComponentPlacement;

import Model.GameObject;
import Model.Player;
import Model.Controller.InputController;
import Utils.Constants;


public class MainPanel extends JPanel{
	InputController inputController; 
	private GamePanel gamePanel;
	private PlayerPanel playerPanel;
	private Dimension area;

	public MainPanel(){
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setPlayerPanel(new PlayerPanel());
		this.setGamePanel(new GamePanel());
		area = new Dimension(Constants.GAMEBOARD_WIDTH, Constants.GAMEBOARD_HEIGHT);

		add(getPlayerPanel());
		add(getGamePanel());
		setSize(area);

	}

	public void setListOfObjectsToRender(ArrayList<GameObject> ListOfObjects){
		getGamePanel().setGameObjectsToDisplay(ListOfObjects);
	}

	public void setPlayer(Player player){
		getPlayerPanel().setPlayer(player);
	}

	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
	}


	public Dimension getArea() {
		return area;
	}

	public void setArea(Dimension area) {
		this.area = area;
	}

	public GamePanel getGamePanel()
	{
		return gamePanel;
	}

	public void setGamePanel(GamePanel gamePanel)
	{
		this.gamePanel = gamePanel;
	}

	public PlayerPanel getPlayerPanel()
	{
		return playerPanel;
	}

	public void setPlayerPanel(PlayerPanel playerPanel)
	{
		this.playerPanel = playerPanel;
	}


}
