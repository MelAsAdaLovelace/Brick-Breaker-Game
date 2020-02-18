package Utils;

import java.awt.Color;
import java.awt.Toolkit;

import View.PlayerPanel;

public final class Constants
{
	private static Constants instance;

	private Constants() {

	}

	public static Constants getInstance() {
		if(instance == null) {
			instance = new Constants();
		}
		return instance;
	}

	public static final int SCREEN_WIDTH = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(); 	
	public static final int SCREEN_HEIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();

	public static final int BUILDING_WIDTH = SCREEN_WIDTH;
	public static final int BUILDING_HEIGHT = SCREEN_HEIGHT;

	public static final int PLAYER_PANEL_HEIGHT = 40;
	public static final int GAMEBOARD_WIDTH = SCREEN_WIDTH;
	public static final int GAMEBOARD_HEIGHT =SCREEN_HEIGHT;





	public static final int BALL_RADIUS = 17/2;

	public final static int PADDLE_WIDTH = (int) (SCREEN_WIDTH * 0.1);
	public static final int PADDLE_HEIGHT = 20;
	public static final int PADDLE_Y = (int) SCREEN_HEIGHT - 6*Constants.PADDLE_HEIGHT;

	public static final int PLAYABLE = (int) (PADDLE_Y * 0.75);


	public final static int L = PADDLE_WIDTH;
	public final static int WALL_WIDTH = 10;
	public final static int BRICK_WIDTH = L / 5;
	public static final int BRICK_HEIGHT = 20;
	public static final int BRICK_RADIUS = 10;

	public static final int MENU_WIDTH = Constants.SCREEN_WIDTH - Constants.GAMEBOARD_WIDTH - Constants.WALL_WIDTH;
	public static final int MENU_HEIGHT = GAMEBOARD_HEIGHT;

	public static final int ALIEN_WIDTH = 100;
	public static final int ALIEN_HEIGHT = BRICK_HEIGHT;

	public final static double BALL_SPEED = 5;
	public final static int LIVE = 100;

	public final static int GAME_LOOP_TIME = 10000000;
	public static final int PADDLE_OFFSET = L / 10;


	public static final Color BALL_COLOR = Color.MAGENTA;
	public static final Color SIMPLE_BRICK_COLOR = new Color(207, 242, 228);
	public static final Color METAL_COLOR = new Color(95,0,0);
	public static final Color PADDLE_COLOR = Color.PINK;
	public static final Color BACKGROUND_COLOR = Color.DARK_GRAY;
	public static final Color WRAPPER_BRICK_COLOR = new Color(253, 72, 83);
	public static final Color MINING_BRICK_COLOR = new Color(208, 78, 83);
	
	public static final int cols =  (GAMEBOARD_WIDTH - 2* WALL_WIDTH ) / BRICK_WIDTH; 
	public static final int rows = (PLAYABLE - WALL_WIDTH - PLAYER_PANEL_HEIGHT)/BRICK_HEIGHT; 

	public static final int TALLER_PADDLE_DURATION = 30000;
	public static final int BULLET_SPEED = 4;
	public static final int BULLETS = 10;	
	public final static int POWERUP_SIZE = 30;
	public final static double POWERUP_SPEED = 1;
	public static final int CHEMBALL_DURATION = 60000;
	public static final double MINING_BRICK_RADIUS =  L/15;
	public static final double MINING_BRICK_VELOCITY = L/4;
	public static final Color MAGNETIC_PADDLE_COLOR = new Color(251, 117, 104);
	public static final int MINING_R = BALL_RADIUS/2;



}
