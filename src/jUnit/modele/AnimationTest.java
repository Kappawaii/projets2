
package jUnit.modele;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import modele.animation.Animation;
import vue.tileset.Tileset;

public class AnimationTest {
	
	@Test
	public void casFramesBetweenSpritesZero() {
		Throwable e = null;
		
		try {
			new Animation(0, null, 0, 0);
		} catch (Throwable ex) {
			e = ex;
		}
		assertTrue(e instanceof  IllegalArgumentException);
	}
	
	@Test
	public void casTilesetNull() {
		Throwable e = null;	

		try {
			new Animation(5, null, 5, 5);
		} catch (Throwable ex) {
			e = ex;
		}
		assertTrue(e instanceof  NullPointerException);
	}

	@Test
	public void casDisplayScale() {
		Throwable e = null;	

		try {
			new Animation(5, new Tileset("sprites/gestionVie/coeursDeVie.png", 4), 0, 5);
		} catch (Throwable ex) {
			e = ex;
		}
		assertTrue(e instanceof  RuntimeException);
	}
	
}
