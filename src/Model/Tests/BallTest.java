package Model.Tests;

import static org.junit.Assert.*;

import java.awt.Color;
import java.beans.PropertyChangeEvent;

import org.junit.Before;
import org.junit.Test;

import Model.Ball;
import Model.Paddle;
import Model.Bricks.SimpleBrick;
import Utils.Constants;

public class BallTest {
	Ball ball;
	Paddle paddle;
	PropertyChangeEvent evt;
	SimpleBrick simple;

	@Before
	public void setUp() {
		ball = new Ball(500, 500, 1, 1, 0.0, 0.0);		
	}

	@Test
	public void testBounce() {
		paddle = new Paddle (300, 300, 0);
		ball.bounce(this.paddle);
		assertTrue(ball.getDirectionX() == 1);
	}

	@Test
	public void testBounce2() {
		paddle = new Paddle (800, 800, 0);
		ball.bounce(this.paddle);
		assertTrue(ball.getDirectionX() == -1);
	}

	@Test
	public void testPropertyChange() {
		evt = new PropertyChangeEvent(ball, "GangOfBalls", true, false);
		ball.propertyChange(evt);
		assertTrue(ball.isLost() == true);

	}

	@Test
	public void testPropertyChange2() {
		evt = new PropertyChangeEvent(ball, "ChemicalBall", false, true);
		ball.propertyChange(evt);
		assertTrue(ball.isChemical());
		assertTrue(ball.getColor() == Color.YELLOW);
	}

	@Test
	public void testPropertyChange3() {
		evt = new PropertyChangeEvent(ball, "ChemicalBall", false, false);
		ball.propertyChange(evt);
		assertTrue(ball.isChemical() == false);
		assertTrue(ball.getColor() == Constants.BALL_COLOR);
	}

	@Test
	public void testPropertyChange4() {
		evt = new PropertyChangeEvent(ball, "TestBall", false, true);
		ball.propertyChange(evt);
		assertTrue(ball.isChemical() == false);
		assertTrue(ball.getColor() == Constants.BALL_COLOR);
	}

	@Test
	public void testMoreAtSide() {
		paddle = new Paddle(this.ball.getX() + this.ball.getRadius() * 2, this.ball.getY(), 0);
		paddle.setHeight(this.ball.getRadius() * 2);
		assertTrue(ball.moreAtSide(paddle) == true);
	}

	@Test
	public void testCollideWith() {
		simple = new SimpleBrick(this.ball.getX() + this.ball.getRadius() * 2, this.ball.getY());
		simple.setHeight(this.ball.getRadius() * 2);
		ball.collideWith(simple);
		assertTrue(ball.getDirectionX() == 1);
	}

	@Test
	public void testToDestroy() {
		ball.setY(Constants.GAMEBOARD_HEIGHT + 1);
		assertTrue(ball.toDestroy() == true);
	}
	
	@Test
	public void testRepOk() {
		assertTrue(ball.repOk());
	}

}
