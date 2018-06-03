package modele.animation;

import java.util.ArrayList;

import modele.coordonnee.Coordonnee;
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
	
	public void updateAnimationsPos(Coordonnee pos, int scale) {
		for (int i = 0; i < animations.size(); i++) {
			for (int j = 0; j < animations.get(i).getAllSprites().length; j++) {
				animations.get(i).getAllSprites()[j].getView().setX(pos.getX() * scale);
				animations.get(i).getAllSprites()[j].getView().setY(pos.getY() * scale);
			}
		}
	}
	
	public ArrayList<Animation> getAnimations() {
		return animations;
	}
}
