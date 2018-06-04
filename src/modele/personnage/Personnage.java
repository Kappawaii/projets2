package modele.personnage;

import java.util.ArrayList;

import modele.Modele;
import modele.animation.Animation;
import modele.animation.AnimationManager;
import modele.collision.Collider;
import modele.coordonnee.Axe;
import modele.coordonnee.Coordonnee;
import modele.objet.Objet;
import vue.sprite.Sprite;
import vue.tileset.Tileset;

public abstract class Personnage {

	private String nom;
	private int pv;
	private Coordonnee position;
	private int vitesse;
	private ArrayList<Objet> inventaire;
	private AnimationManager animationManager;
	private Collider collider;
	protected Tileset tileset;
	private Axe direction;

	public Collider getCollider() {
		return collider;
	}

	public AnimationManager getAnimationManager() {
		return animationManager;
	}

	String url;
	Sprite spr;

	public Personnage(String nom, int pv, Coordonnee position, int vitesse, Tileset tileset, int taille) {
		this.nom = nom;
		this.pv = pv;
		this.position = position;
		this.vitesse = vitesse;
		this.inventaire = new ArrayList<>(); 
		this.tileset = tileset;
		animationManager = new AnimationManager();
		collider = new Collider(position, taille, true);
	}

	public void gagneUnObjet(Objet unObjet) {
		this.inventaire.add(unObjet);
	}

	public void perdUnObjet(Objet unObjet) {
		this.inventaire.remove(unObjet);
	}

	public void seDeplace(Axe direction, Modele modele) {
		int nextPosX = position.getX()+direction.x()*vitesse;
		int nextPosY = position.getY()+direction.y()*vitesse;
		if (direction.isMovement()) {
			Collider nextPosCollider = new Collider(new Coordonnee(nextPosX,nextPosY), collider.getTaille(), collider.isTrigger());
			ArrayList<Collider> collisions = nextPosCollider.detecterCollisions(modele.getAllColliders());
			boolean result = false;
			for (int i = 0; (i < collisions.size()); i++) {
				if(collisions.get(i).isTrigger())
					if(i+1 == collisions.size())
						result = true;
//				System.out.print("Collider:");
//				collisions.get(i).sysout();
			}
			if (result) {
				this.direction = direction;
//				System.out.println(direction);
//				System.out.println(this.direction);
//				System.out.println("Déplacement");
				position.setX(nextPosX);
				position.setY(nextPosY);
				collider.setPosition(position);
				updateAnimation();
			}
		}
		else 
			throw new Error("Bad direction parameter : '" + direction +"' Axe.isMovement should be true");
	}

	public String getNom() {
		return this.nom;
	}

	public int getVie() {
		return this.pv;
	}

	public int getVitesse() {
		return this.vitesse;
	}	

	public Coordonnee getPosition() {
		return this.position;
	}

	public void setPosition(int x, int y) {
		this.position.setXandY(x, y);
	}

	public void setImage(String path, int scale, int id) {
		spr = new Sprite(tileset,scale,id);
	}

	public void setImage(Sprite newSprite) {
		spr.setView(newSprite.getView());
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

	public void updateAnimation() {
		System.out.println("Nouvelle direction");
		System.out.println(direction);
		if(direction != null) {
			int[] orientation = direction.directiontoArray();
			orientation[0] = orientation[0]*10 + orientation[1];
			System.out.println(orientation[0]);
			switch (orientation[0]) {
			case 10: case 11: case 9:
				if(animationManager.getCurrentAnimation() != 0)
					animationManager.setCurrentAnimation(0);
				break;
			case 1:
				if(animationManager.getCurrentAnimation() != 1)
					animationManager.setCurrentAnimation(1);
				break;
			case -10: case -11: case -9:
				if(animationManager.getCurrentAnimation() != 2)
					animationManager.setCurrentAnimation(2);
				break;			
			case -1:
				if(animationManager.getCurrentAnimation() != 3)
					animationManager.setCurrentAnimation(3);
				break;
			default:
				break;
			}
		}
	}

}
