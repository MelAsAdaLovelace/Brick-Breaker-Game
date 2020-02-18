package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Utils.Constants;

public class PausePanel extends JPanel{
	private boolean translated;
	private JLabel text;	

	public PausePanel(){
		setTranslated(true);
		setLayout(new BorderLayout());
		text = new JLabel();
		Font f = new Font("monospaced", Font.BOLD, 24);
		text.setForeground(Color.white);
		text.setFont(f);
		add(text, BorderLayout.CENTER);
		text.setHorizontalAlignment(JLabel.CENTER);
		text.setText("<html><center>***GAME PAUSED***<br>Press P to continue or Q to go back to menu</br></html>");
		text.setVisible(false);
		setBackground(new Color(0,0,0,0));
		setOpaque(false);
		setLocation(0, -1);
		setSize(new Dimension(Constants.GAMEBOARD_WIDTH,
				Constants.GAMEBOARD_HEIGHT + Constants.PLAYER_PANEL_HEIGHT));
		setMaximumSize(new Dimension(Constants.GAMEBOARD_WIDTH,
				Constants.GAMEBOARD_HEIGHT + Constants.PLAYER_PANEL_HEIGHT));
	}


	public void pauseGame(){
		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run(){
				setOpaque(true);
				text.setText("<html><center>***GAME PAUSED***<br>Press P to continue or Q to go back to menu</br></html>");
				text.setVisible(true);
			}
		});
	}


	public void resumeGame()	{
		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run(){
				text.setVisible(false);
				setOpaque(false);
				setTranslated(true);
			}
		});
	}

	public void goodbye(){
		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run(){
				setOpaque(true);
				text.setText("GAME OVER");
				text.setVisible(true);
				setTranslated(false);
			}
		});
	}

	@Override
	public void paintComponent(Graphics g){
		LayeredPanel lp = (LayeredPanel) getParent();
		setSize(lp.getSize());
		if (isTranslated() == true)
			g.translate(-(lp.getWidth() - (int) lp.getLayeredPaneArea().getWidth()) / 2,
					-(lp.getHeight() - (int) lp.getLayeredPaneArea().getHeight()) / 2);

		super.paintComponent(g);
	}
	
	public JLabel getText(){
		return text;
	}

	public void setText(JLabel text){
		this.text = text;
	}

	public boolean isTranslated(){
		return translated;
	}

	public void setTranslated(boolean translated){
		this.translated = translated;
	}
}
