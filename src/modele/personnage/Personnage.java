package modele.personnage;

import java.util.ArrayList;

import modele.Entity.Entity;
import modele.animation.Animation;
import modele.animation.AnimationManager;
import modele.collision.Collider;
import modele.coordonnee.Axe;
import modele.coordonnee.Coordonnee;
import vue.sprite.Sprite;

public abstract class Personnage extends Entity {

	private AnimationManager animationManager;
	protected Collider collider;
	protected Axe direction;

	public Personnage(String nom, int pv, Coordonnee position, int taille) {
		super.nom = nom;
		super.position = position;
		animationManager = new AnimationManager();
		collider = new Collider(position, taille, true);
	}

	public void updateAnimation() {
		if(direction != null) {
			int[] orientation = direction.directiontoArray();
			orientation[0] = orientation[0]*10 + orientation[1];
			switch (orientation[0]) {
			case 10: case 11: case 9:
				if(animationManager.getCurrentLigne() != 0)
					animationManager.setCurrentAnimation(0);
				else 
					animationManager.nextFrame();
				break;
			case 1:
				if(animationManager.getCurrentLigne() != 1)
					animationManager.setCurrentAnimation(1);
				else 
					animationManager.nextFrame();
				break;
			case -10: case -11: case -9:
				if(animationManager.getCurrentLigne() != 2)
					animationManager.setCurrentAnimation(2);
				else 
					animationManager.nextFrame();
				break;			
			case -1:
				if(animationManager.getCurrentLigne() != 3)
					animationManager.setCurrentAnimation(3);
				else 
					animationManager.nextFrame();
				break;
			default:
				break;
			}
		}
	}
	
	public Collider getCollider() {
		return collider;
	}

	public AnimationManager getAnimationManager() {
		return animationManager;
	}
	
	public String getNom() {
		return nom;
	}

	public Coordonnee getPosition() {
		return this.position;
	}

	public void setPosition(int x, int y) {
		this.position.setXandY(x, y);
	}

	public Sprite getSprite() {
		return animationManager.getCurrentAnimation();
	}

	public ArrayList<Animation> getAnimations() {
		return animationManager.getAnimations();
	}
}
