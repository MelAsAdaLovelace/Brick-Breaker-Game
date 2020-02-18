package View;

import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import Model.Controller.InputController;
import Utils.Constants;

public class BrickFrame {
	private boolean gameOngoing = false;
	private JFrame mainFrame;
	private JPanel mainPanel;

	private JButton wrapperButton;
	private JButton simpleButton;
	private JButton halfMetalButton;
	private JButton mineButton;
	private JButton save;
	private JButton OK;
	private JButton about;

	private JTextField wrapperField;
	private JTextField simpleField;
	private JTextField halfMetalField;
	private JTextField mineField;
	private JTextField totalBricks;
	private JTextField username;
	private JTextField message;

	private static int total = 0;
	int simple = 0;
	int metal = 0;
	int wrapper = 0;
	int mining = 0;

	private JPanel morePanel;
	private JPanel aboutPanel;
	private InputController inputController;

	public BrickFrame() {
		mainFrame = new JFrame("Bricking Bad Launcher2");
		getMainFrame().setSize(500, 500);
		getMainFrame().setLocationRelativeTo(null);
		getMainFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		getMainFrame().setLayout(new GridLayout(2, 2));

		setMainPanel(new JPanel() {
			{

				setLayout(new GridLayout(0, 2));
				setMessage(new JTextField());
				message.setEditable(false);
				message.setVisible(true);

				setSimpleField(new JTextField());
				getSimpleField().setText("");
				setSimpleButton(new JButton("Simple Brick") {
					{
						addActionListener(ae -> {
							getInputController().setBrickType(0);
							String st = "";
							try {
								message.setText("");
								st = getSimpleField().getText();
								if(Integer.parseInt(getSimpleField().getText()) < 75) {
									st = "75";
									getMessage().setText("At least 75 Simple Bricks");
								}
								if(Integer.parseInt(getSimpleField().getText()) > 750) {
									getMessage().setText("At most 750 Simple Bricks");
									st = "750";

								}
								

							}catch(NumberFormatException exp) {
								st = "75";
							}
							simple = Integer.parseInt(st);
							total = wrapper + metal + simple;
							getTotalBricks().setText(""+total);
							getInputController().simpleBrCount(st);
							getSimpleField().setText(st);
						
						});
					}
				});

				setHalfMetalField(new JTextField());
				getHalfMetalField().setText("");
				setHalfMetalButton(new JButton("Half-Metal Brick") {
					{
						addActionListener(ae -> {
							message.setText("");
							getInputController().setBrickType(1);
							String st;
							try {
								st = getHalfMetalField().getText();
								if(Integer.parseInt(getHalfMetalField().getText()) < 10) {
									st = "10";
									getMessage().setText("At least 10 Half Metal Brick");
								}
								if(Integer.parseInt(getHalfMetalField().getText()) >= Constants.cols) {
									getMessage().setText("At most " + (Constants.cols-1) +" Half Metal Brick");
									st = "" + Constants.cols;

								}
							}catch(NumberFormatException exp) {
								st = "10";
							}

							metal = Integer.parseInt(st);
							getInputController().halfMetalBrCount(st);
							total = wrapper + metal + simple;
							getTotalBricks().setText(""+total);
							getHalfMetalField().setText(st);

						});
					}
				});

				setWrapperField(new JTextField());
				getWrapperField().setText("");
				setWrapperButton(new JButton("Wrapper Brick") {
					{
						addActionListener(ae -> {
							message.setText("");
							getInputController().setBrickType(2);
							String st;
							try {
								st = getWrapperField().getText();
								if(Integer.parseInt(getWrapperField().getText()) < 10) {
									getMessage().setText("At least 10 Wrapper Brick");
									st = "10";
								}
								if(Integer.parseInt(getWrapperField().getText()) > 150) {
									getMessage().setText("At most 150 Wrapper Brick");
									st = "150";
								}


							}catch(NumberFormatException exp) {
								st = "10";
							}

							getWrapperField().setText(st);
							wrapper = Integer.parseInt(st);
							total = wrapper + metal + simple;
							getTotalBricks().setText(""+total);
							getInputController().wrapperBrCount(st);
							
						});
					}
				});
				
				setMineField(new JTextField(""));
				setMineButton(new JButton("Mining Brick") {
					{
						addActionListener(ae -> {
							message.setText("");
							getInputController().setBrickType(3);
							String st;
							try {
								st = getMineField().getText();
								if(Integer.parseInt(getMineField().getText()) < 5) {
									getMessage().setText("At least 5 Mining Brick");
									st = "5";
								}
								if(Integer.parseInt(getMineField().getText()) > 10) {
									getMessage().setText("At most 10 Mining Brick");
									st = "10";
								}


							}catch(NumberFormatException exp) {
								st = "10";
							}

							getMineField().setText(st);
							mining = Integer.parseInt(st);
							total = wrapper + metal + simple + mining;
							getTotalBricks().setText(""+total);
							getInputController().mineBrCount(st);
							
						});
					}
				});

				setTotalBricks(new JTextField());
				getTotalBricks().setText("");
				getTotalBricks().setEditable(false);
				setOK(new JButton("Start"){
					{
						addActionListener(ae -> {
							message.setText("");
							getInputController().setBrickType(0);
							getInputController().simpleBrCount(getSimpleField().getText());
							getInputController().wrapperBrCount(getWrapperField().getText());
							getInputController().halfMetalBrCount(getHalfMetalField().getText());
							total = wrapper + metal + simple;
							getTotalBricks().setText(""+total);
							if (!gameOngoing)
								(new Thread() {
									public void run() {
										getInputController().menuStart();
									}
								}).start();
							else
								getInputController().menuPlay();

						});
					}
				});

				

				add(getSimpleField());
				add(getSimpleButton());
				add(getHalfMetalField());
				add(getHalfMetalButton());
				add(getWrapperField());
				add(getWrapperButton());
				add(getMineField());
				add(getMineButton());
				add(getTotalBricks());
				add(getOK());
				//add(getStartButton());

				setUsername(new JTextField("Username here"));
				username.setEnabled(true);
				add(new JButton("Load Game"){
					{
						addActionListener(ae -> {
							message.setText("");
							if(getInputController().getModel().tryToLoad(username.getText())) {
								getInputController().getModel().loadGame(username.getText());
								message.setText(username.getText() + " is successfully loaded!");
							}
								
							
							else {
								message.setText("Username is not found, please try again or built from the scratch");
							}
						});
					}
				});


				setSave(new JButton("Save") {
					{
						addActionListener(ae -> {
							message.setText("");
							if(username.getText().equals("")) {
								message.setText("You have to enter a user name.");
							}else {
								getInputController().setPlayer(username.getText());
								getInputController().saveDesign(username.getText());
							}
						});
					}
				});	
				
				setAbout(new JButton("About") {
					{
						addActionListener(ae -> {
								JFrame instructionFrame = new JFrame();

								instructionFrame.setSize(1000, 700);
								instructionFrame.setLocationRelativeTo(null);
								instructionFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
								JPanel instructionPanel = new JPanel();
								instructionPanel.setLayout(new BoxLayout(instructionPanel, BoxLayout.Y_AXIS));
								JTextArea instructions = new JTextArea();
								instructions.setEditable(false);
								instructions.setText(
										"HOW TO PLAY:\r\n\n" + 
										"Bricking Bad’s goal is to destroy all of the bricks without you, the player, ran out of lives. "
										+ "The paddle is a physical object that you can move side-to-side with the left and right arrow keys. "
										+ "Player controls paddle to hit the ball. Every time you hit and destroy the brick with the ball, your score will increase.\r\n" + 
										"\n\nINSTRUCTION MANUAL:\n\nOnce you start the application, you will be welcome with this window. "
										+ "You can either start a new game, or enter your nickname to play previously saved games. "
										+ "Build your own maps with the building mode. There are four types of bricks in the Bricking Bad: "
										+ "Simple, Half-Metal, Wrapper and Mining Bricks.You can choose any of them in any quantity to build your own map with building mode. "
										+ "Use your mouse to place bricks."
										+ "There are also power-ups falling from the bricks you destroyed. You can collect them with paddle and use them. The power-ups matching with the colors are described below:\r\n" + 
										"*Magnetic Paddle – ORANGE POWER-UP FALLS\r\n" + 
										"*Destructive Laser Gun – RED POWER-UP FALLS\r\n" + 
										"*Double Paddle Size – DARK BLUE POWER-UP FALLS\r\n"
										+ "Gang-of-Balls – PURPLE POWER-UP FALLS\r\n" + 
										"*Chemical Balls – GREEN POWER-UP FALLS\r\n\n" + 
										"The Player also needs to handle alliens while playing. There are many types of alliens in Bricking Bad.\r\n" + 
										"*Cooperative,\r\n" + 
										"*CooperativeDrunk,\r\n" + 
										"*Drunk,\r\n" + 
										"*Protecting,\r\n" + 
										"*ProtectingDrunk,\r\n" + 
										"*Repairing,\r\n" + 
										"*RepairingDrunk\n\n"
										+ "CONTROLLERS:\n\n"
										+ "Move Arrow: Right/Left Button\n" 
										+ "Pause/Resume Game: P\n"
										+ "Activate Magnetic Paddle: M \n"
										+ "Return To Menu: Q\n"
										+ "Rotate Paddle: A-D\n"
										+ "Laser Gun Shoot: Space Bar\n"
										+ "Throw Ball: W - Mouse Click\n"
										+ "Add Brick on Pause: Left Click \n"
										+ "Remove Brick on Pause: Right Click\n");
								instructions.setLineWrap(true);
								instructions.setAlignmentX(Component.CENTER_ALIGNMENT);
								instructions.setWrapStyleWord(true);
								instructions.setFont(new Font("Arial", Font.PLAIN, 25));
								JScrollPane scrollPane = new JScrollPane(instructions);
								instructions.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
								instructionPanel.add(scrollPane);
								instructionFrame.add(instructionPanel);
								instructionFrame.setVisible(true);
							});			
					}
				});

				add(username);
				add(save);
				add(about);
			
			}



		});

		getMainFrame().add(getMainPanel());
		setMorePanel(new JPanel() {
			{
				setLayout(new GridLayout(1,1));
				add(message);
			}
		});
		
		getMainFrame().add(morePanel, JPanel.BOTTOM_ALIGNMENT);
		


	}

	public void returnToMenu() {
		gameOngoing = true;
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				getOK().setText("Continue");
				getMainFrame().setVisible(true);
			}
		});

	}

	public JButton getWrapperButton() {
		return wrapperButton;
	}

	public void setWrapperButton(JButton wrapperButton) {
		this.wrapperButton = wrapperButton;
	}

	public JButton getSimpleButton() {
		return simpleButton;
	}

	public void setSimpleButton(JButton simpleButton) {
		this.simpleButton = simpleButton;
	}

	public JButton getHalfMetalButton() {
		return halfMetalButton;
	}

	public void setHalfMetalButton(JButton halfMetalButton) {
		this.halfMetalButton = halfMetalButton;
	}

	public JTextField getWrapperField() {
		return wrapperField;
	}

	public void setWrapperField(JTextField wrapperField) {
		this.wrapperField = wrapperField;
	}

	public JTextField getSimpleField() {
		return simpleField;
	}

	public void setSimpleField(JTextField simpleField) {
		this.simpleField = simpleField;
	}

	public JTextField getHalfMetalField() {
		return halfMetalField;
	}

	public void setHalfMetalField(JTextField halfMetalField) {
		this.halfMetalField = halfMetalField;
	}

	public JFrame getMainFrame() {
		return mainFrame;
	}

	public void setMainFrame(JFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	public JPanel getMainPanel() {
		return mainPanel;
	}

	public void setMainPanel(JPanel mainPanel) {
		this.mainPanel = mainPanel;
	}

	public InputController getInputController() {
		return inputController;
	}

	public void setInputController(InputController inputController) {
		this.inputController = inputController;
	}

	public JButton getOK() {
		return OK;
	}

	public void setOK(JButton oK) {
		OK = oK;
	}

	public JTextField getTotalBricks() {
		return totalBricks;
	}

	public void setTotalBricks(JTextField totalBricks) {
		this.totalBricks = totalBricks;
	}


	public JPanel getMorePanel() {
		return morePanel;
	}

	public void setMorePanel(JPanel morePanel) {
		this.morePanel = morePanel;
	}

	public JPanel getAboutPanel() {
		return aboutPanel;
	}

	public void setAboutPanel(JPanel aboutPanel) {
		this.aboutPanel = aboutPanel;
	}

	/**
	 * @return the save
	 */
	public JButton getSave() {
		return save;
	}

	/**
	 * @param save the save to set
	 */
	public void setSave(JButton save) {
		this.save = save;
	}

	/**
	 * @return the username
	 */
	public JTextField getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(JTextField username) {
		this.username = username;
	}

	/**
	 * @return the message
	 */
	public JTextField getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(JTextField message) {
		this.message = message;
	}

	/**
	 * @return the about
	 */
	public JButton getAbout() {
		return about;
	}

	/**
	 * @param about the about to set
	 */
	public void setAbout(JButton about) {
		this.about = about;
	}

	/**
	 * @return the mineButton
	 */
	public JButton getMineButton() {
		return mineButton;
	}

	/**
	 * @param mineButton the mineButton to set
	 */
	public void setMineButton(JButton mineButton) {
		this.mineButton = mineButton;
	}

	/**
	 * @return the mineField
	 */
	public JTextField getMineField() {
		return mineField;
	}

	/**
	 * @param mineField the mineField to set
	 */
	public void setMineField(JTextField mineField) {
		this.mineField = mineField;
	}




}
