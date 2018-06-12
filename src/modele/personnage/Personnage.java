package modele.personnage;

import modele.Entity.Entity;
import modele.animation.Animation;
import modele.collision.Collider;
import modele.coordonnee.Axe;
import modele.coordonnee.Coordonnee;
import modele.personnage.joueur.Joueur;
import vue.sprite.Sprite;

public abstract class Personnage extends Entity {

	private Animation animation;
	protected Collider collider;
	protected Axe direction;
	private int pv;
	protected int vitesse;
	
	public Personnage(String nom, int pv, Coordonnee position, int taille,int vitesse, Animation a) {
		super.nom = nom;
		super.position = position;
		animation = a;
		collider = new Collider(position, false, taille, taille);
		this.pv=pv;
		this.vitesse=vitesse;
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

	public Sprite getSprite() {
		return animation.getSprite();
	}
	
	public int getPV() {
		return this.pv;
	}
	
	public boolean isAlive() {
		if(this.getPV() > 0) {
			return true;
		}
		return false;
	}
	
	public void attaquePersoVisible(Joueur joueur) {
		double distance = Math.sqrt(Math.pow((this.getPosition().getX()-joueur.getPosition().getX()), 2) + Math.pow((this.getPosition().getY()-joueur.getPosition().getY()), 2));
		if(this.isAlive() && distance < 4) {
			this.attaque(joueur);
		}
	}
	
	public abstract void attaque(Personnage p);

	public int getVitesse() {
		return vitesse;
	}

	public void setVitesse(int vitesse) {
		this.vitesse = vitesse;
	}
	
}
