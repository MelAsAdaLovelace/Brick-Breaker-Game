package Model.Tests;

import static org.junit.Assert.*;

import java.beans.PropertyChangeEvent;

import org.junit.Before;
import org.junit.Test;

import Model.Paddle;
import Utils.Constants;

public class PaddleTest {
	Paddle paddle;
	PropertyChangeEvent evt;
	
	@Before
	public void setUp() {
		paddle = new Paddle(10,10,10);
	}


	@Test
	public void testPropertyChange() {
		int w = paddle.getWidth();
		evt = new PropertyChangeEvent(paddle, "DoublePaddle", true, false);
		paddle.propertyChange(evt);
		paddle.doublePaddle();
		assertTrue(paddle.getWidth() != w);
	}

	
	@Test
	public void testPaddle() {
		assertTrue(paddle.getPaddleWidth() == Constants.L);
	}


	@Test
	public void testMove() {
		int x = paddle.getX();
		paddle.move(10);
		assertTrue(paddle.getX() != x);
	}


	@Test
	public void testDoublePaddle() {
		paddle.doublePaddle();
		assertTrue(paddle.getWidth() == Constants.L *2 );
	}
	
	public void testRepOk() {
		assertTrue(paddle.repOk());
	}

}
