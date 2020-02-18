package Model.Tests;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;

import Model.Paddle;
import Model.PowerUps.FallingPowerUp;
import Model.PowerUps.PowerUp;

public class FallingPowerUpTest {
	PowerUp powerUp = new PowerUp("tallerPaddle");
	FallingPowerUp fallingPowerUp = new FallingPowerUp(30, 0, 50, 50, 1, 0, 2, powerUp, Color.magenta);
	Paddle paddle = new Paddle(10, 10, 10);

	@Test
	public void testCollideWith() {
		fallingPowerUp.setPowerUp(powerUp);
		fallingPowerUp.collideWith(paddle);
		assertTrue(fallingPowerUp.isLost());
	}

	@Test
	public void testGetType() {
		assertTrue(powerUp.getType().equals("tallerPaddle"));
	}

	@Test
	public void testSetType() {
		powerUp.setType("magnetic");
		assertTrue(powerUp.getType().equals("magnetic"));
	}
	
	@Test
	public void testRepOk() {
		assertTrue(fallingPowerUp.repOK());
	}

}
