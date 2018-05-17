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
	
	
	
	
	
	
	
	
	
}
