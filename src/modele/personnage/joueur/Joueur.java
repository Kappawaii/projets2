
package modele.personnage.joueur;

import java.util.ArrayList;

import modele.animation.Animation;
import modele.coordonnee.Coordonnee;
import modele.objet.Objet;
import modele.personnage.Personnage;
import vue.tileset.Tileset;

public class Joueur extends Personnage{
	private ArrayList<Objet> inventaire;

	public Joueur (String nom, int pv, Coordonnee position, int vitesse, Tileset tileset, Animation a) {
		super(nom, pv, position, 16,vitesse, a);
		this.inventaire = new ArrayList<>(); 
	}



	public void gagneUnObjet(Objet unObjet) {
		this.inventaire.add(unObjet);
	}

	public void perdUnObjet(Objet unObjet) {
		this.inventaire.remove(unObjet);
	}
	
	public void attaque(Personnage p) {
		System.out.println("Je t'attaque "+p.getNom());
	}
}
