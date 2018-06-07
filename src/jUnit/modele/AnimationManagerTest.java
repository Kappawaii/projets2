package jUnit.modele;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import modele.animation.AnimationManager;

public class AnimationManagerTest {

	@Test
	public void casInferieurAZero() {
		Throwable e = null;
		AnimationManager animationManager = new AnimationManager();
		
		try {
			animationManager.setCurrentAnimation(-1);
		} catch (Throwable ex) {
			e = ex;
		}
		assertTrue(e instanceof ArrayIndexOutOfBoundsException);
	}
	
	@Test
	public void casOutOfRange() {
		Throwable e = null;
		AnimationManager animationManager = new AnimationManager();
		int a = 0;
		try {
			a = animationManager.getListSize()+1;
			animationManager.setCurrentAnimation(a);
			
		} catch (Throwable ex) {
			e = ex;
		}
		assertTrue(e instanceof ArrayIndexOutOfBoundsException);
		assertTrue(
				e.getMessage().equals(
				"Index out of exception : i supérieur à ArrayList.size() : i="+a));
	}
	
}
