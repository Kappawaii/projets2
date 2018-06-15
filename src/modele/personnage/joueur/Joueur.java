
package modele.personnage.joueur;

import java.util.ArrayList;

import modele.Modele;
import modele.Objet;
import modele.animation.Animation;
import modele.coordonnee.Coordonnee;
import modele.personnage.Personnage;

public class Joueur extends Personnage{
	private ArrayList<Objet> inventaire;

	public Joueur (String nom, int pv, Coordonnee position, int vitesse, Animation a, Modele modele) {
		super(pv, position, 16, vitesse,a, modele);
		this.inventaire = new ArrayList<>(); 
	}



	public void gagneUnObjet(Objet unObjet) {
		this.inventaire.add(unObjet);
	}

	public void perdUnObjet(Objet unObjet) {
		this.inventaire.remove(unObjet);
	}



	@Override
	public void attaque(Personnage p) {
		
	}
	
	//TODO Polymorphism




}
