package View;

import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Model.GameObject;
import Model.Player;
import Model.Controller.Controller;
import Utils.Constants;

public class View{
	private Controller controller;

	private JFrame mainFrame;
	private LayeredPanel layeredPanel;
	private BrickFrame brickFrame;

	private MenuView menuView;


	public View(){
		setMainFrame(new JFrame("Bricking Bad by Ravenclaws"));
		getMainFrame().setSize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
		getMainFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getMainFrame().setExtendedState(JFrame.MAXIMIZED_BOTH);
		getMainFrame().setUndecorated(true);
	
		
		setLayeredPanel(new LayeredPanel());
		getMainFrame().add(getLayeredPanel());
//		setMenuView(new MenuView());
		brickFrame = new BrickFrame();
	}
	
	public void setController(Controller controller){
		this.controller = controller;
		getLayeredPanel().setController(getController());
//		getBuilderView().setInputController(getController().getInputController());
//		getMenuView().setInputController(getController().getInputController());
		getBrickFrame().setInputController(getController().getInputController());
	}
	
	public void render(ArrayList<GameObject> ListOfObjects, Player player){
		getLayeredPanel().render(ListOfObjects, player);
	}
	


	public MenuView getMenuView(){
		return menuView;
	}

	public void setMenuView(MenuView menuView)
	{
		this.menuView = menuView;
	}


	public LayeredPanel getLayeredPanel(){
		return layeredPanel;
	}

	public void setLayeredPanel(LayeredPanel layeredPanel){
		this.layeredPanel = layeredPanel;
	}


	public JFrame getMainFrame(){
		return mainFrame;
	}

	public void setMainFrame(JFrame mainFrame){
		this.mainFrame = mainFrame;
	}

	public Controller getController(){
		return controller;
	}

	public BrickFrame getBrickFrame() {
		return brickFrame;
	}

	public void setBrickFrame(BrickFrame brickFrame) {
		this.brickFrame = brickFrame;
	}
	
	
}
