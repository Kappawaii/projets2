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
			currentAnimation = 0;
			animations.get(0).resetAnimation();
			animations.get(0).setLigneIndex(i);
			animations.get(0).increment();
	}
	
	public int getListSize() {
		return animations.size();
	}
	
	public void nextFrame() {
			animations.get(currentAnimation).increment();
	}
	
	public ArrayList<Animation> getAnimations() {
		return animations;
	}
	
	public Sprite getCurrentAnimation() {
		return animations.get(currentAnimation).getSprite();
	}

	public int getAnimIndex() {
		return currentAnimation;
	}

	public int getCurrentLigne() {
		return animations.get(0).getLigneIndex();
	}
}
