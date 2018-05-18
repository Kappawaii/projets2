package modele.Personnage;

import java.util.ArrayList;

import modele.Coordonnee.Coordonnee;
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
	
	public void seDeplace(char direction) {
		switch(direction) {
		case('N') :
			this.position.plusUnY();;
		break;
		case('S') :
			this.position.moinsUnY();
		break;
		case('E'):
			this.position.plusUnX();
		break;
		case('O') :
			this.position.moinsUnX();
		break;
		default :
			throw new Error("Ce n'est pas une bonne direction !! : 'N,S,O,E'");
		
		}
		
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
	
	/*
	// return the character which is in front of this character
    public Character talkWith() {
        int nextX = 0;
        int nextY = 0;
        switch (direction) {
        case LEFT:
            nextX = x - 1;
            nextY = y;
            break;
        case RIGHT:
            nextX = x + 1;
            nextY = y;
            break;
        case UP:
            nextX = x;
            nextY = y - 1;
            break;
        case DOWN:
            nextX = x;
            nextY = y + 1;
            break;
        }
        */

	
}
