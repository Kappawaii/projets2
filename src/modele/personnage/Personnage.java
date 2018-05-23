package modele.personnage;

import java.util.ArrayList;

import modele.animation.Animation;
import modele.coordonnee.*;
import modele.objet.Objet;
import vue.sprite.Sprite;
import vue.tileset.Tileset;

public abstract class Personnage {

	private String nom;
	private int pv;
	private Coordonnee position;
	private int vitesse;
	private ArrayList<Objet> inventaire;
	protected Tileset tileset;
	private ArrayList<Animation> animations = new ArrayList<Animation>();
	String url;
	Sprite spr;
	
	public Personnage(String nom, int pv, Coordonnee position, int vitesse, Tileset tileset) {
		this.nom = nom;
		this.pv = pv;
		this.position = position;
		this.vitesse = vitesse;
		this.inventaire = new ArrayList<>(); 
		this.tileset = tileset;
	}

	public void gagneUnObjet(Objet unObjet) {
		this.inventaire.add(unObjet);
	}

	public void perdUnObjet(Objet unObjet) {
		this.inventaire.remove(unObjet);
	}

	public String parle(String phrase) {
		return phrase;
	}

	public void seDeplace(Axe direction) {
		int nextPosX = position.getX()+direction.x()*vitesse;
		int nextPosY = position.getY()+direction.y()*vitesse;
//		if (direction.isMovement()) {
//			if (nextPosY > 500 || nextPosY < 320 || nextPosX < 205 || nextPosX > 500 ) { // limite de la map
//				position.setY(position.getY());
//				position.setX(position.getX());
//			}
//			else {
				position.setX(nextPosX);
				position.setY(nextPosY);
//			}
//		}
//		else 
//			throw new Error("Bad direction parameter : '" + direction +"' Axe.isMovement should be true");

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
	
	public void setImage(String path, int scale) {
		spr = new Sprite(tileset, scale, scale, scale, scale);
	}
	
	public Sprite getSprite() {
		return spr;
	}

	public void setSprite(Sprite spr) {
		this.spr = spr;
	}

}
