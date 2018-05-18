package modele.Personnage;

import java.util.ArrayList;

import modele.Coordonnee.*;
import modele.Objet.Objet;

public abstract class Personnage {

	private String nom;
	private int pv;
	private Coordonnee position;
	private int vitesse;
	private ArrayList<Objet> inventaire;

	public Personnage(String nom, int pv, Coordonnee position, int vitesse) {
		this.nom = nom;
		this.pv = pv;
		this.position = position;
		this.vitesse = vitesse;
		this.inventaire = new ArrayList<>(); 
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
		if (direction.isMovement()) {
			position.setX((position.getX()+direction.x()*vitesse));
			position.setY((position.getY()+direction.y()*vitesse));
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

}
