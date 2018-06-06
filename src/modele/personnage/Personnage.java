package modele.personnage;

import java.util.ArrayList;

import modele.Entity.Entity;
import modele.animation.Animation;
import modele.animation.AnimationManager;
import modele.collision.Collider;
import modele.coordonnee.Axe;
import modele.coordonnee.Coordonnee;
import vue.sprite.Sprite;
import vue.tileset.Tileset;

public abstract class Personnage extends Entity {

	private AnimationManager animationManager;
	protected Collider collider;
	protected Axe direction;
	Sprite spr;

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
				if(animationManager.getCurrentAnimation() != 0)
					animationManager.setCurrentAnimation(0);
				else 
					animationManager.enableNextFrame();
				break;
			case 1:
				if(animationManager.getCurrentAnimation() != 1)
					animationManager.setCurrentAnimation(1);
				else 
					animationManager.enableNextFrame();
				break;
			case -10: case -11: case -9:
				if(animationManager.getCurrentAnimation() != 2)
					animationManager.setCurrentAnimation(2);
				else 
					animationManager.enableNextFrame();
				break;			
			case -1:
				if(animationManager.getCurrentAnimation() != 3)
					animationManager.setCurrentAnimation(3);
				else 
					animationManager.enableNextFrame();
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

	public void setImage(String path, int scale, int id, Tileset tileset) {
		spr = new Sprite(tileset,scale,id);
	}

	public void setImage(Sprite newSprite) {
		spr.setView(newSprite.getView());
	}
	
	public void resetImage() {
		spr = new Sprite();
	}

	public Sprite getSprite() {
		return spr;
	}

	public void setSprite(Sprite spr) {
		this.spr = spr;
	}

	public ArrayList<Animation> getAnimations() {
		return animationManager.getAnimations();
	}
}
