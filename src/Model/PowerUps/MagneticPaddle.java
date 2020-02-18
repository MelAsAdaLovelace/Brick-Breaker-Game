package Model.PowerUps;

import java.awt.Color;
import java.awt.Graphics;

import Model.Paddle;
import Utils.Constants;

public class MagneticPaddle extends Paddle{

	public MagneticPaddle(int x, int y, int x_speed) {
		super(x, y, x_speed);
		setColor(Constants.MAGNETIC_PADDLE_COLOR);
	}
	
	
	
}
