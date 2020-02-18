package Model.Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Model.Ball;
import Model.GameObject;
import Model.Aliens.Alien;
import Model.Aliens.AlienType;
import Model.Aliens.CooperativeAlien;
import Model.Bricks.SimpleBrick;

public class AlienTest {
	private static final Ball ball = new Ball(0, 0, 0, 0, 0, 0);
	Alien alien;
	
	@Before
	public void setUp() {
		alien = new CooperativeAlien(0,0);
	}
	
	@Test
	public void testMove() {
		int temp = alien.getX();
		alien.move();
		assertNotEquals(temp, alien.getX());
	}

	@Test
	public void testHitted() {
		int health = alien.getHealth();
		alien.collideWith(ball);
		assertFalse(health == alien.getHealth());
	}

	@Test
	public void testSetHealth() {
		alien.setHealth(2);
		int health = alien.getHealth();
		assertEquals(2, health);
	}

	@Test
	public void testSetType() {
		alien.setType(AlienType.Cooperative);
		assertEquals(AlienType.Cooperative, alien.getType());
	}

	@Test
	public void testRepOk() {
		assertTrue(alien.repOk());
	}

}
