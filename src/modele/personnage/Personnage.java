package modele.personnage;

import modele.Entity.Entity;
import modele.animation.Animation;
import modele.collision.Collider;
import modele.coordonnee.Axe;
import modele.coordonnee.Coordonnee;
import vue.sprite.Sprite;

public abstract class Personnage extends Entity {

	private Animation animation;
	protected Collider collider;
	protected Axe direction;

	public Personnage(String nom, int pv, Coordonnee position, int taille, Animation a) {
		super.nom = nom;
		super.position = position;
		animation = a;
		collider = new Collider(position, true, taille, taille);
	}

	public void updateAnimation() {
		if(direction != null) {
			int[] orientation = direction.directiontoArray();
			orientation[0] = orientation[0]*10 + orientation[1];
			switch (orientation[0]) {
			case 10: case 11: case 9:
				if(animation.getCurrentLigne() != 0)
					animation.setCurrentAnimation(0);
				else 
					animation.nextFrame();
				break;
			case 1:
				if(animation.getCurrentLigne() != 1)
					animation.setCurrentAnimation(1);
				else 
					animation.nextFrame();
				break;
			case -10: case -11: case -9:
				if(animation.getCurrentLigne() != 2)
					animation.setCurrentAnimation(2);
				else 
					animation.nextFrame();
				break;			
			case -1:
				if(animation.getCurrentLigne() != 3)
					animation.setCurrentAnimation(3);
				else 
					animation.nextFrame();
				break;
			default:
				break;
			}
		}
	}
	
	public Collider getCollider() {
		return collider;
	}

	public Animation getAnimation() {
		return animation;
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
		return animation.getSprite();
	}
}
