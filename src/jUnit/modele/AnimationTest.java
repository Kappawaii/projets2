package jUnit.modele;

import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.Test;
import modele.animation.Animation;

public class AnimationTest {
	@Test
	public void casdisplayScaleEgalAZero() {
		Throwable e = null;
		

		try {
			Animation animation = new Animation(2, null, 0);
		} catch (Throwable ex) {
			e = ex;
		}
		assertTrue(e instanceof IllegalArgumentException);
	}
	
}
