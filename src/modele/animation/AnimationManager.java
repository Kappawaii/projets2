package modele.animation;

import java.util.ArrayList;

import vue.sprite.Sprite;

public class AnimationManager {

	private ArrayList<Animation> animations;
	private int currentAnimation;
	
	public AnimationManager() {
		animations = new ArrayList<Animation>();
		currentAnimation = 0;
	}
	
	public void addAnimations(Animation animation) {
		 animations.add(animation);
	}
	
	public void setCurrentAnimation(int i) throws ArrayIndexOutOfBoundsException {
		if(i < 0 ) {
			throw new ArrayIndexOutOfBoundsException("Index out of exception : i inférieur à 0 : i="+i);
		}
		else if (i > animations.size()) {
			throw new ArrayIndexOutOfBoundsException("Index out of exception : i supérieur à ArrayList.size() : i="+i);
		}
		else {
			currentAnimation = i;
			animations.get(i).resetAnimation();
		}
	}
	
	public int getListSize() {
		return animations.size();
	}
	
	public Sprite nextFrame() {
		return animations.get(currentAnimation).next();
	}

	public ArrayList<Animation> getAnimations() {
		return animations;
	}
}
