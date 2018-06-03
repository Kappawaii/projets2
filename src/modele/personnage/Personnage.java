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
			if (!nextPosCollider.detecterCollision(modele.getAllColliders())){
				position.setX(nextPosX);
				position.setY(nextPosY);
				collider.setPosition(position);
			}
			else {
				//System.out.println("collision détectée");
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

}
