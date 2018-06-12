
package jUnit.modele;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import modele.animation.Animation;

public class AnimationTest {
	@Test
	public void casdisplayScaleEgalAZero() {
		Throwable e = null;
		

		try {
			new Animation(0, null, 0, 0);
		} catch (Throwable ex) {
			e = ex;
		}
		assertTrue(e instanceof  Exception);
	}
	
}
