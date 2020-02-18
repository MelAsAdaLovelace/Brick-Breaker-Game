package Model.Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Model.Bricks.Brick;
import Model.Bricks.WrapperBrick;

public class BrickTest {
	/**
	 *  Tests the public void setHasSuprrise(boolean hasSurprise)
	 */
	@Test
	public void testSetHasSuprrise() {
		Brick b = new WrapperBrick(0, 0);
		b.setHasSurprise(false);
		boolean hasSurprise = b.isHasSurprise();
		assertFalse(hasSurprise);
	}
	
	/**
	 *  Tests the public void isHasSurprise()
	 */
	@Test
	public void testIshasSuprrise() {
		Brick b = new WrapperBrick(0, 0);
		boolean hasSurprise = b.isHasSurprise();
		assertTrue(hasSurprise);
	}
	
	/**
	 *  Tests the public void isMetal()
	 */
	@Test
	public void testIsMetal() {
		Brick b = new WrapperBrick(0, 0);
		boolean isMetal = b.isMetal();
		assertTrue(isMetal);
	}
	/**
	 *  Tests the public void setMetal(boolean isMetal)
	 */
	public @Test
	void testSetMetal() {
		Brick b = new WrapperBrick(0, 0);
		b.setMetal(false);
		boolean isMetal = b.isMetal();
		assertFalse(isMetal);
	}
	/**
	 *  Tests the public void hitted(GameObject go)
	 */
	@Test
	public void testHitted() {
		Brick b = new WrapperBrick(0, 0);
		boolean isHit = b.hitted(b);
		assertTrue(isHit);
	}
	
	/**
	 *  Tests the representation invariant
	 */
	@Test
	public void testRepOk() {
		Brick b = new WrapperBrick(0, 0);
		assertTrue(b.repOk());
	}
	
}
