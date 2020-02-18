package View;

import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import Model.Controller.InputController;
public class MenuView {
	private boolean gameOngoing = false;
	private InputController inputController;
	private JButton startButton;
	private JFrame menuFrame;
	private JPanel menuPanel;
	private JPanel aboutPanel;
	private Font buttonFont = new Font("Times New Roman", Font.BOLD, 40);
	public MenuView() {
		setMenuFrame(new JFrame("Bricking Bad Launcher"));
		getMenuFrame().setSize(400, 700);
		getMenuFrame().setLocationRelativeTo(null);
		getMenuFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getMenuFrame().setLayout(new GridLayout(0, 1));

		setMenuPanel(new JPanel() {
			{
				setLayout(new GridLayout(0, 1));
//				setStartButton(new JButton("Play") {
//					{
//						addActionListener(ae -> {
//							if (!gameOngoing)
//								(new Thread() {
//									public void run() {
//										getInputController().menuStart();
//									}
//								}).start();
//							else
//								getInputController().menuPlay();
//
//						});
//						setFont(buttonFont);
//
//					}
//				});
//				add(getStartButton());

	

	
			
		



				add(new JButton("Instructions") {
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
									"Hello.");
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
						setFont(buttonFont);
					}
				});


			}
		});

		getMenuFrame().add(getMenuPanel());
	}

//	public void returnToMenu() {
//		gameOngoing = true;
//		SwingUtilities.invokeLater(new Runnable() {
//
//			@Override
//			public void run() {
//				getStartButton().setText("Continue");
//
//				getMenuFrame().setVisible(true);
//			}
//		});
//
//	}

	public InputController getInputController() {
		return inputController;
	}

	public void setInputController(InputController inputController) {
		this.inputController = inputController;
	}

	public JFrame getMenuFrame() {
		return menuFrame;
	}

	public void setMenuFrame(JFrame menuFrame) {
		this.menuFrame = menuFrame;
	}

	public JPanel getMenuPanel() {
		return menuPanel;
	}

	public void setMenuPanel(JPanel menuPanel) {
		this.menuPanel = menuPanel;
	}

	public JPanel getAboutPanel() {
		return aboutPanel;
	}

	public void setAboutPanel(JPanel aboutPanel) {
		this.aboutPanel = aboutPanel;
	}

	public JButton getStartButton() {
		return startButton;
	}

	public void setStartButton(JButton startButton) {
		this.startButton = startButton;
	}

}
